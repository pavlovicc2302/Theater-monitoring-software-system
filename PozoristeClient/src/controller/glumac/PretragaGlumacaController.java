/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.glumac;

import coordinator.ClientCoordinator;
import domain.Glumac;
import forms.PretragaGlumacaForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.table.GlumacTableModel;

/**
 *
 * @author Ana
 */
public class PretragaGlumacaController {

    private final PretragaGlumacaForm pgf;
    private GlumacTableModel model;
    private ArrayList<Glumac> glumci;
    private ArrayList<Glumac> sviGlumci;
    private boolean signal = false;

    public PretragaGlumacaController(PretragaGlumacaForm pgf) {
        this.pgf = pgf;

        addActionListener();

    }

    public PretragaGlumacaForm getPgf() {
        return pgf;
    }

    private void addActionListener() {
        pgf.btnPretragaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String vrednost = pgf.getTxtImePrezime().getText().trim();
                    if (vrednost.isEmpty()) {
                        JOptionPane.showMessageDialog(pgf, "Morate uneti vrednosti za pretragu.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String ime = razdvojiImePrezime(vrednost).get(0);
                    String prezime = ime;
                    if (razdvojiImePrezime(vrednost).size() == 2) {
                        prezime = razdvojiImePrezime(vrednost).get(1);
                    }

                    Glumac glumac = new Glumac();
                    glumac.setIme(ime);
                    glumac.setPrezime(prezime);
                    glumci = communication.Communication.getInstance().pronadjiGlumce(glumac);
                    if (glumci.isEmpty()) {
                        prepareForm();
                        JOptionPane.showMessageDialog(pgf, "Sistem ne može da nađe glumce po zadatoj vrednosti.", "Greška", JOptionPane.ERROR_MESSAGE);
                        pgf.getTxtImePrezime().setText("");
                        return;
                    }

                   
                    JOptionPane.showMessageDialog(pgf, "Sistem je našao glumce po zadatoj vrednosti.","Glumci",JOptionPane.INFORMATION_MESSAGE);
                    model = (GlumacTableModel) pgf.getTblResultGlumci().getModel();
                    model.setGlumci(glumci);
                    pgf.getTxtImePrezime().setText("");
                    signal = true;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pgf, "Sistem ne može da učita glumca.", "Greška", JOptionPane.ERROR_MESSAGE);
                }

            }

            private ArrayList<String> razdvojiImePrezime(String vrednost) {
                int space = 0;
                ArrayList<String> imePrezimeRazdvojeno = new ArrayList<>();
                String ime = "";
                String prezime = "";

                for (int i = 0; i < vrednost.length(); i++) {
                    if (vrednost.charAt(i) == ' ') {
                        space = i;
                        break;
                    } else {
                        ime += vrednost.charAt(i);
                    }
                }
                imePrezimeRazdvojeno.add(ime);

                if (space == 0) {
                    return imePrezimeRazdvojeno;
                }

                for (int i = space + 1; i < vrednost.length(); i++) {
                    prezime += vrednost.charAt(i);
                }
                imePrezimeRazdvojeno.add(prezime);
                return imePrezimeRazdvojeno;

            }

        });

        pgf.btnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = pgf.getTblResultGlumci().getSelectedRow();
                   
                    if (row == -1) {
                        JOptionPane.showMessageDialog(pgf, "Morate izbrati glumca čije podatke želite da izmenite.");
                    } else {
                        if (!signal) {
                            
                            glumci = communication.Communication.getInstance().ucitajListuGlumaca();
                            Glumac glumac = sviGlumci.get(row);
                            coordinator.ClientCoordinator.getInstance().openIzmenaGlumcaForm(pgf, glumac);
                            return;
                        }
                        Glumac glumac = glumci.get(row);
                        coordinator.ClientCoordinator.getInstance().openIzmenaGlumcaForm(pgf, glumac);
                        
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pgf, "Sistem ne može da učita glumca.");
                }
            }

        });

        pgf.btnResetujAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prepareForm();
                    signal = false;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pgf, "Sistem ne može da učita listu glumaca.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

        });
    }

    public void openForm() {
        try {
            prepareForm();
        } catch (Exception ex) {
            Logger.getLogger(PretragaGlumacaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pgf.setLocationRelativeTo(ClientCoordinator.getInstance().getMainController().getMainForm());
        pgf.setVisible(true);
    }

    private void prepareForm() throws Exception {
        ArrayList<Glumac> glumci = communication.Communication.getInstance().ucitajListuGlumaca();
        model = new GlumacTableModel(glumci);
        pgf.getTblResultGlumci().setModel(model);
        sviGlumci = glumci;
    }

}
