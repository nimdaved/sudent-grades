package org.ab.student.dao.json;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.ab.student.domain.Student;
import org.ab.student.domain.StudentSummary;
import org.ab.student.service.StudentFinder;
import org.ab.student.util.StudentHelper;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentFinderDao implements StudentFinder {

	private static final int COUNT_MAX_DEFAULT = 100;

	public static final long STUDENT_ID_BASE = 3000L;

	public static final String DATASTORE_LOCATION_DEFAULT = "./students_classes.json";

	public static final String DATA_PROPERTY_NAME = "data";

	private final Datastore datastore;

	public Datastore getDatastore() {
		return datastore;
	}

	private final StudentHelper sh;

	public StudentFinderDao() {
		sh = new StudentHelper();

		datastore = initializeDatastore();
	}

	private Datastore initializeDatastore() {
		Datastore ds = load(System.getProperty(DATA_PROPERTY_NAME, DATASTORE_LOCATION_DEFAULT));
		log.info("Loaded datastore");
		AtomicLong id = new AtomicLong(STUDENT_ID_BASE);
		ds.getStudents().forEach(s -> {
			s.setId(id.incrementAndGet());
			sh.appyGpa(s);
			sh.appyClassName(s, ds.getClasses());
		});
		return ds;
	}

	private Datastore load(String path) {
		try {
			return new ObjectMapper().readValue(getClass().getClassLoader().getResourceAsStream(path), Datastore.class);
		} catch (IOException e) {
			String errMsg = "Could not load datastore on path: " + path;
			log.error(errMsg, e);
			throw new IllegalStateException(errMsg, e);
		}
	}

	@Override
	public Optional<Student> findById(Long id) {

		return datastore.getStudents().stream().filter(s -> s.getId().equals(id)).findFirst();
	}

	@Override
	public List<StudentSummary> findBy(Optional<String> first, Optional<String> last, Optional<Integer> countMax) {

		return datastore.getStudents().stream().filter(sh.studentFilter(first, last))
				.limit(countMax.orElse(COUNT_MAX_DEFAULT))
				.map(s -> new StudentSummary(s.getId(), s.getFirst(), s.getLast(), s.getEmail(), s.getGpa()))
				.collect(Collectors.toList());
	}

}
