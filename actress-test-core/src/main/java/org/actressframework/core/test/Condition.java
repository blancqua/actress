package org.actressframework.core.test;


public abstract class Condition {

	public abstract boolean validate();
	
	public RuntimeException exceptionToThrowAfterTimeout() {
		return new TimeoutException();
	}
	
}
