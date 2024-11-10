/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.glumac;

import domain.Glumac;
import forms.IzmenaGlumcaForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class IzmenaGlumcaController {

    private final IzmenaGlumcaForm detaljiForma;
    private Glumac izabraniGlumac;

    public IzmenaGlumcaController(IzmenaGlumcaForm detaljiForma, Glumac glumac) {
        this.detaljiForma = detaljiForma;
        this.izabraniGlumac = glumac;

        detaljiForma.getTxtIme().setText(glumac.getIme());
        detaljiForma.getTxtPrezime().setText(glumac.getPrezime());
        detaljiForma.getTxtEmail().setText(glumac.getEmail());
        detaljiForma.getTxtTelefon().setText(glumac.getTelefon());

        addActionListener();
    }

    public IzmenaGlumcaForm getdetaljiForma() {
        return detaljiForma;
    }

    public void openForm(Glumac glumac) {
        detaljiForma.setLocationRelativeTo(null);
        detaljiForma.setTitle("Detalji o glumcu");
        JOptionPane.showMessageDialog(detaljiForma, "Sistem je učitao glumca.");
        detaljiForma.setVisible(true);

    }

    private void addActionListener() {
        detaljiForma.btnIzmeniGlumcaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (!validForm()) {
                        return;
                    }

                    izabraniGlumac.setIme(detaljiForma.getTxtIme().getText());
                    izabraniGlumac.setPrezime(detaljiForma.getTxtPrezime().getText());
                    izabraniGlumac.setEmail(detaljiForma.getTxtEmail().getText());
                    izabraniGlumac.setTelefon(detaljiForma.getTxtTelefon().getText());

                    boolean uspesno = communication.Communication.getInstance().izmeniGlumca(izabraniGlumac);
                    if (uspesno) {
                        JOptionPane.showMessageDialog(detaljiForma, "Sistem je izmenio glumca.", "Glumci", JOptionPane.INFORMATION_MESSAGE);
                        detaljiForma.dispose();
                    } else {
                        JOptionPane.showMessageDialog(detaljiForma, "Sistem ne može da izmeni glumca.", "Greška", JOptionPane.ERROR_MESSAGE);
                        detaljiForma.dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(detaljiForma, "Sistem ne može da izmeni glumca.", "Greška", JOptionPane.ERROR_MESSAGE);

                }

            }

            private boolean validForm() {
                String email = detaljiForma.getTxtEmail().getText();
                String telefon = detaljiForma.getTxtTelefon().getText();

                if (!izabraniGlumac.validEmailTelefon(email, telefon)[0]) {
                    JOptionPane.showMessageDialog(detaljiForma, "Email adresa treba da bude u formatu test@test.com", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (!izabraniGlumac.validEmailTelefon(email, telefon)[1]) {
                    JOptionPane.showMessageDialog(detaljiForma, "Telefon mora da pocinje sa 06 i da ima ukupno 9 ili 10 cifara", "Greska", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;

            }

        });

        detaljiForma.btnObrisiGlumcaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int odgovor = JOptionPane.showConfirmDialog(detaljiForma, "Da li sigurno želite da obrišete glumca?", "Potvrda", JOptionPane.YES_NO_OPTION);

                if (odgovor == JOptionPane.YES_OPTION) {
                    try {
                        Glumac glumacBrisanje = izabraniGlumac;
                        boolean uspesnoBrisanje = communication.Communication.getInstance().obrisiGlumca(glumacBrisanje);

                        if (uspesnoBrisanje) {
                            JOptionPane.showMessageDialog(detaljiForma, "Sistem je uspešno izbrisao glumca.");
                            detaljiForma.dispose();
                        } else {
                            JOptionPane.showMessageDialog(detaljiForma, "Sistem ne može da obriše glumca jer je angažovan u predstavama i projekcijama", "Greška", JOptionPane.ERROR_MESSAGE);
                            detaljiForma.dispose();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(detaljiForma, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                        detaljiForma.dispose();
                    }

                } else if (odgovor == JOptionPane.NO_OPTION) {
                    return;
                }
            }

        });
    }

}
