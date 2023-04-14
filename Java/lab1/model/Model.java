/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanie4.model;



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
 * Array containing lotto score
 */
    private int[] wynikLotto= new int[6];
       /**
 *
 * Array containing multilotto score
 */
    private int[] wynikMultiLotto=new int [20];
   /**
 *
 * Array containing minilotto score
 */
    private int[] wynikMiniLotto= new int [5];
    
   /**
 *
 * Array containing names of games
 */    
    private String[] nazwyGry={"Lotto","Multi Lotto","Mini Lotto"};
    
       /**
 *
 * Number of game type
 */
    int nrOpcji;

 /**
 * Sets the value of lotto score
 * @param settingValue setting array
 * @throws EmptyArrayException when empty array
 */
    public void setWynikLotto(int[] settingValue)throws EmptyArrayException
    {
        if(settingValue.length==0)
        {
            throw new EmptyArrayException("Empty array");
        }
        else
        {
        wynikLotto=settingValue.clone();
        }
    }
 /**
 * Sets the value of multilotto score
 * @param settingValue setting array
 * @throws EmptyArrayException when empty array
 */    
    public void setWynikMultiLotto(int[] settingValue)throws EmptyArrayException
    {
        if(settingValue.length==0)
        {
            throw new EmptyArrayException("Empty array");
        }
        else
        {
        wynikMultiLotto=settingValue.clone();
        }
    }
  /**
 * Sets the value of minilotto score
 * @param settingValue setting array
 * @throws EmptyArrayException when empty array
 */    
    public void setWynikMiniLotto(int[] settingValue)throws EmptyArrayException
    {    if(settingValue.length==0)
        {
            throw new EmptyArrayException("Empty array");
        }
        else
        {
        wynikMiniLotto=settingValue.clone();
        }
    }
    /**
 *
 * Gets the lotto score
 * @return this lotto score
 */
    public int[] getWynikLotto()
    {
        return wynikLotto;
    }
      /**
 *
 * Gets the multilotto score
 * @return this multilotto score
 */
    public int[] getWynikMultiLotto()
    {
        return wynikMultiLotto;
    }
      /**
 *
 * Gets the minilotto score
 * @return this minilotto score
 */ 
    public int[] getWynikMiniLotto()
    {
        return wynikMiniLotto;
    }
    /**
 *
 * Gets the name of game from given int position
 * @return String name of game or empty 
 */ 
    public String getNazwaZPozycji(int i)
    {  
        if(i>nazwyGry.length||i<0)
        {
            System.out.println("Out of range");
            return "";
            
        }
        return nazwyGry[i-1];
    }
   /**
 *
 * Gets the names of games
 * @return nazwyGry name of games
 */   
    public String[] getNazwy()
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
    public Model(){};
    


}




