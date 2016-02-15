package org.softinica.maven.jmeter.report;

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
import java.io.File;

public class InputDefinition {

	private File inputFile;
	private PageDefinition[] pageDefinitions;
	private String parserClass;
	private int scale = 1;
	
	public InputDefinition() {
	}
	
	public File getInputFile() {
		return inputFile;
	}
	
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	
	public PageDefinition[] getPageDefinitions() {
		return pageDefinitions;
	}
	public void setPageDefinitions(PageDefinition[] pageDefinitions) {
		this.pageDefinitions = pageDefinitions;
	}
	
	public String getParserClass() {
		return parserClass;
	}
	
	public void setParserClass(String parserClass) {
		this.parserClass = parserClass;
	}
	
	public int getScale() {
		return scale;
	}
	
	public void setScale(int scale) {
		this.scale = scale;
	}
}
