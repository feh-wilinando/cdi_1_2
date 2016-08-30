package br.com.alura.alura4ioc.jpa;

import java.io.Serializable;

import javax.interceptor.InvocationContext;

public interface ManagerTransaction extends Serializable {

	Object doTransactioned(InvocationContext context); 
	
}
