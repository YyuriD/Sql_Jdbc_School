package com.foxminded.yuri.school.commands;

import java.util.List;

import com.foxminded.yuri.school.SchoolApp;
import com.foxminded.yuri.school.model.Group;
import com.foxminded.yuri.school.model.Student;
import com.foxminded.yuri.school.service.SchoolService;
import com.foxminded.yuri.school.utils.DataConverter;

public enum Command {

	FIND_GROUPS_BY_MAX_STUDENTS("max students quantity") {

		@Override
		public String execute(String parameters) {
			Integer studentsQuantity;
			try {
				studentsQuantity = DataConverter.convertToInt(parameters);
				List<Group> groups = schoolService.findGroupsByMaxStudent(studentsQuantity);
				if (groups.isEmpty()) {
					return "not found";
				}
				return DataConverter.convertFromListToString(groups);
			} catch (Exception e) {
				return e.getMessage();
			}
		}
	},

	FIND_STUDENTS_BY_COURSE("course id") {

		@Override
		public String execute(String parameters) {
			Integer courseId;
			try {
				courseId = DataConverter.convertToInt(parameters);
				List<Student> students = schoolService.findStudentsByCourse(courseId);
				if (students.isEmpty()) {
					return "not found";
				}
				return DataConverter.convertFromListToString(students);
			} catch (Exception e) {
				return e.getMessage();
			}
		}
	},

	ADD_STUDENT("student data: id,firstName,lastName,groupId") {

		@Override
		public String execute(String parameters) {
			Student student = null;
			try {
				student = DataConverter.convertStudent(parameters);
				schoolService.addStudent(student);
				return "New student was added.";
			} catch (Exception e) {
				return e.getMessage();
			}
		}
	},

	DELETE_STUDENT("student id") {

		@Override
		public String execute(String parameters) {
			Integer studentId = null;
			try {
				studentId = DataConverter.convertToInt(parameters);
				schoolService.deleteStudent(studentId);
				return "Student was deleted";
			} catch (Exception e) {
				return e.getMessage();
			}
		}
	},

	ADD_STUDENT_TO_COURSE("student id,course id") {

		@Override
		public String execute(String parameters) {
			Integer[] parts;
			try {
				parts = DataConverter.convertToIntegerArray(parameters);
				if (parts == null || parts.length != 2) {
					return "Error: wrong parameters";
				}
				schoolService.addStudentToCourse(parts[0], parts[1]);
				return "Student was added to course";
			} catch (Exception e) {
				return e.getMessage();
			}
		}
	},

	REMOVE_STUDENT_FROM_COURSE("student id,course id") {

		@Override
		public String execute(String parameters) {
			Integer[] parts;
			try {
				parts = DataConverter.convertToIntegerArray(parameters);
				if (parts == null || parts.length != 2) {
					return "Error: wrong parameters";
				}
				schoolService.removeStudentFromCourse(parts[0], parts[1]);
				return "student was removed from course";
			} catch (Exception e) {
				return e.getMessage();
			}
		}
	},

	MENU("") {

		@Override
		public String execute(String parameters) {
			return SchoolApp.getMenuView();
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
