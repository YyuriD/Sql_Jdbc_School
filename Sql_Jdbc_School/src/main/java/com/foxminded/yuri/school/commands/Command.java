package com.foxminded.yuri.school.commands;

import java.util.List;

import com.foxminded.yuri.school.parameters.Parameter;
import com.foxminded.yuri.school.service.SchoolService;

public interface Command {

	String getName();

	String getDescription();

	List<Parameter> getParameters();

	String execute(List<Parameter> parameters, SchoolService schoolService);
}
