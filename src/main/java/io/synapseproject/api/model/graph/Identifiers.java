package io.synapseproject.api.model.graph;

import java.util.Objects;
import org.jspecify.annotations.NullMarked;

@NullMarked
final class Identifiers {

  private Identifiers() {}

  static String require(String value, String name) {
    Objects.requireNonNull(value, name);
    if (value.isBlank()) {
      throw new IllegalArgumentException(name + " must not be blank");
    }
    return value;
  }
}
