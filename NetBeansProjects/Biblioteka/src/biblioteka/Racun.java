/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zoranhome
 */
public class Racun {
    
    
    private static ArrayList<Racun> listaRacuna = new ArrayList<>();
    private  int idRacun;
    private String ime;
    private int brojPosudjenihKnjiga;
    private int idPosudjenjeKnjige;

    public Racun() {
    }

    public void setBrojPosudjenihKnjiga(int a) {
        this.brojPosudjenihKnjiga--;
    }
    

   
    

    public Racun(int idRacun, String ime, int brojPosudjenihKnjiga) {
        this.idRacun = idRacun;
        this.ime = ime;
        this.brojPosudjenihKnjiga = brojPosudjenihKnjiga;

        
    }
    
    public Racun(int idRacun){
        this.idRacun= idRacun;
    }

   

    public void setBrojPosudjenihKnjiga() {
        this.brojPosudjenihKnjiga ++;
    }

    public void setIdPosudjenjeKnjige(int idPosudjenjeKnjige) {
        this.idPosudjenjeKnjige = idPosudjenjeKnjige;
    }

    public int getIdPosudjenjeKnjige() {
        return idPosudjenjeKnjige;
    }
    
    

    
    
   
    public int getBrojPosudjenihKnjiga() {
        return brojPosudjenihKnjiga;
    }

    public  int getIdRacun() {
        return idRacun;
    }

    public String getIme() {
        return ime;
    }

    @Override
    public String toString() {
        return "\nIme: "+getIme()+"\nId racuna: "+getIdRacun()+"\nBroj izdatih knjiga: "+getBrojPosudjenihKnjiga()+"\n";
    }
    
     public static Racun getRacun(int idRacuna)
    {
        for (Racun racun : listaRacuna) 
             if(racun.getIdRacun() == idRacuna)
                 return racun;
            
             
        return null;
    }
    
    private static void dodajUlistuRacun(Racun noviRacun)
    {
        
        listaRacuna.add(noviRacun);
        
    }
    
    public static void prikaziListu(){
        System.out.println("\tAll accounts: ");
        for (Racun racun : listaRacuna) {
            
            System.out.println(racun.toString());
        }
            
        }
    
    public static void noviRacun()
    {
                    Scanner sc = new Scanner(System.in);
                    int idRacunIn=0;
                    boolean validInput = false;
                    
                    System.out.println("Enter your name: ");
                    String name = sc.nextLine();
                    
                    
                    System.out.println("Enter your account id: ");
                    
                    while(!validInput)
                    {
                        try{
                            idRacunIn = sc.nextInt();
                            validInput=true;
                            
                        }
                        catch (InputMismatchException ex)
                        {
                            System.err.println("Check your input!");
                            sc.nextLine();
                        }
                    }
                    
                    
                    while(idRacunIn <= 0)
                    {
                        System.out.println("Id has to be bigger than 0");
                        idRacunIn = sc.nextInt();
                        
                    }
                   
                    
                    
                    
                    validInput = false;
                    int booksTaken = 0;
                    
                      System.out.println("Enter number of books taken:");
                       while (!validInput) 
                       {
                        try {
                          booksTaken = sc.nextInt();
                          validInput = true;
                       } catch (InputMismatchException ex) {
                             System.err.println("Check your input!");
                          sc.nextLine();
                         }
                     }

                    Racun noviRacun = new Racun(idRacunIn, name, booksTaken);
                   
                    Racun.dodajUlistuRacun(noviRacun);
                    
    }
    
    public static boolean checkValidRacunList(int id)
    {
        for (Racun racun : listaRacuna) 
        
            if(racun.idRacun == id)
                    return true;
        return false;
        
    }
    
    public static boolean checkInputNumeric(String in) {

        boolean res = in.chars().allMatch(Character::isDigit);

        return res;
    }
    
    }
  

    
    
    

    
    
    
    
    
    
    
    

