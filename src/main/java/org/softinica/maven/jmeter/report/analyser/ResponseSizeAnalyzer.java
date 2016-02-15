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
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.softinica.maven.jmeter.report.PageDefinition;
import org.softinica.maven.jmeter.report.model.Input;
import org.softinica.maven.jmeter.report.model.Sample;

public class ResponseSizeAnalyzer extends AbstractGraphAnalyzer {

	public ResponseSizeAnalyzer() {
	}
	
	@Override
	protected JFreeChart createChart(PageDefinition definition, Input input) {
		Map<String, Map<Long, Long>> allSeries = new HashMap<String, Map<Long, Long>>();
		for (Sample sample : input.getSamples()) {
			Map<Long, Long> duration = allSeries.get(sample.getLabel());
			if (duration == null) {
				duration = new HashMap<Long, Long>();
				allSeries.put(sample.getLabel(), duration);
			}
			duration.put((sample.getTimestamp() + (long) sample.getValue()), (long) sample.getByteCount());
		}
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		for (String label : allSeries.keySet()) {
			Map<Long, Long> duration = allSeries.get(label);
			TimeSeries series = new TimeSeries(label);
			for (Long key : duration.keySet()) {
				series.addOrUpdate(new Millisecond(new Date(key)), duration.get(key));
			}
			dataset.addSeries(series);
		}
		
		return ChartFactory.createTimeSeriesChart(definition.getTitle(), "Time", "Size(bytes)", dataset);
	}
}
