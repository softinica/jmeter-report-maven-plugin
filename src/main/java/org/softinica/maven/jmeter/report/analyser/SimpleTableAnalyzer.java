package org.softinica.maven.jmeter.report.analyser;

import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Sample;
import org.softinica.maven.jmeter.report.model.Table;

public class SimpleTableAnalyzer extends AbstractTableAnalyzer {

	public SimpleTableAnalyzer() {
	}
	
	@Override
	protected Table createTable(Input input) {
		Table table = new Table(input.getSamples(), input.getSamples().get(0).keys());
		for (Sample sample : input.getSamples()) {
			for (String column : sample.keys()) {
				table.put(sample, column, sample.getString(column));
			}
		}
		return table;
	}
}
