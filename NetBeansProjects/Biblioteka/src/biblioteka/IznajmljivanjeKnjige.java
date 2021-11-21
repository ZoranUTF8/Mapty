/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 *
 * @author zoranhome
 */
public class IznajmljivanjeKnjige extends Knjiga
{

    private static ArrayList<IznajmljivanjeKnjige> zapisnik = new ArrayList<>();
    private int idRacuna;
    private int idKnjige;
    private Date date;

    public IznajmljivanjeKnjige() {
    }

    public IznajmljivanjeKnjige(int idRacuna, int idKnjige) {
        this.idRacuna = idRacuna;
        this.idKnjige = idKnjige;
        this.date = new Date();
    }

    public int getIdKnjige() {
        return idKnjige;
    }

    public int getIdRacuna() {
        return idRacuna;
    }

    public ArrayList<IznajmljivanjeKnjige> getZapisnik() {
        return zapisnik;
    }

    public Date getDate() {
        return date;
    }

    public static boolean checkKnjigaDostupna(int idKnjige) {
        boolean result = (knjigaDostupna(idKnjige));

        return result;
    }

    public static boolean checkValidRacunList(int idRacun) {
        boolean result = Racun.checkValidRacunList(idRacun);
        return result;
    }

    public static boolean checkRacunMozePosuditi(int racunId) {
        Racun noviRacun = Racun.getRacun(racunId);
        if (noviRacun.getBrojPosudjenihKnjiga() >= 3) {

            return true;
        }

        return false;

    }

    public static void posudiKnjigu() {
        Scanner sc = new Scanner(System.in);
        int idRacun;
        int idKnjiga;

        System.out.println("Enter account id: ");
        System.out.println("\t\t checking if account is valid");

        idRacun = sc.nextInt();

        if (IznajmljivanjeKnjige.checkValidRacunList(idRacun)) {
            System.out.println("Account is: valid");
        } else {
            System.err.println("Account not valid, try again. ");
            System.exit(0);
        }
        System.out.println("Account valid.");
        System.out.println("\n Checking if alowed to borrow...");
        if(IznajmljivanjeKnjige.checkRacunMozePosuditi(idRacun))
        {
            System.out.println("You already have 3 books.");
            System.exit(0);
        }
        else
        {
            System.out.println("You can still take: "+(3%Racun.getRacun(idRacun).getBrojPosudjenihKnjiga()));
        }
        System.out.println("\n\t");
        System.out.println("Enter book id: ");
        idKnjiga = sc.nextInt();
        if (IznajmljivanjeKnjige.checkKnjigaDostupna(idKnjiga)) {
            System.out.println("Book is: Available");
        } else {
            System.err.println("Book is: Not available");
            System.exit(0);
        }

        Racun noviRacun = Racun.getRacun(idRacun);
        noviRacun.setBrojPosudjenihKnjiga();
        noviRacun.setIdPosudjenjeKnjige(idKnjiga);

        IznajmljivanjeKnjige noviZapis = new IznajmljivanjeKnjige(idRacun, idKnjiga);
        IznajmljivanjeKnjige.dodajZapisUlistu(noviZapis);
        System.out.println(noviRacun.getBrojPosudjenihKnjiga());
        System.out.println("Uspjesno");

    }

    private static void dodajZapisUlistu(IznajmljivanjeKnjige noviUnos) {
        zapisnik.add(noviUnos);
    }
    
    public static void printZapisnik()
    {
        for (IznajmljivanjeKnjige zapis : zapisnik) 
        {
            System.out.println(zapis);
                   
        }
    }

    @Override
    public String toString() {
        return "Racun broj:\t"+idRacuna+"\n pozajmio je knjigu:\t"+idKnjige+"\nDatuma: "+date;
    }
     

}
