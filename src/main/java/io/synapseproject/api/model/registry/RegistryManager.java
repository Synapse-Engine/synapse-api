package io.synapseproject.api.model.registry;

import io.synapseproject.api.model.execution.CustomActionHandler;
import io.synapseproject.api.model.execution.CustomConditionHandler;
import java.util.concurrent.CompletableFuture;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface RegistryManager {

  CompletableFuture<Void> registerAction(String key, CustomActionHandler handler);

  CompletableFuture<Void> unregisterAction(String key);

  CompletableFuture<Void> registerCondition(String key, CustomConditionHandler handler);

  CompletableFuture<Void> unregisterCondition(String key);
}
