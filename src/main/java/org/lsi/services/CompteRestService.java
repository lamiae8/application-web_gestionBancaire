package org.lsi.services;

import java.util.Date;

import org.lsi.dao.ClientRepository;
import org.lsi.dao.CompteRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.lsi.entities.CompteCourant;
import org.lsi.entities.CompteEpargne;
import org.lsi.entities.Operation;
import org.lsi.metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import antlr.collections.List;

@Controller
public class CompteRestService {
	
	@Autowired
	private CompteMetier compteMetier;
	@Autowired
	private ClientRepository clientRepository;

	
	@GetMapping(value="/listeComptes")
	public String consulterCompte(Model model) {
		
		model.addAttribute("listComptes", compteMetier.listCompte());
	
		return "listeComptes";
	}

	
	
	

	
		@GetMapping(value="/consulterCompte")
		public String getCompte(Model m, String codeCompte) {
			
			m.addAttribute("codeCompte", codeCompte);
			try {
				Compte c=compteMetier.getCompte(codeCompte);
				try {
					Page <Operation> pageOps = compteMetier.listOperation(codeCompte, 0,c.getOperations().size());
				
					m.addAttribute("PagesOperations", pageOps.getContent());
				}
				catch(Exception e) {
					System.out.println("FAIL");
					e.printStackTrace();
				}
				m.addAttribute("getComptes", c);	
			}
			catch(Exception e) {
				m.addAttribute("exception", e);
				e.printStackTrace();
			}
			return "comptes";
		}
		
		
		//compte courant
		@GetMapping(value="/ajouterCompteC")
		public String ajouterPageC(Model model) {
			model.addAttribute("compteC", new CompteCourant());
			return "ajouterCompte";
		}

        @PostMapping(value="/saveCompteCourant")	
		public Object CompteCourant(Model model,CompteCourant c) {
				// TODO Auto-generated method stub
			compteMetier.saveCompte(c);
				return "redirect:/comptes";
			
			}

        
        //compte epargne
		@GetMapping(value="/ajouterCompteE")
		public String ajouterPageE(Model model) {
			model.addAttribute("compteE", new CompteEpargne());
			return "ajouterCompte";
		}
		
		
		
		@PostMapping(value="/saveCompteEpargne")	
		public Object CompteEpargnet(Model model,CompteEpargne c) {
				// TODO Auto-generated method stub
			compteMetier.saveCompte(c);
				return "redirect:/comptes";
			
			}
		
		
		
	@GetMapping(value="/comptesClient/{codeClient}")
	public String comptesClient(Model model,@PathVariable("codeClient") Long codeClient) {
		Client cl=clientRepository.findById(codeClient).get();
			

		model.addAttribute("sesComptes", cl.getComptes());
	
		return "comptesClient";
	}
	
	

	
	
	
	
	
	@GetMapping(value="/comptes")
	public String coo(Model m) {
		
		return "comptes";
	}
	
	
}
