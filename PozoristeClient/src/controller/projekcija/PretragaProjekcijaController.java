/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.projekcija;

import coordinator.ClientCoordinator;
import domain.Administrator;
import domain.Predstava;
import domain.Projekcija;
import forms.PretragaProjekcijaForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.table.ProjekcijeTableModel;

/**
 *
 * @author Ana
 */
public class PretragaProjekcijaController {

    private final PretragaProjekcijaForm projekcijaForm;
    private ProjekcijeTableModel model;
    private ArrayList<Object[]> sveProjekcije;
    private ArrayList<Object[]> projekcijePretraga;
    boolean signal = false;
    private Administrator admin;

    public PretragaProjekcijaController(PretragaProjekcijaForm projekcijaForm, Administrator admin) {
        this.projekcijaForm = projekcijaForm;
        this.admin = admin;
        addActionListener();
    }

    public PretragaProjekcijaForm getProjekcijaForm() {
        return projekcijaForm;
    }

    private void addActionListener() {
        projekcijaForm.btnPretragaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String vrednost = projekcijaForm.getTxtNazivPredstave().getText();
                    if (vrednost.isEmpty()) {
                        JOptionPane.showMessageDialog(projekcijaForm, "Morate uneti vrednosti za pretragu.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Predstava predstava = new Predstava();
                    predstava.setNaziv(vrednost);
                    Projekcija projekcijaPredstava = new Projekcija(-1, null, null, null, null, predstava, admin);
                    projekcijePretraga = communication.Communication.getInstance().nadjiProjekcije(projekcijaPredstava);

                    if (projekcijePretraga.isEmpty()) {
                        JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne može da nađe projekcije po zadatoj vrednosti.", "Greška", JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    JOptionPane.showMessageDialog(projekcijaForm, "Sistem je našao projekcije po zadatoj vrednosti.", "Projekcije", JOptionPane.INFORMATION_MESSAGE);

                    model = (ProjekcijeTableModel) projekcijaForm.getTblProjekcije().getModel();
                    model.setLista(projekcijePretraga);
                    signal = true;

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne može da nađe projekcije po zadatoj vrednosti.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        projekcijaForm.btnResetujAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prepareForm();
                    signal = false;
                    projekcijaForm.getTxtNazivPredstave().setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne moze da učita listu projekcija.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        projekcijaForm.btnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = projekcijaForm.getTblProjekcije().getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(projekcijaForm, "Morate izbrati projekciju cije podatke zelite da izmenite");
                        return;
                    }
                    if (!signal) {
                        projekcijePretraga = communication.Communication.getInstance().ucitajListuProjekcija();
                        Object[] projekcija = sveProjekcije.get(row);
                        coordinator.ClientCoordinator.getInstance().openIzmenaProjekcijeForm(projekcijaForm, projekcija, admin);
                        return;
                    }
                    Object[] projekcija = projekcijePretraga.get(row);
                    coordinator.ClientCoordinator.getInstance().openIzmenaProjekcijeForm(projekcijaForm, projekcija, admin);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne moze da učita projekciju.");
                }
            }

        });
    }

    public void openForm() {
        try {
            prepareForm();
            projekcijaForm.setLocationRelativeTo(ClientCoordinator.getInstance().getMainController().getMainForm());
            projekcijaForm.setTitle("Pretraga projekcija");
            projekcijaForm.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne može da učita listu projekcija", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void prepareForm() throws Exception {
        ArrayList<Object[]> lista = communication.Communication.getInstance().ucitajListuProjekcija();
        model = new ProjekcijeTableModel(lista);
        projekcijaForm.getTblProjekcije().setModel(model);
        sveProjekcije = lista;
    }

}
