package com.yashbhandare.courseinfo.cli.service;

import com.yashbhandare.courseinfo.domain.Course;
import com.yashbhandare.courseinfo.repository.CourseRepository;

import java.util.List;

public class CourseStorageService {

    private final CourseRepository courseRepository;

    private static final String PS_BASE_URL = "https://raw.githubusercontent.com";

    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void pluralSightCourses(List<PluralSightCourse> psCourses){

        for (PluralSightCourse psCourse: psCourses){
            Course course = new Course(
                    psCourse.id(),
                    psCourse.title(),
                    psCourse.durationInMinutues(),
                    PS_BASE_URL+psCourse.contentUrl());
            courseRepository.saveCourse(course);
        }

    }
}
