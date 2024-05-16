package com.jp.codegen;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jp.codegen.generator.JPAEntityGenerator;
import com.jp.codegen.generator.JavaDTOGenerator;
import com.jp.codegen.generator.SqlTableFileGenerator;
import com.jp.codegen.generator.TypeScriptInterfaceGenerator;
import com.jp.codegen.model.GenerationOptions;
import java.io.File;
import java.util.Set;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schema.View;
import schemacrawler.schemacrawler.LimitOptionsBuilder;
import schemacrawler.schemacrawler.LoadOptionsBuilder;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaCrawlerOptionsBuilder;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.tools.utility.SchemaCrawlerUtility;
import us.fatehi.utility.datasource.DatabaseConnectionSource;
import us.fatehi.utility.datasource.DatabaseConnectionSources;
import us.fatehi.utility.datasource.MultiUseUserCredentials;

// @SpringBootApplication
public class CodegenApplication {

    private static final String URL = "jdbc:mysql://localhost:3306/sport_data?createDatabaseIfNotExist=false";

    public static void main(String[] args) {
        //        SpringApplication.run(CodegenApplication.class, args);

        // Create the options
        final LimitOptionsBuilder limitOptionsBuilder =
                LimitOptionsBuilder.builder().includeTables(tableFullName -> !tableFullName.contains("_PK"));
        final LoadOptionsBuilder loadOptionsBuilder = LoadOptionsBuilder.builder()
                // Set what details are required in the schema - this affects the
                // time taken to crawl the schema
                .withSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
        final SchemaCrawlerOptions options = SchemaCrawlerOptionsBuilder.newSchemaCrawlerOptions()
                .withLimitOptions(limitOptionsBuilder.toOptions())
                .withLoadOptions(loadOptionsBuilder.toOptions());

        // Get the schema definition
        final DatabaseConnectionSource dataSource = getDataSource();
        final Catalog catalog = SchemaCrawlerUtility.getCatalog(dataSource, options);

        GenerationOptions generationOptions = GenerationOptions.builder()
                .generateJavaDto(true)
                .javaDtoOutputDirectory(new File("src/main/java/com/jp/dto"))
                .javaDtoPackageName("com.jp.dto")
                .generateJpaEntity(true)
                .jpaEntityOutputDirectory(new File("src/main/java/com/jp/entity"))
                .jpaEntityClassPrefix("Fd")
                .jpaEntityPackageName("com.jp.entity")
                .generateTypeScript(true)
                .typeScriptOutputDirectory(new File("src/main/webapp/app/entities"))
                .build();
        for (final Schema schema : catalog.getSchemas()) {
            if (!schema.toString().equals(generationOptions.getSchemaName())) {
                continue;
            }
            System.out.println(schema);
            for (final Table table : catalog.getTables(schema)) {
                if (generationOptions.getIgnoredTableNames().contains(table.getName())) {
                    continue;
                }
                System.out.print("o--> " + table);
                if (table instanceof View) {
                    System.out.println(" (VIEW)");
                } else {
                    System.out.println();
                }

                System.out.println("Type -> " + table.getTableType());
                table.getReferencedTables()
                        .forEach(referencedTable -> System.out.println("Referenced -> " + referencedTable));
                table.getDependentTables()
                        .forEach(referencedTable -> System.out.println("Dependent -> " + referencedTable));
                table.getTableConstraints()
                        .forEach(constraint -> System.out.println(
                                "Constraint -> " + constraint.getType() + " - " + constraint.getConstrainedColumns()));
                final Set<SqlTableFileGenerator> generators = Set.of(
                        new JPAEntityGenerator(generationOptions),
                        new JavaDTOGenerator(generationOptions),
                        new TypeScriptInterfaceGenerator(generationOptions));
                generators.forEach(generator -> generator.generate(table));

                for (final Column column : table.getColumns()) {
                    System.out.printf("     o--> %s (%s)%n", column, column.getType());
                }
            }
        }
    }

    private static DatabaseConnectionSource getDataSource() {
        return DatabaseConnectionSources.newDatabaseConnectionSource(
                URL, new MultiUseUserCredentials("root", "password"));
    }
}
