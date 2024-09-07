package com.foxminded.yuri.school.commands;

import java.util.List;

import com.foxminded.yuri.school.model.Student;
import com.foxminded.yuri.school.parameters.Parameter;
import com.foxminded.yuri.school.service.SchoolService;

public class CommandAddStudent implements Command {

	private String name;
	private List<Parameter> parameters;
	private String description = "Add student to data base";

	public CommandAddStudent(String name, List<Parameter> parameters) {
		this.name = name;
		this.parameters = parameters;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public List<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public String execute(List<Parameter> parameters, SchoolService schoolService) {
		try {
			Student student = new Student(Integer.parseInt(parameters.get(0).getValue()), parameters.get(1).getValue(),
					parameters.get(2).getValue(), Integer.parseInt(parameters.get(3).getValue()));
			schoolService.addStudent(student);
			return "New student was added.";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
