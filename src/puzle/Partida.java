package puzle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class Partida implements Serializable {
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

    public static void guardarPartida(Partida partida) {
        ArrayList<Partida> saves = llegirPartides();
        saves.add(partida);
        System.out.println(partida.getPunts());
        System.out.println(partida.jugador);
        System.out.println(partida.data);
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(Path.of("src/puzle/saves.txt"))))) {
           for(Partida p: saves){
               oos.writeObject(p);
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Partida> llegirPartides() {
        ArrayList<Partida> partides = new ArrayList<>();
        boolean eof = false;
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(Path.of("src/puzle/saves.txt"))))) {
            while (!eof) {
                try {
                    Partida p = (Partida) ois.readObject();
                    partides.add(p);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("fallo");
        }
            return partides;
    }
}
