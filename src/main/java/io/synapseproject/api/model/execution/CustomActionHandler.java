package io.synapseproject.api.model.execution;

import java.util.concurrent.CompletableFuture;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface CustomActionHandler {

  CompletableFuture<NodeResult> handle(ExecutionContext context);
}
