package com.jp.codegen.generator;

import com.google.common.base.CaseFormat;
import com.jp.codegen.model.GenerationOptions;
import com.jp.codegen.model.TableRelationship;
import com.jp.codegen.util.CaseUtil;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import schemacrawler.schema.Table;

@Slf4j
public class RepositoryGenerator extends SqlTableFileGenerator {
    public RepositoryGenerator(GenerationOptions options) {
        super(options);
    }

    @Override
    protected boolean shouldGenerate(Table table) {
        return options.isGenerateTypeScript();
    }

    @Override
    protected void validateOptions() throws IllegalStateException {
        if (options.isGenerateRepository()) {
            if (options.getRepositoryPackageName() == null) {
                throw new IllegalStateException("Repository package name is required");
            }
            if (options.getRepositoryProjectDirectory() == null) {
                throw new IllegalStateException("Repository project directory is required");
            }
            if (options.getRepositoryProjectDirectory().exists()
                    && !options.getRepositoryProjectDirectory().isDirectory()) {
                throw new IllegalStateException("Repository project directory must be a directory");
            }
            if (options.getJpaEntityPackageName() == null) {
                throw new IllegalStateException("JPA entity package name is required");
            }
            if (options.getJpaEntityClassPrefix() == null) {
                throw new IllegalStateException("JPA entity class prefix is required");
            }
            if (options.getRepositorySuperInterfaceName() == null) {
                throw new IllegalStateException("Repository super interface name is required");
            }
        }
    }

    @Override
    protected void generate(Table table, Collection<TableRelationship> allTableRelationships) {
        TypeSpec.Builder repositorySpec = TypeSpec.interfaceBuilder(getDtoName(table) + "Repository");
        repositorySpec.addAnnotation(ClassName.get("org.springframework.stereotype", "Repository"));
        repositorySpec.addSuperinterface(getRepositorySuperInterface(table));

        JavaFile interfaceJavaFile = JavaFile.builder(options.getRepositoryPackageName(), repositorySpec.build())
                .indent("    ")
                .build();
        writeJavaFile(interfaceJavaFile, options.getRepositoryProjectDirectory());
    }

    private TypeName getRepositorySuperInterface(Table table) {
        return ParameterizedTypeName.get(
                ClassName.bestGuess(options.getRepositorySuperInterfaceName()),
                ClassName.get(options.getJpaEntityPackageName(), getEntityName(table)),
                ClassName.get(options.getJpaEntityPackageName(), getQClassName(table)),
                getEntityIdClassName(table));
    }

    private ClassName getEntityIdClassName(Table table) {
        if (!table.hasPrimaryKey()) {
            return ClassName.get("java.lang", "Object");
        } else if (table.getPrimaryKey().getConstrainedColumns().size() == 1) {
            return ClassName.get(table.getPrimaryKey()
                    .getConstrainedColumns()
                    .get(0)
                    .getType()
                    .getTypeMappedClass());
        } else {
            return ClassName.get(options.getJpaEntityPackageName(), getEntityName(table) + "Id");
        }
    }

    private String getEntityName(Table table) {
        return CaseUtil.convert(options.getJpaEntityClassPrefix(), CaseFormat.UPPER_CAMEL)
                + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName());
    }

    private String getQClassName(Table table) {
        return "Q" + getEntityName(table);
    }

    private String getDtoName(Table table) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName());
    }
}
