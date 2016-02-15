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
public class Sample {

	private String label;
	private double value;
	private long timestamp;
	private long latency;
	private boolean success;
	private String rescultCode;
	private String threadName;
	private String dataType;
	private long byteCount;
	private long activeThreadsInGroup;
	private long activeThreads;
	
	public Sample() {
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public long getActiveThreads() {
		return activeThreads;
	}
	
	public void setActiveThreads(long activeThreads) {
		this.activeThreads = activeThreads;
	}
	
	public long getActiveThreadsInGroup() {
		return activeThreadsInGroup;
	}
	
	public void setActiveThreadsInGroup(long activeThreadsInGroup) {
		this.activeThreadsInGroup = activeThreadsInGroup;
	}
	
	public long getByteCount() {
		return byteCount;
	}
	
	public void setByteCount(long byteCount) {
		this.byteCount = byteCount;
	}
	
	public String getDataType() {
		return dataType;
	}
	
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public long getLatency() {
		return latency;
	}
	
	public void setLatency(long latency) {
		this.latency = latency;
	}
	
	public String getRescultCode() {
		return rescultCode;
	}
	
	public void setRescultCode(String rescultCode) {
		this.rescultCode = rescultCode;
	}
	
	public String getThreadName() {
		return threadName;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
	public boolean isSuccess() {
		return success;
	}
}
