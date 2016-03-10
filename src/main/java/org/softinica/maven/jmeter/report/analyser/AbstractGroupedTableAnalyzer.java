package org.softinica.maven.jmeter.report.analyser;

import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Sample;
import org.softinica.maven.jmeter.report.model.Table;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public abstract class AbstractGroupedTableAnalyzer extends AbstractTableAnalyzer {

	private static final String DEFAULT_NAME = "Unknown";
	
	private String groupField;
	private List<String> columns;
	private String defaultName = DEFAULT_NAME;

	public AbstractGroupedTableAnalyzer(String groupField, List<String> columns, String defaultName) {
		this.groupField = groupField;
		this.columns = columns;
		this.defaultName = defaultName;
	}
	
	public AbstractGroupedTableAnalyzer(String groupField, List<String> columns) {
		this(groupField, columns, DEFAULT_NAME);
	}
	
	@Override
	protected Table createTable(Input input) {
		Multimap<Object, Sample> grouped = group(input);
		Table table = new Table(grouped.keySet(), columns);
		fillTable(table, grouped);
		return table;
	}
	
	protected abstract void fillTable(Table table, Multimap<Object, Sample> grouped);
	
	protected Multimap<Object, Sample> group(Input input) {
		Multimap<Object, Sample> grouped = HashMultimap.create();
		for (Sample sample : input.getSamples()) {
			Object key = sample.get(groupField);
			if (key instanceof String) {
				String stringKey = (String) key;
				if (StringUtils.isBlank(stringKey)) {
					key = defaultName;
				}
			}
			grouped.put(key, sample);
		}
		return grouped;
	}
}
