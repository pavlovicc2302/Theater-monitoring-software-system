/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.glumacprojekcije;

import domain.GlumacProjekcije;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class IzmeniGlumacProjekcije extends GenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof GlumacProjekcije)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        GlumacProjekcije izmenjenaNapomena = (GlumacProjekcije) object;
        repository.izmeni(izmenjenaNapomena);
    }
    
}
