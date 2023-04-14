/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanie4.losowanie;
import com.mycompany.zadanie4.widok.*;
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
 */
  
    private Okienko okno;
    /**
 * Model
 * 
 */ 
    private Model model;
  /**
 * Constructor
 * @param o view
 * @param m model
 */   
    public Controller(Model m,Okienko o){
        this.model=m;
        this.okno=o;
        
        
        
        
    };
    
    
    public Controller(){
    }
    
   /**
    * Initialize window
    */
    public void initOkno()
    {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Okienko().setVisible(true);
                showGameOption();
                okno.setVisible(true);
                
                           
            }
        });
    }
    /**
     * Main game
     * @param args app arguments
     */
    public void game(String... args)
    {
        boolean play=true;
     
        if(args.length==0||args.length>1)
        {
            okno.errorMessage(1);
            gameLottery();
       
        }
        else
        {   int tmp1=0;
            boolean error=false;
            try{
                tmp1=Integer.parseInt(args[0]);
            }
            catch(NumberFormatException e)
            { 
               okno.errorMessage(2);
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
                      okno.errorMessage(3);
                      gameLottery();
                  }
                
            }
            else
            {
                gameLottery();
            }
        }
        
        okno.errorMessage(4);
        okno.setButtonDisable();
    }
   
 /**
  * Game with parameter given in app arguments
  * @param opcja chosen option
  */   
    public void gameLotteryViaParameter(int opcja)
    {
        try{
        //okno.showWynik("Wynik",model.getNazwaZPozycji(opcja));
        model.setNrOpcji(opcja);
        lottery(1);
        okno.showWynik(okno.wyswietlNumery(getRightWynik()),model.getNazwaZPozycji(opcja));
            
        }
        catch(Exception e)
        {
            okno.errorMessage(5);
        }
     
        
    }
   /**
    * Game with parameter from window, input in field
    */ 
    public void gameLottery()
    {
        boolean play=true;
         
        while(play==true)
        {
         
            okno.showWynik("","Wynik");
         if(okno.getPrzyciskState()==true)
            {
               
               lottery();
                String option="";
               try{
               option=model.getNazwaZPozycji(model.getNrOpcji());
               }
                catch(Exception e){}
               
               okno.showWynik(okno.wyswietlNumery(getRightWynik()),option);
               
              
               play=false;
           } 
        
        }
        
    }
    
    /**
     * Gets the right lottery depending on option
     * @return list of right lottery score
     */
    public List<Integer> getRightWynik()
    { 
        List<Integer> lista=new ArrayList();
        if(model.getNrOpcji()==1)
        {
            lista= model.getWynikLotto();
        }
        else if(model.getNrOpcji()==2)
        {
            lista=  model.getWynikMultiLotto();
        }
        else if(model.getNrOpcji()==3)
        {
            lista= model.getWynikMiniLotto();
        }
        return lista;
    }
    
    /**
     * Shows the game options in window
     */
    public void showGameOption()
    {
        okno.wyswietlOpcje(model.getNazwy());
    }
    /**
     * Lottery
     * @param opcja chosen game
     */
    public void lottery(int... opcja)
    {
        if(opcja.length==0)
        {
       model.setNrOpcji(okno.giveOptionChosen());
          
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
        else
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
        
        okno.errorMessage(5);
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
         
             okno.errorMessage(5);
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
            
             okno.errorMessage(5);
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
}