package com.pluralsight.course.build.application.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluralsight.course.build.application.cli.CourseRetriever.CourseRetriever;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseRetrieverService {
    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    // if at any time the URL Changes, its redirects to new one
    private static final HttpClient CLIENT = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public List<PluralSignCourse> getCoursesFor(String authorId) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET().build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch (response.statusCode()) {
                case 200 -> getCourseRetrievers(response);
                case 400 -> List.of();
                default -> throw new RuntimeException("Pluralsight API call failed with status code " + response);
            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<PluralSignCourse> getCourseRetrievers(HttpResponse<String> response) throws JsonProcessingException {
        JavaType returnType = OBJECT_MAPPER.getTypeFactory()
                        .constructCollectionType(List.class, PluralSignCourse.class);
        return OBJECT_MAPPER.readValue(response.body(), returnType);
    }
}
