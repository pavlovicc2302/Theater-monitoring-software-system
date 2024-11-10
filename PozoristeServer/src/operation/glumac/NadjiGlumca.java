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
public class NadjiGlumca extends GenericOperation {

    private ArrayList<Glumac> glumci;

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Glumac)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        glumci = repository.vratiSvePodUslovom((Glumac) object);
    }

    public ArrayList<Glumac> getGlumci() {
        return glumci;
    }
    
    

}
