package com.miage.umlconvertisseur;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ScreenView {

    static List<CategorieUnit> listMeasure = Converter.listMeasure;
    
    
    public static void main(String[] args) throws IOException {
        initialiser();
    }

    // /////// 
    // MENU //
    // ///////
    /***
     * @param 
     * @return void
     * @throws IOException 
     */
    public static void initialiser() throws IOException {

        int reponseMenu = 0;

        while (reponseMenu != 42) {
            System.out
                    .println(" \n ____________________________________________________________ ");
            System.out
                    .println("                                                              ");
            System.out
                    .println("                 Converter d'unite                        ");
            System.out
                    .println("                                                              ");
            System.out
                    .println("    1) Afficher toutes les categories d'unites                ");
            System.out
                    .println("    2) Afficher toutes les categories d'unites et leurs unites");
            System.out
                    .println("    3) Convertir deux unites                                  ");
            System.out
                    .println("    4) Ajouter une nouvelle unite                             ");
            System.out
                    .println("    5) Supprimer une unite                                    ");
            System.out
                    .println("    42) Pour quitter l'application                            ");
            System.out
                    .println(" ____________________________________________________________ ");
            System.out
                    .println("                                                              ");

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
                    System.out.println("   \n Les differentes categorieUnite : ");
                    afficherMesure(listMeasure);
                    break;
                case 2:
                    System.out.println("   \n Les differentes categorieUnite : ");
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
                .println(" \n  Dans quelle categorie d'unites se situent les deux unites que vous souhaitez convertir : ");
        afficherMesure(listMeasure);
        System.out.print(" \n  Choix : ");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        // On recupere la reponse
        String nomMesure = s.next().trim();
            for (CategorieUnit mes : listMeasure){
        	if (mes.getNameCategorieUnite().endsWith(nomMesure)){
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

        Converter.convertTwoUnit(nomMesure, nomUniteIn,
                nomUniteOut, valIn);

    }
    
    private static void supprimerUnite() {

        System.out
                .print(" \n  Dans quel categorie d'unites se situe l'unite que vous souhaitez supprimer : ");
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

        Converter.deleteUnit(nomMesure, nomUnite);
    }

    private static void ajouterUniteToMesure() throws IOException {

        System.out.print(" \n  INFO : ");
        System.out.print(" \n  Une convention prend la forme d'une fonction A x X + B. ");
        System.out.print(" \n  A = Le multi et B = le decalage");
        System.out.print(" \n  Exemple, dans la categorieUnite longueur, l'unite de reference est le metre, car son multicateur est de 1.0, ");
        System.out.print(" \n  Si vous souhaitez ajouter l'unite km, le multi a indiquer est donc 1000, car il faut 1000 metre faire un km");

        String nomMesure = "";
        String nomUnite = "";
        String multiUnite = "";
        String additionUnite = "";

        BigDecimal ratUnite = new BigDecimal("0.00");
        BigDecimal decal = new BigDecimal("0.00");

        System.out
                .print(" \n  Dans quel categorie d'unites souhaitez vous ajouter une unite ? \n");
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

            Converter.addUnit(nomMesure, nomUnite, ratUnite, decal);
        }

    }
    
    /////////////////////////////////////////////////////////////////////////////
    // Afficher CategorieUnit // Afficher CategorieUnit&Unit // Afficher unité d'une categorieUnite //
    // //////////////////////////////////////////////////////////////////////////
    private static void afficherMesureEtUnite(List<CategorieUnit> listeMesure) {
        for (int i = 0; i < listeMesure.size(); i++) {
            System.out.println("   > " + listeMesure.get(i).getNameCategorieUnite());
            for (int j = 0; j < listeMesure.get(i).getLstUnite().size(); j++) {
                System.out.println("     - "
                        + listeMesure.get(i).getLstUnite().get(j).getNameUnite()
                        + "   | multi : "
                        + listeMesure.get(i).getLstUnite().get(j)
                        .getmultiParRapportDefaut()
                        + "   | addition : "
                        + listeMesure.get(i).getLstUnite().get(j)
                        .getadditionParRapportDefaut());
            }
        }
    }

    private static void afficherMesure(List<CategorieUnit> listeMesure) {
        for (CategorieUnit categorieUnit : listeMesure) {
            System.out.println("   > " + categorieUnit.getNameCategorieUnite());
        }
    }
    
    private static void afficherUniteDeMesure(CategorieUnit categorieUnit){
	for (Unit unit : categorieUnit.getLstUnite()){
	    System.out.println("   > " + unit.getNameUnite());
	}
    }
}
