package com.foxminded.yuri.school.commands;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.yuri.school.model.Group;
import com.foxminded.yuri.school.parameters.Parameter;
import com.foxminded.yuri.school.service.SchoolService;
import com.foxminded.yuri.school.utils.DataConverter;

public class CommandFindGroupByMaxStudent implements Command {

	private String name;
	private List<Parameter> parameters;
	private String description = "Find all groups with less or equal students number";

	public CommandFindGroupByMaxStudent() {
		this.name = "FIND_GROUPS_BY_MAX_STUDENTS";
		parameters = new ArrayList<>();
		parameters.add(new Parameter("Student quantity", "Enter student quantity"));
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
			Integer studentsQuantity = DataConverter.convertToInt(parameters.get(0).getValue());
			List<Group> groups = schoolService.findGroupsByMaxStudent(studentsQuantity);
			if (groups.isEmpty()) {
				return "not found";
			}
			return DataConverter.convertFromListToString(groups);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
