package com.foxminded.yuri.school.service;

import java.util.List;

import com.foxminded.yuri.school.dao.DAOException;
import com.foxminded.yuri.school.dao.StudentDao;
import com.foxminded.yuri.school.model.Group;
import com.foxminded.yuri.school.model.Student;

public class SchoolService  {

	StudentDao studentDao = new StudentDao();

	public List<Group> findGroupsByMaxStudent(Integer studentQuantity) {
		return null;
	}

	public List<Student> findStudentsByCourse(Integer courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addStudent(Student student) throws ServiceLayerException {
		try {
			if (isStudentExist(student.getId())) {
				throw new ServiceLayerException("Can't add student, already exists.");
			}
			studentDao.add(student);
		} catch (DAOException e) {
			throw new ServiceLayerException("Can't add student.", e);
		}
	}

	public void deleteStudent(Integer studentId) throws ServiceLayerException {
		try {
			if (!isStudentExist(studentId)) {
				throw new ServiceLayerException("Can't remove student, not found.");
			}
			studentDao.remove(studentId);
		} catch (DAOException e) {
			throw new ServiceLayerException("Can't remove student.", e);
		}
	}

	public boolean addStudentToCourse(Integer studentId, Integer courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeStudentFromCourse(Integer studentId, Integer courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isStudentExist(Integer studentId) throws DAOException {
		return studentDao.get(studentId) != null ? true : false;
	}

}
