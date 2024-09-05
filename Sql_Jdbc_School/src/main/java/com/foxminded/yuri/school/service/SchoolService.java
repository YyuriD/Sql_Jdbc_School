package com.foxminded.yuri.school.service;

import java.util.List;

import com.foxminded.yuri.school.dao.CourseDao;
import com.foxminded.yuri.school.dao.DAOException;
import com.foxminded.yuri.school.dao.GroupDao;
import com.foxminded.yuri.school.dao.StudentDao;
import com.foxminded.yuri.school.model.Group;
import com.foxminded.yuri.school.model.Student;

public class SchoolService  {

	StudentDao studentDao = new StudentDao();
	GroupDao groupDao = new GroupDao();
	CourseDao courseDao = new CourseDao();

	public List<Group> findGroupsByMaxStudent(Integer maxStudent) throws DAOException {
		return groupDao.findByMaxStudent(maxStudent);
	}

	public List<Student> findStudentsByCourse(Integer courseId) throws DAOException {
		return studentDao.findByCourse(courseId);
	}

	public void addStudent(Student student) throws ServiceLayerException {
		try {
			if (studentDao.isExists(student.getId())) {
				throw new ServiceLayerException("Can't add student, already exists.");
			}
			studentDao.create(student);
		} catch (DAOException e) {
			throw new ServiceLayerException("Can't add student.", e);
		}
	}

	public void deleteStudent(Integer studentId) throws ServiceLayerException {
		try {
			if (!studentDao.isExists(studentId)) {
				throw new ServiceLayerException("Can't remove student, not found.");
			}
			studentDao.remove(studentId);
		} catch (DAOException e) {
			throw new ServiceLayerException("Can't remove student.", e);
		}
	}

	public void addStudentToCourse(Integer studentId, Integer courseId) throws ServiceLayerException {
		try {
			courseDao.addStudentToCourse(studentId, courseId);
		} catch (DAOException e) {
			throw new ServiceLayerException(e.getMessage(), e);
		}
	}

	public void removeStudentFromCourse(Integer studentId, Integer courseId) throws ServiceLayerException {
		try {
			courseDao.removeStudentFromCourse(studentId, courseId);
		} catch (DAOException e) {
			throw new ServiceLayerException(e.getMessage(), e);
		}
	}



}
