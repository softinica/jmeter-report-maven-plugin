package org.softinica.maven.jmeter.report.analyser;

import java.io.IOException;
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
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.softinica.maven.jmeter.report.PageDefinition;
import org.softinica.maven.jmeter.report.model.Graph;
import org.softinica.maven.jmeter.report.model.Input;

public abstract class AbstractGraphAnalyzer implements IInputAnalyzer<Graph> {

	public AbstractGraphAnalyzer() {
	}
	
	@Override
	public Graph analyse(PageDefinition definition, Input input) {
		JFreeChart chart = createChart(definition, input);
		saveJpegChart(definition, chart);
		Graph graph = new Graph();
		graph.setTitle(definition.getTitle());
		graph.setDescription(definition.getDescription());
		graph.setExternalFile(definition.getOutputFile().getAbsolutePath());
		return graph;
	}
	
	protected abstract JFreeChart createChart(PageDefinition definition, Input input);
	
	protected void saveJpegChart(PageDefinition definition, JFreeChart chart) {
		try {
			ChartUtilities.saveChartAsJPEG(definition.getOutputFile(), chart, definition.getWidth(), definition.getHeight());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
