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
public class Predstava extends GenericObjectDomain {

    private long predstavaID;
    private String naziv;
    private String opis;
    private int trajanjeMinuti;
    private int brojCinova;
    private String reditelj;
    private Zanr zanr;

    public Predstava() {
    }

    public Predstava(long predstavaID, String naziv, String opis, int trajanjeMinuti, int brojCinova, String reditelj, Zanr zanr) {
        this.predstavaID = predstavaID;
        this.naziv = naziv;
        this.opis = opis;
        this.trajanjeMinuti = trajanjeMinuti;
        this.brojCinova = brojCinova;
        this.reditelj = reditelj;
        this.zanr = zanr;
    }

    @Override
    public String toString() {
        return naziv + " | Reditelj: " + reditelj;
    }

    public long getPredstavaID() {
        return predstavaID;
    }

    public void setPredstavaID(long predstavaID) {
        this.predstavaID = predstavaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getTrajanjeMinuti() {
        return trajanjeMinuti;
    }

    public void setTrajanjeMinuti(int trajanjeMinuti) {
        this.trajanjeMinuti = trajanjeMinuti;
    }

    public int getBrojCinova() {
        return brojCinova;
    }

    public void setBrojCinova(int brojCinova) {
        this.brojCinova = brojCinova;
    }

    public String getReditelj() {
        return reditelj;
    }

    public void setReditelj(String reditelj) {
        this.reditelj = reditelj;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    @Override
    public String getTableName() {
        return "predstava";
    }

    @Override
    public String getTableAbbreviation() {
        return " P ";
    }

    @Override
    public String getColumnsInsert() {
        return "naziv,opis,trajanjeminuti,brojcinova,reditelj,zanrid";
    }

    @Override
    public String getParamsInsert() {
        return "?,?,?,?,?,?";
    }

    @Override
    public String getColumnsUpdate() {
        return "naziv=?,opis=?,trajanjeminuti=?,brojcinova=?,reditelj=?,zanrid=?";
    }

    @Override
    public String getCondition(GenericObjectDomain god) {
        return "P.NAZIV = '' ";
    }

    @Override
    public String getOrderBy() {
        return "naziv";
    }

    @Override
    public String getPrimaryKey(GenericObjectDomain god) {
        return "P.predstavaID";
    }

    @Override
    public GenericObjectDomain getResult(ResultSet rs) throws Exception {
        GenericObjectDomain god = null;
        if (rs.next()) {
            long predstavaID = rs.getLong("PredstavaID");
            String naziv = rs.getString("Naziv");
            String opis = rs.getString("Opis");
            int trajanjeMinuti = rs.getInt("TrajanjeMinuti");
            int brojCinova = rs.getInt("BrojCinova");
            String reditelj = rs.getString("Reditelj");

            Zanr z = new Zanr();
            z.setZanrID(rs.getLong("ZanrID"));

            god = new Predstava(predstavaID, naziv, opis, trajanjeMinuti, brojCinova, reditelj, z);
        }
        return god;
    }

    @Override
    public ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception {
        ArrayList<GenericObjectDomain> lista = new ArrayList<>();
        while (rs.next()) {
            long predstavaID = rs.getLong("PredstavaID");
            String naziv = rs.getString("Naziv");
            String opis = rs.getString("Opis");
            int trajanjeMinuti = rs.getInt("TrajanjeMinuti");
            int brojCinova = rs.getInt("BrojCinova");
            String reditelj = rs.getString("Reditelj");

            Zanr z = new Zanr();
            z.setZanrID(rs.getLong("ZanrID"));

            Predstava p = new Predstava(predstavaID, naziv, opis, trajanjeMinuti, brojCinova, reditelj, z);
            lista.add(p);

        }
        return lista;
    }

    @Override
    public String getJoin() {
        return " JOIN ULOGA U ON (P.PREDSTAVAID = U.PREDSTAVAID) "
                + "JOIN GLUMAC G ON (U.GLUMACID = G.GLUMACID)";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception {
        Predstava predstava = (Predstava) god;
        ps.setString(1, predstava.getNaziv());
        ps.setString(2, predstava.getOpis());
        ps.setInt(3, predstava.getTrajanjeMinuti());
        ps.setInt(4, predstava.getBrojCinova());
        ps.setString(5, predstava.getReditelj());
        ps.setLong(6, predstava.getZanr().getZanrID());
    }

    @Override
    public String getJoinColumns() {
        return " U.NAZIV, G.*";
    }

    @Override
    public ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception {
        ArrayList<Object[]> lista = new ArrayList<>();

        while (rs.next()) {
            Object[] red = new String[3];
            red[0] = rs.getString("NAZIV");
            red[1] = rs.getString("Ime");
            red[2] = rs.getString("Prezime");
            lista.add(red);
        }
        return lista;
    }

    @Override
    public String getPrimaryKeyForJoin(GenericObjectDomain god) {
        Predstava p = (Predstava) god;
        return "P.predstavaid=" + p.getPredstavaID();
    }

    @Override
    public String getGroupBy() {
        return "";
    }

}
