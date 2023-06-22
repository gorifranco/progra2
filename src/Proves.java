import puzle.Partida;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Proves {
    public static void main(String[] args) throws IOException {
        ArrayList<Partida> p = Partida.llegirPartides();
        for (Partida pp : p){
            System.out.println(pp.toString());
        }
    }
}
