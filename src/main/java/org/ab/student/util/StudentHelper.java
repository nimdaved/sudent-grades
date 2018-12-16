package org.ab.student.util;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.ab.student.domain.Student;

public class StudentHelper {
	public Student appyClassName(Student student, Map<String,String> classes) {
		student.getStudentClasses().forEach(s -> s.setClassName(classes.get(String.valueOf(s.getId()))));
		return student;
	}
	
	public Student appyGpa(Student student) {
		student.setGpa(BigDecimal
				.valueOf(student.getStudentClasses().stream().filter(s -> s.getGrade() != null)
						.mapToDouble(s -> s.getGrade().doubleValue()).average().getAsDouble())
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		return student;
	}
	
	public Predicate<Student> studentFilter(Optional<String> first, Optional<String> last) {
		return s -> (first.map(f -> s.getFirst().equalsIgnoreCase(f.trim())).orElse(true)
				&& last.map(f -> s.getLast().equalsIgnoreCase(f.trim())).orElse(true));
	}
}
