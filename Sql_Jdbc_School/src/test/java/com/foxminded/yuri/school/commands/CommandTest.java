package com.foxminded.yuri.school.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxminded.yuri.school.commands.Command;
import com.foxminded.yuri.school.dao.DAOException;
import com.foxminded.yuri.school.model.Group;
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

		String parameters = "15";
		List<Group> groups = new ArrayList<>(Arrays.asList(new Group(2, "GR-B-34")));
		when(schoolService.findGroupsByMaxStudent(15)).thenReturn(groups);
		String actual = Command.FIND_GROUPS_BY_MAX_STUDENTS.execute(parameters);
		String expected = "Group [id=2, name=GR-B-34]\n";
		assertEquals(expected, actual);
	}

}
