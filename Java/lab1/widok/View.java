/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanie4.widok;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Represents the view of application
 * @author Agata Przykaza
 * @version 1.0
 */
public class View {
 /**
 * Constructor
 * 
 */   
    public View(){};
 /**
 * Outputs the score in consol
 * @param wynik score array
 * @param rodzaj type of game
 * 
 */    
   public void wyswietlWynik(int[] wynik,String rodzaj){
     System.out.println("Wyniki losowania " + rodzaj);
     wyswietlNumery(wynik);
    };
 
/**
 * Outputs the array of numbers
 * @param wynik score array
 * 
 */    
   public void wyswietlNumery(int[] wynik)
   {    
       Arrays.sort(wynik);
       for(int i=0;i<wynik.length;i++)
       {    
           if(i==wynik.length-1)
           {
               System.out.print(wynik[i]);
           }
           else{
           System.out.print(wynik[i]+", ");
           }
       }
   }
   
   /**
 * Outputs game options and inputs it number
 * @param opcje array of game options
 * 
 */ 
    public int wyswietlOpcjeIPobierz(String[] opcje){
        
        System.out.println("Mozliwosci losowan: ");
        for(int i=0;i<opcje.length;i++)
        {
            System.out.println((i+1)+" "+opcje[i]);
        }
        
        Scanner scan = new Scanner(System.in);
        
        int o=0;
        boolean isGoodNumber=false;
        while(!isGoodNumber)
        {
                
                if (checkCzyInt(scan)) {
                     
                    o=scan.nextInt();
                    

                   if(o<=3&&o>=1)
                    { isGoodNumber=true;
                   }
                    else
                    { System.out.println("Blad, podaj jeszcze raz: ");
                   }
                }
               else
                { System.out.println("Blad, podaj jeszcze raz: ");
                   scan.next();
                }
                
                
        }
        
        
        
        
        return o;
    };
    
    /**
 * Checks if in sacnnner is an int number
 *@param sc scanner 
 */ 
   public boolean checkCzyInt(Scanner sc)
    {
        if(sc.hasNextInt())
        {
            return true;
        }
        else
        {
            return false;
            
        }
    }
    


}


