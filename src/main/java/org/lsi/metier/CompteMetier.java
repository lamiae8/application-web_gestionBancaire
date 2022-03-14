package org.lsi.metier;

import java.util.List;

import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.lsi.entities.Operation;
import org.springframework.data.domain.Page;

public interface CompteMetier {
	
	public Compte saveCompte(Compte cp);
	public Compte getCompte(String code);
	public Page<Operation> listOperation(String codeCompte,int nbrePage,int nbreCompte);
	
	public  List<Compte> listCompte();
		
	
	
	public  List<Compte> listCompte(Client C);
}
