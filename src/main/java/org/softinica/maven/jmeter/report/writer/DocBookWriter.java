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
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.softinica.maven.jmeter.report.model.Graph;
import org.softinica.maven.jmeter.report.model.Report;
import org.softinica.maven.jmeter.report.model.Table;

public class DocBookWriter extends AbstractWriter {

	private static final XMLOutputFactory FACTORY = XMLOutputFactory.newInstance();
	private XMLStreamWriter xmlWriter = null;

	public DocBookWriter(OutputStream outputStream) {
		try {
			xmlWriter = FACTORY.createXMLStreamWriter(new OutputStreamWriter(outputStream));
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}

	public void writeFooter(Report report) {
//		writeEndElement();
		writeEndElement();
		writeEndDocument();
	}

	public void writeHeader(Report report) {
		writeStartDocument();
		writeStartElement("articleinfo");
		writeSimpleNode("title", report.getTitle());
//		writeStartElement("abstract");
//		writeSimpleNode("para", report.getDescription());
//		writeEndElement();
		writeEndElement();
//		writeStartElement("chapter");
	}

	@Override
	protected void writeGraph(Graph graph) {
		writeStartElement("figure");
		writeSimpleNode("title", graph.getTitle());
		writeStartElement("graphic");
		writeAttribute("fileref", graph.getExternalFile());
		writeEndElement();
		writeEndElement();
		writeSimpleNode("para", graph.getDescription());
	}

	@Override
	protected void writeTable(Table table) {
		writeStartElement("table");
		writeAttribute("frame", "all");
		writeSimpleNode("title", table.getTitle());
		writeStartElement("tgroup");
		writeAttribute("cols", String.valueOf(table.getColumnCount()));
		writeAttribute("aligh", "left");
		writeAttribute("colsep", "1");
		writeAttribute("rowsep", "1");
		writeStartElement("thead");
		writeStartElement("row");
		for (String columnKey : table.columnKeys()) {
			writeSimpleNode("entry", columnKey);
		}
		writeEndElement();
		writeEndElement();
		
		writeStartElement("tbody");
		for (String rowKey : table.rowKeys()) {
			writeStartElement("row");
			for (String columnKey : table.columnKeys()) {
				writeSimpleNode("entry", table.get(rowKey, columnKey));
			}
			writeEndElement();
		}
		writeEndElement();
		writeEndElement();
		writeEndElement();
		writeSimpleNode("para", table.getDescription());
	}

	public void close() throws IOException {
		try {
			xmlWriter.flush();
			xmlWriter.close();
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}

	protected void writeSimpleNode(String name, String value) {
		try {
			xmlWriter.writeStartElement(name);
			xmlWriter.writeCharacters(value);
			xmlWriter.writeEndElement();
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}

	protected void writeStartElement(String name) {
		try {
			xmlWriter.writeStartElement(name);
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}

	protected void writeEndElement() {
		try {
			xmlWriter.writeEndElement();
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void writeAttribute(String name, String value) {
		try {
			xmlWriter.writeAttribute(name, value);
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}

	protected void writeStartDocument() {
		try {
			xmlWriter.writeStartDocument();
			xmlWriter.writeDTD("<!DOCTYPE article[<!ENTITY root \"http://www.neilsen.com/watch-api\"> ]>");
			xmlWriter.writeStartElement("article");
			xmlWriter.writeAttribute("version", "5.0");
			xmlWriter.writeAttribute("xmlns", "http://docbook.org/ns/docbook");
			xmlWriter.writeAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
			xmlWriter.writeAttribute("xmlns:xi", "http://www.w3.org/2001/XInclude");
			xmlWriter.writeAttribute("xmlns:db", "http://docbook.org/ns/docbook");
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}

	protected void writeEndDocument() {
		try {
			xmlWriter.writeEndDocument();
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}
}
