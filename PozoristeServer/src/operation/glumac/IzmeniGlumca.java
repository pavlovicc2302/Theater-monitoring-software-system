/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.glumac;

import domain.Glumac;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class IzmeniGlumca extends GenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Glumac)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        Glumac glumac =(Glumac) object;
        repository.izmeni(glumac);
    }
    
}
