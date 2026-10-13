package io.synapseproject.api.model.graph;

import io.synapseproject.api.model.PropertyValue;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record Node(
    String id, NodeType type, Map<String, PropertyValue> properties, List<Port> ports) {

  public Node {
    id = Identifiers.require(id, "id");
    Objects.requireNonNull(type, "type");
    Objects.requireNonNull(properties, "properties");
    Objects.requireNonNull(ports, "ports");
    properties = Map.copyOf(properties);
    ports = List.copyOf(ports);

    Set<String> portIds = new HashSet<>();
    for (Port port : ports) {
      if (!portIds.add(port.id())) {
        throw new IllegalArgumentException("duplicate port id: " + port.id());
      }
    }
  }
}
