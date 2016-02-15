package org.softinica.maven.jmeter.report;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.softinica.maven.jmeter.report.analyser.IInputAnalyzer;
import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Report;
import org.softinica.maven.jmeter.report.parser.IInputParser;
import org.softinica.maven.jmeter.report.writer.IWriter;
import org.softinica.maven.jmeter.report.writer.DocBookWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Mojo(name = "report", defaultPhase = LifecyclePhase.VERIFY)
/**
 * jmeter-report-maven-plugin can generate reports based on jmeter datasources.
 * This plugin supports two formats:
 * JTL - simple xml format.
 * CSV - comma separated value.
 * 
 * The plugin can generate PDF reports.
 *
 */
public class JMeterReportMojo extends AbstractMojo {
	/**
	 * Location of the output file.
	 */
	@Parameter(property = "outputFile", required = true )
	private File outputFile;

	/**
	 * Report definition.
	 */
	@Parameter(required = true)
	private ReportDefinition reportDefinition;

	public void execute() throws MojoExecutionException {
		getLog().info(outputFile.getAbsolutePath());
		IWriter writer = createWriter(outputFile);
		Report report = new Report();
		report.setTitle(reportDefinition.getTitle());
		report.setDescription(reportDefinition.getDescription());
		try {
			writer.writeHeader(report);
			for (InputDefinition definition : reportDefinition.getInputDefinitions()) {
				IInputParser parser = createParser(definition);
				Input input = parser.parseInput(definition);
				for (PageDefinition pageDefinition : definition.getPageDefinitions()) {
					writer.writeReportable(createAnalyser(pageDefinition).analyse(pageDefinition, input));
				}
			}
			writer.writeFooter(report);
		} finally {
			Utils.close(writer);
		}
	}

	private IInputAnalyzer<?> createAnalyser(PageDefinition definition) {
		return Utils.create(definition.getAnalyzerClass());
	}

	private IInputParser createParser(InputDefinition definition) {
		return Utils.create(definition.getParserClass());
	}

	private IWriter createWriter(File outputFile) {
		try {
			outputFile.getParentFile().mkdirs();
			OutputStream outputStream = new FileOutputStream(outputFile);
			return new DocBookWriter(outputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
