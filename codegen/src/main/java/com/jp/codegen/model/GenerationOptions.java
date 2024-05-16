package com.jp.codegen.model;

import java.io.File;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenerationOptions {
    private final boolean generateJavaDto;
    private final File javaDtoOutputDirectory;
    private final String javaDtoPackageName;

    private final boolean generateJpaEntity;
    private final File jpaEntityOutputDirectory;
    private final String jpaEntityPackageName;
    private final String jpaEntityClassPrefix;
    private final String jpaEntityClassSuffix;

    private final boolean generateTypeScript;
    private final File typeScriptOutputDirectory;
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
    private final String schemaName = "sport_data";
}
