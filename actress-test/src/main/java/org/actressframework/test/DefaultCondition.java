package org.actressframework.test;



public class DefaultCondition extends Condition {
	
	private Runnable runnable;
	private RuntimeException throwable = new TimeoutException();
	
	public DefaultCondition(Runnable runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public boolean validate() {
		try {
			runnable.run();
			return true;
		} catch (Exception e) {
			throwable = new RuntimeException(e);
			return false;
		}
	}
	
	@Override
	public RuntimeException exceptionToThrowAfterTimeout() {
		return throwable;
	}

}
