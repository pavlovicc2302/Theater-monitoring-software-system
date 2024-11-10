/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.projekcija;

import communication.Communication;
import domain.Administrator;
import domain.GenericObjectDomain;
import domain.Glumac;
import domain.GlumacProjekcije;
import domain.Predstava;
import domain.Projekcija;
import domain.Sala;
import domain.Uloga;
import forms.ProjekcijaForm;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.table.GlumacUlogaTableModel;
import model.table.ProjekcijeTableModel;

/**
 *
 * @author Ana
 */
public class ProjekcijaController {
    
    private final ProjekcijaForm projekcijaForm;
    private GlumacUlogaTableModel model;
    private ProjekcijeTableModel modelProjekcija;
    private ArrayList<Uloga> uloge;
    private Predstava izabranaPredstava = new Predstava();
    private boolean signal = false;
    private Administrator admin;
    private List<GenericObjectDomain> listaBaza = new ArrayList<>();
    private ArrayList<Object[]> listaModel = new ArrayList<>();
    int rb = 0;
    
    public ProjekcijaController(ProjekcijaForm projekcijaForm, Administrator admin) {
        this.projekcijaForm = projekcijaForm;
        this.admin = admin;
        
        try {
            izabranaPredstava = communication.Communication.getInstance().ucitajListuPredstava().get(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne može da učita listu predstava", "Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ProjekcijaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addActionListener();
    }
    
    public void openForm() {
        projekcijaForm.setLocationRelativeTo(projekcijaForm.getParent());
        prepareForm();
        projekcijaForm.setTitle("Kreiranje projekcije");
        projekcijaForm.setVisible(true);
        
    }
    
    private void addActionListener() {
        
        projekcijaForm.btnPrikaziUlogeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Predstava predstava = (Predstava) projekcijaForm.getCmbPredstave().getSelectedItem();
                    List<String[]> lista = communication.Communication.getInstance().ucitajUlogeGlumaca(predstava);
                    model = (GlumacUlogaTableModel) projekcijaForm.getTblGlumciUloge().getModel();
                    model.setLista(lista);
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne može da učita glumce i njihove uloge u izabranoj predstavi.", "Greška", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
            
        });
        
        projekcijaForm.btnKreirajProjekcijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    Communication.getInstance().kreirajProjekciju(listaBaza);
                    
                    JOptionPane.showMessageDialog(projekcijaForm, "Sistem je kreirao projekcije.", "Projekcije", JOptionPane.INFORMATION_MESSAGE);
                    projekcijaForm.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne moze da kreira projekcije.", "Projekcije", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(ProjekcijaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        projekcijaForm.btnDodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Sala sala = (Sala) projekcijaForm.getCmbSala().getSelectedItem();
                    Predstava predstava = (Predstava) projekcijaForm.getCmbPredstave().getSelectedItem();
                    
                    if (projekcijaForm.getTxtDatum().getText().isEmpty() || projekcijaForm.getTxtVreme().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(projekcijaForm, "Morate popuniti sva polja.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    String datumPolje = projekcijaForm.getTxtDatum().getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    sdf.setLenient(false);
                    java.util.Date utilDatum = sdf.parse(datumPolje);
                    java.util.Date danas = sdf.parse(sdf.format(new Date()));                    
                    
                    if (!utilDatum.after(danas)) {
                        JOptionPane.showMessageDialog(projekcijaForm, "Datum projekcije mora biti posle današnjeg datuma. ", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    java.sql.Date datum = new java.sql.Date(utilDatum.getTime());
                    
                    String vremePolje = projekcijaForm.getTxtVreme().getText();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime localTime = LocalTime.parse(vremePolje, dtf);
                    java.sql.Time vreme = java.sql.Time.valueOf(localTime);
                    
                    String status = projekcijaForm.getTxtStatus().getText();
                    
                    Projekcija projekcija = new Projekcija(-1, datum, vreme, status, sala, predstava, admin);
                    if (listaBaza.contains(projekcija)) {
                        JOptionPane.showMessageDialog(projekcijaForm, "Vec postoji projekcija u toj sali na taj datum i vreme");
                        return;
                    }
                    projekcija.setProjekcijaID(++rb);
                    
                    String napomena = projekcijaForm.getTxtNapomena().getText();
                    Glumac glumacNapomena = (Glumac) projekcijaForm.getCmbGlumacNapomena().getSelectedItem();
                    
                    GlumacProjekcije glumacProjekcije = new GlumacProjekcije(projekcija, glumacNapomena, napomena);
                    
                    Object[] projekcijaNapomena = new Object[9];
                    projekcijaNapomena[0] = projekcija.getProjekcijaID();
                    projekcijaNapomena[1] = predstava.getNaziv();
                    projekcijaNapomena[2] = datum;
                    projekcijaNapomena[3] = localTime;
                    projekcijaNapomena[4] = sala.getNaziv();
                    projekcijaNapomena[5] = status;
                    projekcijaNapomena[6] = glumacNapomena.getIme();
                    projekcijaNapomena[7] = napomena;
                    projekcijaNapomena[8] = admin;
                    
                    listaModel.add(projekcijaNapomena);
                    modelProjekcija = new ProjekcijeTableModel(listaModel);
                    projekcijaForm.getTblProjekcije().setModel(modelProjekcija);
                    
                    listaBaza.add(projekcija);
                    listaBaza.add(glumacProjekcije);
                    
                    projekcijaForm.getTxtDatum().setText("");
                    projekcijaForm.getTxtVreme().setText("");
                    projekcijaForm.getTxtNapomena().setText("");
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(projekcijaForm, "Sistem ne može da kreira projekciju.", "Greška", JOptionPane.ERROR_MESSAGE);
                    projekcijaForm.dispose();
                }
            }
            
        });
    }
    
