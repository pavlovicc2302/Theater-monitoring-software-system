/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import constants.Constants;
import coordinator.ClientCoordinator;
import domain.Administrator;
import forms.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class LoginController {

    private final LoginForm loginForm;

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;
        addActionListener();
    }

    public void openForm() {
        loginForm.setVisible(true);

    }

    private void addActionListener() {
        loginForm.btnLoginAddActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginAdministrator(e);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(LoginController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }

            private void loginAdministrator(ActionEvent e) throws Exception {
                try {
                    String username = loginForm.getTxtUsername().getText().trim();
                    String password = String.valueOf(loginForm.getTxtPassword().getPassword());

                    boolean error = validateData(username, password);
                    if (error) {
                        return;
                    }
                    Administrator admin = communication.Communication.getInstance().loginAdministrator(username, password);
                    if (admin == null) {
                        loginForm.dispose();
                        return;
                    }
                    JOptionPane.showMessageDialog(loginForm, "Uspešno ste se prijavili na sistem.", "Prijava", JOptionPane.INFORMATION_MESSAGE);
                    ClientCoordinator.getInstance().addParam(Constants.ADMINISTRATOR, admin);

                    if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                        ClientCoordinator.getInstance().openMainForm();
                    }
                    loginForm.dispose();

                } catch (Exception ex) {
                    if (ex.getMessage().startsWith("Korisnik je već prijavljen.")) {
                        JOptionPane.showMessageDialog(loginForm, "Korisnik je već prijavljen.", "Greška", JOptionPane.ERROR_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(loginForm, "Sistem ne moze da pronađe administratora na osnovu unetih podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                        throw ex;
                    }
                }
            }

            private boolean validateData(String username, String password) throws Exception {
                String errorMessage = "";
                boolean notOk = true;
                if (username.isEmpty() && password.isEmpty()) {
                    loginForm.getLblError().setText("Oba polja moraju biti popunjena.");
                    loginForm.getLblError().setVisible(true);
                    errorMessage = loginForm.getLblError().getText();
                    return notOk;

                }
                if (username.isEmpty()) {
                    loginForm.getLblError().setText("Polje za korisničko ime mora da bude popunjeno.");
                    loginForm.getLblError().setVisible(true);
                    errorMessage = loginForm.getLblError().getText();
                    return notOk;

                }
                if (password.isEmpty()) {
                    loginForm.getLblError().setText("Polje za lozinku mora da bude popunjeno.");
                    loginForm.getLblError().setVisible(true);
                    errorMessage = loginForm.getLblError().getText();
                    return notOk;

                }
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
                notOk = false;
                return notOk;
            }

        });
    }

}
