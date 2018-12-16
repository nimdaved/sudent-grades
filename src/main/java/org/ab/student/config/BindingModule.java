package org.ab.student.config;

import org.ab.student.dao.json.StudentFinderDao;
import org.ab.student.service.StudentFinder;

import com.google.inject.AbstractModule;


public class BindingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StudentFinder.class).to(StudentFinderDao.class);
    }
}
