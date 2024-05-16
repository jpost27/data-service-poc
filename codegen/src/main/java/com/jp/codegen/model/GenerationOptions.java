package com.jp.codegen.model;

import java.io.File;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenerationOptions {
    // The following fields are used to generate Java DTO classes
    private final boolean generateJavaDto;
    private final File javaDtoProjectDirectory;
    private final String javaDtoPackageName;

    // The following fields are used to generate JPA entity classes
    private final boolean generateJpaEntity;
    private final File jpaEntityProjectDirectory;
    private final String jpaEntityPackageName;
    private final String jpaEntityClassPrefix;
    private final String jpaEntityClassSuffix;

    // The following fields are used to generate TypeScript interfaces
    private final boolean generateTypeScript;
    private final File typeScriptOutputDirectory;

    // The following fields are used to generate Spring Data JPA repositories
    private final boolean generateRepository;
    private final File repositoryProjectDirectory;
    private final String repositoryPackageName;
    private final String repositorySuperInterfaceName;

    private final List<String> ignoredTableNames = List.of(
            "BATCH_JOB_EXECUTION",
            "BATCH_JOB_EXECUTION_CONTEXT",
            "BATCH_JOB_EXECUTION_PARAMS",
            "BATCH_STEP_EXECUTION",
            "BATCH_STEP_EXECUTION_CONTEXT",
            "BATCH_JOB_INSTANCE",
            "BATCH_JOB_EXECUTION_SEQ",
            "BATCH_JOB_SEQ",
            "BATCH_STEP_EXECUTION_SEQ",
            "team_team_associations",
            "competitors_positions");
    private final String schemaName;
}
