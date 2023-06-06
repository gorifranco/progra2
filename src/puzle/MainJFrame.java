package puzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Format;
import java.text.NumberFormat;
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
    private JPanel panellTop, panellStandby, panellBotons;
    private JSplitPane separadorNorte, separadorSur, separadorOeste;
    private JToolBar iconesMenu;
    private Container panellContinguts;
    private AreaVisualitzacio panellVisualitzacio;
    private JLabel imatgeUIB;
    private final Font FONT1 = new Font("arial", Font.BOLD, 14);
    private final Font FONT2 = new Font("arial", Font.BOLD, 18);
    private File carpetaImatges;
    private Partida p;


    //MÉTODO MAIN
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
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
        botoContinuar.addMouseListener(new mouseListenerCustom());

        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //          COMPONENTE JMenuBar barraMenu y COMPONENTES            //
        ////////////////////////////////////////////////////////////////////////
        barraMenu = new JMenuBar();

        JMenu generalMenu = new JMenu("MENU");

        barraMenu.setBackground(Color.BLACK);
        barraMenu.setForeground(Color.WHITE);

        generalMenu.setBackground(Color.black);
        generalMenu.setForeground(Color.WHITE);

        novaPartidaBotoMenu = new JMenuItem("PARTIDA NOVA");
        classificacioBotoMenu = new JMenuItem("CLASSIFICACIÓ GENERAL");
        historialBotoMenu = new JMenuItem("HISTORIAL");
        canviarDirectoriBotoMenu = new JMenuItem("CANVIAR DIRECTORI D'IMATGES");
        sortirBotoMenu = new JMenuItem("SORTIR");

        novaPartidaBotoMenu.setBackground(Color.black);
        novaPartidaBotoMenu.setForeground(Color.WHITE);
        novaPartidaBotoMenu.setFont(FONT1);

        classificacioBotoMenu.setBackground(Color.black);
        classificacioBotoMenu.setForeground(Color.WHITE);
        classificacioBotoMenu.setFont(FONT1);

        historialBotoMenu.setBackground(Color.black);
        historialBotoMenu.setForeground(Color.WHITE);
        historialBotoMenu.setFont(FONT1);

        canviarDirectoriBotoMenu.setBackground(Color.black);
        canviarDirectoriBotoMenu.setForeground(Color.WHITE);
        canviarDirectoriBotoMenu.setFont(FONT1);

        sortirBotoMenu.setBackground(Color.black);
        sortirBotoMenu.setForeground(Color.WHITE);
        sortirBotoMenu.setFont(FONT1);

        novaPartidaBotoMenu.setUI(new ButtonUICustom());
        classificacioBotoMenu.setUI(new ButtonUICustom());
        sortirBotoMenu.setUI(new ButtonUICustom());
        historialBotoMenu.setUI(new ButtonUICustom());
        canviarDirectoriBotoMenu.setUI(new ButtonUICustom());
        sortirBotoMenu.setUI(new ButtonUICustom());

        novaPartidaBotoMenu.addMouseListener(new mouseListenerCustom());
        classificacioBotoMenu.addMouseListener(new mouseListenerCustom());
        sortirBotoMenu.addMouseListener(new mouseListenerCustom());
        historialBotoMenu.addMouseListener(new mouseListenerCustom());
        canviarDirectoriBotoMenu.addMouseListener(new mouseListenerCustom());
        sortirBotoMenu.addMouseListener(new mouseListenerCustom());

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

        novaPartidaIcona = new JButton();
        classificacioIcona = new JButton();
        historialIcona = new JButton();
        canviarDirectoriIcona = new JButton();
        sortirIcona = new JButton();

        novaPartidaIcona.setUI(new ButtonUICustom());
        classificacioIcona.setUI(new ButtonUICustom());
        historialIcona.setUI(new ButtonUICustom());
        canviarDirectoriIcona.setUI(new ButtonUICustom());
        sortirIcona.setUI(new ButtonUICustom());

        novaPartidaIcona.addMouseListener(new mouseListenerCustom());
        classificacioIcona.addMouseListener(new mouseListenerCustom());
        historialIcona.addMouseListener(new mouseListenerCustom());
        canviarDirectoriIcona.addMouseListener(new mouseListenerCustom());
        sortirIcona.addMouseListener(new mouseListenerCustom());

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

        carpetaImatges = new File("src/imatges");
    }

    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                        CLASE AreaVisualizacion                         //
    //                                                                        //
    //////////////////////////////////////////////////////////////////////////// 
    public class AreaVisualitzacio extends JPanel {
        public AreaVisualitzacio() {
            panellStandby = new JPanel();
            imatgeUIB = new JLabel();
            panellStandby.add(imatgeUIB);
            this.setBackground(Color.BLACK);

            try {
                imatgeUIB.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/UIB.jpg"))));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            add(panellStandby);
        }
    }

    private JTextArea obtenirResultats() {
        areaVisualitzacioResultats = new JTextArea();
        areaVisualitzacioResultats.setFont(FONT2);
        areaVisualitzacioResultats.setBackground(Color.WHITE);
        areaVisualitzacioResultats.setText("HISTORIAL");

        return areaVisualitzacioResultats;
    }

    private void canviarDirectoriImatges() {
        JFileChooser ventanaSeleccion = new JFileChooser();
        ventanaSeleccion.setMultiSelectionEnabled(false);
        ventanaSeleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ventanaSeleccion.setDialogTitle("SELECCIONA/ESPECIFICA EL directorio");

        int op = ventanaSeleccion.showSaveDialog(this);
        if (op == JFileChooser.APPROVE_OPTION) {
            carpetaImatges = ventanaSeleccion.getSelectedFile();
        }
    }

    private File seleccionarImatgeRandom() {
        List<Path> imatges = null;
        try (Stream<Path> walk = Files.walk(carpetaImatges.toPath(), 5)) {
            imatges = walk
                    .filter(Files::isRegularFile)
                    .filter(s -> s.getFileName().toString().substring(s.getFileName().toString().length() - 4, s.getFileName().toString().length() - 1).matches("\\.(jpg|png)$"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return (imatges != null) ? imatges.get(new Random(imatges.size()).nextInt()).toFile() : null;
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
                panellVisualitzacio.add(obtenirResultats());
                panellVisualitzacio.setBackground(Color.WHITE);
                imatgeUIB.setVisible(false);
            }
            if (e.getSource().equals(canviarDirectoriIcona) || e.getSource().equals(canviarDirectoriBotoMenu)) {
                canviarDirectoriImatges();
            }
            if (e.getSource().equals(novaPartidaBotoMenu) || e.getSource().equals(novaPartidaButton) || e.getSource().equals(novaPartidaIcona))
                new IntroduirDades(finestraMain);

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
        private JFormattedTextField subHoritzontal, subVertical;
        private JTextField nom;
        private JLabel nomLabel, subHLabel, subVLabel;
        private JPanel camps;

        public IntroduirDades(Frame owner) {
            super(owner, true);
            setTitle("INTRODUCCIÓ DE DADES");
            setLayout(new BorderLayout());
            crearVista();
           setLocationRelativeTo(MainJFrame.this);
           setVisible(true);
        }

        private void crearVista() {
            camps = new JPanel(new GridLayout(3, 2));

            nom = new JTextField();
            nom.setFont(FONT1);
            nom.setBackground(Color.white);
            nom.setForeground(Color.BLACK);

            subHoritzontal = new JFormatedTextFieldCustom();
            subHoritzontal.setFont(FONT1);
            subHoritzontal.setBackground(Color.white);
            subHoritzontal.setForeground(Color.BLACK);

            subVertical = new JFormatedTextFieldCustom();
            subVertical.setFont(FONT1);
            subVertical.setBackground(Color.white);
            subVertical.setForeground(Color.BLACK);

            nomLabel = new JLabel("NOM JUGADOR");
            nomLabel.setFont(FONT1);
            nomLabel.setBackground(Color.black);
            nomLabel.setForeground(Color.WHITE);
            nomLabel.setBorder(new LineBorder(Color.WHITE));
            nomLabel.setOpaque(true);

            subHLabel = new JLabel("NOMBRE DE SUBDIVISIONS HORITZONTALS");
            subHLabel.setFont(FONT1);
            subHLabel.setBackground(Color.BLACK);
            subHLabel.setForeground(Color.WHITE);
            subHLabel.setBorder(new LineBorder(Color.WHITE));
            subHLabel.setOpaque(true);

            subVLabel = new JLabel("NOMBRE DE SUBDIVISIONS VERTICALS");
            subVLabel.setFont(FONT1);
            subVLabel.setBackground(Color.BLACK);
            subVLabel.setForeground(Color.WHITE);
            subVLabel.setBorder(new LineBorder(Color.WHITE));
            subVLabel.setOpaque(true);

            continuar = new JButton("CONTINUAR");
            continuar.setFont(FONT1);

            continuar.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                if(checkdatos()){
                    setVisible(false);
//                 MainJFrame.this.comensaPartida();
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
            setSize(700,150);
        }
        private void guardarDades(){
            if(p == null) p = new Partida();
            MainJFrame.this.p.setJugador(nom.getText());
            MainJFrame.this.p.setDivisionsHoritzaontals(Integer.parseInt(subHoritzontal.getText()));
            MainJFrame.this.p.setDivisionsVerticals(Integer.parseInt(subVertical.getText()));
        }
        private boolean checkdatos(){
            boolean resultat = true;
            String missatge = "";
            if(nom.getText().equals("")){
                missatge += "* Nom no pot estar buit\n";
                resultat = false;
            }
            if(subHoritzontal.getText().equals("")){
                missatge += "* Divisions horitzontals no pot estar buit\n";
                resultat = false;
            } else if (Integer.parseInt(subHoritzontal.getText()) <1) {
                missatge += "* Divisions horitzontals no pot ser menor que 1\n";
                resultat = false;
            }
            if(subVertical.getText().equals("")){
                missatge += "* Divisions verticals no pot estar buit\n";
                resultat = false;
            }else if(Integer.parseInt(subVertical.getText()) <1){
                missatge += "* Divisions verticals no pot ser menor que 1\n";
                resultat = false;
            }
            if(!resultat) JOptionPane.showMessageDialog(this, missatge, "Error", JOptionPane.ERROR_MESSAGE);
            return resultat;
        }
        private class JFormatedTextFieldCustom extends JFormattedTextField{

            public JFormatedTextFieldCustom() {
                create();
            }
            private void create(){
                NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
                formatter.setAllowsInvalid(false);
                formatter.setValueClass(Integer.class);
                setFormatter(formatter);
                this.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && getText().length() == 1){
                            setText("0");
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
            }
        }
    }
}
