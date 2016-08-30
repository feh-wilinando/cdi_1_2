package br.com.alura.alura4ioc.jpa.factory;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAFactory implements Serializable {

	private static final long serialVersionUID = -6118766022119863783L;

	private EntityManagerFactory emf;

	@Inject
	private Properties configuration;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void close(@Disposes EntityManager em) {

		if (em.isOpen()) {
			em.close();
		}
	}

	@PreDestroy
	private void preDestroy() {
		if (emf.isOpen()) {
			emf.close();
		}
	}

	@PostConstruct
	private void postConstruct() {
		String persistenceUnit = configuration.getProperty("alura4ioc.persistenceUnit");

		emf = Persistence.createEntityManagerFactory(persistenceUnit);
	}

}
