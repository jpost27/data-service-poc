package com.jp.dataservice.framework;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Subgraph;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FetchTreeGenerator {

    private final PersistenceMapping persistenceMapping;

    public FetchNode createFetchTree(Class<?> rootEntity, Collection<String> fetchClauses) {
        FetchNode root = new FetchNode(rootEntity.getSimpleName(), rootEntity, new HashMap<>());
        for (String fetchClause : fetchClauses) {
            addFetchClause(root, fetchClause);
        }
        return root;
    }

    public void addFetchClause(FetchNode root, String fetchClause) {
        String[] nodeNames = fetchClause.split("\\.");
        FetchNode currentNode = null;
        for (String nodeName : nodeNames) {
            if (currentNode == null) {
                // Root node
                PersistenceMapping.EntityConnection entityConnection = getEntityConnection(root.entityClass(), nodeName)
                        .orElseThrow(() -> new IllegalArgumentException("No such field: " + nodeName));
                currentNode = root.children()
                        .getOrDefault(
                                nodeName, new FetchNode(nodeName, entityConnection.targetEntity(), new HashMap<>()));
                root.children().put(nodeName, currentNode);
            } else if (currentNode.children().containsKey(nodeName)) {
                // Node has been created by another fetch clause
                currentNode = currentNode.children().get(nodeName);
            } else {
                // New path found in fetch clause
                PersistenceMapping.EntityConnection entityConnection = getEntityConnection(
                                currentNode.entityClass(), nodeName)
                        .orElseThrow(() -> new IllegalArgumentException("No such field: " + nodeName));
                currentNode
                        .children()
                        .put(nodeName, new FetchNode(nodeName, entityConnection.targetEntity(), new HashMap<>()));
                currentNode = currentNode.children().get(nodeName);
            }
        }
    }

    private Optional<PersistenceMapping.EntityConnection> getEntityConnection(Class<?> entityClass, String fieldName) {
        return Optional.of(persistenceMapping.getEntityRelationshipMap())
                .map(map -> map.get(entityClass))
                .map(map -> map.get(persistenceMapping.dtoFieldNameToEntityFieldName(fieldName)));
    }

    public <E> void applyToEntityGraph(FetchNode root, EntityGraph<E> entityGraph) {
        root.children().forEach((key, value) -> {
            boolean isBagFetch = relationshipIsBagFetch(root, value.name());
            if (!isBagFetch) {
                addSubgraph(entityGraph.addSubgraph(PersistenceMapping.queryStringToGraphPath(key)), value);
            }
        });
    }

    private <X> void addSubgraph(Subgraph<X> subgraph, FetchNode node) {
        for (Map.Entry<String, FetchNode> child : node.children().entrySet()) {
            boolean isBagFetch = relationshipIsBagFetch(node, child.getValue().name());
            if (!isBagFetch) {
                if (child.getValue().children().isEmpty()) {
                    subgraph.addAttributeNodes(PersistenceMapping.queryStringToGraphPath(child.getKey()));
                } else {
                    addSubgraph(
                            subgraph.addSubgraph(PersistenceMapping.queryStringToGraphPath(child.getKey())),
                            child.getValue());
                }
            }
        }
    }

    private boolean relationshipIsBagFetch(FetchNode parentNode, String childName) {
        // field to entityName
        return Optional.of(persistenceMapping.getEntityRelationshipMap())
                .map(map -> map.get(parentNode.entityClass()))
                .map(map -> map.get(persistenceMapping.dtoFieldNameToEntityFieldName(childName)))
                .map(entityConnection ->
                        Set.of(OneToMany.class, ManyToMany.class).contains(entityConnection.relationshipType()))
                .orElse(false);
    }

    public record FetchNode(String name, Class<?> entityClass, @NonNull Map<String, FetchNode> children) {}
}
