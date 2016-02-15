package org.softinica.maven.jmeter.report.model;
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
import com.google.common.collect.ArrayTable;

public class Table extends AbstractReportable {

	private ArrayTable<String, String, String> table;

	public Table(Iterable<String> rowKeys, Iterable<String> columnKeys) {
		table = ArrayTable.create(rowKeys, columnKeys);
	}
	
	public void put(String rowKey, String columnKey, String value) {
		table.put(rowKey, columnKey, value);
	}
	
	public int getRowCount() {
		return table.rowKeySet().size();
	}
	
	public Iterable<String> rowKeys() {
		return table.rowKeySet();
	}
	
	public int getColumnCount() {
		return table.columnKeySet().size();
	}
	
	public Iterable<String> columnKeys() {
		return table.columnKeySet();
	}
	
	public String get(int i, int j) {
		return table.at(i, j);
	}
	
	public String get(String rowKey, String columnKey) {
		return table.get(rowKey, columnKey);
	}
}
