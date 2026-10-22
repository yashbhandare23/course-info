package com.yashbhandare.courseinfo.cli.service;

import com.yashbhandare.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void pluralSightCourses() {
        CourseRepository repository;
        CourseStorageService courseStorageService = new CourseStorageService(repository);
    }
}