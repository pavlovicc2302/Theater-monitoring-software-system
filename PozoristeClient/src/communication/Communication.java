/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import constants.Constants;
import coordinator.ClientCoordinator;
import domain.Administrator;
import domain.GenericObjectDomain;
import domain.Glumac;
import domain.GlumacProjekcije;
import domain.Predstava;
import domain.Projekcija;
import domain.Sala;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class Communication {

    private static Communication instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private Communication() throws IOException {
        try {
            socket = new Socket("localhost", 9001);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server nije pokrenut.", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public static Communication getInstance() {
        if (instance == null) {
            try {
                instance = new Communication();
            } catch (IOException ex) {
                throw new RuntimeException("Nije moguce kreirati instancu.", ex);
            }
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public Administrator loginAdministrator(String username, String password) throws Exception {
        Administrator admin = new Administrator();
        admin.setUsername(username);
        admin.setPassword(password);

        Request request = new Request(admin, Operation.LOGIN);
        if (sender == null) {
            return null;
        }
        sender.send(request);

        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Administrator) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void logout() throws Exception {
        Request request = new Request(ClientCoordinator.getInstance().getParam(Constants.ADMINISTRATOR), Operation.LOGOUT);
        sender.send(request);
    }

    public void kreirajGlumca(Glumac glumac) throws Exception {
        Request request = new Request(glumac, Operation.KREIRAJ_GLUMCA);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public ArrayList<Glumac> pronadjiGlumce(Glumac glumac) throws Exception {
        Request request = new Request(glumac, Operation.NADJI_GLUMCA);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (ArrayList<Glumac>) response.getResult();
        }
    }

    public boolean izmeniGlumca(Glumac izabraniGlumac) throws Exception {
        Request request = new Request(izabraniGlumac, Operation.IZMENI_GLUMCA);
        sender.send(request);
        Response response = (Response) receiver.receive();

        return (boolean) response.getResult();
    }

    public ArrayList<Predstava> ucitajListuPredstava() throws Exception {
        Request request = new Request(null, Operation.UCITAJ_LISTU_PREDSTAVA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (ArrayList<Predstava>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public ArrayList<Sala> ucitajListuSala() throws Exception {
        Request request = new Request(null, Operation.UCITAJ_LISTU_SALA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (ArrayList<Sala>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<String[]> ucitajUlogeGlumaca(Predstava izabranaPredstava) throws Exception {
        Request request = new Request(izabranaPredstava, Operation.UCITAJ_GLUMCE_ULOGE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<String[]>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public ArrayList<Glumac> ucitajListuGlumaca() throws Exception {
        Request request = new Request(null, Operation.UCITAJ_LISTU_GLUMACA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (ArrayList<Glumac>) response.getResult();
        }

    }

    public void kreirajProjekciju(List<GenericObjectDomain> projekcijaNapomena) throws Exception {
         Request request = new Request(projekcijaNapomena, Operation.KREIRAJ_PROJEKCIJU);
    try {
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    } catch (Exception e) {
        // Logovanje i prosleđivanje izuzetka dalje
        //Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, "Greška u komunikaciji", e);
        throw e;
    }
    }

    public ArrayList<Object[]> ucitajListuProjekcija() throws Exception {
        Request request = new Request(null, Operation.UCITAJ_LISTU_PROJEKCIJA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (ArrayList<Object[]>) response.getResult();
        }
    }

    public ArrayList<Object[]> nadjiProjekcije(Projekcija projekcijaPredstava) throws Exception {
        Request request = new Request(projekcijaPredstava, Operation.NADJI_PROJEKCIJU);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (ArrayList<Object[]>) response.getResult();
        }
    }

    public boolean izmeniProjekciju(Projekcija izmenjenaProjekcija) throws Exception {
        Request request = new Request(izmenjenaProjekcija, Operation.IZMENI_PROJEKCIJU);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (boolean) response.getResult();
    }

    public boolean izmeniGlumacProjekcije(GlumacProjekcije izmenjenaNapomena) throws Exception {
        Request request = new Request(izmenjenaNapomena, Operation.IZMENI_GLUMACPROJEKCIJE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (boolean) response.getResult();
    }

    public boolean obrisiGlumca(Glumac glumacBrisanje) throws Exception {
        Request request = new Request(glumacBrisanje, Operation.OBRISI_GLUMCA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (boolean) response.getResult();
    }

    public boolean otkaziProjekciju(Projekcija otkazanaProjekcija) throws Exception {
        Request request = new Request(otkazanaProjekcija, Operation.OTKAZI_PROJEKCIJU);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (boolean) response.getResult();
    }

}
