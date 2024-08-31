package com.foxminded.yuri.school.cli.commands;

import java.util.List;

import com.foxminded.yuri.school.model.Group;
import com.foxminded.yuri.school.model.Student;
import com.foxminded.yuri.school.service.SchoolService;

public enum Command {

	FIND_GROUPS_BY_MAX_STUDENTS("max students quantity") {

		@Override
		public void execute(String parameters) {
			Integer studentsQuantity = null;
			try {
				studentsQuantity = Integer.valueOf(parameters);
				List<Group> groups = schoolService.findGroupsByMaxStudent(studentsQuantity);
				if (groups != null) {
					groups.forEach(g -> System.out.println(g.getName()));
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: parameter must be number");
			}
		}

	},

	FIND_STUDENTS_BY_COURSE("course id") {

		@Override
		public void execute(String parameters) {
			Integer courseId = null;
			try {
				courseId = Integer.valueOf(parameters);
				schoolService.findStudentsByCourse(courseId).forEach(g -> System.out.println(g.toString()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	},

	ADD_STUDENT("student data: id,name,firstName,lastName,groupId") {

		@Override
		public void execute(String parameters) {

			try {
				String[] parts = parameters.split(",");
				if (parts.length != Student.FIELDS_QUANTITY) {
					throw new IllegalArgumentException("wrong parameters quantity");
				}
				Integer id = Integer.valueOf(parts[0]);
				String firstName = parts[1];
				String lastName = parts[2];
				Integer groupId = Integer.valueOf(parts[3]);
				Student student = new Student(id, firstName, lastName, groupId);
				if (schoolService.addStudent(student)) {
					System.out.println("student was added");
				} else {
					System.out.println("error");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	},

	DELETE_STUDENT("student id") {

		@Override
		public void execute(String parameters) {
			// TODO Auto-generated method stub
		}
	},

	ADD_STUDENT_TO_COURSE("course id") {

		@Override
		public void execute(String parameters) {
			// TODO Auto-generated method stub
		}
	},

	REMOVE_STUDENT_FROM_COURSE("course id") {

		@Override
		public void execute(String parameters) {
			// TODO Auto-generated method stub
		}

	},

	EXIT("") {

		@Override
		public void execute(String parameters) {
			System.out.println(parameters);
		}

	};

	public String parameterDiscription;
	public abstract void execute(String parameters);

	private static final SchoolService schoolService = new SchoolService();

	private Command(String parameterDiscription) {
		this.parameterDiscription = parameterDiscription;
	}
}
