package br.com.alura.alura4ioc.interceptor;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.alura.alura4ioc.interceptor.annotation.CicloDeVida;

@Interceptor @CicloDeVida
public class CicloDeVidaInterceptor implements Serializable {

	
	private static final long serialVersionUID = 5011776627679663852L;

	@AroundConstruct
	public Object construtor(InvocationContext context) throws Exception{
	
		System.out.println("ANTES: Construtor");
		
		Object result = context.proceed();
		
		System.out.println("DEPOIS: Construtor");
		
		return result ;
		
	}
	
	
	@AroundInvoke
	public Object metodo(InvocationContext context) throws Exception{
	
		System.out.println("ANTES: método - " + context.getMethod().getName());
		
		Object result = context.proceed();
		
		System.out.println("DEPOIS: método - " + context.getMethod().getName());
		
		return result ;
	}
	
	
	@PostConstruct
	public void postConstruct(InvocationContext context) throws Exception{
		System.out.println("ANTES: postConstruct");
		
		context.proceed();
		
		System.out.println("DEPOIS: postConstruct");
		
	}
	
	@PreDestroy
	public void preDestroy(InvocationContext context) throws Exception{
		System.out.println("ANTES: preDestroy");
		
		context.proceed();
		
		System.out.println("DEPOIS: preDestroy");
	}
	
}

