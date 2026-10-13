package io.synapseproject.api;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.synapseproject.api.event.EventManager;
import io.synapseproject.api.model.graph.GraphManager;
import io.synapseproject.api.model.player.PlayerStateManager;
import io.synapseproject.api.model.registry.RegistryManager;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class SynapseProviderTest {

  @AfterEach
  void reset() {
    SynapseProvider.unregister();
  }

  @Test
  void getFailsWhenUnregistered() {
    assertThrows(IllegalStateException.class, SynapseAPI::get);
  }

  @Test
  void registerReturnsTheSameInstance() {
    SynapseAPI api = new Stub();
    SynapseProvider.register(api);
    assertSame(api, SynapseAPI.get());
  }

  @Test
  void secondRegisterFails() {
    SynapseProvider.register(new Stub());
    assertThrows(IllegalStateException.class, () -> SynapseProvider.register(new Stub()));
  }

  @NullMarked
  private static final class Stub implements SynapseAPI {

    @Override
    public GraphManager getGraphManager() {
      throw new UnsupportedOperationException();
    }

    @Override
    public PlayerStateManager getPlayerStateManager() {
      throw new UnsupportedOperationException();
    }

    @Override
    public RegistryManager getRegistryManager() {
      throw new UnsupportedOperationException();
    }

    @Override
    public EventManager getEventManager() {
      throw new UnsupportedOperationException();
    }
  }
}
