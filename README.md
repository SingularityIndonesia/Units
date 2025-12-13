## Units
Is a collection of software components written in Kotlin, designed to exist without any runtime context beyond Kotlin itself.

This project contains components that are invariant by design: _they are not bound to any platform or environmental assumptions_. This invariance is not achieved through multiplatform abstractions, but through components that are intentionally defined without requiring platform context in the first place.

Units is intended to serve as fundamental building blocks—code that can be composed, reused, and embedded across different environments without conceptual adaptation. The primary focus is on clear contracts, semantic stability, and freedom from implicit external dependencies.

Through this approach, Units aims to provide a reliable foundation for larger systems while avoiding context leakage and hidden coupling at the core level.