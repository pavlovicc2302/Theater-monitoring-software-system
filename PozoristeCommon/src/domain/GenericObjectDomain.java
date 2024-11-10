/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;

/**
 *
 * @author Ana
 */
public abstract class GenericObjectDomain implements Serializable {

    public abstract String getTableName();
    
    public abstract String getTableAbbreviation();

    public abstract String getColumnsInsert();

    public abstract String getColumnsUpdate();

    public abstract String getParamsInsert();

    public abstract String getCondition(GenericObjectDomain god);

    public abstract String getOrderBy();

    public abstract String getPrimaryKey(GenericObjectDomain god);
    
    public abstract String getPrimaryKeyForJoin(GenericObjectDomain god);

    public abstract GenericObjectDomain getResult(ResultSet rs) throws Exception;

    public abstract ArrayList<GenericObjectDomain> getList(ResultSet rs) throws Exception;
        
    public abstract ArrayList<Object[]> getListFromJoin(ResultSet rs) throws Exception;

    public abstract String getJoin();
    
    public abstract String getJoinColumns();

    public abstract String getGroupBy();

    public abstract void prepareStatement(PreparedStatement ps, GenericObjectDomain god) throws Exception;


    /*
    
     */
}
