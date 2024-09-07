package com.foxminded.yuri.school.parameters;

public class ParamStudentId implements Parameter {

	String prompt;
	String value;

	public ParamStudentId(String prompt) {
		this.prompt = prompt;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public void setValue(String value) throws IllegalArgumentException {
		// TODO validate value
		this.value = value;
	}

	@Override
	public String getPrompt() {
		return this.prompt;
	}

	@Override
	public boolean isValueFilled() {
		return this.value != null ? true : false;
	}


}
