package org.lsi.metier;

public interface OperationMetier {
	
	public boolean verser(String code,double montant, Long codeEmp);
	public boolean retirer(String code,double montant, Long codeEmp);
	public boolean virement(String cpte1,String cpte2,double solde, Long codeEmp);
		public PageOperation getOperation(String codeCompte, int page, int size);


}
