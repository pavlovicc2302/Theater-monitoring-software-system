/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class Sala extends GenericObjectDomain {

    private long salaID;
    private String naziv;
    private int kapacitet;

    public Sala() {
    }

    public Sala(long salaID, String naziv, int kapacitet) {
        this.salaID = salaID;
        this.naziv = naziv;
        this.kapacitet = kapacitet;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public long getSalaID() {
        return salaID;
    }

    public void setSalaID(long salaID) {
        this.salaID = salaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String getTableName() {
        return "sala";
    }

    @Override
    public String getTableAbbreviation() {
        return " S ";
    }

    @Override
    public String getColumnsInsert() {
        return "naziv, kapacitet";
    }

    @Override
    public String getColumnsUpdate() {
        return "naziv, kapacitet";
    }

    @Override
    public String getParamsInsert() {
        return "?,?";
    }

    @Override
    public String getCondition(GenericObjectDomain god) {
        return "";
    }

    @Override
    public String getOrderBy() {
        return " s.naziv";
    }

    @Override
    public String getPrimaryKey(GenericObjectDomain god) {
        Sala sala = (Sala) god;
        return "salaid = " + sala.getSalaID();
    }

    @Override
    public GenericObjectDomain getResult(ResultSet rs) throws Exception {
        GenericObjectDomain god = null;

        if (rs.next()) {
            long salaID = rs.getLong("SalaID");
            String naziv = rs.getString("Naziv");
            int kapacitet = rs.getInt("Kapacitet");

            god = new Sala(salaID, naziv, kapacitet);
        }
        return god;
    }

    @Override
    public ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception {
        ArrayList<GenericObjectDomain> lista = new ArrayList<>();
        while (rs.next()) {
            long salaID = rs.getLong("SalaID");
            String naziv = rs.getString("Naziv");
            int kapacitet = rs.getInt("Kapacitet");

            Sala s = new Sala(salaID, naziv, kapacitet);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String getJoin() {
        return " JOIN PROJEKCIJA PR ON (PR.SALAID = S.SALAID)";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception {
        Sala sala = (Sala) god;
        ps.setString(1, sala.getNaziv());
        ps.setInt(2, sala.getKapacitet());
    }

    @Override
    public String getJoinColumns() {
        return "S.NAZIV AS NAZIV_SALE, COUNT(PR.PROJEKCIJAID) AS BROJ_PROJEKCIJA";
    }

    @Override
    public ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception {
        ArrayList<Object[]> lista = new ArrayList<>();
        while (rs.next()) {
            Object[] red = new Object[2];
            red[0] = rs.getString("NAZIV_SALE");
            red[1] = rs.getLong("BROJ_PROJEKCIJA");

            lista.add(red);
        }
        return lista;
    }

    @Override
    public String getPrimaryKeyForJoin(GenericObjectDomain god) {
        Sala s = (Sala) god;
        return "S.salaid=" + s.getSalaID();
    }

    @Override
    public String getGroupBy() {
        return " GROUP BY S.NAZIV ";
    }

}
