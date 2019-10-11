
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bo.Agence;
import bo.Compte;
import bo.typeCompte.CompteEpargne;
import bo.typeCompte.ComptePayant;
import bo.typeCompte.CompteSimple;
import jdbc.dao.AgenceDAO;
import jdbc.dao.CompteDAO;
import jdbc.dao.IDAO;


public class App {
	
	private static Scanner sc = new Scanner( System.in );
	
	public static void dspMainMenu() {
		int mode = 0;
		boolean start=true;
		do {
			if(!start) {
				System.out.println("Ce mode n'existe pas merci de selectionner le mode parmis cette liste");
			}
			
			System.out.println( "======================================" );
			System.out.println( "========== MENU - PRINCIPALE =========" );
			System.out.println( "======================================" );
			System.out.println( "1 - Creer une nouvelle Agence" );
			System.out.println( "2 - Consulter une Agence" );
			System.out.println( "3 - Modifiez une Agence" );
			System.out.println( "4 - Supprimez une Agence" );
			System.out.println( "5 - Creer un nouveau Compte" );
			System.out.println( "6 - Consulter un Compte" );
			System.out.println( "7 - Modifiez un Compte" );
			System.out.println( "8 - Supprimez un Compte" );
			System.out.println( "9 - Les Opérations" );
			System.out.println( "10 - Quitter" );
			System.out.print( "Entrez votre choix : " );
			try {
				mode = sc.nextInt();
			} catch ( InputMismatchException e ) {
				mode = -1;
			} finally {
				sc.nextLine();
			}
		}while (mode<1 || mode>10);
		
		switch (mode) {
		case 1:
			createAgence();
			break;
		case 2:
			consultAgence();
			break;
		case 3:
			modifAgence();
			break;
		case 4:
			suppAgence();
			break;
		case 5:
			createCompte();
			break;
		case 6:
			consultCompte();
			break;
		case 7:
			modifCompte();
			break;
		case 8:
			suppCompte();
			break;
		case 9:
			operation();
			break;
		case 10:
			System.exit(0);
			break;
		}
	
	}
	
