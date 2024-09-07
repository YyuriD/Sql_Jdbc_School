package com.foxminded.yuri.school.command;

import java.util.List;

import com.foxminded.yuri.school.parameter.Parameter;

public interface Command {

	String getName();

	List<Parameter> getParameters();

	void execute(List<Parameter> parameters);
}
