package com.yashbhandare.courseinfo.cli.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralSightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            01:08:54.9613330, 68
            00:05:37, 5
            00:00:00.0, 0
            """)
    void testDurationInMinutues(String input, long expected) {
        PluralSightCourse course = new
                PluralSightCourse("id", "Test Course", input, "url", false);
        assertEquals(expected, course.durationInMinutues());
    }
}