/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.coordinator;

import forms.FormServerMain;
import forms.KonfiguracijaForm;
import forms.SalaProjekcijeForm;
import forms.controller.KonfiguracijaController;
import forms.controller.MainController;
import forms.controller.SalaProjekcijeController;

/**
 *
 * @author Ana
 */
public class ServerCoordinator {

    private static ServerCoordinator instance;
    private final MainController mainController;

    public ServerCoordinator() {
        mainController = new MainController(new FormServerMain());

    }

    public static ServerCoordinator getInstance() {
        if (instance == null) {
            instance = new ServerCoordinator();
        }
        return instance;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void openMainForm() {
        mainController.openForm();
    }

    public void openKonfiguracijaForm() {
        KonfiguracijaController konfiguracijaController = new KonfiguracijaController(new KonfiguracijaForm(mainController.getFormServerMain(), true));
        konfiguracijaController.openForm();
    }

    public void openSalaProjekcijeForm() {
        SalaProjekcijeController spc = new SalaProjekcijeController(new SalaProjekcijeForm(mainController.getFormServerMain(), true));
        spc.openForm();
    }

}
