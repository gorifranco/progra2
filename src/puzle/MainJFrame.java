package puzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class MainJFrame extends JFrame {

    private MainJFrame ventana;
    private JButton novaPartidaIcona, classificacioIcona, historialIcona, canviarDirectoriIcona, sortirIcona;
    private JTextArea areaVisualitzacioResultats;
    private JPanel panellTop, panellStandby;
    private JToolBar iconesMenu;
    private ObjetoGrafico objeto;
    private Container panelContenidos;
    private AreaVisualizacion visualizador;
    private boolean visualizacionSolida = false;
    private Color colorFondo = Color.BLACK;
    private JRadioButton pintadoBoton, trazadoBoton;
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
        panelContenidos = getContentPane();
        panelContenidos.setLayout(new BorderLayout());
        creacionContenedoresYComponentes();
        setVisible(true);
    }

    private void creacionContenedoresYComponentes() {
        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //                  CONTENEDOR JPanel visualizador                    //
        ////////////////////////////////////////////////////////////////////////       
        //DECLARACIÓN CONTENEDOR JPanel AreaVisualizacion visualizador
        visualizador = new AreaVisualizacion();
        //INTRODUCCIÓN CONTENEDOR JPanel visualizador EN EL PANEL DE CONTENIDOS
        //DEL JFrame
        panelContenidos.add(visualizador, BorderLayout.CENTER);


        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //           CONTENEDOR JPanel panelCreacion y COMPONENTES            //
        ////////////////////////////////////////////////////////////////////////        
        //DECLARACIÓN CONTENEDOR JPanel panelCreacion 
        JPanel panelCreacion = new JPanel();
        //ASIGNACIÓN AL CONTENEDOR panelCreacion DEL ADMINISTRADOR DE LAYOUT 
        //GridLayout CON UNA ESTRUCTURA DE 8 FILAS Y 1 COLUMNA
        panelCreacion.setLayout(new GridLayout(4, 1));

        ////////COMPONENTE JButton novaPartidaButton
        JButton novaPartidaButton = new JButton("NOVA PARTIDA");
        novaPartidaButton.setFont(FONT1);
        novaPartidaButton.setForeground(Color.WHITE);
        novaPartidaButton.setBackground(Color.BLACK);
        novaPartidaButton.addMouseListener(new mouseListenerCustom());
        panelCreacion.add(novaPartidaButton);

        ////////COMPONENTE JButton historialGeneralButton
        JButton historialGeneralButton = new JButton("HISTORIAL GENERAL");
        historialGeneralButton.setFont(FONT1);
        historialGeneralButton.setForeground(Color.WHITE);
        historialGeneralButton.setBackground(Color.BLACK);
        historialGeneralButton.addMouseListener(new mouseListenerCustom());
        panelCreacion.add(historialGeneralButton);

        ////////COMPONENTE JButton historialSelectiuButton
        JButton historialSelectiuButton = new JButton("HISTORIAL SELECTIU");
        historialSelectiuButton.setFont(FONT1);
        historialSelectiuButton.setForeground(Color.WHITE);
        historialSelectiuButton.setBackground(Color.BLACK);
        historialSelectiuButton.addMouseListener(new mouseListenerCustom());
        panelCreacion.add(historialSelectiuButton);

        ////////COMPONENTE JButton sortirButton
        JButton sortirButton = new JButton("SORTIR");
        sortirButton.setFont(FONT1);
        sortirButton.setForeground(Color.WHITE);
        sortirButton.setBackground(Color.BLACK);
        sortirButton.addMouseListener(new mouseListenerCustom());
        panelCreacion.add(sortirButton);


        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //           CONTENEDOR JPanel panelContexto y COMPONENTES            //
        ////////////////////////////////////////////////////////////////////////               
        //DECLARACIÓN CONTENEDOR JPanel panelContexto 
        JPanel panelContexto = new JPanel();
        //ASIGNACIÓN AL CONTENEDOR panelCreacion DEL ADMINISTRADOR DE LAYOUT 
        //GridLayout CON UNA ESTRUCTURA DE 8 FILAS Y 1 COLUMNA
        panelContexto.setLayout(new GridLayout(4, 1));


        ////////////////////////////////////////////////////////////////////////
        ////////////////   CONTENEDOR JPanel contenedorStroke   ////////////////
        //DECLARACIÓN CONTENEDOR JPanel contenedorStroke
        JPanel contenedorStroke = new JPanel();
        //ASIGNACIÓN AL contenedorSTroke DEL ADMINISTRADOR DE LAYOUT BorderLayout
        contenedorStroke.setLayout(new BorderLayout());

        ////////COMPONENTE JButton strokeBoton
        JButton strokeBoton = new JButton("STROKE");
        //asignación tipografia a la componente JButton strokeBoton
        strokeBoton.setFont(new Font("arial", Font.BOLD, 13));
        //asignación color de abrirBoton componente JButton strokeBoton
        strokeBoton.setForeground(Color.WHITE);
        //asignación color de fondo componente JButton strokeBoton
        strokeBoton.setBackground(Color.BLACK);
        //manipulador de evento asociado a la componente 
        //JButton strokeBoton
        strokeBoton.addMouseListener(new mouseListenerCustom());
        //introducción de la componente JButton strokeBoton en el contenedor
        //JPanel contenedorStroke
        contenedorStroke.add(strokeBoton, BorderLayout.NORTH);

        ////////////////////////////////////////////////////////////////////////
        ////////////////   CONTENEDOR JPanel contenedorPaint    ////////////////
        //DECLARACIÓN contenedor JPanel contenedoPaint
        JPanel contenedorPaint = new JPanel();
        //ASIGNACIÓN AL contenedorPaint DEL ADMINISTRADOR DE LAYOUT BorderLayout
        contenedorPaint.setLayout(new BorderLayout());

        ////////COMPONENTE JButton paintBoton
        JButton paintBoton = new JButton("PAINT");
        //asignación tipografia a la componente JButton paintBoton
        paintBoton.setFont(new Font("arial", Font.BOLD, 13));
        //asignación color de abrirBoton componente JButton paintBoton
        paintBoton.setForeground(Color.WHITE);
        //asignación color de fondo componente JButton paintBoton
        paintBoton.setBackground(Color.BLACK);
        //manipulador de evento asociado a la componente
        //JButton paintBoton
        paintBoton.addMouseListener(new mouseListenerCustom());
        //inclusión de la componente JButton paintBoton en el contenedor JPanel
        //contenedorPaint
        contenedorPaint.add(paintBoton, BorderLayout.NORTH);

        ////////////////////////////////////////////////////////////////////////
        ////////////////   CONTENEDOR JPanel contenedorTrazado  ////////////////
        //declaración contenedor JPanel contenedorTrazado
        JPanel contenedorTrazado = new JPanel();
        //ASIGNACIÓN AL contenedorTrazado DEL ADMINISTRADOR DE LAYOUT BorderLayout
        contenedorTrazado.setLayout(new BorderLayout());

        ////////COMPONENTE JButton colorTrazadoBoton
        JButton colorTrazadoBoton = new JButton("COLOR TRAZADO");
        //asignación tipografia a la componente JButton colorTrazadoBoton
        colorTrazadoBoton.setFont(new Font("arial", Font.BOLD, 13));
        //asignación color de abrirBoton componente JButton colorTrazadoBoton
        colorTrazadoBoton.setForeground(Color.WHITE);
        //asignación color de fondo componente JButton colorTrazadoBoton
        colorTrazadoBoton.setBackground(Color.BLACK);
        //manipulador de evento asociado a la componente
        //JButton colorTrazadoBoton
        colorTrazadoBoton.addMouseListener(new mouseListenerCustom());
        //inclusión de la componente JButton colorTrazadoBoton en el contenedor JPanel
        //contenedorTrazado
        contenedorTrazado.add(colorTrazadoBoton, BorderLayout.NORTH);


        //INTRODUCCIÓN EN EL CONTENEDOR JPanel panelContexto DEL CONTENEDOR
        //JPanel contenedorTrazado
        panelContexto.add(contenedorTrazado);


        ////////COMPONENTE JButton colorFondoBoton
        JButton colorFondoBoton = new JButton("COLOR FONDO");
        //asignación tipografia a la componente JButton colorFondoBoton
        colorFondoBoton.setFont(new Font("arial", Font.BOLD, 13));
        //asignación color de abrirBoton componente JButton colorFondoBoton
        colorFondoBoton.setForeground(Color.WHITE);
        //asignación color de fondo componente JButton colorFondoBoton
        colorFondoBoton.setBackground(Color.BLACK);
        //manipulador de evento asociado a la componente
        //JButton colorFondoBoton
        colorFondoBoton.addMouseListener(new mouseListenerCustom());
        //inclusión de la componente JButton colorFondoBoton en el contenedor JPanel
        //panelColores
        panelContexto.add(colorFondoBoton);


        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //            CONTENEDOR JPanel panelVarios y COMPONENTES             //
        ////////////////////////////////////////////////////////////////////////
        //DECLARACIÓN contenedor JPanel para colocar 5 componentes JButton y el
        //contenedor JPanel panelVarios
        JPanel panelVarios = new JPanel();
        //asignación administrador GridLayout al contenedor JPanel panelVarios
        panelVarios.setLayout(new GridLayout(1, 1));

        ////////COMPONENTE JButton borrarBoton
        JButton borrarBoton = new JButton("CONTINUAR");
        //asignación tipografia a la componente JButton borrarBoton
        borrarBoton.setFont(new Font("arial", Font.BOLD, 13));
        //asignación color de abrirBoton componente JButton COLOR TRAZADO
        borrarBoton.setForeground(Color.WHITE);
        //asignación color de fondo componente JButton COLOR TRAZADO
        borrarBoton.setBackground(Color.BLACK);
        //manipulador de evento asociado a la componente
        //JButton COLOR TRAZADO
        borrarBoton.addMouseListener(new mouseListenerCustom());
        //inclusión de la componente JButton fondo en el contenedor JPanel
        //panelVarios
        panelVarios.add(borrarBoton);


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

        JMenuItem novaPartidaBoto = new JMenuItem("PARTIDA NOVA");
        JMenuItem classificacioBoto = new JMenuItem("CLASSIFICACIÓ GENERAL");
        JMenuItem historialBoto = new JMenuItem("HISTORIAL");
        JMenuItem canviarDirectoriBoto = new JMenuItem("CANVIAR DIRECTORI D'IMATGES");
        JMenuItem sortirBoto = new JMenuItem("SORTIR");

        novaPartidaBoto.setBackground(Color.black);
        novaPartidaBoto.setForeground(Color.WHITE);
        novaPartidaBoto.setFont(FONT1);
        classificacioBoto.setBackground(Color.black);
        classificacioBoto.setForeground(Color.WHITE);
        classificacioBoto.setFont(FONT1);
        historialBoto.setBackground(Color.black);
        historialBoto.setForeground(Color.WHITE);
        historialBoto.setFont(FONT1);
        canviarDirectoriBoto.setBackground(Color.black);
        canviarDirectoriBoto.setForeground(Color.WHITE);
        canviarDirectoriBoto.setFont(FONT1);
        sortirBoto.setBackground(Color.black);
        sortirBoto.setForeground(Color.WHITE);
        sortirBoto.setFont(FONT1);

        novaPartidaBoto.addMouseListener(new mouseListenerCustom());
        classificacioBoto.addMouseListener(new mouseListenerCustom());
        sortirBoto.addMouseListener(new mouseListenerCustom());
        historialBoto.addMouseListener(new mouseListenerCustom());
        canviarDirectoriBoto.addMouseListener(new mouseListenerCustom());
        sortirBoto.addMouseListener(new mouseListenerCustom());

        generalMenu.add(novaPartidaBoto);
        generalMenu.add(classificacioBoto);
        generalMenu.add(historialBoto);
        generalMenu.add(canviarDirectoriBoto);
        generalMenu.add(sortirBoto);

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

        separadorOeste.add(panelCreacion);
        panelContenidos.add(separadorOeste, BorderLayout.WEST);

        separadorNorte.add(panellTop);
        panelContenidos.add(separadorNorte, BorderLayout.NORTH);

        separadorSur.setBottomComponent(panelVarios);
        panelContenidos.add(separadorSur, BorderLayout.SOUTH);
    }

    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                        CLASE AreaVisualizacion                         //
    //                                                                        //
    //////////////////////////////////////////////////////////////////////////// 
    public class AreaVisualizacion extends JPanel {
        private JLabel imatgeUIB;

        public AreaVisualizacion() {
            imatgeUIB = new JLabel();
            this.setBackground(Color.black);
            try {
                imatgeUIB.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("../icones/UIB.jpg"))));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            add(imatgeUIB);
            imatgeUIB.setVisible(false);
            add(obtenirResultats());
            this.setBackground(Color.WHITE);
            areaVisualitzacioResultats.setVisible(true);
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
