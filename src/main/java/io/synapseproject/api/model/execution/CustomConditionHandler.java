package io.synapseproject.api.model.execution;

import java.util.concurrent.CompletableFuture;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface CustomConditionHandler {

  CompletableFuture<NodeResult> handle(ExecutionContext context);
}
