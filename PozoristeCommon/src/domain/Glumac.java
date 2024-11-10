/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ana
 */
public class Glumac extends GenericObjectDomain {

    private long glumacID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;

    public Glumac() {
    }

    public Glumac(long glumacID, String ime, String prezime, String email, String telefon) {
        this.glumacID = glumacID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public long getGlumacID() {
        return glumacID;
    }

    public void setGlumacID(long glumacID) {
        this.glumacID = glumacID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getTableName() {
        return "glumac";
    }

    @Override
    public String getTableAbbreviation() {
        return " G ";
    }

    @Override
    public String getColumnsInsert() {
        return "ime,prezime,email,telefon";
    }

    @Override
    public String getParamsInsert() {
        return "?,?,?,?";
    }

    @Override
    public String getPrimaryKey(GenericObjectDomain god) {
        Glumac glumac = (Glumac) god;
        return "GlumacID=" + glumac.getGlumacID();
    }

    @Override
    public GenericObjectDomain getResult(ResultSet rs) throws Exception {
        GenericObjectDomain god = null;

        if (rs.next()) {

            long glumacID = rs.getLong("GlumacID");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            String telefon = rs.getString("Telefon");
            String email = rs.getString("Email");

            god = new Glumac(glumacID, ime, prezime, email, telefon);
        }

        return god;
    }

    @Override
    public String getJoin() {
        return " JOIN";
    }

    @Override
    public String getColumnsUpdate() {
        return "ime=?, prezime=?, email=?, telefon=?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception {
        Glumac glumac = (Glumac) god;

        ps.setString(1, glumac.getIme());
        ps.setString(2, glumac.getPrezime());
        ps.setString(3, glumac.getEmail());
        ps.setString(4, glumac.getTelefon());
    }

    @Override
    public String getCondition(GenericObjectDomain god) {
        Glumac gl = (Glumac) god;
        return "ime LIKE '%" + gl.getIme() + "%' OR prezime LIKE '%" + gl.getPrezime() + "%'";
    }

    @Override
    public String getOrderBy() {
        return "prezime ASC";
    }

    @Override
    public ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception {
        ArrayList<GenericObjectDomain> lista = new ArrayList<>();
        while (rs.next()) {
            long glumacID = rs.getLong("GlumacID");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            String email = rs.getString("Email");
            String telefon = rs.getString("Telefon");

            Glumac g = new Glumac(glumacID, ime, prezime, email, telefon);
            lista.add(g);
        }
        return lista;
    }

    public boolean[] validEmailTelefon(String email, String telefon) {
        boolean[] results = new boolean[2];

        String email_pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern1 = Pattern.compile(email_pattern);
        Matcher matcher1 = pattern1.matcher(email);

        results[0] = matcher1.matches();

        String telefon_pattern = "^06\\d{7,8}$";
        Pattern pattern2 = Pattern.compile(telefon_pattern);
        Matcher matcher2 = pattern2.matcher(telefon);

        results[1] = matcher2.matches();

        return results;
    }

    @Override
    public String getJoinColumns() {
        return "G.IME, G.PREZIME";
    }

    @Override
    public String getPrimaryKeyForJoin(GenericObjectDomain god) {
        Glumac glumac = (Glumac) god;
        return "G.GLUMACID=" + glumac.getGlumacID();
    }

    @Override
    public ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public String getGroupBy() {
        return "";
    }

}
