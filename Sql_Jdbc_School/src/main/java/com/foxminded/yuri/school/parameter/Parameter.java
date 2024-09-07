package com.foxminded.yuri.school.parameter;

public class Parameter {

	private String name;
	private String prompt;
	private String value;

	public Parameter(String name, String prompt) {
		this.name = name;
		this.prompt = prompt;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) throws IllegalArgumentException {
		// TODO check is value valid
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getPrompt() {
		return prompt;
	}

	public boolean isValueFilled() {
		// TODO
		return false;
	}

}
