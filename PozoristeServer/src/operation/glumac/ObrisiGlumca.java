/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.glumac;

import domain.Glumac;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class ObrisiGlumca extends GenericOperation {

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Glumac)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        try {
            Glumac glumac = (Glumac) object;
            repository.obrisi(glumac);
        } catch (java.sql.SQLIntegrityConstraintViolationException sqlEx) {
            throw sqlEx;
        } catch (Exception ex) {
            throw ex;
//Logger.getLogger(ObrisiGlumca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
