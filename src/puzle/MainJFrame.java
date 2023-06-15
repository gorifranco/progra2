package puzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainJFrame extends JFrame {
    private MainJFrame finestraMain;
    private JButton novaPartidaIcona, classificacioIcona, historialIcona, canviarDirectoriIcona, sortirIcona,
            sortirBoto, historialBoto, classificacioBoto, novaPartidaButton, botoContinuar;
    private JMenuItem novaPartidaBotoMenu, classificacioBotoMenu, historialBotoMenu, canviarDirectoriBotoMenu, sortirBotoMenu;
    private JMenuBar barraMenu;
    private JTextArea areaVisualitzacioResultats;
    private JPanel panellTop, panellStandby, panellBotons, panellHistorial, panellPartida;
    private JSplitPane separadorNorte, separadorSur, separadorOeste;
    private JToolBar iconesMenu;
    private Container panellContinguts;
    private AreaVisualitzacio panellVisualitzacio;
    private JLabel imatgeUIB;
    private final Font FONT1 = new Font("arial", Font.BOLD, 14);
    private final Font FONT2 = new Font("arial", Font.BOLD, 18);
    private File CARPETAIMATGES = new File("src/imatges");
    private Partida p;
    private JPanel[] panellsImatges;
    private boolean[] posicionsCorrectes;


    //MÉTODO MAIN
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
            UIDefaults a = UIManager.getLookAndFeelDefaults();
//            System.out.println(a.getColor("Button.focus"));
        } catch (Exception error) {
            System.out.println("NO SE HA ESTABLECIDO LA APARIENCIA DESEADA: " + error);
        }
        new MainJFrame();
    }


