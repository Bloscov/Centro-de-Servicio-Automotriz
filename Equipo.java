/**
 * Representa un equipo informático o licencia dentro del inventario
 * de la empresa, asignado a un usuario o departamento.
 */
public class Equipo {
    private String numSerie;
    private String tipoEquipo;
    private String marca;
    private String usuarioAsignado;
    private boolean obsoleto;

    public Equipo(String numSerie, String tipoEquipo, String marca, String usuarioAsignado) {
        this.numSerie = numSerie;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.usuarioAsignado = usuarioAsignado;
        this.obsoleto = false;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public boolean isObsoleto() {
        return obsoleto;
    }

    public void marcarObsoleto() {
        this.obsoleto = true;
    }

    @Override
    public String toString() {
        String estado = obsoleto ? "OBSOLETO" : "Activo";
        return "[" + numSerie + "] " + tipoEquipo + " " + marca
                + " | Asignado a: " + usuarioAsignado
                + " | Estado: " + estado;
    }
}
