package br.com.alura.alura4ioc.jpa;

import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

public class ManagerTransactionDefaultImplementation implements ManagerTransaction{

	private static final long serialVersionUID = 1597099564680095780L;
	
	@Inject
	protected EntityManager manager;
	
	@Override
	public Object doTransactioned(InvocationContext context) {
		manager.getTransaction().begin();
		Object result;

		try {
			 result = context.proceed();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new RuntimeException(e);
		}
		
		manager.getTransaction().commit();
		return result;
				
	}

}
