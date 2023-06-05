package puzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class MainJFrame extends JFrame {

    private MainJFrame ventana;
    private JButton novaPartidaIcona, classificacioIcona, historialIcona, canviarDirectoriIcona, sortirIcona,
    sortirBoto, historialBoto, classificacioBoto, novaPartidaButton, botoContinuar;
    private JMenuItem novaPartidaBotoMenu, classificacioBotoMenu, historialBotoMenu, canviarDirectoriBotoMenu, sortirBotoMenu;
    private JTextArea areaVisualitzacioResultats;
    private JPanel panellTop, panellStandby, panellBotons;
    private JToolBar iconesMenu;
    private ObjetoGrafico objeto;
    private Container panellContinguts;
    private AreaVisualitzacio panellVisualitzacio;
    private boolean visualizacionSolida = false;
    private JLabel imatgeUIB;
    private boolean creacionObjeto = false;
    private final Font FONT1 = new Font("arial", Font.BOLD, 14);
    private final Font FONT2 = new Font("arial", Font.BOLD, 18);


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
        panellBotons.add(novaPartidaButton);

        classificacioBoto = new JButton("CLASSIFICACIÓ GENERAL");
        classificacioBoto.setFont(FONT1);
        classificacioBoto.setForeground(Color.WHITE);
        classificacioBoto.setBackground(Color.BLACK);
        classificacioBoto.addMouseListener(new mouseListenerCustom());
        panellBotons.add(classificacioBoto);

        historialBoto = new JButton("HISTORIAL");
        historialBoto.setFont(FONT1);
        historialBoto.setForeground(Color.WHITE);
        historialBoto.setBackground(Color.BLACK);
        historialBoto.addMouseListener(new mouseListenerCustom());
        panellBotons.add(historialBoto);

        ////////COMPONENTE JButton sortirButton
        sortirBoto = new JButton("SORTIR");
        sortirBoto.setFont(FONT1);
        sortirBoto.setForeground(Color.WHITE);
        sortirBoto.setBackground(Color.BLACK);
        sortirBoto.addMouseListener(new mouseListenerCustom());
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
        JMenuBar barraMenu = new JMenuBar();

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
        iconesMenu.setBackground(Color.green);
        iconesMenu.setFloatable(false);

        novaPartidaIcona = new JButton();
        classificacioIcona = new JButton();
        historialIcona = new JButton();
        canviarDirectoriIcona = new JButton();
        sortirIcona = new JButton();

        novaPartidaIcona.addMouseListener();

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

        JSplitPane separadorNorte = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorSur = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorOeste = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

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
            imatgeUIB = new JLabel();
            this.setBackground(Color.black);
            try {
                imatgeUIB.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/UIB.jpg"))));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            add(imatgeUIB);
            imatgeUIB.setVisible(true);

        }
    }

    private JTextArea obtenirResultats() {
        areaVisualitzacioResultats = new JTextArea();
        areaVisualitzacioResultats.setColumns(3);
        areaVisualitzacioResultats.setFont(FONT2);
        areaVisualitzacioResultats.setBackground(Color.WHITE);
        areaVisualitzacioResultats.append("HISTORIAL\n\n");


                return areaVisualitzacioResultats;
    }

    private File obtenerDirectorio() {
        JFileChooser ventanaSeleccion = new JFileChooser();
        ventanaSeleccion.setDialogTitle("SELECCIONA/ESPECIFICA EL FICHERO");
        int op = ventanaSeleccion.showSaveDialog(this);
        if (op == JFileChooser.APPROVE_OPTION) {
            //obtención del nombre del fichero
            return ventanaSeleccion.getSelectedFile();
        }
        return null;
    }


    private class mouseListenerCustom implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
if(e.getSource().equals(sortirIcona)) System.exit(0);
if(e.getSource().equals(sortirBoto)) System.exit(0);
if(e.getSource().equals(historialBoto) || e.getSource().equals(historialIcona)){
    panellVisualitzacio.add(obtenirResultats());
    panellVisualitzacio.setBackground(Color.WHITE);
    imatgeUIB.setVisible(false);
}
if(e.getSource().equals(historialBotoMenu)){
    panellVisualitzacio.add(obtenirResultats());
    panellVisualitzacio.setBackground(Color.WHITE);
    imatgeUIB.setVisible(false);
}
        }

        @Override
        public void mousePressed(MouseEvent e) {

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
}
