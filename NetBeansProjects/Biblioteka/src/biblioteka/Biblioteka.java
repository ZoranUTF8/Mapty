/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zoranhome
 */
public class Biblioteka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int option = 0;

        while (option != 7) {

            System.out.println("\n\t\tLibrary app\n");
            System.out.println("\t Chose your options:");
            
            System.out.println("\t Option 1: Make a new account. ");
            System.out.println("\t Option 2: Make a new Book entry.");
            System.out.println("\t Option 3: Borrow a book.");
            System.out.println("\t Option 4: Return a book.");
            System.out.println("\t Option 5: Print all accounts.");
            System.out.println("\t Option 6: Exit.");
           
try{
            option = sc.nextInt();

            switch (option) {
                case 1: {
                    Racun.noviRacun();
                    break;
                }
                case 2: {
                    Knjiga.novaKnjiga();
                    break;
                }
                case 3: {
                    IznajmljivanjeKnjige.posudiKnjigu();
                    break;

                }
                case 4: {
                     VracanjeKnjige.vratiKnjigu();
                    break;

                }
                case 5: {
                    Racun.prikaziListu();
                    break;

                }
                case 6: {
                    System.out.println("Bye bye");
                    System.exit(0);
                    break;

                }
                default: {
                    System.out.println("Invalid input");
                    break;
                }
            }
        }
        catch (InputMismatchException ex)
                {
                    System.out.println("Check input");
                    sc.nextLine();
                            
                }

    }

    }
}
