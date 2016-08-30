package br.com.alura.alura4ioc.jsf.phaselistener.annotation;

import javax.enterprise.util.AnnotationLiteral;
import javax.faces.event.PhaseId;

public class PhaseAnnotationLiteral extends AnnotationLiteral<Phase> implements Phase{

	private static final long serialVersionUID = 7728519119836355739L;
	private String phaseName;

	public PhaseAnnotationLiteral(PhaseId phaseId) {
		phaseName = phaseId.getName();
	}
	
	@Override
	public String value() {
		return phaseName;
	}

	
	
	
}
