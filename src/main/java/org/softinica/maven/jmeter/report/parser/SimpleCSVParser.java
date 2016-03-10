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
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.softinica.maven.jmeter.report.InputDefinition;
import org.softinica.maven.jmeter.report.Utils;
import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Sample;

public class SimpleCSVParser implements IInputParser {

	public SimpleCSVParser() {
	}

	@Override
	public Input parseInput(InputDefinition definition) {
		CSVParser parser = null;
		List<String> headers = new LinkedList<String>();
		Input input = new Input();
		try {
			Reader reader = new InputStreamReader(new FileInputStream(definition.getInputFile()));
			parser = new CSVParser(reader, CSVFormat.DEFAULT);
			Iterator<CSVRecord> it = parser.iterator();
			if (it.hasNext()) {
				CSVRecord header = it.next();
				for (String value : header) {
					headers.add(value);
				}
				while (it.hasNext()) {
					Sample sample = new Sample();
					CSVRecord record = it.next();
					for (int i = 0; i < record.size(); i++) {
						sample.put(headers.get(i), record.get(i));
					}
					input.getSamples().add(sample);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Utils.close(parser);
		}
		return input;
	}
	
}
