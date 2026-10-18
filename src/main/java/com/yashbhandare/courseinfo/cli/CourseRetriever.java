package com.yashbhandare.courseinfo.cli;

import com.yashbhandare.courseinfo.cli.service.CourseRetrieverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        String coursesToStore = courseRetrieverService.getCoursesFor(authorId);
        LOG.info("Retrieved the following courses {}", coursesToStore);
    }
}

