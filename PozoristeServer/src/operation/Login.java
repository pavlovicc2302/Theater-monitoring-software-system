/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

import domain.Administrator;
import domain.GenericObjectDomain;

/**
 *
 * @author Ana
 */
public class Login extends GenericOperation {

    private GenericObjectDomain god;
    // private Administrator administrator;

    public GenericObjectDomain getGod() {
        return god;
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null  || !(object instanceof Administrator)) {
            throw new Exception("Parametar nije validan.");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        Administrator admin = (Administrator) object;
        god = (GenericObjectDomain) repository.login(admin);
    }


    
    
}
