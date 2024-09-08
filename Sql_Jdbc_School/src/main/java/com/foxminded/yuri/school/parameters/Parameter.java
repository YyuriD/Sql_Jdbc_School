package com.foxminded.yuri.school.parameters;

public class Parameter {

	String value;
	String prompt;
	String name;

	public Parameter(String name, String prompt) {
		this.prompt = prompt;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPrompt() {
		return prompt;
	}


}
