/**
 * Representa una interrupción crítica del sistema: caída de
 * servidores, brechas de seguridad, etc. Exige intervención
 * inmediata del equipo de guardia.
 */
public class Incidente {
    private String idIncidente;
    private String servidorAfectado;
    private String severidad;
    private String horaReporte;

    public Incidente(String idIncidente, String servidorAfectado, String severidad, String horaReporte) {
        this.idIncidente = idIncidente;
        this.servidorAfectado = servidorAfectado;
        this.severidad = severidad;
        this.horaReporte = horaReporte;
    }

    public String getIdIncidente() {
        return idIncidente;
    }

    public String getServidorAfectado() {
        return servidorAfectado;
    }

    public String getSeveridad() {
        return severidad;
    }

    public String getHoraReporte() {
        return horaReporte;
    }

    @Override
    public String toString() {
        return "[" + idIncidente + "] Servidor: " + servidorAfectado
                + " | Severidad: " + severidad
                + " | Reportado: " + horaReporte;
    }
}
