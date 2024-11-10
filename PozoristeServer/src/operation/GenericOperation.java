/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

import database.DBBroker;
import database.DBRespository;
import database.Repository;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public abstract class GenericOperation {

    protected final Repository repository;

    public GenericOperation() {
        this.repository = new DBBroker();
    }

    public final void execute(Object object) throws Exception {
        try {
            validate(object);
            startTransaction();
            executeOperation(object);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    protected abstract void validate(Object object) throws Exception;

    protected abstract void executeOperation(Object object) throws Exception;

    private void startTransaction() throws Exception {
        ((DBRespository) repository).connect();
    }

    private void commitTransaction() throws Exception {
        ((DBRespository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DBRespository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DBRespository) repository).disconnect();
    }
}
