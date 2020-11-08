package com.akondi.usecases.gausssolve;

import java.util.concurrent.atomic.AtomicLong;

//@Component
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class IdGenerator {
	private AtomicLong nextId = new AtomicLong(1);
	
	public long getNextId() {
		return nextId.getAndIncrement();
	}
}
