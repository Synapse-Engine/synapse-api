package io.synapseproject.api.event;

import io.synapseproject.api.model.Identifiers;
import java.util.Objects;
import java.util.UUID;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record GraphTraverseEvent(UUID playerId, String graphId, String fromNodeId, String toNodeId)
    implements SynapseEvent {

  public GraphTraverseEvent {
    Objects.requireNonNull(playerId, "playerId");
    graphId = Identifiers.require(graphId, "graphId");
    fromNodeId = Identifiers.require(fromNodeId, "fromNodeId");
    toNodeId = Identifiers.require(toNodeId, "toNodeId");
  }
}
