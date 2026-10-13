package io.synapseproject.api.model.graph;

import io.synapseproject.api.model.Identifiers;
import java.util.Objects;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record Port(String id, PortDirection direction) {

  public Port {
    id = Identifiers.require(id, "id");
    Objects.requireNonNull(direction, "direction");
  }
}
