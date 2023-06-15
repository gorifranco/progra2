import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Proves {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\Frank\\Desktop\\programacio\\progra2\\progra2\\src\\imatges\\fondo_teemo.png");
File f2 = new File("C:\\Users\\Frank\\Desktop\\programacio\\progra2\\progra2\\src\\icones\\iconoCambiarDIrectorio.jpg");
        JFrame jf = new JFrame();
        JPanel PImatges = new JPanel(new GridLayout(5,5));
        jf.setVisible(true);
        jf.setSize(600,500);
        BufferedImage i = ImageIO.read(f);
        JPanel[] imatges = new JPanel[25];
        i = scale(i, 600,500);
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                Image tembImg = i.getSubimage(k*600/5, j*500/5, 600/5, 500/5);
                JLabel tempJL = new JLabel();
                tempJL.setSize(120,100);
                tempJL.setIcon(new ImageIcon(tembImg));
PImatges.add(tempJL);
            }
        }
jf.add(PImatges);
        jf.repaint();

    }
    public static BufferedImage scale(BufferedImage src, int w, int h)
    {
        BufferedImage img =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
}
