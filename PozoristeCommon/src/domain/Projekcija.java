/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *
 * @author Ana
 */
public class Projekcija extends GenericObjectDomain {

    private long projekcijaID;
    private Date datum;
    private Time vreme;
    private String status;
    private Sala sala;
    private Predstava predstava;
    private Administrator administrator;

    public Projekcija() {
    }

    public Projekcija(long projekcijaID, Date datum, Time vreme, String status, Sala sala, Predstava predstava, Administrator administrator) {
        this.projekcijaID = projekcijaID;
        this.datum = datum;
        this.vreme = vreme;
        this.status = status;
        this.sala = sala;
        this.predstava = predstava;
        this.administrator = administrator;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projekcija other = (Projekcija) obj;
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.vreme, other.vreme)) {
            return false;
        }
        return Objects.equals(this.sala, other.sala);
    }

    
    
    public Predstava getPredstava() {
        return predstava;
    }

    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    public long getProjekcijaID() {
        return projekcijaID;
    }

    public void setProjekcijaID(long projekcijaID) {
        this.projekcijaID = projekcijaID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    @Override
    public String getTableName() {
        return "projekcija ";
    }

    @Override
    public String getTableAbbreviation() {
        return " PR ";
    }

    @Override
    public String getColumnsInsert() {
        return "datum,vreme,status,salaID,predstavaID,administratorID";
    }

    @Override
    public String getColumnsUpdate() {
        return "datum=?,vreme=?,status=?,salaid=?,predstavaid=?,administratorID=?";
    }

    @Override
    public String getParamsInsert() {
        return "?,?,?,?,?,?";
    }

    @Override
    public String getCondition(GenericObjectDomain god) {
        Projekcija projekcija = (Projekcija) god;
        String nazivPredstave = projekcija.getPredstava().getNaziv();
        return " P.NAZIV LIKE '%" + nazivPredstave + "%' " + " ORDER BY" + getOrderBy();
    }

    @Override
    public String getOrderBy() {
        return " PR.STATUS DESC, PR.DATUM";
    }

    @Override
    public String getPrimaryKey(GenericObjectDomain god) {
        Projekcija projekcija = (Projekcija) god;
        return "ProjekcijaID=" + projekcija.getProjekcijaID();
    }

    @Override
    public GenericObjectDomain getResult(ResultSet rs) throws Exception {
        GenericObjectDomain projekcija = null;
        if (rs.next()) {
            long projekcijaid = rs.getLong("projekcijaID");
            Date datum = rs.getDate("datum");
            Time vreme = rs.getTime("vreme");
            String status = rs.getString("Status");

            Sala s = new Sala();
            s.setSalaID(rs.getLong("salaID"));

            Predstava p = new Predstava();
            p.setPredstavaID(rs.getLong("predstavaid"));

            Administrator a = new Administrator();
            a.setAdministratorID(rs.getLong("administratorid"));

            projekcija = new Projekcija(projekcijaid, datum, vreme, status, sala, predstava, administrator);

        }
        return projekcija;
    }

    @Override
    public ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception {
        ArrayList<GenericObjectDomain> lista = new ArrayList<>();
        while (rs.next()) {
            long projekcijaid = rs.getLong("projekcijaID");
            Date datum = rs.getDate("datum");
            Time vreme = rs.getTime("vreme");
            String status = rs.getString("Status");

            Sala s = new Sala();
            s.setSalaID(rs.getLong("salaID"));

            Predstava p = new Predstava();
            p.setPredstavaID(rs.getLong("predstavaid"));

            Administrator a = new Administrator();
            a.setAdministratorID(rs.getLong("administratorid"));

            Projekcija projekcija = new Projekcija(projekcijaid, datum, vreme, status, sala, predstava, administrator);

            lista.add(projekcija);
        }
        return lista;
    }

    @Override
    public String getJoin() {
        return " JOIN PREDSTAVA P ON (PR.PREDSTAVAID = P.PREDSTAVAID)\n"
                + "JOIN SALA S ON (PR.SALAID = S.SALAID) LEFT JOIN GLUMACPROJEKCIJE GP ON (PR.PROJEKCIJAID = GP.PROJEKCIJAID) LEFT JOIN GLUMAC G ON (G.GLUMACID = GP.GLUMACID) JOIN ADMINISTRATOR A ON (PR.ADMINISTRATORID = A.ADMINISTRATORID)";
    }

    @Override
    public String getJoinColumns() {
        return " PR.PROJEKCIJAID AS 'Rb.', P.NAZIV AS 'Naziv predstave', PR.DATUM, PR.VREME, S.NAZIV as 'Naziv sale', PR.STATUS, IFNULL(CONCAT(G.IME,' ', G.PREZIME), 'Nema') AS GLUMAC,IFNULL(GP.NAPOMENA, 'Nema podataka') AS NAPOMENA, A.USERNAME AS ADMIN ";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception {
        try {
            Projekcija projekcija = (Projekcija) god;

            ps.setDate(1, (java.sql.Date) projekcija.getDatum());
            ps.setTime(2, projekcija.getVreme());
            ps.setString(3, projekcija.getStatus());
            ps.setLong(4, projekcija.getSala().getSalaID());
            ps.setLong(5, projekcija.getPredstava().getPredstavaID());
            ps.setLong(6, projekcija.getAdministrator().getAdministratorID());
        } catch (NullPointerException e) {
            throw new Exception("Greska", e);
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception {
        ArrayList<Object[]> lista = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        while (rs.next()) {
            Object[] red = new Object[9];
            red[0] = rs.getLong("Rb.");
            red[1] = rs.getString("Naziv predstave");

            Timestamp datum = rs.getTimestamp("Datum");
            Timestamp vreme = rs.getTimestamp("Vreme");
            red[2] = dateFormat.format(datum);
            red[3] = timeFormat.format(vreme);

            red[4] = rs.getString("Naziv sale");
            red[5] = rs.getString("STATUS");
            red[6] = rs.getString("GLUMAC");
            red[7] = rs.getString("NAPOMENA");
            red[8] = rs.getString("ADMIN");
            lista.add(red);
        }
        return lista;
    }

    @Override
    public String getPrimaryKeyForJoin(GenericObjectDomain god) {
        Projekcija pr = (Projekcija) god;
        return "PR.projekcijaID=" + pr.projekcijaID;
    }

    @Override
    public String getGroupBy() {
        return "";
    }

}
