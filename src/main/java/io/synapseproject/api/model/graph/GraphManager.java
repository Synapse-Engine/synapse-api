package io.synapseproject.api.model.graph;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface GraphManager {

  CompletableFuture<Optional<Graph>> findGraph(String id);

  CompletableFuture<List<Graph>> loadedGraphs();

  CompletableFuture<Void> reload(String id);
}
