/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zoranhome
 */
public class Knjiga {

    private static ArrayList<Knjiga> knjige = new ArrayList<>();

    private int idKnjiga;
    private String imeKnjiga;
    boolean izdata;

    public Knjiga() {
    }
    
    

    public Knjiga(int idKnjiga, String imeKnjiga, boolean izdata) {
        this.idKnjiga = idKnjiga;
        this.imeKnjiga = imeKnjiga;
        this.izdata = izdata;
    }

    public Knjiga(int id) {
        this.idKnjiga = id;
    }

    public void setIzdata(boolean izdata) {
        this.izdata = izdata;
    }

     
    public int getIdKnjiga() {
        return idKnjiga;
    }

    public String getImeKnjiga() {
        return imeKnjiga;
    }
    

    @Override
    public String toString() {
        return "\nIme: " + imeKnjiga + "\n Id: " + idKnjiga + "\n Izdata: " + izdata;
    }

    public boolean isIzdata() {
        return izdata;
    }

     public static Knjiga getKnjiga(int id) 
     {
         
         for (Knjiga knjiga : knjige) 
             if(knjiga.getIdKnjiga() == id)
                 return knjiga;
            
             
        return null;
              
     }
     
    static boolean knjigaDostupna(int knjigaId) {
        
        for (Knjiga knjiga : knjige) {
            
            if (knjiga.getIdKnjiga() == knjigaId) {
               
                if (!knjiga.isIzdata()) 
                {
                    return true;
                }
                
            }
        }
        return false;

        

    }

    
    public static  boolean knjigaValidna(int idKnjiga){
        for (Knjiga knjiga : knjige) {
            if(knjiga.getIdKnjiga() == idKnjiga)
                return true;
            }
        return false;
    }
    
    public static void novaKnjiga() {
        Scanner sc = new Scanner(System.in);
        int idKnjiga = 0;
        String imeKnjiga;
        boolean izdataKnjiga;
        boolean IdinputNotValid=true;
        
        
        System.out.println("Enter name");
        imeKnjiga = sc.nextLine();
        
        
        do {

            try {

                System.out.println("Enter book id: ");
                idKnjiga = sc.nextInt();

                while (idKnjiga <= 0) {
                    System.out.println("Invalid input, id cannot be less or equal to zero: " + idKnjiga);
                    idKnjiga = sc.nextInt();
                }
                
                IdinputNotValid = false;
            } catch (InputMismatchException ex) {
                System.err.println("Check your input: ");
                sc.nextLine();
            }
        } while (IdinputNotValid);


        

        System.out.println("Book given out?");
        izdataKnjiga = sc.nextBoolean();
        System.out.println(izdataKnjiga);

        Knjiga novaKnjiga = new Knjiga(idKnjiga, imeKnjiga, izdataKnjiga);
        dodajKnjiguUlistu(novaKnjiga);
        ispisiListuKnjiga();

    }

    private static void dodajKnjiguUlistu(Knjiga novaKnjiga) {
        knjige.add(novaKnjiga);
    }

    private static void ispisiListuKnjiga() {
        for (Knjiga knjiga : knjige) {
            System.out.println(knjiga);
        }
    }
    
   

}
