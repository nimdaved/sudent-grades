package org.ab.student.dao.json;

import java.util.LinkedHashMap;
import java.util.List;

import org.ab.student.domain.Student;

import lombok.Data;

@Data
public class Datastore {
	private List<Student> students;
	private LinkedHashMap<String, String> classes;
}
