package io.synapseproject.api.model.player;

import io.synapseproject.api.model.PropertyValue;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface PlayerStateManager {

  CompletableFuture<VariableContainer> getVariables(UUID playerId);

  CompletableFuture<Void> setVariable(
      UUID playerId, VariableScope scope, String key, PropertyValue value);

  CompletableFuture<Boolean> isNodeCompleted(UUID playerId, String graphId, String nodeId);
}
