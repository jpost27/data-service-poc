package com.jp.codegen.generator;

import com.jp.codegen.model.SqlTable;
import java.io.File;

public interface SqlTableFileGenerator {

    /**
     * Generate a file for the given table
     * @param table
     * @param outputDirectory
     */
    void generate(SqlTable table, File outputDirectory);
}
