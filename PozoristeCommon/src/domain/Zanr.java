/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class Zanr extends GenericObjectDomain {

    private long zanrID;
    private String naziv;

    public Zanr() {
    }

    public Zanr(long zanrID, String naziv) {
        this.zanrID = zanrID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public long getZanrID() {
        return zanrID;
    }

    public void setZanrID(long zanrID) {
        this.zanrID = zanrID;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String getTableName() {
        return "zanr";
    }

    @Override
    public String getTableAbbreviation() {
        return "Z";
    }

    @Override
    public String getColumnsInsert() {
        return "naziv";
    }

    @Override
    public String getColumnsUpdate() {
        return "naziv=?";
    }

    @Override
    public GenericObjectDomain getResult(ResultSet rs) {
        Zanr zanr = new Zanr();

        try {
            zanr.setZanrID(rs.getLong("ZanrID"));
            zanr.setNaziv(rs.getString("Naziv"));
        } catch (SQLException ex) {
            Logger.getLogger(Zanr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zanr;
    }

    @Override
    public String getParamsInsert() {
        return "?";
    }

    @Override
    public String getCondition(GenericObjectDomain god) {
        return "";
    }

    @Override
    public String getOrderBy() {
        return "naziv";
    }

    @Override
    public String getPrimaryKey(GenericObjectDomain god) {
        Zanr zanr = (Zanr) god;
        return "zanrid = " + zanr.getZanrID();
    }

    @Override
    public ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception {
        ArrayList<GenericObjectDomain> lista = new ArrayList<>();
        while (rs.next()) {
            long zanrID = rs.getLong("ZanrID");
            String naziv = rs.getString("Naziv");

            Zanr z = new Zanr(zanrID, naziv);
            lista.add(z);
        }
        return lista;
    }

    @Override
    public String getJoin() {
        return " JOIN PREDSTAVA P ON (Z.ZANRID = P.PREDSTAVAID)";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception {
        Zanr zanr = (Zanr) god;

        ps.setString(1, zanr.getNaziv());
    }

    @Override
    public String getJoinColumns() {
        return "Z.NAZIV";
    }

    @Override
    public ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public String getPrimaryKeyForJoin(GenericObjectDomain god) {
        Zanr z = (Zanr) god;
        return "Z.zanrid=" + z.getZanrID();
    }

    @Override
    public String getGroupBy() {
        return "";
    }

}
