package br.com.alura.alura4ioc.jpa.factory;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.alura4ioc.jpa.annotation.Query;

@SuppressWarnings("unchecked")
public class QueryFactory {

	
	@Inject
	private EntityManager manager;
	
	
	@Produces @Query("")
	public <T> TypedQuery<T> newEntity(InjectionPoint point){
		
		String jpql = point.getAnnotated().getAnnotation(Query.class).value();
		
		
		ParameterizedType type = (ParameterizedType) point.getType();
		
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
			
		return manager.createQuery(jpql, classe);
	}
	
	
}
