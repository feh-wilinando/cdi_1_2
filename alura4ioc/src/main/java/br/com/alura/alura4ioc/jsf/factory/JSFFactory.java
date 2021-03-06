package br.com.alura.alura4ioc.jsf.factory;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@ApplicationScoped
public class JSFFactory implements Serializable{

	private static final long serialVersionUID = 8166471889205441293L;

	@Produces
	@RequestScoped
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	@Produces
	@RequestScoped
	public Flash getFlash(){
		return getExternalContext().getFlash();
	}
	
	private ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}
	
}
