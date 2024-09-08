package com.foxminded.yuri.school.commands;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.yuri.school.model.Student;
import com.foxminded.yuri.school.parameters.Parameter;
import com.foxminded.yuri.school.service.SchoolService;
import com.foxminded.yuri.school.utils.DataConverter;

public class CommandFindStudentsByCourseName implements Command {

	private String name;
	private List<Parameter> parameters;
	private String description = "Find all students by course name";

	public CommandFindStudentsByCourseName() {
		this.name = "FIND_STUDENTS_BY_COURSE_NAME";
		parameters = new ArrayList<>();
		parameters.add(new Parameter("Course name", "Enter course name"));
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
			String courseName = parameters.get(0).getValue();
			List<Student> students = schoolService.findStudentsByCourseName(courseName);
			if (students.isEmpty()) {
				return "not found";
			}
			return DataConverter.convertFromListToString(students);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
