package puzle;

import java.time.LocalDate;

public class Partida {
    private String jugador;
    private LocalDate data;
    private int punts;

    public Partida(String jugador, LocalDate data, int punts) {
        this.jugador = jugador;
        this.data = data;
        this.punts = punts;
    }

    public Partida() {
        this.data = LocalDate.now();
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }
}
