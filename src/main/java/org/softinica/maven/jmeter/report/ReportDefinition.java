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
import java.util.LinkedList;
import java.util.List;

import org.softinica.maven.jmeter.report.writer.DocBookWriter;

public class ReportDefinition {

	private String title;
	private String description;
	private List<InputDefinition> inputDefinitions = new LinkedList<InputDefinition>();
	private String writerClass = DocBookWriter.class.getCanonicalName();
	
	public ReportDefinition() {
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<InputDefinition> getInputDefinitions() {
		return inputDefinitions;
	}
	
	public void setInputDefinitions(List<InputDefinition> inputDefinitions) {
		this.inputDefinitions = inputDefinitions;
	}
	
	public String getWriterClass() {
		return writerClass;
	}
	
	public void setWriterClass(String writerClass) {
		this.writerClass = writerClass;
	}
}
