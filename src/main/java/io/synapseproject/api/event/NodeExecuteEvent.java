package io.synapseproject.api.event;

import io.synapseproject.api.model.Identifiers;
import java.util.Objects;
import java.util.UUID;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record NodeExecuteEvent(UUID playerId, String graphId, String nodeId)
    implements SynapseEvent {

  public NodeExecuteEvent {
    Objects.requireNonNull(playerId, "playerId");
    graphId = Identifiers.require(graphId, "graphId");
    nodeId = Identifiers.require(nodeId, "nodeId");
  }
}
