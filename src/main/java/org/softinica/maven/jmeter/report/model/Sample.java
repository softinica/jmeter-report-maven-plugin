package org.softinica.maven.jmeter.report.model;

import java.util.HashMap;
import java.util.Map;

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
public class Sample {
	
	public static final String LABEL = "jmx.label";
	public static final String VALUE = "jmx.value";
	public static final String TIMESTAMP = "jmx.timestamp";
	public static final String LATENCY = "jmx.latency";
	public static final String SUCCESS = "jmx.success";
	public static final String RESULT_CODE = "jmx.resultCode";
	public static final String THREAD_NAME = "jmx.threadName";
	public static final String DATA_TYPE = "jmx.dataType";
	public static final String BYTE_COUNT = "jmx.byteCount";
	public static final String ACTIVE_THREADS_IN_GROUP = "jmx.activeThreadsInGroup";
	public static final String ACTIVE_THREADS = "jmx.activeThreads";
	
	private Map<String, Object> fields = new HashMap<String, Object>();

	public Sample() {
	}
	
	public Object get(String key) {
		return fields.get(key);
	}
	
	public Iterable<String> keys() {
		return fields.keySet();
	}
	
	public String getString(String key) {
		return (String) fields.get(key);
	}
	
	public long getLong(String key) {
		return (Long) fields.get(key);
	}
	
	public int getInt(String key) {
		return (Integer) fields.get(key);
	}
	
	public double getDouble(String key) {
		return (Double) fields.get(key);
	}
	
	public boolean getBoolean(String key) {
		return (Boolean) fields.get(key);
	}
	
	public String getLabel() {
		return getString(LABEL);
	}
	
	public void put(String key, Object value) {
		fields.put(key, value);
	}
	
	public void setLabel(String label) {
		put(LABEL, label);
	}
	
	public long getTimestamp() {
		return getLong(TIMESTAMP);
	}
	
	public void setTimestamp(long timestamp) {
		put(TIMESTAMP, timestamp);
	}
	
	public double getValue() {
		return getDouble(VALUE);
	}
	
	public void setValue(double value) {
		put(VALUE, value);
	}
	
	public long getActiveThreads() {
		return getLong(ACTIVE_THREADS);
	}
	
	public void setActiveThreads(long activeThreads) {
		put(ACTIVE_THREADS, activeThreads);
	}
	
	public long getActiveThreadsInGroup() {
		return getLong(ACTIVE_THREADS_IN_GROUP);
	}
	
	public void setActiveThreadsInGroup(long activeThreadsInGroup) {
		put(ACTIVE_THREADS_IN_GROUP, activeThreadsInGroup);
	}
	
	public long getByteCount() {
		return getLong(BYTE_COUNT);
	}
	
	public void setByteCount(long byteCount) {
		put(BYTE_COUNT, byteCount);
	}
	
	public String getDataType() {
		return getString(DATA_TYPE);
	}
	
	
	public void setDataType(String dataType) {
		put(DATA_TYPE, dataType);
	}
	
	public long getLatency() {
		return getLong(LATENCY);
	}
	
	public void setLatency(long latency) {
		put(LATENCY, latency);
	}
	
	public String getResultCode() {
		return getString(RESULT_CODE);
	}
	
	public void setResultCode(String resultCode) {
		put(RESULT_CODE, resultCode);
	}
	
	public String getThreadName() {
		return getString(THREAD_NAME);
	}
	
	public void setSuccess(boolean success) {
		put(SUCCESS, success);
	}
	
	public void setThreadName(String threadName) {
		put(THREAD_NAME, threadName);
	}
	
	public boolean isSuccess() {
		return getBoolean(SUCCESS);
	}
}
