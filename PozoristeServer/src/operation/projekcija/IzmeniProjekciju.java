/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.projekcija;

import domain.Projekcija;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class IzmeniProjekciju extends GenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof Projekcija)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        Projekcija izmenjenaProjekcija = (Projekcija) object;
        repository.izmeni(izmenjenaProjekcija);
    }
    
}
