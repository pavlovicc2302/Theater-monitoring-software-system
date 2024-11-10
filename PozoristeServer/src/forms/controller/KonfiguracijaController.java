/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.controller;

import forms.KonfiguracijaForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import server.Settings;

/**
 *
 * @author Ana
 */
public class KonfiguracijaController {

    private final KonfiguracijaForm konfiguracijaForm;

    public KonfiguracijaController(KonfiguracijaForm konfiguracijaForm) {
        this.konfiguracijaForm = konfiguracijaForm;
        addActionListener();
    }

    public void openForm() {
        konfiguracijaForm.setTitle("Konfiguracija");
        konfiguracijaForm.setLocationRelativeTo(null);
        konfiguracijaForm.setVisible(true);
    }

    private void addActionListener() {
        konfiguracijaForm.btnSacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = konfiguracijaForm.getTxtUrl().getText().trim();
                String username = konfiguracijaForm.getTxtUsername().getText().trim();
                String password = String.valueOf(konfiguracijaForm.getTxtPassword().getPassword()).trim();

                if (url.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(konfiguracijaForm, "Polja za username i password moraju biti popunjena!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!(url.contains("jdbc:mysql://localhost:"))) {
                    JOptionPane.showMessageDialog(konfiguracijaForm, "Neispravno unet url", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Settings.getInstance().setURL(url);
                Settings.getInstance().setUsername(username);
                Settings.getInstance().setPassword(password);
                Settings.getInstance().sacuvajPodesavanja();

                JOptionPane.showMessageDialog(konfiguracijaForm, "Uspešno ste izmenili podešavanja", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                konfiguracijaForm.dispose();
            }

        });
    }

}
