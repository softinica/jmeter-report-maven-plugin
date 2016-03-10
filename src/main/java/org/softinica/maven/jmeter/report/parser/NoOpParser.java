package org.softinica.maven.jmeter.report.parser;

import org.softinica.maven.jmeter.report.InputDefinition;
import org.softinica.maven.jmeter.report.model.Input;

public class NoOpParser implements IInputParser {

	public NoOpParser() {
	}
	
	@Override
	public Input parseInput(InputDefinition definition) {
		return new Input();
	}
}
