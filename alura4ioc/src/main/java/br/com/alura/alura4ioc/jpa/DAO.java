package br.com.alura.alura4ioc.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T, P> {

	private Class<T> classe;
	protected EntityManager manager;

	public DAO(){}
	
	@Inject
	public DAO(Class<T> classe, EntityManager manager) {
		this.classe = classe;
		this.manager = manager;		
	}

	public void adiciona(T t) {

		// abre transacao
		manager.getTransaction().begin();

		// persiste o objeto
		manager.persist(t);

		// commita a transacao
		manager.getTransaction().commit();

	}

	public void remove(T t) {
		manager.getTransaction().begin();

		manager.remove(manager.merge(t));

		manager.getTransaction().commit();
	}

	public void atualiza(T t) {
		manager.getTransaction().begin();

		manager.merge(t);

		manager.getTransaction().commit();
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).getResultList();

		return lista;
	}

	public T buscaPorId(P id) {
		T instancia = manager.find(classe, id);
		return instancia;
	}

	public int contaTodos() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		query.select(builder.count(query.from(classe)));
				
		long result = (Long) manager.createQuery(query).getSingleResult();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		return lista;
	}
	
	@PostConstruct
	private void load(){
		System.out.println("=============================> Construindo DAO");
	}

}
