package org.ab.student.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.ab.student.dao.json.Datastore;
import org.ab.student.dao.json.StudentFinderDao;
import org.ab.student.domain.Student;
import org.ab.student.domain.StudentSummary;
import org.junit.Test;

public class StudentFinderDaoTest {
	
	private final StudentFinderDao dao = new StudentFinderDao();
	private final String existingFirst = "Tyler";
	private final String existingLast = "Cortez";
	private final String nonExistingFirst = "someFirst";
	private final String nonExistingLast = "someLast";
	
	@Test
	public void shouldLoadAndInitialize() {
		Datastore ds = dao.getDatastore();
		assertFalse(ds.getStudents().isEmpty());
		ds.getStudents().forEach(s-> {
			//test summary part
			assertNotNull(s);
			assertNotNull(s.getFirst());
			assertNotNull(s.getLast());
			assertNotNull(s.getEmail());			
			assertNotNull(s.getId());
			assertNotNull(s.getGpa());
			//test detail part
			s.getStudentClasses().forEach(sc->{
				assertNotNull(sc.getId());
				assertNotNull(sc.getGrade());
				assertNotNull(sc.getClassName());
			});
			
		});
		assertFalse(ds.getClasses().isEmpty());
		ds.getClasses().forEach((k, v) -> {
			assertNotNull(v);
		});		
	}
	
	@Test
	public void shouldFindById_whenExists() {
		List<Student> ls = dao.getDatastore().getStudents();
		Student existing = ls.get(ls.size()/2);
		Student found = dao.findById(existing.getId()).get();
		assertSame(existing, found);
	}
	
	
	@Test
	public void shouldNotFindById_whenNotExists() {
		assertFalse(dao.findById(8000000L).isPresent());
	}
	
	@Test
	public void shouldFindByFirst_whenExists() {		
		List<StudentSummary> l = dao.findBy(Optional.of(existingFirst), Optional.empty(), Optional.empty());
		assertFalse(l.isEmpty());
		l.stream().allMatch(s-> s.getFirst().equalsIgnoreCase(existingFirst));
	}
	
	@Test
	public void shouldFindByLast_whenExists() {		
		List<StudentSummary> l = dao.findBy(Optional.empty(), Optional.of(existingLast),  Optional.empty());
		assertFalse(l.isEmpty());
		l.stream().allMatch(s-> s.getLast().equalsIgnoreCase(existingLast));
	}
	
	@Test
	public void shouldFindByFirstAndLast_whenExists() {		
		List<StudentSummary> l = dao.findBy(Optional.of(existingFirst), Optional.of(existingLast), Optional.empty());
		assertFalse(l.isEmpty());
		l.stream().allMatch(s-> s.getFirst().equalsIgnoreCase(existingFirst) && s.getLast().equalsIgnoreCase(existingLast));
	}
	
	@Test
	public void shouldFindAllatMostCountMaxByFirstAndLastEmpty() {
		int count = 3;
		int expectedSize = Math.min(dao.getDatastore().getStudents().size(), count);
		List<StudentSummary> l = dao.findBy(Optional.empty(), Optional.empty(), Optional.ofNullable(count));
		assertEquals(expectedSize, l.size()) ;
	}
	
	@Test
	public void shouldNotFindByFirstAndLast_whenAnyIsWrong() {		
		
		List<StudentSummary> l = dao.findBy(Optional.of(existingFirst), Optional.of(nonExistingLast), Optional.empty());
		assertTrue(l.isEmpty());
		
		List<StudentSummary> ll = dao.findBy(Optional.of(nonExistingFirst), Optional.of(existingLast), Optional.empty());
		assertTrue(ll.isEmpty());
		
	}
	
	@Test
	public void shouldNotFindByFirstAndLast_whenAllAreWrong() {		
		List<StudentSummary> l = dao.findBy(Optional.of(nonExistingFirst), Optional.of(nonExistingLast), Optional.empty());
		assertTrue(l.isEmpty());
		
	}

}
