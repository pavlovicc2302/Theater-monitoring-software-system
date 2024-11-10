/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.table;

import domain.Glumac;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class GlumacTableModel extends AbstractTableModel {

    private final String[] kolone = {"Ime", "Prezime", "Email", "Telefon"};
    private ArrayList<Glumac> glumci = new ArrayList<>();

    public GlumacTableModel(ArrayList<Glumac> glumci) {
        this.glumci = glumci;
    }

    public ArrayList<Glumac> getGlumci() {
        return glumci;
    }

    public void setGlumci(ArrayList<Glumac> glumci) {
        this.glumci = glumci;
        fireTableDataChanged();

    }

    @Override
    public int getRowCount() {
        return glumci.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Glumac g = glumci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return g.getIme();
            case 1:
                return g.getPrezime();
            case 2:
                return g.getEmail();
            case 3:
                return g.getTelefon();
            default:
                return "";
        }
    }
    
    public Glumac vratiGlumca(int row){
        return glumci.get(row);
    }

}
