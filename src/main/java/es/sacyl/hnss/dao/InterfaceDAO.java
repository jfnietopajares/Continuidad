/**
 *
 */
package es.sacyl.hnss.dao;

import java.sql.ResultSet;

/**
 * The Interface InterfaceDAO.
 *
 * @author JuanNieto
 */
public interface InterfaceDAO {

    /**
     * Revisa si tiene referencias externas en otras tablas para desactivar el
     * botón de borrado.
     *
     * @param id the id
     * @return the referencias externas
     */
    public boolean getReferenciasExternas(Long id);

    /**
     * Graba datos del objeto en la base de datos.
     *
     * @param object the object
     * @return true, if successful
     */
    public boolean grabaDatos(Object object);

    /**
     * Actualiza datos del objeto de referencia en la base de datos.
     *
     * @param mensajeparam the mensajeparam
     * @return true, if successful
     */
    public boolean actualizaDatos(Object mensajeparam);

    /**
     * Inserta en la base de datos el objeto .
     *
     * @param mensajeparam the mensajeparam
     * @return true, if successful
     */
    public boolean insertaDatos(Object mensajeparam);

    /**
     * Recupera un objeto bean a partir del objeto resulset.
     *
     * @param rs the rs
     * @return the registro resulset
     */
    public Object getRegistroResulset(ResultSet rs);

    /**
     * A partir del id de la tabla recupera el objeto bean
     *
     * @param id the id
     * @return the registro id
     */
    public Object getRegistroId(Long id);

    /**
     * Construye la sentencia WHERE para las búsquedas de registros tabulados.
     *
     * @param cadena the cadena
     * @return the sql where
     */
    public String getSqlWhere(String cadena);

    /**
     * Borra de la base de datos el objeto de referencia.
     *
     * @param objeto the objeto
     * @return true, if successful
     */
    public boolean borraDatos(Object objeto);

}
