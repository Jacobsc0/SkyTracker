public class ClimaDetallado {
    private String hora;
    private String temperatura;
    private String condiciones;
    private String viento;
    private String sensacion;
    private String riesgo;

    public ClimaDetallado(String hora, String temperatura, String condiciones, String viento, String sensacion, String riesgo) {
        this.hora = hora;
        this.temperatura = temperatura;
        this.condiciones = condiciones;
        this.viento = viento;
        this.sensacion = sensacion;
        this.riesgo = riesgo;
    }

    public String getHora() {
        return hora;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public String getViento() {
        return viento;
    }

    public String getSensacion() {
        return sensacion;
    }

    public String getRiesgo() {
        return riesgo;
    }
}
