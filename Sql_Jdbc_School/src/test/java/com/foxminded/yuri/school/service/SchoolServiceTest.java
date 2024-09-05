package com.foxminded.yuri.school.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxminded.yuri.school.dao.CourseDao;
import com.foxminded.yuri.school.dao.GroupDao;
import com.foxminded.yuri.school.dao.StudentDao;
import com.foxminded.yuri.school.model.Student;

public class SchoolServiceTest {

	@Mock
	private GroupDao groupDao;
	@Mock
	private StudentDao studentDao;
	@Mock
	private CourseDao courseDao;

	@InjectMocks
	private SchoolService schoolService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddStudentThrowsExceptionWhenStudentExists() throws Exception {

		Student student = new Student(1, "John", "Doe", 1);

		when(studentDao.isExists(student.getId())).thenReturn(true);

		assertThrows(ServiceLayerException.class, () -> schoolService.addStudent(student));
	}

	@Test
	void testDeleteStudentThrowsExceptionWhenNotFound() throws Exception {

		when(studentDao.isExists(1)).thenReturn(false);

		assertThrows(ServiceLayerException.class, () -> schoolService.deleteStudent(1));
	}

	@Test
	void testAddStudentToCourse() throws Exception {

		schoolService.addStudentToCourse(1, 1);

		verify(courseDao, times(1)).addStudentToCourse(1, 1);
	}

	@Test
	void testRemoveStudentFromCourse() throws Exception {

		schoolService.removeStudentFromCourse(1, 1);

		verify(courseDao, times(1)).removeStudentFromCourse(1, 1);
	}

}
