package io.synapseproject.api.model.player;

import io.synapseproject.api.model.PropertyValue;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record VariableContainer(
    UUID playerId,
    Map<String, PropertyValue> player,
    Map<String, PropertyValue> session,
    Map<String, PropertyValue> global) {

  public VariableContainer {
    Objects.requireNonNull(playerId, "playerId");
    Objects.requireNonNull(player, "player");
    Objects.requireNonNull(session, "session");
    Objects.requireNonNull(global, "global");
    player = Map.copyOf(player);
    session = Map.copyOf(session);
    global = Map.copyOf(global);
    rejectBlankKeys(player, "player");
    rejectBlankKeys(session, "session");
    rejectBlankKeys(global, "global");
  }

  private static void rejectBlankKeys(Map<String, PropertyValue> values, String name) {
    for (String key : values.keySet()) {
      if (key.isBlank()) {
        throw new IllegalArgumentException(name + " key must not be blank");
      }
    }
  }
}
