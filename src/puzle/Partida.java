package puzle;

import java.time.LocalDate;

public class Partida {
    private String jugador;
    private LocalDate data;

    public Partida(String jugador, LocalDate data) {
        this.jugador = jugador;
        this.data = data;
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
}
