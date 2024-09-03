package com.foxminded.yuri.school.service;

import java.util.List;

import com.foxminded.yuri.school.dao.DAOException;
import com.foxminded.yuri.school.dao.StudentDao;
import com.foxminded.yuri.school.model.Group;
import com.foxminded.yuri.school.model.Student;

public class SchoolService  {

	StudentDao studentDao = new StudentDao();

	public List<Group> findGroupsByMaxStudent(Integer studentQuantity) {
		System.out.println("execution findGroupsByMaxStudent");
		return null;
	}

	public List<Student> findStudentsByCourse(Integer courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Student addStudent(Student student) throws DAOException {
		studentDao.add(student);
		return student;
	}

	public boolean deleteStudent(Integer studentId) throws DAOException {
		return studentDao.remove(studentId);
	}

	public boolean addStudentToCourse(Integer studentId, Integer courseId) {
		// TODO Auto-generated method stub
		System.out.println("execution addStudentToCourse " + studentId + " " + courseId);
		return false;
	}

	public boolean removeStudentFromCourse(Integer studentId, Integer courseId) {
		// TODO Auto-generated method stub
		return false;
	}

}
