/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.zadanie4;
import com.mycompany.zadanie4.widok.*;
import com.mycompany.zadanie4.model.Model;
import com.mycompany.zadanie4.losowanie.Controller;




/**
 *Aplication ganerates given lotery game.
 * First an only parameter is a int number form 1 to 3
 * Each number represents an lotery option
 * 1.Lotto
 * 2.Multi lotto
 * 3.Mini lotto
 * @author Agata Przykaza
 * @version 1.0
 */
public class Zadanie4 {

    public static void main(String[] args) {
       
        
        
        Model num= new Model();
        Okienko okno=new Okienko();
        Controller kontroler = new Controller(num,okno);
       kontroler.initOkno();
       kontroler.game(args);
    
      
      
       

    }
}
