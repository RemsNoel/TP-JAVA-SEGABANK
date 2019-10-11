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
			System.out.println( "9 - Changez de type de Compte" );
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
			changTypeCompte();
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
			System.out.print( "Entrez le code de votre Agence a modifié : " );
			try {
				listAgence.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			agence=(Agence) listAgence;
			
		}else {
			dspMainMenu();	
		}	
		
	}
	private static void modifAgence() {
		IDAO<Long, Agence> modifAgence=new AgenceDAO();
		IDAO<Long, Agence> finalModifAgence=new AgenceDAO();
		System.out.println( "======================================" );
		System.out.println( "====== MODIFICATION D'UNE AGENCE =====" );
		System.out.println( "======================================" );
		System.out.println("====== Choisir l'Agence a modifié ======");
		Agence agence = new Agence();
		System.out.print( "Entrez le code de votre Agence a modifié : " );
		try {
			modifAgence.findById(sc.nextLong());
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			System.out.println("Ce code n'existe pas ou est errone\n");
			e1.printStackTrace();
		}
		agence=(Agence) modifAgence;
		System.out.println("Rentrez le nouveau code : ");
		try {
		agence.setCode(sc.nextInt());
		}catch(InputMismatchException e) {
			System.out.println( "\n Le code doit être un nombre entier" );
		}
		System.out.print( "Entrez la nouvelle adresse : " );
		try {
		agence.setAdresse(sc.next());
		}catch(InputMismatchException e){
			System.out.println("\n L'Adresse doit être une chaine de caractere" );
		}
		try {
			finalModifAgence.update(agence);
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
			supprAgence.findById(sc.nextLong());
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			System.out.println("Ce code n'existe pas ou est errone\n");
			e1.printStackTrace();
		}
		agence=(Agence) supprAgence;
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
				listAgence.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			agence=(Agence)listAgence;
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
				listAgence.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			agence2=(Agence)listAgence;
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
				listAgence.findById(sc.nextLong());
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Ce code n'existe pas ou est errone\n");
				e1.printStackTrace();
			}
			agence3=(Agence)listAgence;
			System.out.println( "Entrez le solde du Compte : " );
			try {
				comptePayant.setSolde(sc.nextInt());
				}catch(InputMismatchException e) {
					System.out.println( "\n Le code doit être un nombre entier" );
				}
			agence3.getCompte().add(comptePayant);
			System.out.println("Votre Compte Simple a bien été créer");
			dspMainMenu();
			break;
		}
	}
	private static void consultCompte() {
		System.out.println( "======================================" );
		System.out.println( "======= CONSULTATION DES AGENCES =====" );
		System.out.println( "======================================" );
	}
	private static void modifCompte() {
		System.out.println( "======================================" );
		System.out.println( "======= CONSULTATION DES AGENCES =====" );
		System.out.println( "======================================" );
	}
	private static void suppCompte() {
		System.out.println( "======================================" );
		System.out.println( "======= CONSULTATION DES AGENCES =====" );
		System.out.println( "======================================" );
	}
	private static void changTypeCompte() {
		System.out.println( "======================================" );
		System.out.println( "======= CONSULTATION DES AGENCES =====" );
		System.out.println( "======================================" );
	}
	
    public static void main(String args[]) {
    	
    	dspMainMenu();
    	
    }
}
