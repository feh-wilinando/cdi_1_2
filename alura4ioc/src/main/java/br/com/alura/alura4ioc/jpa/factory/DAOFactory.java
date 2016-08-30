package br.com.alura.alura4ioc.jpa.factory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.alura.alura4ioc.jpa.Crud;
import br.com.alura.alura4ioc.jpa.CrudDefaultImplementation;

@SuppressWarnings("unchecked")
public class DAOFactory implements Serializable {

	private static final long serialVersionUID = -7774649187591993030L;
	
	@Inject
	private EntityManager manager;
	
	@Produces
	public <T,P> Crud<T,P> getDao(InjectionPoint point){
		
		ParameterizedType type = (ParameterizedType) point.getType();
		 Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		 return new CrudDefaultImplementation<>(classe, manager);
		
	}
	
}
