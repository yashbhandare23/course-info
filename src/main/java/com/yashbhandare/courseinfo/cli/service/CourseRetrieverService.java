package com.yashbhandare.courseinfo.cli.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrieverService {

    private static final String UAUTHOR_CONTENT_URL = "app.pluralsight.com/profile/data/author/%s/all-content";

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public String getCoursesFor(String authorId) {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(UAUTHOR_CONTENT_URL.formatted(authorId)));

        CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return "";
    }
}
