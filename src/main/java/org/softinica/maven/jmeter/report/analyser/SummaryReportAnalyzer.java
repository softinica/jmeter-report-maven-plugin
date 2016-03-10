package org.softinica.maven.jmeter.report.analyser;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.softinica.maven.jmeter.report.model.Sample;
import org.softinica.maven.jmeter.report.model.Table;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class SummaryReportAnalyzer extends AbstractGroupedTableAnalyzer {
	
	private static final String LABEL = "Label";
	private static final String SAMPLES = "#Samples";
	private static final String AVERAGE = "Average";
	private static final String MIN = "Min";
	private static final String MAX = "Max";
	private static final String STD_DEV = "Std.Dev";
	private static final String ERROR = "Error %";
	private static final String THROUGHPUT = "Throughput";
	private static final String KB_PER_SEC = "Bytes/sec";
	private static final String AVERAGE_BYTES = "Avg. Bytes";
	private static final List<String> COLUMNS = Lists.newArrayList(LABEL, SAMPLES, AVERAGE, 
			MIN, MAX, STD_DEV, ERROR, THROUGHPUT, KB_PER_SEC, AVERAGE_BYTES);
	private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("#.##");

	public SummaryReportAnalyzer() {
		super(Sample.LABEL, COLUMNS);
	}

	
	@Override
	protected void fillTable(Table table, Multimap<Object, Sample> grouped) {
		StandardDeviation deviation = new StandardDeviation();
		for (Object key : grouped.keySet()) {
			double total = 0D;
			int sampleCount = grouped.get(key).size();
			double[] values = new double[sampleCount];
			double min = Double.MAX_VALUE;
			double max = Double.MIN_VALUE;
			long minTimestamp = Long.MAX_VALUE;
			long maxTimestamp = Long.MIN_VALUE;
			long totalBytes = 0;
			int errorCount = 0;
			int i = 0;
			for (Sample sample : grouped.get(key)) {
				total += sample.getValue();
				values[i] = sample.getValue();
				i++;
				if (min > sample.getValue()) {
					min = sample.getValue();
				}
				if (max < sample.getValue()) {
					max = sample.getValue();
				}
				if (!sample.isSuccess()) {
					errorCount++;
				}
				if (minTimestamp > sample.getTimestamp()) {
					minTimestamp = sample.getTimestamp();
				}
				if (maxTimestamp < sample.getTimestamp()) {
					maxTimestamp = sample.getTimestamp();
				}
				totalBytes += sample.getByteCount();
			}
			table.put(key, LABEL, key.toString());
			table.put(key, SAMPLES, String.valueOf(sampleCount));
			table.put(key, AVERAGE, NUMBER_FORMAT.format(total / sampleCount));
			table.put(key, MIN, NUMBER_FORMAT.format(min));
			table.put(key, MAX, NUMBER_FORMAT.format(max));
			table.put(key, STD_DEV, NUMBER_FORMAT.format(deviation.evaluate(values)));
			table.put(key, ERROR, NUMBER_FORMAT.format(100.0D * errorCount / sampleCount));
			table.put(key, THROUGHPUT, NUMBER_FORMAT.format(sampleCount / total * 1000));
			table.put(key, KB_PER_SEC, NUMBER_FORMAT.format(totalBytes / total * 1000));
			table.put(key, AVERAGE_BYTES, NUMBER_FORMAT.format(totalBytes / sampleCount));
		}
	}
}
