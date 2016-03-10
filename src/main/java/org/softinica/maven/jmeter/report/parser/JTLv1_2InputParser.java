package org.softinica.maven.jmeter.report.parser;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.codehaus.plexus.util.IOUtil;
import org.softinica.maven.jmeter.report.InputDefinition;
import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Sample;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class JTLv1_2InputParser implements IInputParser {

	private static final SAXParserFactory FACTORY = SAXParserFactory.newInstance();

	public JTLv1_2InputParser() {
	}

	public Input parseInput(InputDefinition definition) {
		InputStream input = null;
		try {
			input = new FileInputStream(definition.getInputFile());
			SAXParser parser = FACTORY.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			JTLHandler handler = new JTLHandler(definition.getScale());
			reader.setContentHandler(handler);
			reader.parse(new InputSource(input));
			return handler.getInput();
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtil.close(input);
		}
	}
	
	private static class JTLHandler extends DefaultHandler {

		private Input input = new Input();
		private int scale;
		
		public JTLHandler(int scale) {
			this.scale = scale;
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			if (qName.toLowerCase().contains("sample")) {
				input.getSamples().add(parseSample(attributes));
			}
		}
		
		public Input getInput() {
			return input;
		}
		
		protected Sample parseSample(Attributes attributes) {
			Sample sample = new Sample();
			sample.setValue(Double.valueOf(attributes.getValue("t")) * scale);
			sample.setActiveThreads(Long.valueOf(attributes.getValue("na")));
			sample.setActiveThreadsInGroup(Long.valueOf(attributes.getValue("ng")));
			sample.setByteCount(Long.valueOf(attributes.getValue("by")));
			sample.setDataType(attributes.getValue("dt"));
			sample.setLabel(attributes.getValue("lb"));
			sample.setLatency(Long.valueOf(attributes.getValue("lt")));
			sample.setResultCode(attributes.getValue("rc"));
			sample.setSuccess(Boolean.parseBoolean(attributes.getValue("s")));
			sample.setThreadName(attributes.getValue("tn"));
			sample.setTimestamp(Long.valueOf(attributes.getValue("ts")));
			return sample;
		}
	}
}
