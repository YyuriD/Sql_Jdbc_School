package com.foxminded.yuri.school.commands;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.yuri.school.model.Student;
import com.foxminded.yuri.school.parameters.Parameter;
import com.foxminded.yuri.school.service.SchoolService;

public class CommandAddStudent implements Command {

	private String name;
	private List<Parameter> parameters;
	private String description = "Add a new student to data base";

	public CommandAddStudent() {
		this.name = "ADD_STUDENT";
		parameters = new ArrayList<>();
		parameters.add(new Parameter("Student id", "Enter student Id"));
		parameters.add(new Parameter("First name", "Enter student first name"));
		parameters.add(new Parameter("Last name", "Enter student last name"));
		parameters.add(new Parameter("Group id", "Enter group Id"));
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public List<Parameter> getParameters() {
		return this.parameters;
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
