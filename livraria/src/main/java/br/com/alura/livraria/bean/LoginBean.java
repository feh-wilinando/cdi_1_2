package br.com.alura.livraria.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.alura4ioc.jsf.helper.MessageHelper;
import br.com.alura.livraria.dao.UsuarioDao;
import br.com.alura.livraria.modelo.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesContext context;
	
	private Usuario usuario = new Usuario();

	@Inject
	private MessageHelper messageHelper;

	@Inject
	private UsuarioDao usuarioDao;

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {		
		System.out.println("fazendo login do usuario " + this.usuario.getEmail());
		
		
		boolean existe = usuarioDao.existe(this.usuario);
		if(existe ) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		
		messageHelper.onFlash().addMessage(new FacesMessage("Usuário não encontrado"));
		
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
	
}
