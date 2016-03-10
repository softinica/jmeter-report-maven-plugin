package org.softinica.maven.jmeter.report.analyser;
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
import org.softinica.maven.jmeter.report.PageDefinition;
import org.softinica.maven.jmeter.report.model.Graph;
import org.softinica.maven.jmeter.report.model.Input;

public class ImageAnalyzer implements IInputAnalyzer<Graph> {

	public ImageAnalyzer() {
	}

	@Override
	public Graph analyse(PageDefinition definition, Input input) {
		Graph graph = new Graph();
		graph.setTitle(definition.getTitle());
		graph.setDescription(definition.getDescription());
		graph.setExternalFile(definition.getOutputFile().getAbsolutePath());
		return graph;
	}
}
