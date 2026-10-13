package io.synapseproject.api.model.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.synapseproject.api.model.PropertyValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class GraphTest {

  @Test
  void graphIgnoresLaterChanges() {
    Node node = node("start");
    Map<String, Node> nodes = new HashMap<>();
    nodes.put("start", node);
    Map<String, PropertyValue> metadata = new HashMap<>();
    metadata.put("title", new PropertyValue.Text("Quest"));
    Graph graph = new Graph("quest", 1, nodes, List.of(), metadata);

    nodes.put("end", node("end"));
    metadata.put("author", new PropertyValue.Text("ada"));

    assertEquals(node, graph.getNodeOrThrow("start"));
    assertFalse(graph.nodes().containsKey("end"));
    assertFalse(graph.metadata().containsKey("author"));
  }

  @Test
  @SuppressWarnings("DataFlowIssue")
  void nullIdsAndElementsFail() {
    Map<String, Node> nodes = new HashMap<>();
    nodes.put("start", null);

    assertThrows(
        NullPointerException.class, () -> new Graph(null, 1, Map.of(), List.of(), Map.of()));
    assertThrows(
        IllegalArgumentException.class, () -> new Graph(" ", 1, Map.of(), List.of(), Map.of()));
    assertThrows(
        NullPointerException.class, () -> new Node(null, NodeType.ACTION, Map.of(), List.of()));
    assertThrows(
        IllegalArgumentException.class, () -> new Node(" ", NodeType.ACTION, Map.of(), List.of()));
    assertThrows(
        NullPointerException.class, () -> new Graph("quest", 1, nodes, List.of(), Map.of()));
  }

  @Test
  void getNodeOrThrowReturnsTheNodeOrFails() {
    Node node = node("start");
    Graph graph = new Graph("quest", 1, Map.of("start", node), List.of(), Map.of());

    assertEquals(node, graph.getNodeOrThrow("start"));
    assertThrows(IllegalArgumentException.class, () -> graph.getNodeOrThrow("missing"));
  }

  private static Node node(String id) {
    return new Node(id, NodeType.ACTION, Map.of(), List.of());
  }
}
