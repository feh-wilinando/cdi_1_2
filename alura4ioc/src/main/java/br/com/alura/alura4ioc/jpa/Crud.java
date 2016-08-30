package br.com.alura.alura4ioc.jpa;

import java.io.Serializable;
import java.util.List;

public interface Crud<T,P> extends Serializable {

	void adiciona(T entity);
	void remove(T entity);
	void atualiza(T entity);
	List<T> listaTodos();
	T buscaPorId(P id);
	long contaTodos();
	List<T> listaTodosPaginada(int primeiro, int quantidadeNaPagina);
	
}
