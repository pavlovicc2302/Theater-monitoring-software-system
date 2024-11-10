/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ana
 */
public interface Repository<T> {

    public T login(T param) throws Exception;

    public void dodaj(T param) throws Exception;
    
    public void dodajViseTabela(List<T> param) throws Exception;
        
    public ArrayList<T> vratiSve(T param) throws Exception;
    
    public ArrayList<T> vratiSvePodUslovom(T param) throws Exception;
    
    public ArrayList<Object[]> vratiSaJoinPodUslovom(T param) throws Exception;
    
    public ArrayList<Object[]> vratiSaJoin(T param) throws Exception;
    
    public boolean izmeni(T param) throws Exception;
    
    public boolean obrisi(T param) throws Exception;
}
