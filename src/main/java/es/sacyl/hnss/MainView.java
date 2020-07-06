package es.sacyl.hnss;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import es.sacyl.hnss.dao.ConexionDAO;
import es.sacyl.hnss.ui.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "farmacia", shortName = "farmacia")
//@Theme(value = Lumo.class)
public class MainView extends VerticalLayout {

    private static final Logger LOGGER = LogManager.getLogger(MainView.class);
    private HorizontalLayout contenedorMenu = new HorizontalLayout();
    private HorizontalLayout contenedorFormularios = new HorizontalLayout();

    public MainView() {
        LOGGER.info("Inicio aplicación");
        LOGGER.debug("Inicio debug");
        LOGGER.error("Inicio error");
        LOGGER.fatal("Inicio fatal");

        this.setAlignItems(Alignment.CENTER);
        if (new ConexionDAO().getConexionBBDD() == null) {
            (new Notification("Error BBDD", 3000, Notification.Position.MIDDLE)).open();
        } else {
            this.doLogin();
        }
    }

    public void domuestraMenu() {
        this.removeAll();
        this.add(contenedorMenu);
        this.add(contenedorFormularios);
        contenedorMenu.add(new Menu(contenedorFormularios));
    }

    public boolean authenticate() {

        return true;
    }

    public void doLogin() {
        LoginForm componentLogin = new LoginForm();
        componentLogin.setForgotPasswordButtonVisible(false);
        componentLogin.setI18n(createEspanolI18n());
        componentLogin.addLoginListener(e -> {
            boolean isAuthenticated = authenticate();
            if (isAuthenticated) {
                this.domuestraMenu();

            } else {
                componentLogin.setError(true);
            }
        });
        this.add(componentLogin);
    }

    private LoginI18n createEspanolI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Farmacia Complejo Asistencial de Ávila");
        i18n.getHeader().setDescription("Aplicaciones internas del servicio de farmacia");
        i18n.getForm().setUsername("Usuario");
        i18n.getForm().setTitle("Farmacia del Complejo Asistencial de Ávila");
        i18n.getForm().setSubmit("Conectar");
        i18n.getForm().setPassword("Clave");
        i18n.getForm().setForgotPassword("Resetear clave");
        i18n.getErrorMessage().setTitle("Datos no válidos");
        i18n.getErrorMessage()
                .setMessage("Registra de nuevo el dato del usuario y la clave");
        i18n.setAdditionalInformation(
                "Para cualquier duda o problema en el acceso ponte en contacto con el servicio de informática.\n Teléfono 31500, correo: informatica.hnss@saludcastillaylen.es");
        return i18n;
    }

}
