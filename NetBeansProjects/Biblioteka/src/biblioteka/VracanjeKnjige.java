/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author zoranhome
 */
public class VracanjeKnjige extends Knjiga{
    
    private static ArrayList<VracanjeKnjige> zapisnikVracanje = new ArrayList<>();
    private int idRacuna;
    private int idKnjige;
    private Date date;
    

    public VracanjeKnjige() {
    }
    
    public static boolean knjigaValid(int idKnjige)
    {
        return Knjiga.knjigaValidna(idKnjige);
                    
    }
    public static boolean racunIznajmioKnjigu(int idRacun,int idKnjiga){
        
       Racun noviRacun = Racun.getRacun(idRacun);
       
       
       if(noviRacun.getIdPosudjenjeKnjige()==idKnjiga)
           return true;
       
        return false;
        
    }
    public static boolean racunValidan(int id){
        
       if(Racun.checkValidRacunList(id))
           return true;
       
        return false;
       
    }

    public static void vratiKnjigu() {

        Scanner sc = new Scanner(System.in);
        int racunId;
        int knjigaId;
        String confirm;

        System.out.println("Enter account id: ");
        racunId = sc.nextInt();

        if (racunValidan(racunId)) {
            System.out.println("Valid account");
        } else {
            System.err.println("Invalid account.");
            System.exit(0);
        }

        System.out.println("Enter book id: ");
        knjigaId = sc.nextInt();
        if (knjigaValid(knjigaId)) {
            System.out.println("Valid book id");
        } else {
            System.err.println("Invalid book id.");
            System.exit(0);
        }

        if (racunIznajmioKnjigu(racunId, knjigaId)) {
            Racun racunVracanje = Racun.getRacun(racunId);
            Knjiga knjigaVracanje = Knjiga.getKnjiga(knjigaId);
            System.out.println("Confirm that you are returning the book id: " + knjigaVracanje.getIdKnjiga()
                    + "\nName: " + knjigaVracanje.getImeKnjiga()
                    + "\nFor account id: " + racunVracanje.getIdRacun()
                    + "\nAccount name: " + racunVracanje.getIme()
                    + "\nENTER Y for yes N for no");

            confirm = sc.next();

            if (confirm.toLowerCase().equals("y")) {
                racunVracanje.setBrojPosudjenihKnjiga(racunId);
                knjigaVracanje.setIzdata(false);
                System.out.println("Book returned");
            } else {
                System.out.println("Cannceled");
                System.exit(0);
            }

        } else {
            System.out.println("Account did not rent this book");
            System.exit(0);
        }

        
        
        
      

    }
    
    
    
    
        
                
    
    
    
}
