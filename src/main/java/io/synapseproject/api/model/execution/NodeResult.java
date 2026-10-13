package io.synapseproject.api.model.execution;

import io.synapseproject.api.model.Identifiers;
import java.util.Objects;
import org.jspecify.annotations.NullMarked;

@NullMarked
public sealed interface NodeResult
    permits NodeResult.Success, NodeResult.Failed, NodeResult.Pending, NodeResult.Branch {

  record Success() implements NodeResult {}

  record Failed(String reason) implements NodeResult {
    public Failed {
      Objects.requireNonNull(reason, "reason");
    }
  }

  record Pending() implements NodeResult {}

  record Branch(String portId) implements NodeResult {
    public Branch {
      portId = Identifiers.require(portId, "portId");
    }
  }
}
