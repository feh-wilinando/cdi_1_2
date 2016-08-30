package br.com.alura.alura4ioc.jsf.phaselistener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class GenericPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -6610943043674874279L;

	@Override
	public void afterPhase(PhaseEvent event) {
		new ObserverPhaseListener(event).after().onPhase(event.getPhaseId()).fire();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		new ObserverPhaseListener(event).before().onPhase(event.getPhaseId()).fire();
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
