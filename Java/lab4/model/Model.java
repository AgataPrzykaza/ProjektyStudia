/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.zadanie4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the data for application
 * @author Agata Przykaza
 * @version 1.0
 */

public class Model {
   /**
 *
 * Range of Lotto
 */
    private int zakresLotto=49;
    
    /**
 *
 * Range of multiLotto
 */
    private int zakresMultiLotto=80;
    /**
 *
 * Range of MiniLotto
 */
    private int zakresMiniLotto=42;
    
    /**
 *
 * List containing lotto score
 */
    private List<Integer> wynikLotto= new ArrayList();
       /**
 *
 * List containing multilotto score
 */
    private List<Integer> wynikMultiLotto= new ArrayList();
   /**
 *
 * List containing minilotto score
 */
    private List<Integer> wynikMiniLotto= new ArrayList();
    
   /**
 *
 * List containing names of games
 */    
    private List<String> nazwyGry =new ArrayList(3); 
    
       /**
 *
 * Number of game type
 */
    int nrOpcji;

 /**
 * Sets the value of lotto score
 * @param settingValue setting list
 * @throws EmptyArrayException when empty list
 */
    public void setWynikLotto(List<Integer> settingValue)throws EmptyArrayException  
    {
        if(settingValue.size()==0)
        {
            throw new EmptyArrayException("Empty array");
        }
        else
        {
             wynikLotto=settingValue;
        }
    }
 /**
 * Sets the value of multilotto score
 * @param settingValue setting list
 * @throws EmptyArrayException when empty list
 */    
    public void setWynikMultiLotto(List<Integer> settingValue)throws EmptyArrayException 
    {
        if(settingValue.size()==0)
        {
            throw new EmptyArrayException("Empty array");
        }
        else
        {
        wynikMultiLotto=settingValue;
        }
    }
  /**
 * Sets the value of minilotto score
 * @param settingValue setting list
 * @throws EmptyArrayException when empty list
 */    
    public void setWynikMiniLotto(List<Integer> settingValue)throws EmptyArrayException 
    {    if(settingValue.size()==0)
        {
            throw new EmptyArrayException("Empty array");
        }
        else
        {
        wynikMiniLotto=settingValue;
        }
    }
    /**
 *
 * Gets the lotto score
 * @return this lotto score
 */
    public List<Integer> getWynikLotto()
    {
        return wynikLotto;
    }
      /**
 *
 * Gets the multilotto score
 * @return this multilotto score
 */
    public List<Integer> getWynikMultiLotto()
    {
        return wynikMultiLotto;
    }
      /**
 *
 * Gets the minilotto score
 * @return this minilotto score
 */ 
    public List<Integer> getWynikMiniLotto()
    {
        return wynikMiniLotto;
    }
    /**
 *
 * Gets the name of game from given int position
 * @param i number of game
 * @throws Exception
 * @return String name of game or empty 
 */ 
    public String getNazwaZPozycji(int i) throws Exception 
    {  
        
        if(i>nazwyGry.size()||i<=0)
        {
            
            throw new Exception("Out of ranges");
            
        }
       
        return nazwyGry.get(i-1);
    }
   /**
 *
 * Gets the names of games
 * @return nazwyGry name of games
 */   
    public List<String> getNazwy()
    {
        return nazwyGry;
    }
   /**
 *
 * Gets the lotto range
 * @return  range of lotto
 */      
    public int getZakresLotto()
    {
        return zakresLotto;
    }
    
       /**
 *
 * Gets the multilotto range
 * @return  range of multilotto
 */  
    public int getZakresMultiLotto()
    {
        return zakresMultiLotto;
    }
       /**
 *
 * Gets the minilotto range
 * @return  range of minilotto
 */     
    public int getZakresMiniLotto()
    {
        return zakresMiniLotto;
    }
         /**
 *
 * Gets the chosen option
 * @return  chosen option
 */   
    public int getNrOpcji()
    {
        return nrOpcji;
    }
          /**
 *
 * Sets the chosen option
 * @param nr chosen option
 * 
 */  
    public void setNrOpcji(int nr)
    {
        nrOpcji=nr;
    }
           /**
 *
 * Constructor
 * 
 */ 
    public Model(){
        initNazwyGier();
    };
    
/**
 * Initialize the name of games
 */
    public void initNazwyGier() 
    {    
       
        nazwyGry.add("Lotto");
         nazwyGry.add("Multi Lotto");
         nazwyGry.add("Mini Lotto");
       
    }
    
       /**
 * Generates random number for given range
 * @param zakres range of generation
 * @return int
 */   
      public int ganerateNumber(int zakres)
      { 
          Random rand=new Random();
          int randomNum=rand.nextInt(zakres)+1;
          return randomNum;
      }
      
  
  /**
 * Checks if given value is already in list
 * @param ar list
 * @param value value int
 * @return boolean true or false
 * 
 */    
      public boolean isInArray(List<Integer> ar,int value)
      { 

          for(Integer i:ar)
          {
              if(i==value)
              {
                  return true;
              }
          }
                return false;
      }
      
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
         
        
          
          int generated=ganerateNumber(zakres);
          for(int i=0;i<iloscLiczb;i++)
          {
              while(isInArray(tmp,generated))
              {
                  generated=ganerateNumber(zakres);
              }
               tmp.set(i,generated);
               generated=ganerateNumber(zakres);
          }
          
          
          return tmp;
      }
      
      
       /**
 * Lotery minilotto,ganerated minilotto score and set in model
 * 
 */     
    public  void losowanieMiniLotto(){
        List<Integer> wynik=losowanieNumerow(getZakresMiniLotto(),5);
        try{
       setWynikMiniLotto(wynik);
        }
        catch(EmptyArrayException e)
        {
            
             
        }
    };
      
      
       /**
 * Lotery lotto,ganerated lotto score and set in model
 * 
 */    
    public void losowanieLotto(){
        List<Integer> wynik=losowanieNumerow(getZakresLotto(),6);
        try{
        setWynikLotto(wynik);
        }
        catch(EmptyArrayException e){
        
       
        }
    };
    
  /**
 * Lotery mutlilotto,ganerated multilotto score and set in model
 * 
 */ 
     public void losowanieMultiLotto(){
        List<Integer> wynik=losowanieNumerow(getZakresMultiLotto(),20);
        try{
        setWynikMultiLotto(wynik);
        }
        catch(EmptyArrayException e){
         
           
        }
        
        };
}

