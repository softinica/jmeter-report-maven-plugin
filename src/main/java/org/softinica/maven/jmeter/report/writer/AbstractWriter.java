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


import org.softinica.maven.jmeter.report.model.AbstractReportable;
import org.softinica.maven.jmeter.report.model.Graph;
import org.softinica.maven.jmeter.report.model.Report;
import org.softinica.maven.jmeter.report.model.Table;

public abstract class AbstractWriter implements IWriter {

	private static final XMLOutputFactory FACTORY = XMLOutputFactory.newInstance();
	protected XMLStreamWriter xmlWriter = null;
	protected String rootElement;
	
	public AbstractWriter(OutputStream outputStream, String rootElement) {
		this.rootElement = rootElement;
		try {
			xmlWriter = FACTORY.createXMLStreamWriter(new OutputStreamWriter(outputStream));
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void writeReportable(AbstractReportable reportable) {
		if (reportable instanceof Table) {
			writeTable((Table) reportable);
		} else if (reportable instanceof Graph) {
			writeGraph((Graph) reportable);
		}
	}
	
	public void writeFooter(Report report) {
		writeEndElement();
		writeEndDocument();
	}
	
	public void close() throws IOException {
		try {
			xmlWriter.flush();
			xmlWriter.close();
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected abstract void writeTable(Table table);
		
	protected abstract void writeGraph(Graph graph);
	
	protected void writeDTD() throws XMLStreamException {
	}
	
	protected void writeRootElement() throws XMLStreamException {
		writeStartElement(rootElement);
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
			writeDTD();
			writeRootElement();
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
