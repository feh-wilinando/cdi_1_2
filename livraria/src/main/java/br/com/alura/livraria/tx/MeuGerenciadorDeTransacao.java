package br.com.alura.livraria.tx;

import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;

import br.com.alura.alura4ioc.jpa.ManagerTransactionDefaultImplementation;

@Specializes
public class MeuGerenciadorDeTransacao extends ManagerTransactionDefaultImplementation{


	private static final long serialVersionUID = 1147509534493876562L;
		
	@Inject
	private FacesContext faceContext;

	@Override
	public Object doTransactioned(InvocationContext context) {
		this.manager.getTransaction().begin();

		Object result;

		try {
			result = context.proceed();
			System.out.println(	getMessage(getUserName(), context.getTarget().getClass().getName(), context.getMethod().getName(), "Sucesso"));
		} catch (Exception e) {
			this.manager.getTransaction().rollback();
			System.out.println(	getMessage(getUserName(), context.getTarget().getClass().getName(), context.getMethod().getName(), "Erro"));

			throw new RuntimeException(e);
		}

		this.manager.getTransaction().commit();

		return result;
	}

	private String getMessage(String userName, String classe, String metodo, String prefixo){
		return "\n\n[" + userName + "] :: "+prefixo+ " ao executar " + metodo
				+ " da classe " + classe + "dentro de uma transação\n\n";
	}
	
	private String getUserName() {
		return this.faceContext.getExternalContext().getSessionMap().get("usuarioLogado").toString();
	}

}
