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
public class GlumacProjekcije extends GenericObjectDomain {

    private Projekcija projekcija;
    private Glumac glumac;
    private String napomena;

    public GlumacProjekcije() {
    }

    public GlumacProjekcije(Projekcija projekcija, Glumac glumac, String napomena) {
        this.projekcija = projekcija;
        this.glumac = glumac;
        this.napomena = napomena;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public Glumac getGlumac() {
        return glumac;
    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }

    @Override
    public String getTableName() {
        return "glumacProjekcije";
    }

    @Override
    public String getTableAbbreviation() {
        return " GP ";
    }

    @Override
    public String getColumnsInsert() {
        return "glumacid,napomena,projekcijaid";
    }

    @Override
    public String getColumnsUpdate() {
        return "glumacid=?,napomena=?";
    }

    @Override
    public String getParamsInsert() {
        return "?,?,?";
    }

    @Override
    public String getCondition(GenericObjectDomain god) {
        return "";
    }

    @Override
    public String getOrderBy() {
        return "projekcijaid";
    }

    @Override
    public String getPrimaryKey(GenericObjectDomain god) {
        GlumacProjekcije glumacProjekcije = (GlumacProjekcije) god;
        long projekcijaID = glumacProjekcije.getProjekcija().getProjekcijaID();
        return "projekcijaid = " + projekcijaID;
    }

    @Override
    public GenericObjectDomain getResult(ResultSet rs) throws Exception {
        GenericObjectDomain gp = null;
        if (rs.next()) {
            String napomena = rs.getString("Napomena");
            Glumac g = new Glumac();
            g.setGlumacID(rs.getLong("GlumacID"));

            Projekcija pr = new Projekcija();
            pr.setProjekcijaID(rs.getLong("ProjekcijaID"));

            gp = new GlumacProjekcije(pr, g, napomena);

        }
        return gp;
    }

    @Override
    public ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception {
        ArrayList<GenericObjectDomain> lista = new ArrayList<>();
        while (rs.next()) {
            String napomena = rs.getString("Napomena");
            Glumac g = new Glumac();
            g.setGlumacID(rs.getLong("GlumacID"));

            Projekcija pr = new Projekcija();
            pr.setProjekcijaID(rs.getLong("ProjekcijaID"));

            GlumacProjekcije gp = new GlumacProjekcije(pr, g, napomena);
            lista.add(gp);
        }
        return lista;
    }

    @Override
    public ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public String getJoin() {
        return "JOIN GLUMAC G ON (G.GLUMACID = GP.GLUMACID) JOIN PROJEKCIJA PR ON (PR.PROJEKCIJAID = GP.PROJEKCIJAID)";
    }

    @Override
    public String getJoinColumns() {
        return " GP.NAPOMENA";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception {
        try{
        GlumacProjekcije glumacProjekcije = (GlumacProjekcije) god;

        ps.setLong(1, glumacProjekcije.getGlumac().getGlumacID());
        //ps.setLong(3, glumacProjekcije.getProjekcija().getProjekcijaID());
        ps.setString(2, glumacProjekcije.getNapomena());
        }catch(Exception e){
            throw new Exception("Greska",e);
        }

    }

    @Override
    public String getPrimaryKeyForJoin(GenericObjectDomain god) {
        GlumacProjekcije gp = (GlumacProjekcije) god;
        return "GP.projekcijaid=" + gp.getProjekcija().getProjekcijaID();
    }

    @Override
    public String getGroupBy() {
        return "";
    }

}
