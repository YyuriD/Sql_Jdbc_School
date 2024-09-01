package com.foxminded.yuri.school.commands;

import java.util.List;

import com.foxminded.yuri.school.SchoolApp;
import com.foxminded.yuri.school.converter.DataConverter;
import com.foxminded.yuri.school.model.Group;
import com.foxminded.yuri.school.model.Student;
import com.foxminded.yuri.school.service.SchoolService;

public enum Command {

	FIND_GROUPS_BY_MAX_STUDENTS("max students quantity") {

		@Override
		public String execute(String parameters) {
			Integer studentsQuantity = DataConverter.convertToInt(parameters);
			if (studentsQuantity == null) {
				return "Error: parameter must be number";
			}
			List<Group> groups = schoolService.findGroupsByMaxStudent(studentsQuantity);
			if (groups == null) {
				return "not found";
			} else {
				return DataConverter.convertFromListToString(groups);
			}
		}
	},

	FIND_STUDENTS_BY_COURSE("course id") {

		@Override
		public String execute(String parameters) {
			Integer courseId = DataConverter.convertToInt(parameters);
			if (courseId == null) {
				return "Error: parameter must be number";
			}
			List<Student> students = schoolService.findStudentsByCourse(courseId);
			if (students == null) {
				return "not found";
			} else {
				return DataConverter.convertFromListToString(students);
			}
		}
	},

	ADD_STUDENT("student data: id,name,firstName,lastName,groupId") {

		@Override
		public String execute(String parameters) {
			Student student = DataConverter.convertStudent(parameters);
			if (schoolService.addStudent(student)) {
				return "student was added";
			} else {
				return "error";
			}
		}
	},

	DELETE_STUDENT("student id") {

		@Override
		public String execute(String parameters) {
			Integer studentId = DataConverter.convertToInt(parameters);
			if (schoolService.deleteStudent(studentId)) {
				return "student was deleted";
			} else {
				return "fail";
			}
		}
	},

	ADD_STUDENT_TO_COURSE("student id,course id") {

		@Override
		public String execute(String parameters) {
			Integer[] parts = DataConverter.convertToIntegerArray(parameters);
			if (schoolService.addStudentToCourse(parts[0], parts[1])) {
				return "student was added to course";
			} else {
				return "fail";
			}
		}
	},

	REMOVE_STUDENT_FROM_COURSE("student id,course id") {

		@Override
		public String execute(String parameters) {
			Integer[] parts = DataConverter.convertToIntegerArray(parameters);
			if (schoolService.removeStudentFromCourse(parts[0], parts[1])) {
				return "student was added to course";
			} else {
				return "fail";
			}
		}
	},

	MENU("") {

		@Override
		public String execute(String parameters) {
			return SchoolApp.getMenu();
		}

	},

	EXIT("") {

		@Override
		public String execute(String parameters) {
			return parameters;
		}
	};

	public String parameterDiscription;

	public abstract String execute(String parameters);

	private static final SchoolService schoolService = new SchoolService();

	private Command(String parameterDiscription) {
		this.parameterDiscription = parameterDiscription;
	}
}
