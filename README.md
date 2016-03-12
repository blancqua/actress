# actress [![Build Status](https://travis-ci.org/wblancqu/actress.svg?branch=master)](https://travis-ci.org/wblancqu/actress)
## @Actor, transform your beans to actors using AOP
And that's it. Every method called on a bean annotated with @Actor will be executed in its proper thread. Concurrent access to the same bean will queue the method calls. Methods with return values will be executed using a Future on the same dedicated thread. All access to the state of the bean is serialized, making the bean itself thread-safe.

### Code sample
```java
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
```

### How to use in your project
If you want to use @Actor in your project, simply add the relevant dependencies using your preferred dependency management system.

Example for Maven:
```xml
<dependency>
    <groupId>com.github.wblancqu</groupId>
    <artifactId>actress-core</artifactId>
    <version>1.2</version>
</dependency>
```

### Artifacts
* actress-core : contains the @Actor annotation and the ActorInterceptor, use this if your project uses the aspectj compiler
* actress-spring : provides the Java Spring configuration class ActressConfiguration, which you can use together with the Spring Aspect capabilities

## Event-Driven Architecture with Actress
