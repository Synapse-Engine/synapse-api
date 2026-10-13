package io.synapseproject.api.model;

import java.util.Objects;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class Identifiers {

  private Identifiers() {}

  public static String require(String value, String name) {
    Objects.requireNonNull(value, name);
    if (value.isBlank()) {
      throw new IllegalArgumentException(name + " must not be blank");
    }
    return value;
  }
}
