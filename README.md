## Units

Units is a collection of software components written in Kotlin, designed to exist without any runtime context beyond Kotlin itself.

This project contains components that are invariant by design: they are not bound to any platform or environmental assumptions. This invariance is not achieved through multiplatform abstractions, but through components that are intentionally defined without requiring platform context in the first place.

Units is not the core of a system, nor does it attempt to define system-wide architecture or behavior. Instead, it is a collection of independent code units and components that remain free from environmental and runtime context, and can be adopted or ignored without structural consequence.

## Rationale

In many systems, components gradually accumulate implicit context: domain assumptions, execution environment expectations, or behavioral meaning that is not part of their explicit contract. Over time, this leads to context leakage, where a unit can no longer be reasoned about or reused without carrying hidden assumptions.

Units exists to counter this pattern by enforcing a clear separation between a unit and the context that gives it meaning.

A unit is intended to be context-invariant: its definition remains stable and complete on its own. When a unit needs to operate within a specific domain, its meaning should emerge through context wrapping, not through implicit definitions embedded inside the unit itself.

Context wrapping produces what can be described as contextual variants: interpretations or usages of the same invariant unit under different surrounding contexts. These variants do not modify the unit; instead, they leverage the unit by enclosing it with domain-specific rules, constraints, or semantics.

By treating context as something that surrounds a unit—rather than something encoded within it—systems preserve:
•	semantic clarity,
•	explicit domain boundaries,
•	and the ability to reuse and reason about units independently.

For this reason, context leveraging and wrapping is considered a preferred practice within Units. It ensures that domain meaning remains visible, deliberate, and external, while the units themselves remain stable, composable, and free from hidden coupling.