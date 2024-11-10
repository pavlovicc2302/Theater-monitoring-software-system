/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Administrator;
import domain.GenericObjectDomain;
import domain.Glumac;
import domain.GlumacProjekcije;
import domain.Predstava;
import domain.Projekcija;
import domain.Sala;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author Ana
 */
public class ClientThread extends Thread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private Server server;
    private boolean end = false;

    public ClientThread(Socket socket, Server server) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        this.server = server;
    }

    @Override
    public void run() {
        while (!end) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();

                switch (request.getOperation()) {
                    case LOGIN:
                        Administrator admin = (Administrator) request.getArg();
                        GenericObjectDomain god = Controller.getInstance().loginAdministrator(admin.getUsername(), admin.getPassword());
                        if (god == null) {
                            Exception exception = new Exception("Sistem ne moze da pronadje administratora na osnovu unetih podataka");
                            response.setException(exception);
                        } else {
                            if (!Controller.getInstance().getLoggedUsers().contains(god)) {
                                Controller.getInstance().getLoggedUsers().add(god);
                                response.setResult(god);
                            } else {
                                Exception exception = new Exception("Korisnik je vec prijavljen");
                                response.setException(exception);
                            }
                        }
                        break;

                    case LOGOUT:
                        Administrator adminLogOut = (Administrator) request.getArg();
                        Controller.getInstance().getLoggedUsers().remove(adminLogOut);
                        end = true;
                        break;

                    case KREIRAJ_GLUMCA:
                        Glumac glumac = (Glumac) request.getArg();
                        Controller.getInstance().kreirajGlumca(glumac);

                        break;

                    case UCITAJ_LISTU_GLUMACA:
                        ArrayList<Glumac> listaGlumaca = Controller.getInstance().ucitajListuGlumaca();
                        response.setResult(listaGlumaca);
                        break;

                    case NADJI_GLUMCA:
                        Glumac glumacPronadji = (Glumac) request.getArg();
                        ArrayList<Glumac> glumci = Controller.getInstance().pronadjiGlumce(glumacPronadji);
                        response.setResult(glumci);
                        break;

                    case IZMENI_GLUMCA:
                        Glumac glumacIzmeni = (Glumac) request.getArg();
                        boolean uspesnaIzmena = Controller.getInstance().izmeniGlumca(glumacIzmeni);
                        response.setResult(uspesnaIzmena);
                        break;

                    case OBRISI_GLUMCA:
                        try {
                            Glumac glumacBrisanje = (Glumac) request.getArg();
                            boolean uspesnoBrisanje = Controller.getInstance().obrisiGlumca(glumacBrisanje);
                            response.setResult(uspesnoBrisanje);
                        } catch (Exception e) {
                            response.setResult(false);
                            response.setException(e);
                        }
                        break;

                    case UCITAJ_LISTU_PREDSTAVA:
                        ArrayList<Predstava> listaPredstava = Controller.getInstance().ucitajListuPredstava();
                        response.setResult(listaPredstava);

                        break;

                    case UCITAJ_LISTU_SALA:
                        ArrayList<Sala> listaSala = Controller.getInstance().ucitajListuSala();
                        response.setResult(listaSala);
                        break;

                    case UCITAJ_GLUMCE_ULOGE:
                        Predstava izabranaPredstava = (Predstava) request.getArg();
                        List<String[]> lista = Controller.getInstance().ucitajGlumceUloge(izabranaPredstava);
                        response.setResult(lista);
                        break;

                    case KREIRAJ_PROJEKCIJU:

                        try {
                            List<GenericObjectDomain> listaBaza = (List<GenericObjectDomain>) request.getArg();
                            Controller.getInstance().kreirajProjekciju(listaBaza);
                        } catch (Exception e) {
                            // Postavljanje izuzetka u Response
                            response.setException(e);
                            //throw e; // Možete baciti izuzetak dalje ako je potrebno
                        }
                        break;

                    case UCITAJ_LISTU_PROJEKCIJA:
                        ArrayList<Object[]> listaProjekcija = Controller.getInstance().ucitajListuProjekcija();
                        response.setResult(listaProjekcija);
                        break;

                    case NADJI_PROJEKCIJU:
                        Projekcija projekcijaPredstava = (Projekcija) request.getArg();
                        ArrayList<Object[]> projekcije = Controller.getInstance().nadjiProjekcije(projekcijaPredstava);
                        response.setResult(projekcije);
                        break;

                    case IZMENI_PROJEKCIJU:
                        Projekcija izmenjenaProjekcija = (Projekcija) request.getArg();
                        boolean uspesnaIzmenaProjekcije = Controller.getInstance().izmeniProjekciju(izmenjenaProjekcija);
                        response.setResult(uspesnaIzmenaProjekcije);
                        break;

                    case IZMENI_GLUMACPROJEKCIJE:
                        GlumacProjekcije izmenjenaNapomena = (GlumacProjekcije) request.getArg();
                        boolean uspesnaIzmenaNapomene = Controller.getInstance().izmeniGlumacProjekcije(izmenjenaNapomena);
                        response.setResult(uspesnaIzmenaNapomene);
                        break;

                    case OTKAZI_PROJEKCIJU:
                        Projekcija otkazanaProjekcija = (Projekcija) request.getArg();
                        boolean uspesnoOtkazanaProjekcija = Controller.getInstance().otkaziProjekciju(otkazanaProjekcija);
                        response.setResult(uspesnoOtkazanaProjekcija);
                        break;

                    default:
                        return;
                }
                sender.send(response);
            } catch (Exception ex) {
                return;
            }
        }

    }

}
