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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.softinica.maven.jmeter.report.PageDefinition;
import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Sample;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

public class ThroughputAnalyzer extends AbstractGraphAnalyzer {

	public ThroughputAnalyzer() {
	}
	
	@Override
	protected JFreeChart createChart(PageDefinition definition, Input input) {
		Map<String, Multiset<Long>> allSeries = new HashMap<String, Multiset<Long>>();
		for (Sample sample : input.getSamples()) {
			Multiset<Long> rps = allSeries.get(sample.getLabel());
			if (rps == null) {
				rps = TreeMultiset.create();
				allSeries.put(sample.getLabel(), rps);
			}
			rps.add(((sample.getTimestamp() + (long) sample.getValue()) / 1000) * 1000);
		}
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		for (String label : allSeries.keySet()) {
			Multiset<Long> rps = allSeries.get(label);
			TimeSeries series = new TimeSeries(label);
			for (Long key : rps) {
				series.addOrUpdate(new Second(new Date(key)), rps.count(key));
			}
			dataset.addSeries(series);
		}
		
		return ChartFactory.createTimeSeriesChart(definition.getTitle(), "Time", "Requests/second", dataset);
	}
}
