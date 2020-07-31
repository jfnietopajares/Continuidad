package es.sacyl.hnss;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Viewport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.page.Inline;
import com.vaadin.flow.component.page.Inline.Wrapping;
import com.vaadin.flow.component.page.Meta;
import com.vaadin.flow.component.page.TargetElement;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.DefaultErrorHandler;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.InitialPageSettings.Position;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinServletResponse;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import es.sacyl.hnss.dao.ConexionDAO;
import es.sacyl.hnss.ui.Menu;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main view contains a button and a click listener.
 */
@Viewport("width=device-width, initial-scale=1")
@PageTitle("Servicio de Farmacia. Complejo Asistencial de Ávila")
@BodySize(height = "100vh", width = "100vw")
@Meta(name = "author", content = "Juan")

//@Inline(wrapping = Wrapping.AUTOMATIC,
//       position = Position.APPEND,
//    target = TargetElement.BODY,
//    value = "custom.html")
//@Push(value = PushMode.MANUAL, transport = Transport.WEBSOCKET)
@Route
@PWA(name = "farmacia", shortName = "farmacia")
//@JsModule("@vaadin/vaadin-lumo-styles/presets/sizing.js")
@Theme(value = Lumo.class)
//@HtmlImport("frontend://FarmaciaTema.html")
//@HtmlImport("frontend//FarmaciaTema.html")
//@HtmlImport("frontend://bower_components/iron-form/iron-form.html") //
//@Theme(value = Lumo.class)
//@Theme(value = Lumo.class, variant = Lumo.DARK)//
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
