/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanie4.losowanie;
import com.mycompany.zadanie4.widok.View;
import com.mycompany.zadanie4.model.*;
import java.util.ArrayList;
import java.util.List;

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
    
    
    public Controller(){
    }

 /**
 * Lotery lotto,ganerated lotto score and set in model
 * 
 */    
    public void losowanieLotto(){
        List<Integer> wynik=losowanieNumerow(model.getZakresLotto(),6);
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
        List<Integer> wynik=losowanieNumerow(model.getZakresMultiLotto(),20);
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
        List<Integer> wynik=losowanieNumerow(model.getZakresMiniLotto(),5);
        try{
        model.setWynikMiniLotto(wynik);
        }
        catch(EmptyArrayException e)
        {
            System.err.println(e.getMessage());
        }
    };
      
 /**
 * Generating random list
 * @param zakres range of generation
 * @param iloscLiczb size of list
 * @return generated list
 * 
 */       
      public List<Integer>losowanieNumerow(int zakres,int iloscLiczb) 
      {
          List<Integer> tmp= new ArrayList(iloscLiczb);
          for(int i=0;i<iloscLiczb;i++)
          {
              tmp.add(0);
          }
         
        
          
          int generated=model.ganerateNumber(zakres);
          for(int i=0;i<iloscLiczb;i++)
          {
              while(model.isInArray(tmp,generated))
              {
                  generated=model.ganerateNumber(zakres);
              }
               tmp.set(i,generated);
               generated=model.ganerateNumber(zakres);
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
              try{
               widok.wyswietlWynik(model.getWynikLotto(), model.getNazwaZPozycji(1));
              }
              catch(Exception e)
              {
                  
              }
          }
          else if(model.getNrOpcji()==2)
          {
              try{
              widok.wyswietlWynik(model.getWynikMultiLotto(), model.getNazwaZPozycji(2));
              }
              catch(Exception e)
              {
                  System.out.println(e.getMessage());
              }
          }
          else
          {
              try{
          widok.wyswietlWynik(model.getWynikMiniLotto(), model.getNazwaZPozycji(3));
              }
              catch(Exception e)
              {
                  System.out.println(e.getMessage());
              }
          }
          
         
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
      public void game(String... args)
      {
      if(args.length==0||args.length>1)
        {
        
             widok.errorMessage(); 
            widok.errorMessage(1);
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
                widok.errorMessage();
                widok.errorMessage(2);
                error=true;
            }
            
            if(error==false)
            {
               
               
                    
                 if(tmp1<=3&&tmp1>=1)
                 {
                       gameLotteryViaParameter(tmp1);
                 }
                else
                  {
                      widok.errorMessage();
                     widok.errorMessage(3);
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
