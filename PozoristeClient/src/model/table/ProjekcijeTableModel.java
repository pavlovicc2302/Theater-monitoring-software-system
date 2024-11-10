/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class ProjekcijeTableModel extends AbstractTableModel {

    private final String[] kolone = {"Rb. projekcije","Naziv predstave", "Datum prikazivanja", "Vreme prikazivanja", "Sala", "Status", "Glumac za napomenu", "Napomena","Admin"};
    private ArrayList<Object[]> lista;

    public ProjekcijeTableModel(ArrayList<Object[]> lista) {
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
        Object[] row = lista.get(rowIndex);
        return row[columnIndex];
    }

    public List<Object[]> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Object[]> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public void izbaciProjekciju(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

   

}
