package io.synapseproject.api.event;

import org.jspecify.annotations.NullMarked;

@NullMarked
public sealed interface SynapseEvent
    permits GraphTraverseEvent, NodeExecuteEvent, QuestStateChangeEvent {}
