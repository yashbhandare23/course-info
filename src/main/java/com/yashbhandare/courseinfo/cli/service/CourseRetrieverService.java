package com.yashbhandare.courseinfo.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseRetrieverService {

    private static final String UAUTHOR_CONTENT_URL = "https://raw.githubusercontent.com/yashbhandare23/course-info/main/src/main/resources/%s.json";

    private static final HttpClient CLIENT = HttpClient  //.newHttpClient();
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    public String getCoursesFor(String authorId) {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(UAUTHOR_CONTENT_URL.formatted(authorId)))
                .GET()
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch(response.statusCode()){
                case 200 -> response.body();
                case 401 -> List.of().toString();
                default -> throw new RuntimeException("API call failed with status code "+response.statusCode());

            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not call the API",e);
        }
    }
}
