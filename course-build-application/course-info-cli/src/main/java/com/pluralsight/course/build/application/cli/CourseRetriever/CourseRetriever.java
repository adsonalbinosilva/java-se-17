package com.pluralsight.course.build.application.cli.CourseRetriever;

import com.pluralsight.course.build.application.cli.service.CourseRetrieverService;
import com.pluralsight.course.build.application.cli.service.PluralSignCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("CourseRetriever started!");
        if (args.length == 0) {
            System.out.println("Please provide an author name as first argument.");
            return;
        }
        try {
            retrieveCourses(args[0]);
        } catch (Exception e) {
            LOG.error("Unexpected error", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieved courses for author '{}' ", authorId);
        CourseRetrieverService courseRetrieverService = new CourseRetrieverService();

        List<PluralSignCourse> coursesToStore = courseRetrieverService.getCoursesFor(authorId)
                        .stream()
                                .filter(not(PluralSignCourse::isRetired))
                                        .toList();

        LOG.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);
    }
}