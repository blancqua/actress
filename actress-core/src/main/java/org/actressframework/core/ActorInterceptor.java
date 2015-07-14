package org.actressframework.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.slf4j.LoggerFactory.getLogger;

@Aspect
public class ActorInterceptor {

    private static boolean actorsEnabled = true;

    private ThreadProvider threadProvider = ThreadProvider.instance();

    @Around("execution(!private * (@org.actressframework.core.Actor *).*(..))")
    public Object actorMethodCall(final ProceedingJoinPoint joinPoint) throws Throwable {
        if (doInActorThread(joinPoint)) {
            if (hasNoReturnValue(joinPoint)) {
                return callNonBlocking(joinPoint);
            } else {
                return callBlocking(joinPoint);
            }
        } else {
            return callInCurrentThread(joinPoint);
        }
    }
    
    private boolean doInActorThread(ProceedingJoinPoint joinPoint) {
        return actorsEnabled && isMethodCall(joinPoint) && !alreadyInActorThread(joinPoint);
    }

    private boolean isMethodCall(ProceedingJoinPoint joinPoint) {
        return joinPoint.getTarget() != null && joinPoint.getSignature() instanceof MethodSignature;
    }

    private boolean alreadyInActorThread(ProceedingJoinPoint joinPoint) {
        return threadProvider.inActorThread(joinPoint.getTarget());
    }
    
    private boolean hasNoReturnValue(final ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getReturnType().equals(void.class);
    }
    
    private Object callNonBlocking(final ProceedingJoinPoint joinPoint) {
        threadProvider.provide(joinPoint.getTarget()).execute(new Runnable() {
            @Override
            public void run() {
                doActualCall(joinPoint);
            }
        });
        return null;
    }
    
    private Object callBlocking(final ProceedingJoinPoint joinPoint) throws InterruptedException, ExecutionException {
        Future<Object> future = threadProvider.provide(joinPoint.getTarget()).submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return doActualCall(joinPoint);
            }
        });
        return future.get();
    }

    private Object callInCurrentThread(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    private Object doActualCall(final ProceedingJoinPoint joinPoint) {
        try {
            return callInCurrentThread(joinPoint);
        } catch (Throwable e) {
            getLogger(joinPoint.getTarget().getClass()).error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static void enableActors() {
        actorsEnabled = true;
    }
    
    public static void disableActors() {
        actorsEnabled = false;
    }

}
