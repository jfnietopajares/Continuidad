package es.sacyl.hnss.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Historia. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class Historia {

    private Long id;
    private Long paciente;
    private String nhc;

    /**
     * Instantiates a new historia.
     */
    public Historia() {

    }

    /**
     * Instantiates a new historia.
     *
     * @param id the id
     * @param paciente the paciente
     * @param nhc the nhc
     */
    public Historia(Long id, Long paciente, String nhc) {
        this.id = id;
        this.paciente = paciente;
        this.nhc = nhc;
    }

    /**
     * Instantiates a new historia.
     *
     * @param paciente the paciente
     * @param nhc the nhc
     */
    public Historia(Long paciente, String nhc) {
        this.paciente = paciente;
        this.nhc = nhc;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the paciente.
     *
     * @return the paciente
     */
    public Long getPaciente() {
        return paciente;
    }

    /**
     * Sets the paciente.
     *
     * @param paciente the new paciente
     */
    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    /**
     * Gets the nhc.
     *
     * @return the nhc
     */
    public String getNhc() {
        return nhc;
    }

    /**
     * Sets the nhc.
     *
     * @param nhc the new nhc
     */
    public void setNhc(String nhc) {
        this.nhc = nhc;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "id=" + getId() + " nhc=" + getNhc() + " paciente=" + getPaciente();
    }
}
