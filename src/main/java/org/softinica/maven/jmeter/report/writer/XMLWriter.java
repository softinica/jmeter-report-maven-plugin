package org.softinica.maven.jmeter.report.writer;
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
import java.io.OutputStream;


import org.softinica.maven.jmeter.report.model.Graph;
import org.softinica.maven.jmeter.report.model.Report;
import org.softinica.maven.jmeter.report.model.Table;

public class XMLWriter extends AbstractWriter {

	public XMLWriter(OutputStream outputStream) {
		super(outputStream, "report");
	}

	@Override
	public void writeHeader(Report report) {
		writeStartDocument();
		writeSimpleNode("title", report.getTitle());
		writeSimpleNode("description", report.getDescription());
	}
	
	@Override
	protected void writeGraph(Graph graph) {
		writeStartElement("graph");
		writeSimpleNode("title", graph.getTitle());
		writeSimpleNode("description", graph.getDescription());
		writeSimpleNode("external-file", graph.getExternalFile());
		writeEndElement();
	}
	
	@Override
	protected void writeTable(Table table) {
		writeStartElement("table");
		writeSimpleNode("title", table.getTitle());
		writeSimpleNode("description", table.getDescription());
		writeSimpleNode("column-count", String.valueOf(table.getColumnCount()));
		writeStartElement("thead");
		writeStartElement("tr");
		for (String columnKey : table.columnKeys()) {
			writeSimpleNode("th", columnKey);
		}
		writeEndElement();
		writeEndElement();
		writeStartElement("tbody");
		for (Object rowKey : table.rowKeys()) {
			writeStartElement("tr");
			for (String columnKey : table.columnKeys()) {
				writeSimpleNode("td", table.get(rowKey, columnKey));
			}
			writeEndElement();
		}
		writeEndElement();
		writeEndElement();
	}
}
