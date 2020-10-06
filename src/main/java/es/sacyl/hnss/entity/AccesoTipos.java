package es.sacyl.hnss.entity;

import java.util.ArrayList;

/**
 * The Class AccesoTipos.
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class AccesoTipos {

    /**
     * The tipo.
     */
    private int tipo;

    /**
     * The descripcion.
     */
    private String descripcion;

    /**
     * The listaccesostipos.
     */
    public static ArrayList<AccesoTipos> LISTACCESOSTIPOS = new ArrayList<AccesoTipos>() {
        /**
         *
         */
        private static final long serialVersionUID = -7367501021176050659L;

        {
            add(new AccesoTipos(1, "Usuario no encontrado"));
            add(new AccesoTipos(2, "Usuario de baja"));
            add(new AccesoTipos(3, "Clave incorrecta"));

            add(new AccesoTipos(4, "Login correcto"));
            add(new AccesoTipos(5, "Fin de sesión"));
            add(new AccesoTipos(6, "Consulta paciente"));
            add(new AccesoTipos(7, "Consulta registro"));
            add(new AccesoTipos(8, "Consulta informe"));
            add(new AccesoTipos(9, "Consulta proceso"));
            add(new AccesoTipos(10, "Consulta servicio rest "));
        }

    };

    /**
     * Instantiates a new acceso tipos.
     */
    public AccesoTipos() {
    }

    /**
     * Instantiates a new acceso tipos.
     *
     * @param tipo the tipo
     */
    public AccesoTipos(int tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case 1:
                setDescripcion(Acceso.ACCESO_USUARIO_NO_ENCONTRADO.getDescripcion());
                break;
            case 2:
                setDescripcion(Acceso.ACCESO_USUARIO_DE_BAJA.getDescripcion());
                break;
            case 3:
                setDescripcion(Acceso.ACCESO_USUARIO_CLAVE_INCORRECTA.getDescripcion());
                break;
            case 4:
                setDescripcion(Acceso.ACCESO_LOGIN.getDescripcion());
                break;
            case 5:
                setDescripcion(Acceso.ACCESO_LOGOUT.getDescripcion());
                break;
            case 6:
                setDescripcion(Acceso.ACCESO_PACIENTE.getDescripcion());
                break;
            case 7:
                setDescripcion(Acceso.ACCESO_REGISTRO.getDescripcion());
                break;
            case 8:
                setDescripcion(Acceso.ACCESO_INFORME.getDescripcion());
                break;
            case 9:
                setDescripcion(Acceso.ACCESO_PROCESO.getDescripcion());
                break;
            case 10:
                setDescripcion(Acceso.ACCESO_SERVICO_RES.getDescripcion());
                break;
        }
    }

    /**
     * Instantiates a new acceso tipos.
     *
     * @param tipo the tipo
     * @param desc the desc
     */
    public AccesoTipos(int tipo, String desc) {
        this.tipo = tipo;
        this.descripcion = desc;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
