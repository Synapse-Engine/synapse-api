package io.synapseproject.api.model.execution;

import io.synapseproject.api.model.PropertyValue;
import io.synapseproject.api.model.player.VariableContainer;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record ExecutionContext(
    UUID playerId,
    String graphId,
    String nodeId,
    Map<String, PropertyValue> inputs,
    VariableContainer variables) {

  public ExecutionContext {
    Objects.requireNonNull(playerId, "playerId");
    graphId = requireText(graphId, "graphId");
    nodeId = requireText(nodeId, "nodeId");
    Objects.requireNonNull(inputs, "inputs");
    Objects.requireNonNull(variables, "variables");
    inputs = Map.copyOf(inputs);
    if (!playerId.equals(variables.playerId())) {
      throw new IllegalArgumentException("playerId does not match variables");
    }
  }

  private static String requireText(String value, String name) {
    Objects.requireNonNull(value, name);
    if (value.isBlank()) {
      throw new IllegalArgumentException(name + " must not be blank");
    }
    return value;
  }
}
