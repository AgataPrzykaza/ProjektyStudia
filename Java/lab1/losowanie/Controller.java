/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanie4.losowanie;
import com.mycompany.zadanie4.widok.View;
import com.mycompany.zadanie4.model.*;

import java.util.Random;




/**
 *Represents the controller of aplication
 *@author Agata Przykaza
 * @version 1.0
 */
public class Controller {
    
    /**
 * View 
 * 
 */ 
    private View widok;
    
    /**
 * Model
 * 
 */ 
    private Model model;
  /**
 * Constructor
 * @param w view
 * @param m model
 */   
    public Controller(View w,Model m){
        this.model=m;
        this.widok=w;
    };
    
    

 /**
 * Lotery lotto,ganerated lotto score and set in model
 * 
 */    
    void losowanieLotto(){
        int[] wynik=losowanieNumerow(model.getZakresLotto(),6);
        try{
        model.setWynikLotto(wynik);
        }
        catch(EmptyArrayException e){
        System.err.println(e.getMessage());
        }
    };
    
  /**
 * Lotery mutlilotto,ganerated multilotto score and set in model
 * 
 */ 
     void losowanieMultiLotto(){
        int[] wynik=losowanieNumerow(model.getZakresMultiLotto(),20);
        try{
        model.setWynikMultiLotto(wynik);
        }
        catch(EmptyArrayException e){
            System.err.println(e.getMessage());
        }
        
        };
  /**
 * Lotery minilotto,ganerated minilotto score and set in model
 * 
 */     
      void losowanieMiniLotto(){
        int[] wynik=losowanieNumerow(model.getZakresMiniLotto(),5);
        try{
        model.setWynikMiniLotto(wynik);
        }
        catch(EmptyArrayException e)
        {
            System.err.println(e.getMessage());
        }
    };
      
 /**
 * Generating random array
 * @param zakres range of generation
 * @param iloscLiczb size of array
 * @return generated array
 * 
 */       
      public int[]losowanieNumerow(int zakres,int iloscLiczb)
      {
          int tmp[]=new int[iloscLiczb];
            
          int generated=ganerateNumber(zakres);
          for(int i=0;i<iloscLiczb;i++)
          {
              while(isInArray(tmp,generated))
              {
                  generated=ganerateNumber(zakres);
              }
               tmp[i]=generated;
               generated=ganerateNumber(zakres);
          }
          
          
          return tmp;
      }
 /**
 * Generating lottery, for specific option in model
 * 
 */       
      public void losowanie()
      {
          if(model.getNrOpcji()==1)
          {
              losowanieLotto();
          }
          else if(model.getNrOpcji()==2)
          {
              losowanieMultiLotto();
          }
          else if(model.getNrOpcji()==3)
          {
              losowanieMiniLotto();
          }
          
      }
       /**
 * Outputs score for specific option in model
 * 
 */ 
      public void pokazacWynik()
      {
          if(model.getNrOpcji()==1)
          {
               widok.wyswietlWynik(model.getWynikLotto(), model.getNazwaZPozycji(1));
          }
          else if(model.getNrOpcji()==2)
          {
              widok.wyswietlWynik(model.getWynikMultiLotto(), model.getNazwaZPozycji(2));
          }
          else
          {
          widok.wyswietlWynik(model.getWynikMiniLotto(), model.getNazwaZPozycji(3));
          }
          
         
      }
      
     /**
 * Generates random number for given range
 * @param zakres range of generation
 * 
 */   
      public int ganerateNumber(int zakres)
      { 
          Random rand=new Random();
          int randomNum=rand.nextInt(zakres)+1;
          return randomNum;
      }
    /**
 * Checks if given value is already in array
 * @param ar array
 * @param value value int
 * @return boolean true or false
 * 
 */    
      public boolean isInArray(int ar[],int value)
      {
          for(int i=0;i<ar.length;i++)
          {
              if(ar[i]==value)
              {
                  return true;
              }
          }
          
          return false;
      }
 /**
 * Lotery game without set game option
 * 
 */       
      public void gameLottery()
      {
         
          model.setNrOpcji(widok.wyswietlOpcjeIPobierz(model.getNazwy()));
          losowanie();
          pokazacWynik();
          
      }
      
 /**
 * Lotery game with set parameter
 * @param param option number
 */ 
      public void gameLotteryViaParameter(int param)
      {
          model.setNrOpcji(param);
          losowanie();
          pokazacWynik();
          
      }
 /**
 * Main game
 * @param args starting parameter for application
 */       
      public void game(String[] args)
      {
      if(args.length==0||args.length>1)
        {
        
              System.out.println("Bad number of parameters 0 or more than 1!");
              gameLottery();
        }
        else
        {
            int tmp1=0;
            boolean error=false;
            
            try{
                tmp1=Integer.parseInt(args[0]);
            }
            catch(NumberFormatException e)
            { 
                System.err.println("Parameter not int!!!!");
                error=true;
            }
            
            if(error==false)
            {
                //tmp1<=3||tmp1>=1
               
                    
                 if(tmp1<=3&&tmp1>=1)
                 {
                       gameLotteryViaParameter(tmp1);
                 }
                else
                  {
                     System.out.println("Bad parameter choose intger: 1-3");
                    gameLottery();
                  }
                
                
            
                
            }
            else
            {
                gameLottery();
            }
            
        }
      }
}
