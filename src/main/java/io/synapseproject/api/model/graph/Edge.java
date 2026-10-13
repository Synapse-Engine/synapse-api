package io.synapseproject.api.model.graph;

import io.synapseproject.api.model.Identifiers;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record Edge(
    String id, String fromNodeId, String fromPortId, String toNodeId, String toPortId) {

  public Edge {
    id = Identifiers.require(id, "id");
    fromNodeId = Identifiers.require(fromNodeId, "fromNodeId");
    fromPortId = Identifiers.require(fromPortId, "fromPortId");
    toNodeId = Identifiers.require(toNodeId, "toNodeId");
    toPortId = Identifiers.require(toPortId, "toPortId");
  }
}
