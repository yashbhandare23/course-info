package com.yashbhandare.courseinfo.repository;

import com.yashbhandare.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {

    void saveCourse(Course course);

    List<Course> getAllCourses();

}
