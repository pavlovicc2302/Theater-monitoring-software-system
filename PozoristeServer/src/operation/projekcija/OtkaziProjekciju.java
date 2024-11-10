/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.projekcija;

import domain.Projekcija;
import operation.GenericOperation;

/**
 *
 * @author pavlo
 */
public class OtkaziProjekciju extends GenericOperation {

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Projekcija)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        Projekcija otkazanaProjekcija = (Projekcija) object;
        repository.izmeni(otkazanaProjekcija);
    }
    
}
