package br.com.alura.alura4ioc.jpa.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.alura.alura4ioc.jpa.ManagerTransaction;
import br.com.alura.alura4ioc.jpa.interceptor.annotation.Transacional;

@Interceptor 
@Transacional
public class ManagerTransactionInterceptor implements Serializable{

	private static final long serialVersionUID = -1851819298811561023L;
	
	@Inject
	private ManagerTransaction managerTransaction;
	
	
	@AroundInvoke
	public Object transacao(InvocationContext context) throws Exception{
		
		Object resultado = managerTransaction.doTransactioned(context);
		
		return resultado;
		
	}
	
	
}
