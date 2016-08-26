package br.com.alura.alura4ioc.jsf.helper;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;


public class MessageHelper implements Serializable{

	

	private static final long serialVersionUID = -1047241173366399699L;

	@Inject
	private FacesContext context;
	
	@Inject
	private Flash flash;
	
	public MessageHelper onFlash(){
	
		flash.setKeepMessages(true);
		
		return this;
	}
	
	
	public void addMessage(FacesMessage message){
		this.addMessage(null, message);
	}


	public void addMessage(String clientId, FacesMessage message) {
		context.addMessage(clientId, message);
	}
	
	
}
