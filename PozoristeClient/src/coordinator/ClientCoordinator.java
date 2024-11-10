/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinator;

import controller.glumac.GlumacController;
import controller.LoginController;
import controller.MainController;
import controller.glumac.IzmenaGlumcaController;
import controller.glumac.PretragaGlumacaController;
import controller.projekcija.IzmenaProjekcijeController;
import controller.projekcija.PretragaProjekcijaController;
import controller.projekcija.ProjekcijaController;
import domain.Administrator;
import domain.Glumac;
import forms.GlumacForm;
import forms.IzmenaGlumcaForm;
import forms.IzmenaProjekcijeForm;
import forms.LoginForm;
import forms.MainForm;
import forms.PretragaGlumacaForm;
import forms.PretragaProjekcijaForm;
import forms.ProjekcijaForm;
import java.util.HashMap;

/**
 *
 * @author Ana
 */
public class ClientCoordinator {

    private static ClientCoordinator instance;
    private final MainController mainController;
    private final HashMap<String, Object> params;

    public ClientCoordinator() {
        this.mainController = new MainController(new MainForm());
        this.params = new HashMap<>();
    }

    public static ClientCoordinator getInstance() {
        if (instance == null) {
            instance = new ClientCoordinator();
        }
        return instance;
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String admin) {
        return params.get(admin);
    }

    public MainController getMainController() {
        return mainController;
    }

    public void openLoginForm() {
        LoginController loginController = new LoginController(new LoginForm());
        loginController.openForm();

    }

    public void openMainForm() {
        mainController.openForm();
    }

    public void openGlumacForm() {
        GlumacController glumacController = new GlumacController(new GlumacForm(mainController.getMainForm(), true));
        glumacController.openForm();
    }

    public void openPretragaGlumcaForm() {
        PretragaGlumacaController pretragaGlumcaController = new PretragaGlumacaController(new PretragaGlumacaForm(mainController.getMainForm(), true));
        pretragaGlumcaController.openForm();
    }

    public void openIzmenaGlumcaForm(PretragaGlumacaForm pgf, Glumac glumac) {
        IzmenaGlumcaController izmeniGlumcaController = new IzmenaGlumcaController(new IzmenaGlumcaForm(pgf, true), glumac);
        izmeniGlumcaController.openForm(glumac);
    }

    public void openProjekcijaForm(Administrator admin) {
        ProjekcijaController projekcijaController = new ProjekcijaController(new ProjekcijaForm(mainController.getMainForm(), true),admin);
        projekcijaController.openForm();
    }

    public void openPretragaProjekcijaForm(Administrator admin) {
        PretragaProjekcijaController nadjiProjekcijuController = new PretragaProjekcijaController(new PretragaProjekcijaForm(mainController.getMainForm(), true),admin);
        nadjiProjekcijuController.openForm();
    }

    public void openIzmenaProjekcijeForm(PretragaProjekcijaForm projekcijaForm, Object[] projekcija, Administrator admin) {
        IzmenaProjekcijeController izmenaProjekcijeController = new IzmenaProjekcijeController(new IzmenaProjekcijeForm(projekcijaForm, true), projekcija, admin);

        izmenaProjekcijeController.openForm(projekcija);
    }

}