	private static void createAgence() {
		IDAO<Long, Agence> agenceCreated= new AgenceDAO();
		System.out.println( "======================================" );
		System.out.println( "======== CREATION D'UNE AGENCE =======" );
		System.out.println( "======================================" );
		Agence agence = new Agence();
		System.out.println( "Entrez le code de votre Agence : " );
		try {
		agence.setCode(sc.nextInt());
		}catch(InputMismatchException e) {
			System.out.println( "\n Le code doit être un nombre entier" );
		}
		System.out.println( "Entrez le nom de votre Agence : " );
		try {
		agence.setNomAgence(sc.next());
		}catch(InputMismatchException e) {
			System.out.println( "\n Le code doit être un nombre entier" );
		}
		
		System.out.println( "Entrez l'Adresse de votre Agence : " );
		try {
		agence.setAdresse(sc.next());
		}catch(InputMismatchException e){
			System.out.println("\n L'Adresse doit être un nombre entier" );	
		}
			try {
				agenceCreated.create( agence );
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		System.out.println( "Agence creer avec succes \n" );
		dspMainMenu();
	}
	private static void consultAgence() {
		IDAO<Long, Agence> listAgence=new AgenceDAO();;
		Agence agence=new Agence();
		System.out.println( "======================================" );
		System.out.println( "======= CONSULTATION DES AGENCES =====" );
		System.out.println( "======================================" );
		System.out.println("Voici la liste des agences :");
		try {
			for(Agence val : listAgence.findAll()) {
				System.out.println(val+"\n");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		int mode=0;
		System.out.println( "1 - Details d'une Agence" );
		System.out.println( "2 - Revenir au Menu" );
		if (mode==1) {
			System.out.print( "Entrez le code de votre Agence a Consulté : " );
			try {
				agence=listAgence.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			System.out.println("Voici la liste de ces Comptes :");
			for(Compte compte : agence.getCompte()) {
				compte.toString();
			}	
		}else {
			dspMainMenu();	
		}	
		
	}
	private static void modifAgence() {
		IDAO<Long, Agence> modifAgence=new AgenceDAO();
		IDAO<Long, Agence> exagence =new AgenceDAO();
		System.out.println( "======================================" );
		System.out.println( "====== MODIFICATION D'UNE AGENCE =====" );
		System.out.println( "======================================" );
		System.out.println("====== Choisir l'Agence a modifié ======");
		Agence agence = new Agence();
		System.out.print( "Entrez le code de votre Agence a modifié : " );
		try {
			exagence=(IDAO<Long, Agence>) modifAgence.findById(sc.nextLong());
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			System.out.println("Ce code n'existe pas ou est errone\n");
			e1.printStackTrace();
		}
		System.out.println("Rentrez le nouveau code : ");
		try {
		agence.setCode(sc.nextInt());
		}catch(InputMismatchException e) {
			System.out.println( "\n Le code doit être un nombre entier" );
		}
		System.out.println("Entrez le nouveau nom : ");
		try {
		agence.setNomAgence(sc.next());
		}catch(InputMismatchException e) {
			System.out.println( "\n Le nom doit être une chaine de caractere" );
		}
		System.out.print( "Entrez la nouvelle adresse : " );
		try {
		agence.setAdresse(sc.next());
		}catch(InputMismatchException e){
			System.out.println("\n L'Adresse doit être une chaine de caractere" );
		}
		try {
			exagence.update(agence);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		System.out.println( "L'Agence a été modifié avec succes" );
		
		dspMainMenu();	
	}
	private static void suppAgence() {
		IDAO<Long, Agence> supprAgence=new AgenceDAO();
		System.out.println( "======================================" );
		System.out.println( "====== SUPPRESSION DE  L'AGENCE ======" );
		System.out.println( "======================================" );
		System.out.println( "Entrez le code de votre Agence a Supprimé : " );
		Agence agence=new Agence();
		try {
			agence=supprAgence.findById(sc.nextLong());
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			System.out.println("Ce code n'existe pas ou est errone\n");
			e1.printStackTrace();
		}
		try {
			supprAgence.remove(agence);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("L'Agence a bien été supprimé");
		dspMainMenu();	
		
	}
	private static void createCompte() {
		int mode = 0;
		boolean start=true;
		do {
			if(!start) {
				System.out.println("Ce mode n'existe pas merci de selectionner le mode parmis cette liste");
			}
		System.out.println( "======================================" );
		System.out.println( "========= CREATION D'UN COMPTE =======" );
		System.out.println( "======================================" );
		System.out.println( "1 - Compte Simple" );
		System.out.println( "2 - Compte Epargne" );
		System.out.println( "3 - Compte Payant" );
		System.out.println("Choississez votre type de compte : ");
		try {
			mode = sc.nextInt();
		} catch ( InputMismatchException e ) {
			mode = -1;
		} finally {
			sc.nextLine();
		}
		}while (mode<1 || mode>3);
		
		IDAO<Long, Agence> listAgence=new AgenceDAO();
		switch(mode) {
		case 1:
			System.out.println( "======================================" );
			System.out.println( "============ COMPTE SIMPLE ===========" );
			System.out.println( "======================================" );
			Agence agence=new Agence();
			CompteSimple comptesimple = null;
			System.out.println("Voici la liste d'Agence : ");
			try {
				for(Agence val : listAgence.findAll()) {
					System.out.println(val+"\n");
				}
			}catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("Choisissez l'Agence par son code : ");
			try {
				agence=listAgence.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			System.out.println( "Entrez le solde du Compte : " );
			try {
				comptesimple.setSolde(sc.nextInt());
				}catch(InputMismatchException e) {
					System.out.println( "\n Le code doit être un nombre entier" );
				}
				System.out.println( "Entrez le découvert du Compte : " );
				try {
					comptesimple.setDecouvert(sc.nextDouble());
				}catch(InputMismatchException e){
					System.out.println("\n Votre découvert dois être un nombre" );	
				}
				agence.getCompte().add(comptesimple);
				System.out.println("Votre Compte Simple a bien été créer");
				comptesimple.setId_agence(agence.getId_agence());
				dspMainMenu();
			break;
		case 2:
			System.out.println( "======================================" );
			System.out.println( "============ COMPTE EPARGNE ==========" );
			System.out.println( "======================================" );
			Agence agence2=new Agence();
			CompteEpargne compteEpargn = null;
			System.out.println("Voici la liste d'Agence : ");
			try {
				for(Agence val : listAgence.findAll()) {
					System.out.println(val+"\n");
				}
			}catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("Choisissez l'Agence par son code : ");
			try {
				agence2=listAgence.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			System.out.println( "Entrez le solde du Compte : " );
			try {
				compteEpargn.setSolde(sc.nextInt());
				}catch(InputMismatchException e) {
					System.out.println( "\n Le code doit être un nombre entier" );
				}
			System.out.println( "Entrez le taux d'interet du Compte : " );
			try {
				compteEpargn.setTaux_interet(sc.nextDouble());
			}catch(InputMismatchException e){
				System.out.println("\n Le taux d'interet doit être un nombre" );	
			}
			agence2.getCompte().add(compteEpargn);
			System.out.println("Votre Compte Simple a bien été créer");
			compteEpargn.setId_agence(agence2.getId_agence());
			dspMainMenu();
			break;
		case 3:
			System.out.println( "======================================" );
			System.out.println( "============ COMPTE PAYANT ===========" );
			System.out.println( "======================================" );
			Agence agence3=new Agence();
			ComptePayant comptePayant = null;
			System.out.println("Voici la liste d'Agence : ");
			try {
				for(Agence val : listAgence.findAll()) {
					System.out.println(val+"\n");
				}
			}catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("Choisissez l'Agence par son code : ");
			try {
				agence3=listAgence.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			System.out.println( "Entrez le solde du Compte : " );
			try {
				comptePayant.setSolde(sc.nextInt());
				}catch(InputMismatchException e) {
					System.out.println( "\n Le code doit être un nombre entier" );
				}
			agence3.getCompte().add(comptePayant);
			System.out.println("Votre Compte Simple a bien été créer");
			comptePayant.setId_agence(agence3.getId_agence());
			dspMainMenu();
			break;
		}
	}
	private static void consultCompte() {
		System.out.println( "======================================" );
		System.out.println( "======= CONSULTATION DE COMPTE =======" );
		System.out.println( "======================================" );
		int mode = 0;
		boolean start=true;
		do {
			if(!start) {
				System.out.println("Ce mode n'existe pas merci de selectionner le mode parmis cette liste");
			}
		System.out.println( "1 - Compte Simple" );
		System.out.println( "2 - Compte Epargne" );
		System.out.println( "3 - Compte Payant" );
		System.out.println("Choississez votre type de compte a consulté : ");
		try {
			mode = sc.nextInt();
		} catch ( InputMismatchException e ) {
			mode = -1;
		} finally {
			sc.nextLine();
		}
		}while (mode<1 || mode>3);
		
		IDAO<Long, Compte> consultCompte=new CompteDAO();
		switch(mode) {
		case 1:
			CompteSimple comptesimple=null;
			System.out.print( "Entrez le code de votre Compte consulté : " );
			try {
				comptesimple=(CompteSimple) consultCompte.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			comptesimple.toString();
			dspMainMenu();
			break;
		case 2:
			CompteSimple compteEpargne=null;
			System.out.print( "Entrez le code de votre Compte consulté : " );
			try {
				compteEpargne=(CompteSimple) consultCompte.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			compteEpargne.toString();
			dspMainMenu();
			break;
		case 3:
			CompteSimple comptePayant=null;
			System.out.print( "Entrez le code de votre Compte consulté : " );
			try {
				comptePayant=(CompteSimple) consultCompte.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			comptePayant.toString();
			dspMainMenu();
			break;
		}
		
	}
	private static void modifCompte() {
		System.out.println( "======================================" );
		System.out.println( "========= MODIFIER DES COMPTE ========" );
		System.out.println( "======================================" );
		int mode = 0;
		boolean start=true;
		do {
			if(!start) {
				System.out.println("Ce mode n'existe pas merci de selectionner le mode parmis cette liste");
			}
		System.out.println( "1 - Compte Simple" );
		System.out.println( "2 - Compte Epargne" );
		System.out.println( "3 - Compte Payant" );
		System.out.println("Choississez votre type de compte a Modifié : ");
		try {
			mode = sc.nextInt();
		} catch ( InputMismatchException e ) {
			mode = -1;
		} finally {
			sc.nextLine();
		}
		}while (mode<1 || mode>3);
		
		switch(mode) {
		case 1:
			IDAO<Long, Compte> modifCompte=new CompteDAO();
			IDAO<Long, Compte> exCompte =new CompteDAO();
			CompteSimple comptesimple=null;
			System.out.print( "Entrez le code de votre Compte a modifié : " );
			try {
				exCompte=(IDAO<Long, Compte>) modifCompte.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			System.out.println("Rentrez le nouveau code : ");
			try {
			comptesimple.setSolde(sc.nextDouble());
			}catch(InputMismatchException e) {
				System.out.println( "\n Le code doit être un nombre entier" );
			}
			System.out.println("Entrez le nouveau nom : ");
			try {
			comptesimple.setDecouvert(sc.nextDouble());
			}catch(InputMismatchException e) {
				System.out.println( "\n Le nom doit être une chaine de caractere" );
			}
			try {
				exCompte.update(comptesimple);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			System.out.println( "Le Compte a été modifié avec succes" );
			dspMainMenu();	
			break;
		case 2:
			IDAO<Long, Compte> modifCompte2=new CompteDAO();
			IDAO<Long, Compte> exCompte2 =new CompteDAO();
			CompteEpargne compteEpargne=null;
			System.out.print( "Entrez le code de votre Compte a modifié : " );
			try {
				exCompte2=(IDAO<Long, Compte>) modifCompte2.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			System.out.println("Rentrez le nouveau code : ");
			try {
			compteEpargne.setSolde(sc.nextDouble());
			}catch(InputMismatchException e) {
				System.out.println( "\n Le code doit être un nombre entier" );
			}
			System.out.println("Entrez le nouveau nom : ");
			try {
			compteEpargne.setTaux_interet(sc.nextDouble());
			}catch(InputMismatchException e) {
				System.out.println( "\n Le nom doit être une chaine de caractere" );
			}
			try {
				exCompte2.update(compteEpargne);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			System.out.println( "Le Compte a été modifié avec succes" );
			dspMainMenu();	
			break;
		case 3:
			IDAO<Long, Compte> modifCompte3=new CompteDAO();
			IDAO<Long, Compte> exCompte3 =new CompteDAO();
			CompteEpargne comptePayant=null;
			System.out.print( "Entrez le code de votre Compte a modifié : " );
			try {
				exCompte3=(IDAO<Long, Compte>) modifCompte3.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			System.out.println("Rentrez le nouveau code : ");
			try {
			comptePayant.setSolde(sc.nextDouble());
			}catch(InputMismatchException e) {
				System.out.println( "\n Le code doit être un nombre entier" );
			}
			System.out.println("Entrez le nouveau nom : ");
			try {
			comptePayant.setTaux_interet(sc.nextDouble());
			}catch(InputMismatchException e) {
				System.out.println( "\n Le nom doit être une chaine de caractere" );
			}
			try {
				exCompte3.update(comptePayant);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			System.out.println( "Le Compte a été modifié avec succes" );
			dspMainMenu();
			break;
		}
		
	}
	private static void suppCompte() {
		System.out.println( "======================================" );
		System.out.println( "======== SUPPRESSION DE COMPTE =======" );
		System.out.println( "======================================" );
		IDAO<Long, Compte> supprCompte=new CompteDAO();
		System.out.println( "Entrez le code de votre Compte a Supprimé : " );
		Compte compte=new Compte();
		try {
			compte=supprCompte.findById(sc.nextLong());
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			System.out.println("Ce code n'existe pas ou est errone\n");
			e1.printStackTrace();
		}
		try {
			supprCompte.remove(compte);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("Le Compte a bien été supprimé");
		dspMainMenu();	
		
		
		
	}
	private static void operation() {
		int mode = 0;
		boolean start=true;
		do {
			if(!start) {
				System.out.println("Ce mode n'existe pas merci de selectionner le mode parmis cette liste");
			}
			
		System.out.println( "======================================" );
		System.out.println( "============ LES OPERATIONS ==========" );
		System.out.println( "======================================" );
		System.out.println( "1 - Operation de Retrait" );
		System.out.println( "2 - Operation de Versement" );
		System.out.println( "3 - Operation de Calcule d'ineteret" );
		try {
			mode = sc.nextInt();
		} catch ( InputMismatchException e ) {
			mode = -1;
		} finally {
			sc.nextLine();
		}
	}while (mode<1 || mode>3);
		
	 switch(mode) {
	 case 1:
		 int mode2 = 0;
			boolean start2=true;
			do {
				if(!start) {
					System.out.println("Ce mode n'existe pas merci de selectionner le mode parmis cette liste");
				}
		 	System.out.println( "======================================" );
			System.out.println( "================ RETRAIT =============" );
			System.out.println( "======================================" );
			System.out.println( "1 - Compte Payant" );
			System.out.println( "2 - Autre compte" );
			try {
				mode2 = sc.nextInt();
			} catch ( InputMismatchException e ) {
				mode2 = -1;
			} finally {
				sc.nextLine();
			}
		}while (mode2<1 || mode2>2);
			if(mode==1) {
				IDAO<Long, Compte> retraitPayant=new CompteDAO();
				System.out.println( "Entrez le code de votre Compte a débiter : " );
				ComptePayant compte=null;
				try {
					compte=(ComptePayant) retraitPayant.findById(sc.nextLong());
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					System.out.println("Ce code n'existe pas ou est errone\n");
					e1.printStackTrace();
				}
				System.out.println("Donnez la somme a retirer \n");
				double sommePayant=compte.getSolde();
				double sommeDebiPayant=sc.nextInt();
				sommePayant-=sommeDebiPayant*0.05;
				compte.setSolde(sommePayant);
				System.out.println("Votre solde est de "+compte.toString());

			}else {
				IDAO<Long, Compte> retrait=new CompteDAO();
				System.out.println( "Entrez le code de votre Compte a débiter : " );
				Compte compte=new Compte();
				try {
					compte= retrait.findById(sc.nextLong());
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					System.out.println("Ce code n'existe pas ou est errone\n");
					e1.printStackTrace();
				}
				System.out.println("Donnez la somme a retirer \n");
				double somme=compte.getSolde();
				double sommeDebi=sc.nextInt();
				somme-=sommeDebi*0.05;
				compte.setSolde(somme);
				System.out.println("Votre solde est de "+compte.toString());
			}
		 break;
	 case 2:
		 int mode3 = 0;
			boolean start3=true;
			do {
				if(!start) {
					System.out.println("Ce mode n'existe pas merci de selectionner le mode parmis cette liste");
				}
		 	System.out.println( "======================================" );
			System.out.println( "================ VERSEMENT ============" );
			System.out.println( "======================================" );
			System.out.println( "1 - Compte Epargne" );
			System.out.println( "2 - Autre compte" );
			try {
				mode3 = sc.nextInt();
			} catch ( InputMismatchException e ) {
				mode3 = -1;
			} finally {
				sc.nextLine();
			}
		}while (mode3<1 || mode3>2);
			
			if(mode==1) {
				IDAO<Long, Compte> versemPayant=new CompteDAO();
				System.out.println( "Entrez le code de votre Compte a débiter : " );
				ComptePayant compte=null;
				try {
					compte=(ComptePayant) versemPayant.findById(sc.nextLong());
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					System.out.println("Ce code n'existe pas ou est errone\n");
					e1.printStackTrace();
				}
				System.out.println("Donnez la somme a retirer \n");
				double sommePayant=compte.getSolde();
				double sommeDebiPayant=sc.nextInt();
				sommePayant+=sommeDebiPayant*0.05;
				compte.setSolde(sommePayant);
				System.out.println("Votre solde est de "+compte.toString());

			}else {
				IDAO<Long, Compte> virement=new CompteDAO();
				System.out.println( "Entrez le code de votre Compte a débiter : " );
				Compte compte=new Compte();
				try {
					compte= virement.findById(sc.nextLong());
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					System.out.println("Ce code n'existe pas ou est errone\n");
					e1.printStackTrace();
				}
				System.out.println("Donnez la somme a retirer \n");
				double somme=compte.getSolde();
				double sommeVerse=sc.nextInt();
				somme-=sommeVerse*0.05;
				compte.setSolde(somme);
				System.out.println("Votre solde est de "+compte.toString());
			}
		 break;
	 case 3:
		 	IDAO<Long, Compte> tauxinteret=new CompteDAO();
			System.out.println( "Entrez le code de votre Compte a Calculer : " );
			CompteEpargne compte=null;
			try {
				compte= (CompteEpargne) tauxinteret.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			System.out.println( "Entrez votre taux d'interet annuel : " );
			int txInteretAnnu=sc.nextInt();
			System.out.println( "Entrez votre Temps de Valorisation en jour : " );
			int tmValoris=sc.nextInt();
			double txInteret=compte.getSolde()*txInteretAnnu*tmValoris;
			System.out.println( "Votre Solde avec les interet est de  : "+txInteret+" €" );
			
		 break;
	 }
		
	}
	
    public static void main(String args[]) {
    	
    	dspMainMenu();
    	
    }
}
