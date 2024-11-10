/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.controller;

import forms.SalaProjekcijeForm;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.table.SalaProjekcijaTableModel;

/**
 *
 * @author Ana
 */
public class SalaProjekcijeController {

    private final SalaProjekcijeForm forma;
    private SalaProjekcijaTableModel model;

    public SalaProjekcijeController(SalaProjekcijeForm forma) {
        this.forma = forma;
        addActionListener();

    }

    private void addActionListener() {

    }

    public void openForm() {
        try {
            prepareForm();
            forma.setTitle("Naslov");
            forma.setLocationRelativeTo(null);
            forma.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(SalaProjekcijeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepareForm() {
        try {
            List<Object[]> listaSala = controller.Controller.getInstance().ucitajListuSalaProjekcija();
            model = new SalaProjekcijaTableModel(listaSala);
            forma.getTblSalaProjekcije().setModel(model);
            model.setLista(listaSala);
        } catch (Exception ex) {
            Logger.getLogger(SalaProjekcijeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
