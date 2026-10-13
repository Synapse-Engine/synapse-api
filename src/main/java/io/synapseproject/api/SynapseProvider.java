package io.synapseproject.api;

import java.util.Objects;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
final class SynapseProvider {

  private static volatile @Nullable SynapseAPI instance;

  private SynapseProvider() {}

  static SynapseAPI get() {
    if (instance == null) {
      throw new IllegalStateException("SynapseAPI is not loaded");
    }
    return Objects.requireNonNull(instance);
  }

  static void register(SynapseAPI api) {
    Objects.requireNonNull(api, "api");
    synchronized (SynapseProvider.class) {
      if (instance != null) {
        throw new IllegalStateException("SynapseAPI is already registered");
      }
      instance = api;
    }
  }

  static void unregister() {
    synchronized (SynapseProvider.class) {
      instance = null;
    }
  }
}
