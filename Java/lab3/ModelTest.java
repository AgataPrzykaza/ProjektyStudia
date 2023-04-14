/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.zadanie4.model;


import com.mycompany.zadanie4.losowanie.Controller;
import com.mycompany.zadanie4.widok.Okienko;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.*;


/**
 * Tests for aplications
 * @author Agata Przykaza
 * @version 1.0
 */
public class ModelTest {
    
    /**
     * Tests the setting of list
     * @throws Exception
    */
     @ParameterizedTest
     @EmptySource
    public void testSetWynikLotto(List<Integer> val) throws Exception {
        try {
           Model m=new Model();
           m.setWynikLotto(val);
        } catch (EmptyArrayException e) {
            fail("Empty list");
        }
    }
    
    /**
     * Tests the setting of list
     * @throws Exception
    */
    @ParameterizedTest
     @EmptySource
    public void testSetWynikMultiLotto(List<Integer> val) throws Exception {
        try {
           Model m=new Model();
           m.setWynikMultiLotto(val);
        } catch (EmptyArrayException e) {
            fail("Empty list");
        }
    }
    
   /**
     * Tests the setting of list
     * @throws Exception
    */
    @ParameterizedTest
    @EmptySource
    public void testSetWynikMiniLotto(List<Integer> val) throws Exception {
        try {
           Model m=new Model();
           m.setWynikMiniLotto(val);
        } catch (EmptyArrayException e) {
            fail("Empty list");
        }
    }
    
    /**
     * Tests getting string from list for an specific position
     * @throws Exception
    */
     @ParameterizedTest
     @CsvSource({"1,Lotto","2,Multi Lotto","3,Mini Lotto"})
    public void testGetNazwaZPozycji(int number,String expected)throws Exception {
        try{Model m=new Model();
            String actual=m.getNazwaZPozycji(number);
        }
        catch(Exception e)
        {
            fail("out of range");
        }
        
    }

    /**
     * Tests checking if elemrnt is in list
     * @param input
     * @param expected
    */
    @ParameterizedTest
    @CsvSource({"1,true","2,true","3,false"})
    public void testIsInArray(int input,boolean expected) {
       List<Integer> tmp=Arrays.asList(1, 2,4,5,6);
       Model c=new Model();
       boolean actual=c.isInArray(tmp, input);
       assertEquals(expected,actual , "Function isInArray gives wrong output");
       
    }
    /**
     * Tests generating number
     * @param a 
     */
     @ParameterizedTest
     @ValueSource(ints = {2,3,4})
     public void testRamdomGenerating(int a)
    {
         Model m=new Model();
         int num=m.ganerateNumber(a);
         assertTrue(  num<a+1,"Bad generation");
    }
     /**
      * Test generating number list if good list size created
      * @param ile 
      */
     @ParameterizedTest
     @ValueSource(ints = {2,3,4,0})
     public void testLosowanieNumerow(int ile)
     {
         Model m=new Model();
        
         Okienko o=new Okienko();
         Controller c=new Controller(m,o);
         List<Integer> l=c.losowanieNumerow(12,ile);
         assertEquals(l.size(),ile,"Bad lenght of list");
     }
    /**
     * Tests initialization of name games
     */
     @Test
     public void testInitialNazwy()
     {
          Model m=new Model();
          assertTrue(m.getNazwy().size()==3);
     }
    
}
