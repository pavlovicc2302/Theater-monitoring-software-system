/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.projekcija;

import domain.GenericObjectDomain;
import domain.Projekcija;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author Ana
 */
public class KreirajProjekciju extends GenericOperation {

    @Override
    protected void validate(Object object) throws Exception {
        if (object == null || !(object instanceof List<?>)) {
            throw new Exception("Invalid parameter.");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {

        try {
            List<GenericObjectDomain> listaBaza = (List<GenericObjectDomain>) object;
            repository.dodajViseTabela(listaBaza);
        } catch (Exception e) {
            throw new Exception("Greška prilikom kreiranja projekcije", e);
        }

    }

}
