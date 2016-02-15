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
import org.softinica.maven.jmeter.report.model.AbstractReportable;
import org.softinica.maven.jmeter.report.model.Graph;
import org.softinica.maven.jmeter.report.model.Table;

public abstract class AbstractWriter implements IWriter {

	public AbstractWriter() {
	}
	
	public void writeReportable(AbstractReportable reportable) {
		if (reportable instanceof Table) {
			writeTable((Table) reportable);
		} else if (reportable instanceof Graph) {
			writeGraph((Graph) reportable);
		}
	}
	
	protected abstract void writeTable(Table table);
		
	protected abstract void writeGraph(Graph graph);
}
