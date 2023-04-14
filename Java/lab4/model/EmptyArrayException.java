/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.zadanie4.model;

/**
 *
 * @author SuperStudent.PL
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