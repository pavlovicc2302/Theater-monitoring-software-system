/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Ana
 */
public class Administrator extends GenericObjectDomain {

    private long administratorID;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(long administratorID, String username, String password) {
        this.administratorID = administratorID;
        this.username = username;
        this.password = password;
    }

    public long getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(long administratorID) {
        this.administratorID = administratorID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public String getTableName() {
        return "administrator";
    }

    @Override
    public String getTableAbbreviation() {
        return "A";
    }

    @Override
    public String getColumnsInsert() {
        return "Username, Password";
    }

    @Override
    public String getParamsInsert() {
        return "?,?";
    }

    @Override
    public String getColumnsUpdate() {
        return "Username=?, Password=?";
    }

    @Override
    public GenericObjectDomain getResult(ResultSet rs) throws Exception {

        GenericObjectDomain god = null;

        if (rs.next()) {

            long administratorID = rs.getLong("AdministratorID");
            String username = rs.getString("Username");
            String password = rs.getString("Password");

            god = new Administrator(administratorID, username, password);
        }

        return god;
    }

    @Override
    public String getCondition(GenericObjectDomain god) {
        Administrator admin = (Administrator) god;
        return "";
    }

    @Override
    public String getOrderBy() {
        return "username";
    }

    @Override
    public String getPrimaryKey(GenericObjectDomain god) {
        Administrator admin = (Administrator) god;
        return "administratorid = " + admin.getAdministratorID();
    }

    @Override
    public ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception {
        ArrayList<GenericObjectDomain> lista = new ArrayList<>();
        while (rs.next()) {
            long administratorID = rs.getLong("administratorID");
            String username = rs.getString("Username");
            String password = rs.getString("Password");

            Administrator a = new Administrator(administratorID, username, password);
            lista.add(a);
        }
        return lista;
    }

    @Override
    public void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception {
        Administrator admin = (Administrator) god;
        ps.setString(1, admin.getUsername());
        ps.setString(2, admin.getPassword());
    }

    @Override
    public String getJoin() {
        return " JOIN PROJEKCIJA PR ON (A.ADMINISTRATORID = PR.ADMINISTRATOR";
    }

    @Override
    public String getJoinColumns() {
        return " A.USERNAME";
    }

    @Override
    public ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public String getPrimaryKeyForJoin(GenericObjectDomain god) {
        Administrator admin = (Administrator) god;
        return "A.administratorID=" + admin.getAdministratorID();
    }

    @Override
    public String getGroupBy() {
        return "";
    }

}
