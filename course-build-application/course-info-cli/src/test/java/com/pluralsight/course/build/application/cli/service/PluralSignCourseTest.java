package com.pluralsight.course.build.application.cli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.management.ConstructorParameters;

import static org.junit.jupiter.api.Assertions.*;

class PluralSignCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            01:08:54.9613330, 68
            00:05:35, 5
            00:00:00.0,0
            """)
    void durationInMinutes(String input, long expected) {
        PluralSignCourse course =
                new PluralSignCourse("id", "Test course", input, "url", false);
        assertEquals(expected, course.durationInMinutes());
    }

}