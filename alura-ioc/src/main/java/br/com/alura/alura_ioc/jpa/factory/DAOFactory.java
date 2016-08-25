package br.com.alura.alura_ioc.jpa.factory;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.alura.alura_ioc.jpa.DAO;

public class DAOFactory {

	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Produces
	public <T, P> DAO<T,P> factory(InjectionPoint point){
		
		ParameterizedType type = (ParameterizedType) point.getType();
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		
		return new DAO<>(classe, manager);
	}
	
}
