package com.yashbhandare.courseinfo.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseRetrieverService {

    private static final String AUTHOR_CONTENT_URL = "https://raw.githubusercontent.com/yashbhandare23/course-info/main/course-info-cli/src/main/resources/%s.json";

    private static final HttpClient CLIENT = HttpClient  //.newHttpClient();
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public List<PluralSightCourse> getCoursesFor(String authorId) {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(AUTHOR_CONTENT_URL.formatted(authorId)))
                .GET()
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch(response.statusCode()){
                case 200 -> toPluralSightCourses(response);
                case 401 -> List.of();
                default -> throw new RuntimeException("API call failed with status code "+response.statusCode());

            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not call the API",e);
        }
    }
    private List<PluralSightCourse> toPluralSightCourses(HttpResponse<String> response) throws
            JsonProcessingException{
        JavaType returnType = OBJECT_MAPPER.getTypeFactory()
                .constructCollectionType(List.class, PluralSightCourse.class);
        return OBJECT_MAPPER.readValue(response.body(), returnType);
    }
}
