/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.sala;

import domain.Sala;
import java.util.ArrayList;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class UcitajListuSala extends GenericOperation{
    
    ArrayList<Sala> listaSala = new ArrayList<>();

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Sala)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        listaSala = repository.vratiSve(object);
    }

    public ArrayList<Sala> getListaSala() {
        return listaSala;
    }
    
    
    
}
