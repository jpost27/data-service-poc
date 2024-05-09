package com.jp.codegen.model;

import com.jp.codegen.model.enums.RelationshipType;

public record TableRelationship(
        String rootTableName,
        String rootColumnName,
        String targetTableName,
        String targetColumnName,
        RelationshipType relationshipType) {}
