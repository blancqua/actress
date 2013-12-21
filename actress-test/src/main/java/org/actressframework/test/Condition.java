package org.actressframework.test;


public abstract class Condition {

	public abstract boolean validate();
	
	public RuntimeException exceptionToThrowAfterTimeout() {
		return new TimeoutException();
	}
	
}
