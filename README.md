# actress
## @Actor, transform your beans to actors
And that's it. Every method called on a bean annotated with @Actor will be executed in its proper thread. Concurrent access to the same bean will queue the method calls. Methods with return values will be executed using a Future on the same dedicated thread. All access to the state of the bean is serialized, making the bean itself thread-safe.

### Code sample
package org.actressframework.sample;

import javax.inject.Named;

    @Named @Actor
    public class WorksInItsOwnThread {
        public void nonBlocking() {
            // every bean is assigned its own thread, so calling this method externally
            // will directly return control to the calling object
        }
    
        public String blocking() {
            // this method will also be executed in the same thread as the one above,
            // however the caller will block until the end of calculation because of the return value
        }
    }

## Event-Driven Architecture with Actress
