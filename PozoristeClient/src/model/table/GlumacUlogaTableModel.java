/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class GlumacUlogaTableModel extends AbstractTableModel {

    private final String[] kolone = {"Naziv uloge", "Ime", "Prezime"};
    private List<String[]> lista;

    public GlumacUlogaTableModel(List<String[]> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
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
        String[] row = lista.get(rowIndex);
        return row[columnIndex];
    }

    public List<String[]> getLista() {
        return lista;
    }

    public void setLista(List<String[]> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    

   

}
