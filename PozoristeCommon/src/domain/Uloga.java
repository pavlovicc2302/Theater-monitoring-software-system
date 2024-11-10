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
public class Uloga extends GenericObjectDomain {
    
    private Predstava predstava;
    private long rbUloge;
    private String naziv;
    private String opis;
    private Glumac glumac;
    
    public Uloga() {
    }
    
    public Uloga(Predstava predstava, long rbUloge, String naziv, String opis, Glumac glumac) {
        this.predstava = predstava;
        this.rbUloge = rbUloge;
        this.naziv = naziv;
        this.opis = opis;
        this.glumac = glumac;
    }
    
    public String getOpis() {
        return opis;
    }
    
    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    public Predstava getPredstava() {
        return predstava;
    }
    
    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }
    
    public long getRbUloge() {
        return rbUloge;
    }
    
    public void setRbUloge(long rbUloge) {
        this.rbUloge = rbUloge;
    }
    
    public String getNaziv() {
        return naziv;
    }
    
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    public Glumac getGlumac() {
        return glumac;
    }
    
    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }
    
    @Override
    public String getTableName() {
        return "uloga";
    }
    
    @Override
    public String getTableAbbreviation() {
        return "U";
    }
    
    @Override
    public String getColumnsInsert() {
        return "predstavaid,opis,naziv,glumacid";
    }
    
    @Override
    public String getColumnsUpdate() {
        return "predstavaid=?,opis=?,naziv=?,glumacid=?";
    }
    
    @Override
    public String getParamsInsert() {
        return "?,?,?,?";
    }
    
    @Override
    public String getCondition(GenericObjectDomain god) {
        long predstavaID = ((Uloga) god).getPredstava().getPredstavaID();
        return "PREDSTAVAID = " + predstavaID;
    }
    
    @Override
    public String getOrderBy() {
        return "naziv";
    }
    
    @Override
    public String getPrimaryKey(GenericObjectDomain god) {
        Uloga uloga = (Uloga) god;
        return "rbuloge =" + uloga.getRbUloge();
    }
    
    @Override
    public GenericObjectDomain getResult(ResultSet rs) throws Exception {
        GenericObjectDomain god = null;
        if (rs.next()) {
            String naziv = rs.getString("Naziv");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            
            god = new Uloga(null, -1, naziv, null, new Glumac(-1, ime, prezime, null, null));
        }
        return god;
    }
    
    @Override
    public ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception {
        ArrayList<GenericObjectDomain> lista = new ArrayList<>();
        
        while (rs.next()) {
            String naziv = rs.getString("Naziv");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            
            Uloga u = new Uloga(null, -1, naziv, null, new Glumac(-1, ime, prezime, null, null));
            lista.add(u);
        }
        return lista;
    }
    
    @Override
    public String getJoin() {
        return " U JOIN PREDSTAVA P ON (P.PREDSTAVAID = U.PREDSTAVAID) "
                + "JOIN GLUMAC G ON (U.GLUMACID = G.GLUMACID)";
    }
    
    @Override
    public void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception {
        Uloga uloga = (Uloga) god;
        
        ps.setLong(1, uloga.getPredstava().getPredstavaID());
        ps.setLong(2, uloga.getRbUloge());
        ps.setString(3, uloga.getNaziv());
        ps.setString(4, uloga.getOpis());
        ps.setLong(5, uloga.getGlumac().getGlumacID());
    }
    
    @Override
    public String getJoinColumns() {
        return "U.NAZIV, G.IME, G.PREZIME";
    }
    
    @Override
    public ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception {
        return null;
    }
    
    @Override
    public String getPrimaryKeyForJoin(GenericObjectDomain god) {
        Uloga u = (Uloga) god;
        return "U.rbuloge=" + u.getRbUloge();
    }

    @Override
    public String getGroupBy() {
        return "";
    }
    
}
