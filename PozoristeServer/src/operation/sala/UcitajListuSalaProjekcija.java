/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.sala;

import domain.Sala;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class UcitajListuSalaProjekcija extends GenericOperation {
    private List<Object[]> lista;

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Sala)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        lista = repository.vratiSaJoin((Sala)object);
    }

    public List<Object[]> getLista() {
        return lista;
    }
}
