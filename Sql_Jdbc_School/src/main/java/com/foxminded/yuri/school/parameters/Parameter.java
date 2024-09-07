package com.foxminded.yuri.school.parameters;

public interface Parameter {

	String getValue();

	void setValue(String value) throws IllegalArgumentException;

	String getPrompt();

	boolean isValueFilled();

}
