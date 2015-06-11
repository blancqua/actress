# actress
## @Actor, transform your beans to actors
And that's it. Every method called on a bean annotated with @Actor will be executed in its proper thread. Concurrent access to the same bean will queue the method calls. Methods with return values will be execute using a Future on the same dedicated thread. All access to the state of the bean is serialized, making the bean itself thread-safe.

### Code sample

## Event-Driving Architecture with Actress
