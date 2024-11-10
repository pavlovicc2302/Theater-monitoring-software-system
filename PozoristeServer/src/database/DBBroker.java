/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import domain.Administrator;
import domain.GenericObjectDomain;
import domain.GlumacProjekcije;
import domain.Projekcija;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public class DBBroker implements DBRespository<GenericObjectDomain> {

    //boolean imaDete = false;
    GenericObjectDomain dete;
    boolean imaDete = false;

    @Override
    public GenericObjectDomain login(GenericObjectDomain god) throws Exception {
        try {
            String query = "SELECT * FROM " + god.getTableName() + " WHERE username='" + ((Administrator) god).getUsername() + "' AND password = '" + ((Administrator) god).getPassword() + "'";
            System.out.println(query);
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);

            god = ((Administrator) god).getResult(rs);
            rs.close();
            statement.close();
            return god;

        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public void dodaj(GenericObjectDomain god) throws Exception {
        String query = "INSERT INTO " + god.getTableName() + " (" + god.getColumnsInsert() + ") VALUES (" + god.getParamsInsert() + ")";

        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        god.prepareStatement(preparedStatement, god);
        preparedStatement.executeUpdate();

        
    }

    @Override
    public ArrayList<GenericObjectDomain> vratiSvePodUslovom(GenericObjectDomain god) throws Exception {
        ArrayList<GenericObjectDomain> lista = null;
        String query = "SELECT * FROM " + god.getTableName() + " WHERE " + god.getCondition(god) + " ORDER BY " + god.getOrderBy();

        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        lista = god.getList(resultSet);
        resultSet.close();
        statement.close();

        return lista;

    }

    @Override
    public boolean izmeni(GenericObjectDomain god) throws Exception {
        try {
            String query = "UPDATE " + god.getTableName() + " SET " + god.getColumnsUpdate() + " WHERE " + god.getPrimaryKey(god);
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(query);
            god.prepareStatement(ps, god);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<GenericObjectDomain> vratiSve(GenericObjectDomain god) throws Exception {
        ArrayList<GenericObjectDomain> lista = null;
        try {

            String query = "SELECT * FROM " + god.getTableName() + god.getTableAbbreviation() + " ORDER BY " + god.getOrderBy();
            System.out.println(query);
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            lista = god.getList(resultSet);
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw e;
        }
        return lista;

    }

    @Override
    public ArrayList<Object[]> vratiSaJoin(GenericObjectDomain god) throws Exception {
        ArrayList<Object[]> lista = new ArrayList<>();
        try {
            String query = "SELECT " + god.getJoinColumns() + " FROM " + god.getTableName() + god.getTableAbbreviation() + god.getJoin() + god.getGroupBy() + " ORDER BY" + god.getOrderBy();
            System.out.println(query);
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            lista = god.getListFromJoin(rs);
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public ArrayList<Object[]> vratiSaJoinPodUslovom(GenericObjectDomain god) throws Exception {
        ArrayList<Object[]> lista = new ArrayList<>();
        try {
            String query = "SELECT" + god.getJoinColumns() + " FROM " + god.getTableName() + god.getTableAbbreviation() + god.getJoin() + " WHERE " + god.getPrimaryKeyForJoin(god) + " OR " + god.getCondition(god);
            System.out.println(query);
            Statement st = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            lista = god.getListFromJoin(rs);
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public boolean obrisi(GenericObjectDomain god) throws Exception {
        try {
            String query = "DELETE FROM " + god.getTableName() + " WHERE " + god.getPrimaryKey(god);
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (java.sql.SQLIntegrityConstraintViolationException sqlEx) {
            throw sqlEx;
            //return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            // return false;
            throw ex;
        }
    }

    @Override
    public void dodajViseTabela(List<GenericObjectDomain> lista) throws Exception {

        try {
            String upitProjekcija = "";
            String upitGlumacProjekcije = "";
            List<Long> projekcijaIDList = new ArrayList<>();

            for (GenericObjectDomain g : lista) {
                if (g instanceof Projekcija) {
                    upitProjekcija = "INSERT INTO " + g.getTableName() + " (" + g.getColumnsInsert() + ") VALUES (" + g.getParamsInsert() + ")";
                    System.out.println(upitProjekcija);
                } else if (g instanceof GlumacProjekcije) {
                    upitGlumacProjekcije = "INSERT INTO " + g.getTableName() + " (" + g.getColumnsInsert() + ") VALUES (" + g.getParamsInsert() + ")";
                    System.out.println(upitGlumacProjekcije);
                }
            }

            PreparedStatement psProjekcija = null;
            PreparedStatement psGlumacProjekcije = null;

            psProjekcija = DBConnection.getInstance().getConnection().prepareStatement(upitProjekcija, Statement.RETURN_GENERATED_KEYS);

            for (GenericObjectDomain god : lista) {
                if (god instanceof Projekcija) {
                    god.prepareStatement(psProjekcija, god);
                    psProjekcija.addBatch();
                }
            }

            if (psProjekcija != null) {
                psProjekcija.executeBatch();
                ResultSet rsProjekcija = psProjekcija.getGeneratedKeys();

                while (rsProjekcija.next()) {
                    projekcijaIDList.add(rsProjekcija.getLong(1));
                }
                psProjekcija.close();
            }

            psGlumacProjekcije = DBConnection.getInstance().getConnection().prepareStatement(upitGlumacProjekcije);
            int projekcijaIndex = 0;

            for (GenericObjectDomain god : lista) {
                if (god instanceof GlumacProjekcije) {
                    if (projekcijaIndex < projekcijaIDList.size()) {
                        long projekcijaID = projekcijaIDList.get(projekcijaIndex);
                        god.prepareStatement(psGlumacProjekcije, god);
                        psGlumacProjekcije.setLong(3, projekcijaID);

                        psGlumacProjekcije.addBatch();
                        projekcijaIndex++;
                    }
                }
            }

            if (psGlumacProjekcije != null) {
                psGlumacProjekcije.executeBatch();
                psGlumacProjekcije.close();
            }
        } catch (Exception e) {
            throw new Exception("Greška prilikom dodavanja u bazu podataka", e);
        }
    }
}
