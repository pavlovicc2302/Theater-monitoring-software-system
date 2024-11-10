/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.glumac;

import domain.Glumac;
import forms.GlumacForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class GlumacController {

    private final GlumacForm glumacForm;

    public GlumacController(GlumacForm glumacForm) {
        this.glumacForm = glumacForm;
        addActionListener();
    }

    private void addActionListener() {
        glumacForm.btnSacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajGlumca();
            }

            private void kreirajGlumca() {
                try {
                    if (postojePraznaPolja()) {
                        JOptionPane.showMessageDialog(glumacForm, "Nisu uneti svi podaci.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String email = glumacForm.getTxtEmail().getText().trim();
                    String telefon = glumacForm.getTxtTelefon().getText().trim();

                    Glumac glumac = new Glumac();
                    glumac.setIme(glumacForm.getTxtIme().getText().trim());
                    glumac.setPrezime(glumacForm.getTxtPrezime().getText().trim());
                    glumac.setEmail(email);
                    glumac.setTelefon(telefon);

                    if (!validForm(glumac)) {
                        return;
                    }

                    communication.Communication.getInstance().kreirajGlumca(glumac);
                    JOptionPane.showMessageDialog(glumacForm, "Sistem je uspešno kreirao glumca.", "Kreiranje glumca", JOptionPane.INFORMATION_MESSAGE);
                    glumacForm.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(glumacForm, "Sistem ne može da kreira glumca.", "Greška", JOptionPane.ERROR_MESSAGE);
                    glumacForm.dispose();
                }

            }

            private boolean postojePraznaPolja() {
                if (glumacForm.getTxtIme().getText().isEmpty() || glumacForm.getTxtPrezime().getText().isEmpty() || glumacForm.getTxtTelefon().getText().isEmpty() || glumacForm.getTxtEmail().getText().isEmpty()) {
                    return true;
                }
                return false;
            }

            private boolean validForm(Glumac glumac) {
                if (!glumac.validEmailTelefon(glumac.getEmail(), glumac.getTelefon())[0]) {
                    JOptionPane.showMessageDialog(glumacForm, "Email adresa mora da bude u formatu test@test.com.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (!glumac.validEmailTelefon(glumac.getEmail(), glumac.getTelefon())[1]) {
                    JOptionPane.showMessageDialog(glumacForm, "Telefon mora da počinje sa 06 i da ima ukupno 9 ili 10 cifara.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;

            }

        });
    }

    public void openForm() {
        glumacForm.setLocationRelativeTo(glumacForm.getParent());
        glumacForm.setVisible(true);
    }

}
