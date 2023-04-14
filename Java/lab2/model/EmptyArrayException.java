/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanie4.model;

/**
 *Represents an own exception for empty array
 * @author Agata Przykaza
 * @version 1.0
 */
  public  class EmptyArrayException extends Exception {

   /**
    * Non-parameter constructor
     */
   public EmptyArrayException() {
    }

    /**
     * Exception class constructor
     *
     * @param message display message
     */
    public EmptyArrayException(String message) {
        super(message);
    }
}