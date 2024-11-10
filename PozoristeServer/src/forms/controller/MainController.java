/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.controller;

import forms.FormServerMain;
import forms.coordinator.ServerCoordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import model.table.SalaProjekcijaTableModel;

/**
 *
 * @author Ana
 */
public class MainController {

    private final FormServerMain formServerMain;
    private SalaProjekcijaTableModel model;

    public MainController(FormServerMain formServerMain) {
        this.formServerMain = formServerMain;
        addActionListener();
    }

    public FormServerMain getFormServerMain() {
        return formServerMain;
    }

    public void openForm() {
        formServerMain.setTitle("Server");

        formServerMain.setLocationRelativeTo(null);
        formServerMain.getBtnStopServer().setEnabled(false);

        formServerMain.setVisible(true);
    }

    private void addActionListener() {
        formServerMain.btnStartServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("app.config.properties");
                if (file.exists()) {
                    controller.Controller.getInstance().startServer();
                    formServerMain.getBtnStartServer().setEnabled(false);
                    formServerMain.getBtnStopServer().setEnabled(true);
                    formServerMain.getLblStatusServera().setText("Status servera: Server je POKRENUT!");
                }
            }

        });

        formServerMain.btnStopServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.Controller.getInstance().stopServer();
                formServerMain.getBtnStartServer().setEnabled(true);
                formServerMain.getBtnStopServer().setEnabled(false);
                formServerMain.getLblStatusServera().setText("Status servera: Server je ZAUSTAVLJEN!");
            }

        });

        formServerMain.jmiKonfiguracijaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerCoordinator.getInstance().openKonfiguracijaForm();
            }

        });

        formServerMain.jmiSaleAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerCoordinator.getInstance().openSalaProjekcijeForm();
            }

        });
    }
}
