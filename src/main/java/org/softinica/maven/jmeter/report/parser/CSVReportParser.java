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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.softinica.maven.jmeter.report.InputDefinition;
import org.softinica.maven.jmeter.report.Utils;
import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Sample;

public class CSVReportParser implements IInputParser {

	public CSVReportParser() {
	}

	@Override
	public Input parseInput(InputDefinition definition) {
		CSVParser parser = null;
		Input input = new Input();
		try {
			Reader reader = new InputStreamReader(new FileInputStream(definition.getInputFile()));
			parser = new CSVParser(reader, CSVFormat.DEFAULT);
			Iterator<CSVRecord> it = parser.iterator();
			while (it.hasNext()) {
				Sample sample = new Sample();
				CSVRecord record = it.next();
				sample.setTimestamp(Long.valueOf(record.get(0)));
				sample.setLabel(record.get(2));
				sample.setValue(Double.valueOf(record.get(4)) * definition.getScale());
				sample.setSuccess(Boolean.parseBoolean(record.get(7)));
				input.getSamples().add(sample);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Utils.close(parser);
		}
		return input;
	}
}
