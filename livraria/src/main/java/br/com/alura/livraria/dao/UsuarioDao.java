package br.com.alura.livraria.dao;

import javax.enterprise.inject.Specializes;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.alura.alura4ioc.jpa.DAO;
import br.com.alura.livraria.modelo.Usuario;

@Specializes
public class UsuarioDao extends DAO<Usuario, Integer> {

	public boolean existe(Usuario usuario) {
		
		TypedQuery<Usuario> query = manager.createQuery(
				  " select u from Usuario u "
				+ " where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		
		
		return true;
	}

}
