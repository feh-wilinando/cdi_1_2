package br.com.alura.alura4ioc.jsf.phaselistener;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import br.com.alura.alura4ioc.jsf.phaselistener.annotation.After;
import br.com.alura.alura4ioc.jsf.phaselistener.annotation.Before;
import br.com.alura.alura4ioc.jsf.phaselistener.annotation.PhaseAnnotationLiteral;

@SuppressWarnings("serial")
@Vetoed
public final class ObserverPhaseListener {

	private BeanManager beanManager = CDI.current().getBeanManager();
	private Annotation phase;
	private Annotation moment;
	private PhaseEvent event;
	
	public ObserverPhaseListener(PhaseEvent event) {
		this.event = event;	
	}

	public ObserverPhaseListener after() {
		moment = new AnnotationLiteral<After>() {};
		return this;
	}

	public ObserverPhaseListener before() {
		moment = new AnnotationLiteral<Before>() {};
		return this;
	}

	public void fire() {
		beanManager.fireEvent(event, moment, phase);
	}

	public ObserverPhaseListener onPhase(PhaseId phaseId) {
		phase = new PhaseAnnotationLiteral(phaseId);
		return this;
	}

}
