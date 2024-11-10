/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.projekcija;

import domain.Predstava;
import domain.Projekcija;
import java.util.ArrayList;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class NadjiProjekcije extends GenericOperation{
    
    private ArrayList<Object[]> projekcije;

    @Override
    protected void validate(Object object) throws Exception {
        if(object == null || !(object instanceof Projekcija)){
            throw new Exception("Invalid parameter.");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        projekcije = repository.vratiSaJoinPodUslovom((Projekcija)object);
    }

    public ArrayList<Object[]> getProjekcije() {
        return projekcije;
    }

    
    
    
}
