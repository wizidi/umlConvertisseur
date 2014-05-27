package com.miage.umlconvertisseur;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Interface {

    static List<Mesure> listMeasure = Convertisseur.listMeasure;
    
    
    public static void main(String[] args) throws IOException {
        initialiser();
    }

    // /////// 
    // MENU //
    // /////// 
    public static void initialiser() throws IOException {

        int reponseMenu = 0;

        while ((reponseMenu != 42)) {
            System.out
                    .println(" \n _________________________________________________________ ");
            System.out
                    .println("                                                           ");
            System.out
                    .println("                 Convertisseur d'unite                     ");
            System.out
                    .println("                                                           ");
            System.out
                    .println("    1) Afficher toutes les mesures                         ");
            System.out
                    .println("    2) Afficher toutes les mesures et leurs unites         ");
            System.out
                    .println("    3) Convertir deux unites                               ");
            System.out
                    .println("    4) Ajouter une nouvelle unite                          ");
            System.out
                    .println("    5) Supprimer une unite                                 ");
            System.out
                    .println("    42) Pour quitter l'application                         ");
            System.out
                    .println(" _________________________________________________________ ");
            System.out
                    .println("                                                           ");

            // Choix d'un entier du menu
            System.out.print("      Choix : ");
            @SuppressWarnings("resource")
            Scanner scan = new Scanner(System.in);
            try {
                // On recupere la reponse
                reponseMenu = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out
                        .print(" \n   Fail ! Vous devez indiquer l'un des choix du menu, un entier : 1, 2, 3, 4, 5 ou 42. \n");
            }
            switch (reponseMenu) {
                default:
                    System.out
                            .print("  \n  Vous devez indiquer l'un des choix du menu, un entier : 1, 2, 3, 4, 5 ou 42.\n \n");
                case 1:
                    System.out.println("   \n Les differentes mesure : ");
                    afficherMesure(listMeasure);
                    break;
                case 2:
                    System.out.println("   \n Les differentes mesure : ");
                    afficherMesureEtUnite(listMeasure);
                    break;
                case 3:
                    convertir();
                    break;
                case 4:
                    ajouterUniteToMesure();
                    break;
                case 5:
                    supprimerUnite();
                    break;
                case 42:
                    System.out
                            .println(" \n ____ Fin de l'application _______________________________ ");
                    System.exit(0);
                    break;
            }

        }
    }
    
    private static void convertir() {

        // Recup nomUniteIn
        System.out
                .println(" \n  Dans quelle mesure se situent les deux unitees que vous souhaitez convertir : ");
        afficherMesure(listMeasure);
        System.out.print(" \n  Choix : ");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        // On recupere la reponse
        String nomMesure = s.next().trim();
            for (Mesure mes : listMeasure){
        	if (mes.getNomMesure().endsWith(nomMesure)){
        	    afficherUniteDeMesure(mes);
        	}
            }

        System.out.print(" \n  Le nom de l'unite en entree : ");

        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        // On recupere la reponse
        String nomUniteIn = scan.next().trim();

        // Recup valIn
        System.out
                .print(" \n  Quel est la valeur a convertir (valeur en " + nomUniteIn + ") : ");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        // On recupere la reponse
        String valeurIn = sc.next().trim();

        // Parse en BigDecimal
        BigDecimal valIn = new BigDecimal("0.00");

        try {
            valIn = new BigDecimal(valeurIn);
        } catch (Exception e) {
            System.out.println(" \n  Convertion impossible, valeur e convertir incorrecte !");
        }

        // Recup nomUniteOut
        System.out.print(" \n  Le nom de l'unite en sortie : ");

        @SuppressWarnings("resource")
        Scanner sca = new Scanner(System.in);
        // On recupere la reponse
        String nomUniteOut = sca.next().trim();

        Convertisseur.convertirDeuxUnite(nomMesure, nomUniteIn,
                nomUniteOut, valIn);

    }
    
    private static void supprimerUnite() {

        System.out
                .print(" \n  Dans quel mesure se situe l'unite que vous souhaitez supprimer : ");
        afficherMesure(listMeasure);
        System.out.print(" \n  Choix : ");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        // On recupere la reponse
        String nomMesure = s.next().trim();

        System.out.print(" \n  Le nom de l'unite e supprimer : ");

        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        // On recupere la reponse
        String nomUnite = scan.next().trim();

        Convertisseur.supprimerUnite(nomMesure, nomUnite);
    }

    private static void ajouterUniteToMesure() throws IOException {

        System.out.print(" \n  INFO : ");
        System.out.print(" \n  Une convention prend la forme d'une fonction A x X + B. ");
        System.out.print(" \n  A = Le multi et B = le decalage");
        System.out.print(" \n  Exemple, dans la mesure longueur, l'unite de reference est le metre, car son multicateur est de 1.0, ");
        System.out.print(" \n  Si vous souhaitez ajouter l'unite km, le multi a indiquer est donc 1000, car il faut 1000 metre faire un km");

        String nomMesure = "";
        String nomUnite = "";
        String multiUnite = "";
        String additionUnite = "";

        BigDecimal ratUnite = new BigDecimal("0.00");
        BigDecimal decal = new BigDecimal("0.00");

        System.out
                .print(" \n  Dans quel mesure souhaitez vous ajouter une unite ? \n");
        afficherMesureEtUnite(listMeasure);
        System.out.print(" \n  Choix : ");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        // On recupere la reponse
        nomMesure = s.next().trim();

        System.out.print(" \n  Comment se nomme votre nouvelle unite ? ");

        System.out.print(" \n  Choix : ");
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        // On recupere la reponse
        nomUnite = scan.next().trim();

        System.out
                .print(" \n  Quel est son multicateur par rapport a la valeur de reference ? ");
        System.out.print(" \n  Choix : ");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        // On recupere la reponse
        multiUnite = sc.next().trim();

        System.out
                .print(" \n  Quel est son decalage par rapport a la valeur de reference ? ");
        System.out.print(" \n  Choix : ");
        @SuppressWarnings("resource")
        Scanner sca = new Scanner(System.in);
        // On recupere la reponse
        additionUnite = sca.next().trim();

        Boolean okDecal = false;
        try {
            decal = new BigDecimal(additionUnite);
            okDecal = true;
        } catch (Exception e) {
            okDecal = false;
            System.out.println("   La valeur de decalage n'est pas valide");
        }

        Boolean okmulti = false;
        try {
            ratUnite = new BigDecimal(multiUnite);
            okmulti = true;
        } catch (Exception e) {
            okmulti = false;
            System.out.println("   La valeur du multi n'est pas valide");
        }

        if (okDecal && okmulti) {

            Convertisseur.ajouterUnite(nomMesure, nomUnite, ratUnite, decal);
        }

    }
    
    /////////////////////////////////////////////////////////////////////////////
    // Afficher Mesure // Afficher Mesure&Unite // Afficher unité d'une mesure //
    // //////////////////////////////////////////////////////////////////////////
    private static void afficherMesureEtUnite(List<Mesure> listeMesure) {
        for (int i = 0; i < listeMesure.size(); i++) {
            System.out.println("   > " + listeMesure.get(i).getNomMesure());
            for (int j = 0; j < listeMesure.get(i).getLstUnite().size(); j++) {
                System.out.println("     - "
                        + listeMesure.get(i).getLstUnite().get(j).getNomUnite()
                        + "   | multi : "
                        + listeMesure.get(i).getLstUnite().get(j)
                        .getmultiParRapportDefaut()
                        + "   | addition : "
                        + listeMesure.get(i).getLstUnite().get(j)
                        .getadditionParRapportDefaut());
            }
        }
    }

    private static void afficherMesure(List<Mesure> listeMesure) {
        for (Mesure mesure : listeMesure) {
            System.out.println("   > " + mesure.getNomMesure());
        }
    }
    
    private static void afficherUniteDeMesure(Mesure mesure){
	for (Unite unite : mesure.getLstUnite()){
	    System.out.println("   > " + unite.getNomUnite());
	}
    }
}
