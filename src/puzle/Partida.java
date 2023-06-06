package puzle;

import java.time.LocalDate;

public class Partida {
    private String jugador;
    private LocalDate data;
    private int divisionsHoritzaontals;
    private int divisionsVerticals;

    public Partida(String jugador, LocalDate data, int divisionsHoritzaontals, int divisionsVerticals) {
        this.jugador = jugador;
        this.data = data;
        this.divisionsHoritzaontals = divisionsHoritzaontals;
        this.divisionsVerticals = divisionsVerticals;
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

    public int getDivisionsHoritzaontals() {
        return divisionsHoritzaontals;
    }

    public void setDivisionsHoritzaontals(int divisionsHoritzaontals) {
        this.divisionsHoritzaontals = divisionsHoritzaontals;
    }

    public int getDivisionsVerticals() {
        return divisionsVerticals;
    }

    public void setDivisionsVerticals(int divisionsVerticals) {
        this.divisionsVerticals = divisionsVerticals;
    }
}
