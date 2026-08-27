/**
 * Representa una solicitud de soporte regular: configuración de
 * correo, cambio de contraseñas, instalación de software, etc.
 */
public class Ticket {
    private String idTicket;
    private String usuario;
    private String departamento;
    private String descripcionProblema;

    public Ticket(String idTicket, String usuario, String departamento, String descripcionProblema) {
        this.idTicket = idTicket;
        this.usuario = usuario;
        this.departamento = departamento;
        this.descripcionProblema = descripcionProblema;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    @Override
    public String toString() {
        return "[" + idTicket + "] Usuario: " + usuario
                + " | Depto: " + departamento
                + " | Problema: " + descripcionProblema;
    }
}
