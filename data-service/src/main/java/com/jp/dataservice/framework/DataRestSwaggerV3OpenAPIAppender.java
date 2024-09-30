package com.jp.dataservice.framework;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Slf4j
@RequiredArgsConstructor
public class DataRestSwaggerV3OpenAPIAppender implements ApplicationListener<ContextRefreshedEvent> {

    private static final String API_PATH = "/api/";
    private final PersistenceMapping persistenceMapping;
    private final OpenAPI openAPI;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Appending Data REST paths to OpenAPI");
        appendDateRestPaths(openAPI);
    }

    public void appendDateRestPaths(OpenAPI openAPI) {
        Set<String> existingPaths = openAPI.getPaths().keySet();
        persistenceMapping.getQueryNameToEntityMetadata().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    if (existingPaths.stream().noneMatch(path -> path.startsWith(API_PATH + entry.getKey()))) {
                        createPagedPathApi(openAPI, entry);
                        createAllPathApi(openAPI, entry);
                        createByIdPathApi(openAPI, entry);
                    } else {
                        log.info(
                                "Existing path found for entity: {}. API docs will not be updated for its endpoints.",
                                entry.getKey());
                    }
                });
    }

    private void createPagedPathApi(
            OpenAPI openAPI, Map.Entry<String, PersistenceMapping.EntityMetadata<?, ?, ?, ?>> entry) {

        openAPI.path(
                "/api/" + entry.getKey(),
                new PathItem()
                        .get(
                                new Operation()
                                        .summary("Get a page of " + entry.getKey())
                                        .description("Get all " + entry.getKey() + " in a paged response")
                                        .operationId("get" + entry.getKey())
                                        .tags(List.of(entry.getKey()))
                                        .parameters(List.of(new Parameter()
                                                .in("query")
                                                .name("page")
                                                .description("Page number")
                                                .required(false)))
                                        .responses(
                                                new ApiResponses()
                                                        .addApiResponse(
                                                                "200",
                                                                new ApiResponse()
                                                                        .description("d operation")
                                                                        .content(
                                                                                new Content()
                                                                                        .addMediaType(
                                                                                                "application/json",
                                                                                                new MediaType()
                                                                                                        .schema(
                                                                                                                new ObjectSchema()
                                                                                                                        .addProperty(
                                                                                                                                entry.getKey()
                                                                                                                                                .toLowerCase()
                                                                                                                                        + "s",
                                                                                                                                new ArraySchema()
                                                                                                                                        .items(
                                                                                                                                                new ObjectSchema()
                                                                                                                                                        .addProperty(
                                                                                                                                                                "sportId",
                                                                                                                                                                new IntegerSchema())
                                                                                                                                                        .addProperty(
                                                                                                                                                                "name",
                                                                                                                                                                new StringSchema()))))))))));
    }

    private void createAllPathApi(
            OpenAPI openAPI, Map.Entry<String, PersistenceMapping.EntityMetadata<?, ?, ?, ?>> entry) {

        openAPI.path(
                "/api/" + entry.getKey() + "/all",
                new PathItem()
                        .get(
                                new Operation()
                                        .summary("Get all " + entry.getKey())
                                        .description("Get all " + entry.getKey() + " from the system")
                                        .operationId("get" + entry.getKey())
                                        .tags(List.of(entry.getKey()))
                                        .parameters(List.of(new Parameter()
                                                .in("query")
                                                .name("sort")
                                                .description("Sort by field")
                                                .required(false)))
                                        .responses(
                                                new ApiResponses()
                                                        .addApiResponse(
                                                                "200",
                                                                new ApiResponse()
                                                                        .description("d operation")
                                                                        .content(
                                                                                new Content()
                                                                                        .addMediaType(
                                                                                                "application/json",
                                                                                                new MediaType()
                                                                                                        .schema(
                                                                                                                new ObjectSchema()
                                                                                                                        .addProperty(
                                                                                                                                entry.getKey()
                                                                                                                                                .toLowerCase()
                                                                                                                                        + "s",
                                                                                                                                new ArraySchema()
                                                                                                                                        .items(
                                                                                                                                                new ObjectSchema()
                                                                                                                                                        .addProperty(
                                                                                                                                                                "sportId",
                                                                                                                                                                new IntegerSchema())
                                                                                                                                                        .addProperty(
                                                                                                                                                                "name",
                                                                                                                                                                new StringSchema()))))))))));
    }

    private void createByIdPathApi(
            OpenAPI openAPI, Map.Entry<String, PersistenceMapping.EntityMetadata<?, ?, ?, ?>> entry) {}
}
