/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.projekcija;

import domain.Predstava;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class UcitajUlogeGlumacaUPredstavi extends GenericOperation {

    private List<String[]> lista;

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Predstava)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        lista = repository.vratiSaJoinPodUslovom((Predstava)object);
    }

    public List<String[]> getLista() {
        return lista;
    }

    

}
