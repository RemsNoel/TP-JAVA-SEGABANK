//package test;
//
//import bo.Agence;
//import jdbc.dao.AgenceDAO;
//import jdbc.dao.IDAO;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestAgenceDAOCompteDAO {
//    IDAO<Long, Agence> dao = new AgenceDAO();
//        Agence agence1 = new Agence(001, "La Faillite", "Rue de la soif");
//        Agence agence2 = new Agence(001, "Le Jackpot", "Rue de la tune");
//        Agence agence3 = new Agence(002, "Les Bosseurs", "Rue de la main d'oeuvre");
//        Agence agence4 = new Agence();
//        List<Agence> listAgence = new ArrayList<>();
//        try {
//            dao.create(agence1);
//            dao.create(agence2);
//            dao.create(agence3);
//            listAgence = dao.findAll();
//            agence4 = dao.findById(2L);
//            dao.remove(agence4);
//
//            for (Agence ag : listAgence) {
//                System.out.println(ag.toString());
//            }
//            System.out.println(agence4.toString());
//            System.out.println("-------------------------");
//            dao.remove(agence4);
//            listAgence = dao.findAll();
//            for (Agence ag : listAgence) {
//                System.out.println(ag.toString());
//            }
//            System.out.println("------------------------------");
//            Agence agence1Update = new Agence(1,100,"Noirfutaie","Quelque part");
//            dao.update(agence1Update);
//            listAgence = dao.findAll();
////            for (Agence ag : listAgence) {
////                System.out.println(ag.toString());
////            }
//        } catch (IOException | SQLException | ClassNotFoundException e) {
//            System.err.println(e.getMessage());
//        }
//
//
//    IDAO<Long, Compte> daoCompte = new CompteDAO();
//    Compte compte1 = new Compte(3000, 01, -200,0);
//    Compte compte2 = new Compte(2000, 03, 0,12);
//    Compte compte3 = new Compte(5000, 01, -250,0);
//    List<Compte> listCompte = new ArrayList<>();
//        try {
//            daoCompte.create(compte1);
//            daoCompte.create(compte2);
//            daoCompte.create(compte3);
//        listCompte = daoCompte.findAll();
//        for (Compte co : listCompte){
//            System.out.println(co.toString());
//        }
//        Compte compte4 = daoCompte.findById(2L);
//        System.out.println("------------------------");
//        System.out.println(compte4);
//        daoCompte.remove(compte4);
//        listCompte = daoCompte.findAll();
//        for (Compte co : listCompte){
//            System.out.println(co.toString());
//        }
//        Compte compte5 = new Compte(1, 1000,1 ,65 ,45);
//        daoCompte.update(compte5);
//        listCompte = daoCompte.findAll();
//        for (Compte co : listCompte){
//            System.out.println(co.toString());
//        }
//
//
//    } catch (IOException | SQLException | ClassNotFoundException e) {
//        System.err.println(e.getMessage());
//    }
//}
