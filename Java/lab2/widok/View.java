/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanie4.widok;
import java.util.Scanner;

import static java.util.Collections.sort;

import java.util.List;

import java.util.stream.Stream;


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
   public void wyswietlWynik(List<Integer> wynik,String rodzaj){
     System.out.println("Wyniki losowania " + rodzaj);
     wyswietlNumery(wynik);
    };
 
/**
 * Outputs the list of numbers
 * @param wynik score list
 * 
 */    
   public void wyswietlNumery(List<Integer> wynik)
   {    
       sort(wynik);
       
       Stream<Integer> stream=wynik.stream();
       stream.forEach(p -> System.out.print(p + ", "));
       
   }
   
   /**
 * Outputs game options and inputs it number
 * @param opcje list of game options
 * 
 */ 
    public int wyswietlOpcjeIPobierz(List<String> opcje){
 
        
        System.out.println("Mozliwosci losowan: ");
        int i=0;
      
        
        for(String s:opcje)
        {
            System.out.println((i+1)+" "+s);
            i++;
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
    
/**
 * Print out the error type
 * @param type number of error
 */
       public void errorMessage(int... type)
       {    String message="Error: ";
           for(int i:type)
           {
               if(i==1)
               {
                   
                   message="Bad number of parameters 0 or more than 1! ";
               }
                if(i==2)
               {
                   message="Parameter not int!!!! ";
               }
                 if(i==3)
               {
                   message="Bad parameter choose intger: 1-3 ";
               }
           }
            System.out.println(message);
           
       }
}


