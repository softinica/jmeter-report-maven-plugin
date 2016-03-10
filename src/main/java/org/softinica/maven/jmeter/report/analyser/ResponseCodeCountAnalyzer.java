package org.softinica.maven.jmeter.report.analyser;

import java.util.List;

import org.softinica.maven.jmeter.report.model.Sample;
import org.softinica.maven.jmeter.report.model.Table;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class ResponseCodeCountAnalyzer extends AbstractGroupedTableAnalyzer {

	private static final String CODE = "CODE";
	private static final String COUNT = "COUNT";
	private static final List<String> COLUMNS = Lists.newArrayList(CODE, COUNT);
	
	public ResponseCodeCountAnalyzer() {
		super(Sample.RESULT_CODE, COLUMNS);
	}
	
	@Override
	protected void fillTable(Table table, Multimap<Object, Sample> grouped) {
		for (Object key : grouped.keySet()) {
			table.put(key, CODE, key.toString());
			table.put(key, COUNT, String.valueOf(grouped.get(key).size()));
		}
	}
}
