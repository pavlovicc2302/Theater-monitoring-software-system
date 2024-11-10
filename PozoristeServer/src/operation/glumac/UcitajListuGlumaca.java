/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.glumac;

import domain.Glumac;
import java.util.ArrayList;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class UcitajListuGlumaca extends GenericOperation{
    
    ArrayList<Glumac> listaGlumaca = new ArrayList<>();

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Glumac)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        listaGlumaca = repository.vratiSve(object);
    }

    public ArrayList<Glumac> getListaGlumaca() {
        return listaGlumaca;
    }
    
    
    
}
