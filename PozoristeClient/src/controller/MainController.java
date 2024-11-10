/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import constants.Constants;
import coordinator.ClientCoordinator;
import domain.Administrator;
import forms.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class MainController {
    
    private final MainForm mainForm;
    
    public MainController(MainForm mainForm) {
        this.mainForm = mainForm;
        addActionListener();
    }
    
    public MainForm getMainForm() {
        return mainForm;
    }
    
    public void openForm() {
        Administrator admin = (Administrator) ClientCoordinator.getInstance().getParam(Constants.ADMINISTRATOR);
        mainForm.getLblLoggedUser().setText("Dobrodošli, " + admin.getUsername());
        mainForm.setVisible(true);
        
    }
    
    private void addActionListener() {
        mainForm.btnLogoutAddActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    communication.Communication.getInstance().logout();
                    Administrator admin = (Administrator) ClientCoordinator.getInstance().getParam(Constants.ADMINISTRATOR);
                    
                    JOptionPane.showMessageDialog(mainForm, "Uspesno ste se odjavili sa sistema, " + admin.getUsername(), "Odjava", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainForm, "Greska pri odjavi sa sistema.");
                }
            }
            
        });
        
        mainForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    communication.Communication.getInstance().logout();
                    Administrator admin = (Administrator) ClientCoordinator.getInstance().getParam(Constants.ADMINISTRATOR);
                    
                    JOptionPane.showMessageDialog(mainForm, "Uspesno ste se odjavili sa sistema, " + admin.getUsername(), "Odjava", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainForm, "Greška pri odjavi sa sistema.");
                    
                }
            }
            
        });
        
        mainForm.jmiCreateGlumacAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientCoordinator.getInstance().openGlumacForm();
            }
            
        });
        
        mainForm.jmiPretragaGlumcaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientCoordinator.getInstance().openPretragaGlumcaForm();
            }
            
        });
        
        mainForm.jmiKreirajProjekcijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administrator admin = (Administrator) ClientCoordinator.getInstance().getParam(Constants.ADMINISTRATOR);
                ClientCoordinator.getInstance().openProjekcijaForm(admin);
            }
            
        });
        
        mainForm.jmiNadjiProjekcijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administrator admin = (Administrator) ClientCoordinator.getInstance().getParam(Constants.ADMINISTRATOR);
                ClientCoordinator.getInstance().openPretragaProjekcijaForm(admin);
            }
            
        });
    }
    
}
