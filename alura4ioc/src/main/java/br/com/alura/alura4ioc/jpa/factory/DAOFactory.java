package br.com.alura.alura4ioc.jpa.factory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.alura.alura4ioc.jpa.DAO;
import br.com.alura.alura4ioc.jpa.DefaultDAO;

@SuppressWarnings("unchecked")
public class DAOFactory implements Serializable {

	private static final long serialVersionUID = -7774649187591993030L;
	
	@Inject
	private EntityManager manager;
	
	@Produces
	public <T,P> DAO<T,P> getDao(InjectionPoint point){
		
		ParameterizedType type = (ParameterizedType) point.getType();
		 Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		 return new DefaultDAO<>(classe, manager);
		
	}
	
}