    private void prepareForm() {
        try {
            ucitajPredstaveUCb();
            ucitajSaleUCb();
            ucitajGlumceUTbl();
            ucitajGlumceUCmb();
            
            modelProjekcija = new ProjekcijeTableModel(listaModel);
            projekcijaForm.getTblProjekcije().setModel(modelProjekcija);
            projekcijaForm.getTxtStatus().setEditable(false);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(projekcijaForm, "Greska pri učitavanju forme.", "Greška", JOptionPane.ERROR_MESSAGE);
            //Logger.getLogger(ProjekcijaController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    
    private void ucitajPredstaveUCb() throws Exception {
        projekcijaForm.getCmbPredstave().removeAllItems();
        ArrayList<Predstava> predstave = Communication.getInstance().ucitajListuPredstava();
        for (Predstava p : predstave) {
            projekcijaForm.getCmbPredstave().addItem(p);
        }
        izabranaPredstava = predstave.get(0);
    }
    
    private void ucitajSaleUCb() throws Exception {
        projekcijaForm.getCmbSala().removeAllItems();
        ArrayList<Sala> sale = Communication.getInstance().ucitajListuSala();
        for (Sala s : sale) {
            projekcijaForm.getCmbSala().addItem(s);
        }
    }
    
    private void ucitajGlumceUTbl() throws Exception {
        
        List<String[]> lista = communication.Communication.getInstance().ucitajUlogeGlumaca(izabranaPredstava);
        model = new GlumacUlogaTableModel(lista);
        projekcijaForm.getTblGlumciUloge().setModel(model);
        
    }
    
    private void ucitajGlumceUCmb() throws Exception {
        projekcijaForm.getCmbGlumacNapomena().removeAllItems();
        ArrayList<Glumac> glumci = Communication.getInstance().ucitajListuGlumaca();
        for (Glumac g : glumci) {
            projekcijaForm.getCmbGlumacNapomena().addItem(g);
        }
    }
    
}
