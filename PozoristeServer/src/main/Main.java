/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import controller.Controller;
import forms.FormServerMain;
import forms.coordinator.ServerCoordinator;

/**
 *
 * @author Ana
 */
public class Main {
    public static void main(String[] args) {
        ServerCoordinator.getInstance().openMainForm();
    }
}
