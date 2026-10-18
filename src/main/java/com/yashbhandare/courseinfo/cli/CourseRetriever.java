package com.yashbhandare.courseinfo.cli;

import com.yashbhandare.courseinfo.cli.service.CourseRetrieverService;
import com.yashbhandare.courseinfo.cli.service.PluralSightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.function.Predicate.not;
import java.util.List;

public class CourseRetriever {

    private static Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("CourseRetriever starting");
        if(args.length==0){
            LOG.warn("Please provide an author name as first argument.");
            return;
        }

        try{
            retriveCourses(args[0]);
        }catch (Exception e){
            LOG.error("Unexpected error", e);
        }

    }

    private static void retriveCourses(String authorId) {
        LOG.info("Retrieving courses for author: '{}'", authorId);

        CourseRetrieverService courseRetrieverService = new CourseRetrieverService();
        List<PluralSightCourse> coursesToStore = courseRetrieverService.getCoursesFor(authorId)
                        .stream()
                        .filter(course -> !course.isRetired())
                        //.filter(not(PluralSightCourse::isRetired))
                        .toList();
        LOG.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);
    }
}

