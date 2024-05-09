package com.jp.codegen.generator;

import java.io.File;
import schemacrawler.schema.Table;

public interface SqlTableFileGenerator {

    /**
     * Generate a file for the given table
     * @param table
     * @param outputDirectory
     */
    void generate(Table table, File outputDirectory);
}
