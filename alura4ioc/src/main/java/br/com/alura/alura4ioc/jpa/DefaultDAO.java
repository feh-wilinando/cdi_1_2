package br.com.alura.alura4ioc.jpa;

import java.util.List;

import javax.enterprise.inject.Vetoed;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Vetoed
public class DefaultDAO<T, P> implements DAO<T,P> {

	private static final long serialVersionUID = 6485692981477733630L;
	
	private Class<T> classe;
	protected EntityManager manager;
		
	public DefaultDAO(Class<T> classe, EntityManager manager) {	
		this.classe = classe;
		this.manager = manager;		
	}

	
	@Override
	public void adiciona(T t) {

		// abre transacao
		manager.getTransaction().begin();

		// persiste o objeto
		manager.persist(t);

		// commita a transacao
		manager.getTransaction().commit();

	}

	@Override
	public void remove(T t) {
		manager.getTransaction().begin();

		manager.remove(manager.merge(t));

		manager.getTransaction().commit();
	}

	@Override
	public void atualiza(T t) {
		manager.getTransaction().begin();

		manager.merge(t);

		manager.getTransaction().commit();
	}

	@Override
	public List<T> listaTodos() {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).getResultList();

		return lista;
	}

	@Override
	public T buscaPorId(P id) {
		T instancia = manager.find(classe, id);
		return instancia;
	}

	@Override
	public long contaTodos() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		query.select(builder.count(query.from(classe)));
				
		long result = (Long) manager.createQuery(query).getSingleResult();

		return result;
	}

	@Override
	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		return lista;
	}
		
}
