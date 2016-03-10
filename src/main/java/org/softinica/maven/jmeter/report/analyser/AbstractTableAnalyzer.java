package org.softinica.maven.jmeter.report.analyser;

import org.softinica.maven.jmeter.report.PageDefinition;
import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Table;

public abstract class AbstractTableAnalyzer implements IInputAnalyzer<Table> {

	public AbstractTableAnalyzer() {
	}
	
	@Override
	public Table analyse(PageDefinition definition, Input input) {
		Table table = null;
		if (input.getSamples().size() > 0) {
			table = createTable(input);
			table.setTitle(definition.getTitle());
			table.setDescription(definition.getDescription());
		}
		return table;
	}

	protected abstract Table createTable(Input input);
}
