package io.synapseproject.api;

import io.synapseproject.api.event.EventManager;
import io.synapseproject.api.model.graph.GraphManager;
import io.synapseproject.api.model.player.PlayerStateManager;
import io.synapseproject.api.model.registry.RegistryManager;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface SynapseAPI {

  GraphManager getGraphManager();

  PlayerStateManager getPlayerStateManager();

  RegistryManager getRegistryManager();

  EventManager getEventManager();

  static SynapseAPI get() {
    return SynapseProvider.get();
  }
}
