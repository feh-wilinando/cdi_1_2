package br.com.alura.livraria.util;

import javax.enterprise.event.Observes;
import javax.faces.event.PhaseEvent;

import br.com.alura.alura4ioc.jsf.phaselistener.annotation.Before;
import br.com.alura.alura4ioc.jsf.phaselistener.annotation.Phase;

public class LogPhaseListener{
	
	public void observar(@Observes @Before @Phase("INVOKE_APPLICATION") PhaseEvent event){
		System.out.println("FASE: " + event.getPhaseId());
	}
	

}
