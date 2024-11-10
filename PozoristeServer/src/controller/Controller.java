/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Administrator;
import domain.GenericObjectDomain;
import domain.Glumac;
import domain.GlumacProjekcije;
import domain.Predstava;
import domain.Projekcija;
import domain.Sala;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.GenericOperation;
import operation.Login;
import operation.glumac.IzmeniGlumca;
import operation.glumac.KreirajGlumca;
import operation.glumac.NadjiGlumca;
import operation.glumac.ObrisiGlumca;
import operation.glumac.UcitajListuGlumaca;
import operation.glumacprojekcije.IzmeniGlumacProjekcije;
import operation.predstava.UcitajListuPredstava;
import operation.projekcija.IzmeniProjekciju;
import operation.projekcija.KreirajProjekciju;
import operation.projekcija.NadjiProjekcije;
import operation.projekcija.OtkaziProjekciju;
import operation.projekcija.UcitajListuProjekcija;
import operation.projekcija.UcitajUlogeGlumacaUPredstavi;
import operation.sala.UcitajListuSala;
import operation.sala.UcitajListuSalaProjekcija;
import server.Server;

/**
 *
 * @author Ana
 */
public class Controller {

    private static Controller instance;
    private Server server;
    private ArrayList<GenericObjectDomain> loggedUsers;

    private Controller() {
        loggedUsers = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void setLoggedUsers(ArrayList<GenericObjectDomain> loggedUsers) {
        this.loggedUsers = loggedUsers;
    }

    public ArrayList<GenericObjectDomain> getLoggedUsers() {
        return loggedUsers;
    }

    public void startServer() {
        if (server == null || !server.isAlive()) {
            server = new Server();
            server.start();
            System.out.println("Server je pokrenut.");
        } else {
            System.out.println("Server je već pokrenut.");
        }
    }

    public void stopServer() {
        if (server != null && server.isAlive()) {
            server.stopServer();
            System.out.println("Server je zaustavljen.");
        }
    }

    public GenericObjectDomain loginAdministrator(String username, String password) throws Exception {
        Administrator admin = new Administrator();
        admin.setUsername(username);
        admin.setPassword(password);

        GenericOperation operation = new Login();
        operation.execute(admin);
        return ((Login) operation).getGod();
    }

    public void kreirajGlumca(Glumac glumac) throws Exception {
        GenericOperation operation = new KreirajGlumca();
        operation.execute(glumac);
    }

    public ArrayList<Glumac> pronadjiGlumce(Glumac glumacPronadji) throws Exception {
        GenericOperation operation = new NadjiGlumca();
        operation.execute(glumacPronadji);
        ArrayList<Glumac> glumci = ((NadjiGlumca) operation).getGlumci();

        return glumci;
    }

    public boolean izmeniGlumca(Glumac glumacIzmeni) {
        GenericOperation operation = new IzmeniGlumca();
        try {
            operation.execute(glumacIzmeni);
            return true;
        } catch (Exception ex) {
            return false;

        }
    }

    public ArrayList<Predstava> ucitajListuPredstava() throws Exception {
        GenericOperation operation = new UcitajListuPredstava();
        operation.execute(new Predstava());
        ArrayList<Predstava> listaPredstava = ((UcitajListuPredstava) operation).getListaPredstava();
        return listaPredstava;
    }

    public ArrayList<Sala> ucitajListuSala() throws Exception {
        GenericOperation operation = new UcitajListuSala();
        operation.execute(new Sala());
        ArrayList<Sala> listaSala = ((UcitajListuSala) operation).getListaSala();
        return listaSala;
    }

    public List<String[]> ucitajGlumceUloge(Predstava izabranaPredstava) throws Exception {
        GenericOperation operation = new UcitajUlogeGlumacaUPredstavi();
        operation.execute(izabranaPredstava);
        List<String[]> lista = ((UcitajUlogeGlumacaUPredstavi) operation).getLista();
        return lista;
    }

    public ArrayList<Glumac> ucitajListuGlumaca() throws Exception {
        GenericOperation operation = new UcitajListuGlumaca();
        operation.execute(new Glumac());
        ArrayList<Glumac> listaGlumaca = ((UcitajListuGlumaca) operation).getListaGlumaca();
        return listaGlumaca;
    }

    public void kreirajProjekciju(List<GenericObjectDomain> projekcijaNapomena) throws Exception {

        try {
            GenericOperation operation = new KreirajProjekciju();
            operation.execute(projekcijaNapomena);
        } catch (Exception e) {
            // Logovanje i prosleđivanje izuzetka dalje
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Greška u kreiranju projekcije", e);
            throw e; // Prosledi izuzetak dalje
        }

    }

    public ArrayList<Object[]> ucitajListuProjekcija() throws Exception {
        GenericOperation operation = new UcitajListuProjekcija();
        operation.execute(new Projekcija());
        ArrayList<Object[]> listaProjekcija = ((UcitajListuProjekcija) operation).getLista();
        return listaProjekcija;
    }

    public ArrayList<Object[]> nadjiProjekcije(Projekcija projekcijaPredstava) throws Exception {
        GenericOperation operation = new NadjiProjekcije();
        operation.execute(projekcijaPredstava);
        ArrayList<Object[]> projekcije = ((NadjiProjekcije) operation).getProjekcije();
        return projekcije;
    }

    public boolean izmeniProjekciju(Projekcija izmenjenaProjekcija) {
        try {
            GenericOperation operation = new IzmeniProjekciju();
            operation.execute(izmenjenaProjekcija);
            return true;
        } catch (Exception ex) {
            return false;

        }
    }

    public boolean izmeniGlumacProjekcije(GlumacProjekcije izmenjenaNapomena) {
        try {
            GenericOperation operation = new IzmeniGlumacProjekcije();
            operation.execute(izmenjenaNapomena);
            return true;
        } catch (Exception ex) {

            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean obrisiGlumca(Glumac glumacBrisanje) throws Exception {
        try {
            GenericOperation operation = new ObrisiGlumca();
            operation.execute(glumacBrisanje);
            return true;
        } catch (java.sql.SQLIntegrityConstraintViolationException sqlEx) {
            throw new Exception("Sistem ne može da izbriše glumca jer je angažovan u predstavama.");
        } catch (Exception ex) {
            throw new Exception("Sistem ne može da obriše glumca.");
        }

    }

    public boolean otkaziProjekciju(Projekcija otkazanaProjekcija) {
        try {
            GenericOperation operation = new OtkaziProjekciju();
            operation.execute(otkazanaProjekcija);
            return true;
        } catch (Exception ex) {

            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Object[]> ucitajListuSalaProjekcija() throws Exception {
        GenericOperation operation = new UcitajListuSalaProjekcija();
        operation.execute(new Sala());
        List<Object[]> lista = ((UcitajListuSalaProjekcija) operation).getLista();
        return lista;
    }

}
