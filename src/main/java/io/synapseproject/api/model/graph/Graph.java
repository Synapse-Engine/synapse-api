package io.synapseproject.api.model.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record Graph(
    String id,
    int version,
    Map<String, Node> nodes,
    List<Edge> edges,
    Map<String, PropertyValue> metadata) {

  public Graph {
    id = Identifiers.require(id, "id");
    Objects.requireNonNull(nodes, "nodes");
    Objects.requireNonNull(edges, "edges");
    Objects.requireNonNull(metadata, "metadata");
    nodes = Map.copyOf(nodes);
    edges = List.copyOf(edges);
    metadata = Map.copyOf(metadata);

    for (Map.Entry<String, Node> entry : nodes.entrySet()) {
      if (!entry.getKey().equals(entry.getValue().id())) {
        throw new IllegalArgumentException(
            "node key '"
                + entry.getKey()
                + "' does not match node id '"
                + entry.getValue().id()
                + "'");
      }
    }

    Set<String> edgeIds = new HashSet<>();
    for (Edge edge : edges) {
      if (!edgeIds.add(edge.id())) {
        throw new IllegalArgumentException("duplicate edge id: " + edge.id());
      }
    }
  }

  public Node getNodeOrThrow(String nodeId) {
    Objects.requireNonNull(nodeId, "nodeId");
    Node node = nodes.get(nodeId);
    if (node == null) {
      throw new IllegalArgumentException("Node not found: " + nodeId);
    }
    return node;
  }
}
