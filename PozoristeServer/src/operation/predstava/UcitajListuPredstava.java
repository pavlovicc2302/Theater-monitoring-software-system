/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.predstava;

import domain.Predstava;
import java.util.ArrayList;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class UcitajListuPredstava extends GenericOperation{
    private ArrayList<Predstava> listaPredstava = new ArrayList<>();

    @Override
    protected void validate(Object object) throws Exception {
         if (object == null || !(object instanceof Predstava)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        listaPredstava = repository.vratiSve((Predstava)object);
    }

    public ArrayList<Predstava> getListaPredstava() {
        return listaPredstava;
    }
    
    
    
}
