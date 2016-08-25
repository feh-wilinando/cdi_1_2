package br.com.alura.alura_ioc.jsf.factory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@ApplicationScoped
public class JSFFactory {

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
