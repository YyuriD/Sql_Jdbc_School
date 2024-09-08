package com.foxminded.yuri.school.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxminded.yuri.school.dao.DAOException;
import com.foxminded.yuri.school.service.SchoolService;

public class CommandTest {

	@Mock
	private SchoolService schoolService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_FIND_GROUPS_BY_MAX_STUDENTS_with_valid_input() throws DAOException {

	}

}