////////CONSTRUCTOR

    public MainJFrame() {
        setTitle("PUZLE");
        setDefaultCloseOperation(MainJFrame.EXIT_ON_CLOSE);
        setSize(1150, 800);
        panellContinguts = getContentPane();
        panellContinguts.setLayout(new BorderLayout());
        creacionContenedoresYComponentes();
        setVisible(true);
    }

    private void creacionContenedoresYComponentes() {
        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //                  CONTENEDOR JPanel visualizador                    //
        ////////////////////////////////////////////////////////////////////////

        panellVisualitzacio = new AreaVisualitzacio();
        panellContinguts.add(panellVisualitzacio, BorderLayout.CENTER);


        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //           CONTENEDOR JPanel panellBotons y COMPONENTES            //
        ////////////////////////////////////////////////////////////////////////        

        panellBotons = new JPanel();
        panellBotons.setLayout(new GridLayout(4, 1));

        novaPartidaButton = new JButton("NOVA PARTIDA");
        novaPartidaButton.setFont(FONT1);
        novaPartidaButton.setForeground(Color.WHITE);
        novaPartidaButton.setBackground(Color.BLACK);
        novaPartidaButton.addMouseListener(new mouseListenerCustom());
        novaPartidaButton.setUI(new ButtonUICustom());
        panellBotons.add(novaPartidaButton);

        classificacioBoto = new JButton("CLASSIFICACIÓ GENERAL");
        classificacioBoto.setFont(FONT1);
        classificacioBoto.setForeground(Color.WHITE);
        classificacioBoto.setBackground(Color.BLACK);
        classificacioBoto.addMouseListener(new mouseListenerCustom());
        classificacioBoto.setUI(new ButtonUICustom());
        panellBotons.add(classificacioBoto);

        historialBoto = new JButton("HISTORIAL");
        historialBoto.setFont(FONT1);
        historialBoto.setForeground(Color.WHITE);
        historialBoto.setBackground(Color.BLACK);
        historialBoto.addMouseListener(new mouseListenerCustom());
        historialBoto.setUI(new ButtonUICustom());
        panellBotons.add(historialBoto);

        ////////COMPONENTE JButton sortirButton
        sortirBoto = new JButton("SORTIR");
        sortirBoto.setFont(FONT1);
        sortirBoto.setForeground(Color.WHITE);
        sortirBoto.setBackground(Color.BLACK);
        sortirBoto.addMouseListener(new mouseListenerCustom());
        sortirBoto.setUI(new ButtonUICustom());
        panellBotons.add(sortirBoto);


        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //            CONTENEDOR JPanel panelVarios y COMPONENTES             //
        ////////////////////////////////////////////////////////////////////////
        botoContinuar = new JButton("CONTINUAR");
        botoContinuar.setFont(FONT1);
        botoContinuar.setForeground(Color.WHITE);
        botoContinuar.setBackground(Color.BLACK);
        botoContinuar.setUI(new ButtonUICustom());
        botoContinuar.addMouseListener(new mouseListenerCustom());

        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //          COMPONENTE JMenuBar barraMenu y COMPONENTES            //
        ////////////////////////////////////////////////////////////////////////
        barraMenu = new JMenuBar();
        barraMenu.setBorder(new LineBorder(Color.WHITE));

        JMenu generalMenu = new JMenu("MENU");

        barraMenu.setBackground(Color.BLACK);
        barraMenu.setForeground(Color.WHITE);

        generalMenu.setBackground(Color.black);
        generalMenu.setForeground(Color.WHITE);
        generalMenu.getPopupMenu().setBackground(Color.BLACK);
        generalMenu.getPopupMenu().setBorder(new LineBorder(Color.WHITE));

        novaPartidaBotoMenu = crearJMenuItem("PARTIDA NOVA");
        classificacioBotoMenu = crearJMenuItem("CLASSIFICACIÓ GENERAL");
        historialBotoMenu = crearJMenuItem("HISTORIAL");
        canviarDirectoriBotoMenu = crearJMenuItem("CANVIAR DIRECTORI D'IMATGES");
        sortirBotoMenu = crearJMenuItem("SORTIR");

        generalMenu.add(novaPartidaBotoMenu);
        generalMenu.add(classificacioBotoMenu);
        generalMenu.add(historialBotoMenu);
        generalMenu.add(canviarDirectoriBotoMenu);
        generalMenu.add(sortirBotoMenu);

        barraMenu.add(generalMenu);


        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //                            JToolBar                                //
        ////////////////////////////////////////////////////////////////////////

        iconesMenu = new JToolBar();
        iconesMenu.setBackground(Color.BLACK);
        iconesMenu.setFloatable(false);
        iconesMenu.setBorder(new LineBorder(Color.WHITE));

        novaPartidaIcona = new JButton();
        novaPartidaIcona.setUI(new ButtonUICustom());
        novaPartidaIcona.setToolTipText("Nova partida");
        novaPartidaIcona.addMouseListener(new mouseListenerCustom());

        classificacioIcona = new JButton();
        classificacioIcona.setUI(new ButtonUICustom());
        classificacioIcona.addMouseListener(new mouseListenerCustom());
        classificacioIcona.setToolTipText("Classificació");

        historialIcona = new JButton();
        historialIcona.setUI(new ButtonUICustom());
        historialIcona.addMouseListener(new mouseListenerCustom());
        historialIcona.setToolTipText("Historial");

        canviarDirectoriIcona = new JButton();
        canviarDirectoriIcona.setUI(new ButtonUICustom());
        canviarDirectoriIcona.addMouseListener(new mouseListenerCustom());
        canviarDirectoriIcona.setToolTipText("Canviar directori");

        sortirIcona = new JButton();
        sortirIcona.setUI(new ButtonUICustom());
        sortirIcona.addMouseListener(new mouseListenerCustom());
        sortirIcona.setToolTipText("Sortir");

        try {
            classificacioIcona.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/iconoHistorialSelectivo.jpg"))));
            novaPartidaIcona.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/iconoNuevaPartida.jpg"))));
            historialIcona.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/iconoHistorialGeneral.jpg"))));
            canviarDirectoriIcona.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/iconoCambiarDIrectorio.jpg"))));
            sortirIcona.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/iconoSalir.jpg"))));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        iconesMenu.add(novaPartidaIcona);
        iconesMenu.add(classificacioIcona);
        iconesMenu.add(historialIcona);
        iconesMenu.add(canviarDirectoriIcona);
        iconesMenu.add(sortirIcona);

        panellTop = new JPanel(new GridLayout(2, 1));


        panellTop.add(barraMenu);
        panellTop.add(iconesMenu);


        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //                SEPARADORES JSplitPane DE LA INTERFACE              //
        ////////////////////////////////////////////////////////////////////////

        separadorNorte = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separadorSur = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separadorOeste = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        separadorOeste.add(panellBotons);
        panellContinguts.add(separadorOeste, BorderLayout.WEST);
        separadorNorte.add(panellTop);
        panellContinguts.add(separadorNorte, BorderLayout.NORTH);
        separadorSur.setBottomComponent(botoContinuar);
        panellContinguts.add(separadorSur, BorderLayout.SOUTH);
    }

    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                        CLASE AreaVisualizacion                         //
    //                                                                        //
    //////////////////////////////////////////////////////////////////////////// 
    public class AreaVisualitzacio extends JPanel {
        public AreaVisualitzacio() {
            this.setLayout(new BorderLayout());
            panellStandby = new JPanel();
            imatgeUIB = new JLabel();
            panellStandby.add(imatgeUIB);
            panellStandby.setBackground(Color.BLACK);
            this.setBackground(Color.BLACK);

            try {
                imatgeUIB.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/UIB.jpg"))));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            add(panellStandby);
        }
    }

    private JPanel crearPanellHistorial() {
        panellHistorial = new JPanel();
        panellHistorial.setBackground(Color.WHITE);

        areaVisualitzacioResultats = new JTextArea();
        areaVisualitzacioResultats.setBackground(Color.WHITE);
        areaVisualitzacioResultats.setEditable(false);
        areaVisualitzacioResultats.setText("\tHISTORIAL\n\n");
        areaVisualitzacioResultats.setFont(FONT1);
        areaVisualitzacioResultats.setTabSize(20);

        areaVisualitzacioResultats.append("JUGADOR: JUANfffffffd\t");
        areaVisualitzacioResultats.append("-fecha: ufsdgdfgsa\t");
        areaVisualitzacioResultats.append("-puntos: 65\n");
        areaVisualitzacioResultats.append("JUGADOR: J\t");
        areaVisualitzacioResultats.append("-fecha: ufsduksa\t");
        areaVisualitzacioResultats.append("-puntos: 65\n");
        areaVisualitzacioResultats.append("JUGADOR: JUAN\t");
        areaVisualitzacioResultats.append("-fecha: ufsduksa\t");
        areaVisualitzacioResultats.append("-puntos: 65");

        panellHistorial.add(areaVisualitzacioResultats);


        return panellHistorial;
    }

    private void canviarDirectoriImatges() {
        JFileChooser ventanaSeleccion = new JFileChooser();
        ventanaSeleccion.setMultiSelectionEnabled(false);
        ventanaSeleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ventanaSeleccion.setDialogTitle("SELECCIONA/ESPECIFICA EL directorio");

        int op = ventanaSeleccion.showSaveDialog(this);
        if (op == JFileChooser.APPROVE_OPTION) {
            CARPETAIMATGES = ventanaSeleccion.getSelectedFile();
        }
    }

    private File seleccionarImatgeRandom() {
        List<Path> imatges = null;
        try (Stream<Path> walk = Files.walk(CARPETAIMATGES.toPath(), 3)) {
            imatges = walk
                    .filter(Files::isRegularFile)
                    .filter(s -> s.getFileName().toString().substring(s.getFileName().toString().length() - 4).matches("\\.(jpg|png)$"))
                             .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return (imatges != null) ? imatges.get(new Random().nextInt(imatges.size())).toFile() : null;
    }

    //////////////////////////////////
    //        CREAR MENU ITEMS      //
    //////////////////////////////////

    private JMenuItem crearJMenuItem(String text) {
        JMenuItem a = new JMenuItem(text);
        a.setBackground(Color.black);
        a.setForeground(Color.WHITE);
        a.setFont(FONT1);
        a.setBorder(new LineBorder(Color.WHITE));
        a.setUI(new ButtonUICustom());
        a.addMouseListener(new mouseListenerCustom());
        return a;
    }


    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                          CREAR PANELL PARTIDA                          //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////

    private void comensarPartida(int horitzontal, int vertival) throws IOException {
        crearPanellPartida(horitzontal, vertival);
        if(panellHistorial != null) panellHistorial.setVisible(false);
        panellStandby.setVisible(false);

        panellVisualitzacio.setBackground(Color.white);
        panellVisualitzacio.add(panellPartida, BorderLayout.CENTER);
        this.setSize(1156, 704);
        this.setResizable(false);
    }

    private void crearPanellPartida(int horitzontal, int vertical) throws IOException{
        File f = seleccionarImatgeRandom();
        BufferedImage bi;
        if (f != null) {
            try {
                bi = ImageIO.read(f);
            }catch (IOException e){
                throw new IOException();
            }
            JLabel[] imatges = dividirImatge(escalarImatge(bi, 910, 540), horitzontal, vertical);
            panellPartida = new JPanel(new GridLayout(horitzontal, vertical, 1, 1));
            panellPartida.setBackground(Color.white);
            for (int i = 0; i < imatges.length; i++) {
                panellPartida.add(imatges[i]);
            }
        }
    }

    //xapa la imatge en trossos i fica aquests dins JPanels dins un array
    private JLabel[] dividirImatge(BufferedImage imatge, int horitzontal, int vertical){
        JLabel[] imatges = new JLabel[horitzontal*vertical];
        int contador = 0;
        for (int j = 0; j < horitzontal; j++) {
            for (int k = 0; k < vertical; k++) {
                Image tembImg = imatge.getSubimage(k*imatge.getWidth()/vertical, j*imatge.getHeight()/horitzontal, imatge.getWidth()/vertical, imatge.getHeight()/horitzontal);
                JLabel tempJL = new JLabel();
                tempJL.setName(Integer.toString(contador));
                tempJL.setIcon(new ImageIcon(tembImg));
                imatges[contador++] = tempJL;
            }
        }
        return imatges;
    }

    private JLabel[] barallarImatges(JLabel[] imatges) {
        Random r = new Random();
        for (int i = 0; i < imatges.length; i++) {
            int j = r.nextInt(imatges.length);
            JLabel temp = imatges[i];
            imatges[i] = imatges[j];
            imatges[j] = temp;
        }
        return imatges;
    }
    private void comprobarPosicionsCorrectes(){
        posicionsCorrectes = new boolean[panellsImatges.length];
        for (int i = 0; i < panellsImatges.length; i++) {
            if(panellsImatges[i].getName().substring(1).equals(Integer.toString(i))) posicionsCorrectes[i] = true;
        }
    }

    private void comprobarPosicionsCorrectes(JPanel p1, JPanel p2){

    }

    private BufferedImage escalarImatge(BufferedImage src, int w, int h)
    {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
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


    private class mouseListenerCustom implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource().equals(sortirIcona) || e.getSource().equals(sortirBoto) || e.getSource().equals(sortirBotoMenu))
                System.exit(0);
            if (e.getSource().equals(historialBoto) || e.getSource().equals(historialIcona) || e.getSource().equals(historialBotoMenu)) {
                panellStandby.setVisible(false);
                panellVisualitzacio.add(crearPanellHistorial(), BorderLayout.CENTER);

            }
            if (e.getSource().equals(canviarDirectoriIcona) || e.getSource().equals(canviarDirectoriBotoMenu)) {
                System.out.println(MainJFrame.this.getWidth());
                System.out.println(MainJFrame.this.getHeight());
                canviarDirectoriImatges();
            }
            if (e.getSource().equals(novaPartidaBotoMenu) || e.getSource().equals(novaPartidaButton) || e.getSource().equals(novaPartidaIcona))
                new IntroduirDades("INTRODUIR DADES");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                     Panell per introduir les dades                     //
    //                                                                        //
    ////////////////////////////////////////////////////////////////////////////

    private class IntroduirDades extends JDialog {
        private JButton continuar;
        private JTextField subHoritzontal, subVertical;
        private JTextField nom;
        private JLabel nomLabel, subHLabel, subVLabel;
        private JPanel camps;

        public IntroduirDades(String title) {
            setModal(false);
            setTitle(title);
            setLayout(new BorderLayout());
            crearVista();
            setLocationRelativeTo(MainJFrame.this);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        }

        private void crearVista() {
            camps = new JPanel(new GridLayout(3, 2));

            nom = new JTextField();
            nom.setFont(FONT1);
            nom.setBackground(Color.white);
            nom.setForeground(Color.BLACK);
            AbstractDocument ad = (AbstractDocument) nom.getDocument();
            ad.setDocumentFilter(new FiltroSize());

            subHoritzontal = new JTextField();
            subHoritzontal.setFont(FONT1);
            subHoritzontal.setBackground(Color.white);
            subHoritzontal.setForeground(Color.BLACK);
            ((PlainDocument) subHoritzontal.getDocument()).setDocumentFilter(new FiltroInt());

            subVertical = new JTextField(5);
            subVertical.setFont(FONT1);
            subVertical.setBackground(Color.white);
            subVertical.setForeground(Color.BLACK);
            ((PlainDocument) subVertical.getDocument()).setDocumentFilter(new FiltroInt());

            nomLabel = new JLabel("  NOM JUGADOR");
            nomLabel.setFont(FONT1);
            nomLabel.setBackground(Color.black);
            nomLabel.setForeground(Color.WHITE);
            nomLabel.setBorder(new LineBorder(Color.WHITE));
            nomLabel.setOpaque(true);

            subHLabel = new JLabel("  NOMBRE DE SUBDIVISIONS HORITZONTALS");
            subHLabel.setFont(FONT1);
            subHLabel.setBackground(Color.BLACK);
            subHLabel.setForeground(Color.WHITE);
            subHLabel.setBorder(new LineBorder(Color.WHITE));
            subHLabel.setOpaque(true);

            subVLabel = new JLabel("  NOMBRE DE SUBDIVISIONS VERTICALS");
            subVLabel.setFont(FONT1);
            subVLabel.setBackground(Color.BLACK);
            subVLabel.setForeground(Color.WHITE);
            subVLabel.setBorder(new LineBorder(Color.WHITE));
            subVLabel.setOpaque(true);

            continuar = new JButton("CONTINUAR");
            continuar.setFont(FONT1);
            continuar.setUI(new ButtonUICustom());

            continuar.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (checkdatos()) {
                        dispose();
                        guardarDades();
                        try {
                            MainJFrame.this.comensarPartida(Integer.parseInt(subHoritzontal.getText()), Integer.parseInt(subVertical.getText()));
                        }catch(IOException exc){
                            JOptionPane.showMessageDialog(IntroduirDades.this, "Error creant la partida");
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            camps.add(nomLabel);
            camps.add(nom);
            camps.add(subHLabel);
            camps.add(subHoritzontal);
            camps.add(subVLabel);
            camps.add(subVertical);
            add(camps);
            add(continuar, BorderLayout.SOUTH);
            setSize(700, 150);
        }

        private void guardarDades() {
            if (p == null) p = new Partida();
            MainJFrame.this.p.setJugador(nom.getText());
            MainJFrame.this.p.setData(LocalDate.now());
        }

        private boolean checkdatos() {
            boolean resultat = true;
            String missatge = "";
            if (nom.getText().equals("")) {
                missatge += "* Nom no pot estar buit\n";
                resultat = false;
            }
            if (subHoritzontal.getText().equals("")) {
                missatge += "* Divisions horitzontals no pot estar buit\n";
                resultat = false;
            } else if (Integer.parseInt(subHoritzontal.getText()) < 1) {
                missatge += "* Divisions horitzontals no pot ser menor que 1\n";
                resultat = false;
            }
            if (subVertical.getText().equals("")) {
                missatge += "* Divisions verticals no pot estar buit\n";
                resultat = false;
            } else if (Integer.parseInt(subVertical.getText()) < 1) {
                missatge += "* Divisions verticals no pot ser menor que 1\n";
                resultat = false;
            }
            if (!resultat) JOptionPane.showMessageDialog(this, missatge, "Error", JOptionPane.ERROR_MESSAGE);
            return resultat;
        }

        class FiltroSize extends DocumentFilter {
            @Override
            public void insertString(FilterBypass fb, int offset, String string,
                                     AttributeSet attr) throws BadLocationException {

                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder();
                sb.append(doc.getText(0, doc.getLength()));
                sb.insert(offset, string);

                if (test(sb.toString())) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            private boolean test(String text) {
                return text.length() < 13;
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {

                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder();
                sb.append(doc.getText(0, doc.getLength()));
                sb.replace(offset, offset + length, text);

                if (test(sb.toString())) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        }

        class FiltroInt extends DocumentFilter {
            @Override
            public void insertString(FilterBypass fb, int offset, String string,
                                     AttributeSet attr) throws BadLocationException {

                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder();
                sb.append(doc.getText(0, doc.getLength()));
                sb.insert(offset, string);

                if (test(sb.toString())) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            private boolean test(String text) {
                if (text.length() > 3) return false;
                try {
                    Integer.parseInt(text);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {

                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder();
                sb.append(doc.getText(0, doc.getLength()));
                sb.replace(offset, offset + length, text);

                if (test(sb.toString())) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length)
                    throws BadLocationException {
                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder();
                sb.append(doc.getText(0, doc.getLength()));
                sb.delete(offset, offset + length);

                if (sb.toString().length() == 0) {
                    super.replace(fb, offset, length, "", null);
                } else {
                    if (test(sb.toString())) {
                        super.remove(fb, offset, length);
                    }
                }
            }
        }
    }
}

