/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

/**
 *
 * @author Ana
 */
public interface DBRespository<T> extends Repository<T>{
    default void connect() throws Exception{
        DBConnection.getInstance().getConnection();
    }
    
    default void disconnect() throws Exception{
        DBConnection.getInstance().getConnection().close();
    }
    
    default void commit() throws Exception{
        DBConnection.getInstance().getConnection().commit();
    }
    
    default void rollback() throws Exception {
        DBConnection.getInstance().getConnection().rollback();
    }
}
