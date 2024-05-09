package com.jp.codegen;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jp.codegen.generator.SqlTableFileGenerator;
import com.jp.codegen.generator.TypeScriptInterfaceGenerator;
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
    private static final Set<SqlTableFileGenerator> generators = Set.of(
            // new JPAEntityGenerator(),
            // new JavaDTOGenerator(),
            new TypeScriptInterfaceGenerator());

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

        for (final Schema schema : catalog.getSchemas()) {
            if (!schema.toString().equals("sport_data")) {
                continue;
            }
            System.out.println(schema);
            for (final Table table : catalog.getTables(schema)) {
                System.out.println("o--> " + table);
                table.getReferencedTables()
                        .forEach(referencedTable -> System.out.println("Referenced -> " + referencedTable));
                table.getDependentTables()
                        .forEach(referencedTable -> System.out.println("Dependent -> " + referencedTable));

                if (table instanceof View) {
                    System.out.println(" (VIEW)");
                } else {
                    System.out.println();
                }

                for (final Column column : table.getColumns()) {
                    System.out.printf("     o--> %s (%s)%n", column, column.getType());
                }
            }
        }

        //        Connection connection = null;
        //        try {
        //            // below two lines are used for connectivity.
        //            Class.forName("com.mysql.cj.jdbc.Driver");
        //            connection = DriverManager.getConnection(URL, "root", "password");
        //
        //            SQLMetadataRepo sqlMetadataRepo = new SQLMetadataRepo(connection);
        //            List<ColumnMetadata> columnMetadata = sqlMetadataRepo.getColumnMetadata();
        //            List<ForeignKeyMetadata> foreignKeyMetadata = sqlMetadataRepo.getForeignKeyMetadata();
        //            Map<String, SqlTable> tableMap = new HashMap<>();
        //            for (ColumnMetadata column : columnMetadata) {
        //                String tableName = column.getTableName();
        //                SqlTable sqlTable = tableMap.get(tableName);
        //                if (sqlTable == null) {
        //                    sqlTable = new SqlTable(tableName, tableMap);
        //                    tableMap.put(tableName, sqlTable);
        //                }
        //                List<ColumnMetadata> columnMetadataList = sqlTable.getColumnMetadataList();
        //                columnMetadataList.add(column);
        //            }
        //            for (ForeignKeyMetadata foreignKey : foreignKeyMetadata) {
        //                String tableName = foreignKey.getReferencingTableName();
        //                String referencedTableName = foreignKey.getReferencedTableName();
        //                SqlTable sqlTable = tableMap.get(tableName);
        //                SqlTable referencedTable = tableMap.get(referencedTableName);
        //                if (sqlTable == null) {
        //                    sqlTable = new SqlTable(tableName, tableMap);
        //                    tableMap.put(tableName, sqlTable);
        //                }
        //                if (referencedTable == null) {
        //                    referencedTable = new SqlTable(referencedTableName, tableMap);
        //                    tableMap.put(referencedTableName, referencedTable);
        //                }
        //                sqlTable.getForeignKeyMetadataSet().add(foreignKey);
        //                referencedTable.getReferecesSet().add(foreignKey);
        //            }
        //            connection.close();
        //            for (SqlTable sqlTable : tableMap.values()) {
        //                System.out.println(sqlTable.getTableName());
        //                System.out.println(sqlTable.getColumnDefinitions());
        //                generators.forEach(generator -> generator.generate(sqlTable, new File("output")));
        //            }
        //        } catch (Exception exception) {
        //            exception.printStackTrace();
        //        }
    }

    private static DatabaseConnectionSource getDataSource() {
        return DatabaseConnectionSources.newDatabaseConnectionSource(
                URL, new MultiUseUserCredentials("root", "password"));
    }
}
