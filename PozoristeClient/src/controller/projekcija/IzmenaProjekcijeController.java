/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.projekcija;

import domain.Administrator;
import domain.Glumac;
import domain.GlumacProjekcije;
import domain.Predstava;
import domain.Projekcija;
import domain.Sala;
import forms.IzmenaProjekcijeForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class IzmenaProjekcijeController {

    private final IzmenaProjekcijeForm izmenaProjekcijeForm;
    private Object[] projekcija;
    private ArrayList<Glumac> listaGlumaca = new ArrayList<>();
    private ArrayList<Sala> listaSala = new ArrayList<>();
    private Administrator admin;

    public IzmenaProjekcijeController(IzmenaProjekcijeForm izmenaProjekcijeForm, Object[] projekcija, Administrator admin) {
        this.izmenaProjekcijeForm = izmenaProjekcijeForm;
        this.projekcija = projekcija;
        this.admin = admin;

        izmenaProjekcijeForm.getTxtNazivPredstave().setText((String) projekcija[1]);
        izmenaProjekcijeForm.getTxtNazivPredstave().setEditable(false);

        izmenaProjekcijeForm.getTxtDatum().setText((String) projekcija[2]);
        izmenaProjekcijeForm.getTxtVreme().setText((String) projekcija[3]);

        izmenaProjekcijeForm.getTxtNazivSale().setText((String) projekcija[4]);
        izmenaProjekcijeForm.getTxtNazivSale().setEditable(false);

        izmenaProjekcijeForm.getTxtStatus().setText((String) projekcija[5]);
        izmenaProjekcijeForm.getTxtStatus().setEditable(false);

        izmenaProjekcijeForm.getTxtGlumacNapomena().setText((String) projekcija[6]);
        izmenaProjekcijeForm.getTxtGlumacNapomena().setEditable(false);

        izmenaProjekcijeForm.getTxtNapomena().setText((String) projekcija[7]);

        addActionListener();

    }

    public void openForm(Object[] projekcija) {
        prepareForm();
        izmenaProjekcijeForm.setTitle("Izmena projekcije");
        izmenaProjekcijeForm.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(izmenaProjekcijeForm, "Sistem je učitao projekciju");
        izmenaProjekcijeForm.setVisible(true);
    }

    private void prepareForm() {
        try {
            ucitajSaleUCmb();
            ucitajGlumceUCmb();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(izmenaProjekcijeForm, "Sistem ne može da učita projekciju", "Greška", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void ucitajSaleUCmb() throws Exception {
        izmenaProjekcijeForm.getCmbSala().removeAllItems();
        listaSala = communication.Communication.getInstance().ucitajListuSala();
        for (Sala s : listaSala) {
            izmenaProjekcijeForm.getCmbSala().addItem(s);
        }
    }

    private void ucitajGlumceUCmb() throws Exception {
        izmenaProjekcijeForm.getCmbGlumacNapomena().removeAllItems();
        listaGlumaca = communication.Communication.getInstance().ucitajListuGlumaca();
        for (Glumac g : listaGlumaca) {
            izmenaProjekcijeForm.getCmbGlumacNapomena().addItem(g);
        }
    }

    private void addActionListener() {

        izmenaProjekcijeForm.btnSacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    long projekcijaID = (long) projekcija[0];
                    String nazivPredstave = (String) projekcija[1];
                    Predstava predstava = pronadjiPredstavu(nazivPredstave);

                    String datumPolje = izmenaProjekcijeForm.getTxtDatum().getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    java.util.Date utilDatum = sdf.parse(datumPolje);
                    java.sql.Date datum = new java.sql.Date(utilDatum.getTime());

                    String vremePolje = izmenaProjekcijeForm.getTxtVreme().getText();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime localTime = LocalTime.parse(vremePolje, dtf);
                    java.sql.Time vreme = java.sql.Time.valueOf(localTime);

                    Sala sala = (Sala) izmenaProjekcijeForm.getCmbSala().getSelectedItem();
                    String status = izmenaProjekcijeForm.getTxtStatus().getText();

                    Glumac glumacNapomena = (Glumac) izmenaProjekcijeForm.getCmbGlumacNapomena().getSelectedItem();
                    String napomena = izmenaProjekcijeForm.getTxtNapomena().getText();

                    Projekcija izmenjenaProjekcija = new Projekcija(projekcijaID, datum, vreme, status, sala, predstava, admin);
                    GlumacProjekcije izmenjenaNapomena = new GlumacProjekcije(izmenjenaProjekcija, glumacNapomena, napomena);

                    boolean uspesnoIzmenjenaProjekcija = communication.Communication.getInstance().izmeniProjekciju(izmenjenaProjekcija);
                    boolean uspesnoIzmenjenaNapomena = communication.Communication.getInstance().izmeniGlumacProjekcije(izmenjenaNapomena);

                    if (uspesnoIzmenjenaProjekcija && uspesnoIzmenjenaNapomena) {
                        JOptionPane.showMessageDialog(izmenaProjekcijeForm, "Sistem je izmenio projekciju.");
                        izmenaProjekcijeForm.dispose();
                    } else {
                        JOptionPane.showMessageDialog(izmenaProjekcijeForm, "Sistem ne može da izmeni projekciju", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(izmenaProjekcijeForm, "Sistem ne može da izmeni projekciju", "Greška", JOptionPane.ERROR_MESSAGE);

                    Logger.getLogger(IzmenaProjekcijeController.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }

            }

        });

        izmenaProjekcijeForm.btnOtkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    long projekcijaID = (long) projekcija[0];
                    String nazivPredstave = (String) projekcija[1];
                    Predstava predstava = pronadjiPredstavu(nazivPredstave);

                    String datumPolje = izmenaProjekcijeForm.getTxtDatum().getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    java.util.Date utilDatum = sdf.parse(datumPolje);
                    java.sql.Date datum = new java.sql.Date(utilDatum.getTime());

                    String vremePolje = izmenaProjekcijeForm.getTxtVreme().getText();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime localTime = LocalTime.parse(vremePolje, dtf);
                    java.sql.Time vreme = java.sql.Time.valueOf(localTime);

                    Sala sala = (Sala) izmenaProjekcijeForm.getCmbSala().getSelectedItem();

                    Glumac glumacNapomena = (Glumac) izmenaProjekcijeForm.getCmbGlumacNapomena().getSelectedItem();
                    String napomena = izmenaProjekcijeForm.getTxtNapomena().getText();

                    String statusOtkazana = "Otkazana";
                    Projekcija otkazanaProjekcija = new Projekcija(projekcijaID, datum, vreme, statusOtkazana, sala, predstava,admin);

                    boolean uspesnoOtkazanaProjekcija = communication.Communication.getInstance().otkaziProjekciju(otkazanaProjekcija);
                    if (uspesnoOtkazanaProjekcija) {
                        JOptionPane.showMessageDialog(izmenaProjekcijeForm, "Sistem je otkazao projekciju.", "Projekcije", JOptionPane.INFORMATION_MESSAGE);
                        izmenaProjekcijeForm.dispose();
                    } else {
                        JOptionPane.showMessageDialog(izmenaProjekcijeForm, "Sistem ne može da otkaže projekciju", "Greška", JOptionPane.ERROR_MESSAGE);
                        izmenaProjekcijeForm.dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(izmenaProjekcijeForm, "Sistem ne može da otkaže projekciju", "Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(IzmenaProjekcijeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    public Predstava pronadjiPredstavu(String nazivPredstave) throws Exception {
        ArrayList<Predstava> predstave = communication.Communication.getInstance().ucitajListuPredstava();
        for (Predstava p : predstave) {
            if (p.getNaziv().equals(nazivPredstave)) {
                return p;
            }
        }
        return null;
    }

}
