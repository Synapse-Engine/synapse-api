package io.synapseproject.api.event;

import java.util.function.Consumer;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface EventManager {

  <T extends SynapseEvent> AutoCloseable subscribe(Class<T> type, Consumer<T> listener);
}
