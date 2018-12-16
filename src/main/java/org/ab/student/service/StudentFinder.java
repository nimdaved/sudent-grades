package org.ab.student.service;

import java.util.List;
import java.util.Optional;

import org.ab.student.domain.Student;
import org.ab.student.domain.StudentSummary;

public interface StudentFinder {
	Optional<Student> findById(Long id);
	List<StudentSummary> findBy(Optional<String> first, Optional<String> last, Optional<Integer> countMax);
	
	default String ping() {
		return "pong";
	};
}
