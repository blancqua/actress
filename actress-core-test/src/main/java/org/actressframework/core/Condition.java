package org.actressframework.core;


public abstract class Condition {

	public abstract boolean validate();
	
	public RuntimeException exceptionToThrowAfterTimeout() {
		return new TimeoutException();
	}
	
}
