package puzle;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainJFrame extends JFrame {

    private MainJFrame ventana;
    private JButton novaPartidaIcona, classificacioIcona, historialIcona, canviarDirectoriIcona, sortirIcona;
    private JToolBar iconesMenu;
    private ObjetoGrafico objeto;  
    private Container panelContenidos;
    private AreaVisualizacion visualizador;
    private boolean visualizacionSolida=false;
    private Color colorFondo=Color.BLACK;
    private JRadioButton pintadoBoton,trazadoBoton;
    private boolean creacionObjeto=false;
    private final Font FONT1 = new Font("arial", Font.BOLD, 14);

    
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
        setSize(1150,800);
        panelContenidos=getContentPane();
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
        visualizador=new AreaVisualizacion();      
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
        panelCreacion.setLayout(new GridLayout( 4, 1 ));
        
        ////////COMPONENTE JButton novaPartidaButton
        JButton novaPartidaButton = new JButton("NOVA PARTIDA");
        novaPartidaButton.setFont(FONT1);
        novaPartidaButton.setForeground(Color.WHITE);
        novaPartidaButton.setBackground(Color.BLACK);
        novaPartidaButton.addActionListener(new manipuladorEventosCreacion());
        panelCreacion.add(novaPartidaButton);
        
        ////////COMPONENTE JButton historialGeneralButton
        JButton historialGeneralButton = new JButton("HISTORIAL GENERAL");
        historialGeneralButton.setFont(FONT1);
        historialGeneralButton.setForeground(Color.WHITE);
        historialGeneralButton.setBackground(Color.BLACK);
        historialGeneralButton.addActionListener(new manipuladorEventosCreacion());
        panelCreacion.add(historialGeneralButton);
        
        ////////COMPONENTE JButton historialSelectiuButton
        JButton historialSelectiuButton = new JButton("HISTORIAL SELECTIU");
        historialSelectiuButton.setFont(FONT1);
        historialSelectiuButton.setForeground(Color.WHITE);
        historialSelectiuButton.setBackground(Color.BLACK);
        historialSelectiuButton.addActionListener(new manipuladorEventosCreacion());
        panelCreacion.add(historialSelectiuButton);
        
        ////////COMPONENTE JButton sortirButton
        JButton sortirButton = new JButton("SORTIR");
        sortirButton.setFont(FONT1);
        sortirButton.setForeground(Color.WHITE);
        sortirButton.setBackground(Color.BLACK);
        sortirButton.addActionListener(new manipuladorEventosCreacion());
        panelCreacion.add(sortirButton);


        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //           CONTENEDOR JPanel panelContexto y COMPONENTES            //
        ////////////////////////////////////////////////////////////////////////               
        //DECLARACIÓN CONTENEDOR JPanel panelContexto 
        JPanel panelContexto = new JPanel();
        //ASIGNACIÓN AL CONTENEDOR panelCreacion DEL ADMINISTRADOR DE LAYOUT 
        //GridLayout CON UNA ESTRUCTURA DE 8 FILAS Y 1 COLUMNA
        panelContexto.setLayout(new GridLayout( 4, 1 ));

        
        ////////////////////////////////////////////////////////////////////////
        ////////////////   CONTENEDOR JPanel contenedorStroke   ////////////////
        //DECLARACIÓN CONTENEDOR JPanel contenedorStroke
        JPanel contenedorStroke=new JPanel();
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
        strokeBoton.addActionListener(new manipuladorEventosCreacion());   
        //introducción de la componente JButton strokeBoton en el contenedor
        //JPanel contenedorStroke
        contenedorStroke.add(strokeBoton,BorderLayout.NORTH);

        ////////////////////////////////////////////////////////////////////////
        ////////////////   CONTENEDOR JPanel contenedorPaint    ////////////////
        //DECLARACIÓN contenedor JPanel contenedoPaint
        JPanel contenedorPaint=new JPanel();
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
        paintBoton.addActionListener(new manipuladorEventosCreacion());
        //inclusión de la componente JButton paintBoton en el contenedor JPanel
        //contenedorPaint
        contenedorPaint.add(paintBoton,BorderLayout.NORTH);

        ////////////////////////////////////////////////////////////////////////
        ////////////////   CONTENEDOR JPanel contenedorTrazado  ////////////////
        //declaración contenedor JPanel contenedorTrazado
        JPanel contenedorTrazado=new JPanel();
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
        colorTrazadoBoton.addActionListener(new manipuladorEventosCreacion());
        //inclusión de la componente JButton colorTrazadoBoton en el contenedor JPanel
        //contenedorTrazado
        contenedorTrazado.add(colorTrazadoBoton,BorderLayout.NORTH);



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
        colorFondoBoton.addActionListener(new manipuladorEventosCreacion());
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
        panelVarios.setLayout(new  GridLayout(1,1));

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
        borrarBoton.addActionListener(new manipuladorEventosCreacion());
        //inclusión de la componente JButton fondo en el contenedor JPanel
        //panelVarios
        panelVarios.add(borrarBoton);

        

        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //          COMPONENTE JMenuBar barraMenu y COMPONENTES            //
        ////////////////////////////////////////////////////////////////////////
        JMenuBar barraMenu=new JMenuBar();

        JMenu generalMenu=new JMenu("MENU");
        barraMenu.setBackground(Color.BLACK);
        barraMenu.setForeground(Color.WHITE);
        generalMenu.setBackground(Color.black);
        generalMenu.setForeground(Color.WHITE);

        JMenuItem novaPartidaBoto=new JMenuItem("PARTIDA NOVA");
        JMenuItem classificacioBoto=new JMenuItem("CLASSIFICACIÓ GENERAL");
        JMenuItem historialBoto = new JMenuItem("HISTORIAL");
        JMenuItem canviarDirectoriBoto = new JMenuItem("CANVIAR DIRECTORI D'IMATGES");
        JMenuItem sortirBoto=new JMenuItem("SORTIR");

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

        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        iconesMenu.add(novaPartidaIcona);
        iconesMenu.add(classificacioIcona);
        iconesMenu.add(historialIcona);
        iconesMenu.add(canviarDirectoriIcona);
        iconesMenu.add(sortirIcona);

JPanel panelTop = new JPanel(new GridLayout(2,1));
panelTop.add(barraMenu);
panelTop.add(iconesMenu);


        

        ////////////////////////////////////////////////////////////////////////
        //                                                                    //
        //                SEPARADORES JSplitPane DE LA INTERFACE              //
        ////////////////////////////////////////////////////////////////////////  
        //DECLARACIÓN SEPARADORES JSplitPane DE LA INTERFACE
        JSplitPane separadorNorte = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorSur= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorOeste = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
 
        ////////////////////////////////////////////////////////////////////////
        //   DISTRIBUCIÓN SEPARADORES, CONTENEDORES Y COMPONENTES SEGÚN EL    //
        //   DISEÑO DE LA INTERFACE                                           //
        //////////////////////////////////////////////////////////////////////// 
        //INCLUSIÓN DEL CONTENEDOR JPanel panelCreacion EN EL SEPARADOR 
        //separadorOeste 
        separadorOeste.add(panelCreacion);    
        
        //INCLUSIÓN DEL SEPARADOR separadorOeste EN LA ZONA WEST DEL PANEL DE 
        //CONTENIDOS DEL JFrame
        panelContenidos.add(separadorOeste, BorderLayout.WEST);       
        
        //INCLUSIÓN DEL CONTENEDOR JPanel panelContexto EN EL SEPARADOR separadorEste 


        //INCLUSIÓN DEL SEPARADOR separadorEste EN LA ZONA EAST DEL PANEL DE 
        //CONTENIDOS DEL JFrame

        
        //INCLUSIÓN DEL CONTENEDOR JPanel panelSuperior EN EL SEPARADOR separadorNorte 
        separadorNorte.add(panelTop);


        //INCLUSIÓN DEL SEPARADOR separadorNorte EN LA ZONA NORTH DEL PANEL DE 
        //CONTENIDOS DEL JFrame
        panelContenidos.add(separadorNorte, BorderLayout.NORTH); 
             
        //INCLUSIÓN DEL CONTENEDOR JPanel panelVarios EN EL SEPARADOR separadorSur 
        separadorSur.setBottomComponent(panelVarios);

        //INCLUSIÓN DEL SEPARADOR separadorSur EN LA ZONA SOUTH DEL PANEL DE 
        //CONTENIDOS DEL JFrame     
        panelContenidos.add(separadorSur, BorderLayout.SOUTH);

        
        
        ////////////////////////////////////////////////////////////////////////
        //                VISIBILIDAD CONTENEDOR JFrame                       //
        ////////////////////////////////////////////////////////////////////////
        setVisible(true);
    }
/////////////////// FIN MÉTODO creacionContenedoresYComponentes ////////////////
    

    ////////////////////////////////////////////////////////////////////////////
    //                                                                        //
    //                        CLASE AreaVisualizacion                         //
    //                                                                        //
    // CLASE INTERNA A TRAVÉS DE LA CUAL SE INSTANCIA EL CONTENEDOR JPanel    //
    // SOBRE EL QUE SE VA A DIRECCIONAR LA VISUALIZACIÓN GRÁFICA DE LA        //
    // APLICACIÓN.                                                            //
    //////////////////////////////////////////////////////////////////////////// 
    public class AreaVisualizacion extends JPanel {
        //DECLARACIÓN DE ATRIBUTOS
        //declaración objeto BufferedImage imagenFichero que representa el
        //buffer de imagenTextura para cargar las imágenes leidas desde ficheros
        private BufferedImage imagenFichero=null; 
        //declaración objeto BufferedImage imagenBuffer1 que representa el
        //buffer de imagenTextura en donde, para cada redibujado, se trabaja a priori
        //hasta que todas las entidades han sido dibujadas/visualizadas, momento
        //en el que su contenido es visualizado en el JPanel AreaVisualizacion a través
        //del objeto gráfico Graphics2D g2.
        private BufferedImage imagenBuffer1=null;
        //declaración objeto BufferedImage imagenBuffer2 que representa el
        //buffer de imagenTextura en donde, para cada redibujado, se trabaja a priori
        //hasta que todas las entidades han sido dibujadas/visualizadas, momento
        //en el que su contenido es visualizado en el JPanel AreaVisualizacion a través
        //del objeto gráfico Graphics2D g2.
        private BufferedImage imagenBuffer2=null;
        //declaración objeto gráfico Graphics2D asociado al contenedor JPanel
        //AreaDibujo
        private Graphics2D g2;
        //declaración objeto gráfico GRaphics2D asociado al Buffer de imagenTextura
        //imagenBuffer1
        private Graphics2D g2ImagenBuffer1;
        //declaración objeto gráfico GRaphics2D asociado al Buffer de imagenTextura
        //imagenBuffer2
        private Graphics2D g2ImagenBuffer2;
        //objeto Grafico
        private Graphics2D gBuffer;
        
        //MÉTODO CONSTRUCTOR
        public AreaVisualizacion() {
            //intanciación del buffer de imagenTextura imagenBuffer1 para una imagenTextura
            //del tipo RGB y con las dimensiones del JPanel 
            imagenBuffer1 = new BufferedImage(842, 
                            658,BufferedImage.TYPE_INT_ARGB);
            //intanciación del buffer de imagenTextura imagenBuffer1 para una imagenTextura
            //del tipo RGB y con las dimensiones del JPanel 
            imagenBuffer2 = new BufferedImage(842, 
                            658,BufferedImage.TYPE_INT_ARGB);
            //asociación del objeto Graphics2D g2ImagenBuffer1 con el buffer
            //de imagenTextura imagenBuffer1 para poder con él dibujar/visualizar
            //en dicho buffer
            g2ImagenBuffer1 = imagenBuffer1.createGraphics();
            //asociación del objeto Graphics2D g2ImagenBuffer2 con el buffer
            //de imagenTextura imagenBuffer2 para poder con él dibujar/visualizar
            //en dicho buffer
            g2ImagenBuffer2 = imagenBuffer2.createGraphics();
        }
        
        //MÉTODO paintComponent ASOCIADO AL CONTENEDOR JPanel AreaVisualizacion
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //asociación del objeto gráfico Graphics2D g2 con el contenedor
            //JPanel AreaVisualizacion`para poder con él dibujar/visualizar
            //en dicho contenedor
            g2=(Graphics2D) g;
            //pintar el fondo del contenedor JPanel AreaVisualizacion
            pintarFondo();
            //en función de si está activada la visualización sólida o no
            //llevaremos a cabo el dibujo en el buffer de imagen imagenBuffer2
            //o imagenBuffer1 respectivamente
            if (visualizacionSolida) {
                gBuffer=g2ImagenBuffer2;
            }
            else {
                gBuffer=g2ImagenBuffer1;
            }
            if (creacionObjeto) {
                //EN FUNCIÓN DEL TIPO DE OBJETO A DIBUJAR/VISUALIZAR
                switch (objeto.getTipo()) {
                    case LINEA:      //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 del
                                     //color de trazado del objeto actual
                                     gBuffer.setColor(objeto.getColorTrazado());
                                     //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 sel
                                     //atributo de stroke del objeto actual
                                     gBuffer.setStroke(objeto.getStroke());
                                     //trazado del objeto en el buffer de imagenTextura imagenBuffer1
                                     gBuffer.draw((Line2D)objeto.getObjeto() );
                                     break;
                    case RECTANGULO: //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 sel
                                     //atributo de stroke del objeto actual
                                     gBuffer.setStroke(objeto.getStroke());
                                     //si es estado de la visualización es solida
                                     //se lleva a cabo el pintado del objeto actual
                                     if (visualizacionSolida) {
                                         //asignación al objeto Graphics2D g2ImagenBuffer1
                                         //del atributo Paint del objeto actual
                                         gBuffer.setPaint(objeto.getPaint());
                                         //visualización sólida (pintado) del objeto actual
                                         //en el buffer de imagenTextura imagenBuffer1
                                         gBuffer.fill((Rectangle2D) objeto.getObjeto());                                        
                                     }
                                     //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 del
                                     //color de trazado del objeto actual
                                     gBuffer.setColor(objeto.getColorTrazado());
                                     //trazado del objeto en el buffer de imagenTextura imagenBuffer1
                                     gBuffer.draw((Rectangle2D) objeto.getObjeto());
                                     break;
                    case ELIPSE:     //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 sel
                                     //atributo de stroke del objeto actual
                                     gBuffer.setStroke(objeto.getStroke());
                                     //si es estado de la visualización es solida
                                     //se lleva a cabo el pintado del objeto actual
                                     if (visualizacionSolida) {
                                         //asignación al objeto Graphics2D g2ImagenBuffer1
                                         //del atributo Paint del objeto actual
                                         gBuffer.setPaint(objeto.getPaint());
                                         //visualización sólida (pintado) del objeto actual
                                         //en el buffer de imagenTextura imagenBuffer1
                                         gBuffer.fill((Ellipse2D) objeto.getObjeto());                                        
                                     }
                                     //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 del
                                     //color de trazado del objeto actual
                                     gBuffer.setColor(objeto.getColorTrazado());
                                     //trazado del objeto en el buffer de imagenTextura imagenBuffer1
                                     gBuffer.draw((Ellipse2D) objeto.getObjeto());
                                     break;
                    case POLIGONO:   //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 sel
                                     //atributo de stroke del objeto actual
                                     gBuffer.setStroke(objeto.getStroke());
                                     //si es estado de la visualización es solida
                                     //se lleva a cabo el pintado del objeto actual
                                     if (visualizacionSolida) {
                                         //asignación al objeto Graphics2D g2ImagenBuffer1
                                         //del atributo Paint del objeto actual
                                         gBuffer.setPaint(objeto.getPaint());
                                         //visualización sólida (pintado) del objeto actual
                                         //en el buffer de imagenTextura imagenBuffer1
                                         gBuffer.fillPolygon((Polygon) objeto.getObjeto());                     
                                     }
                                     //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 del
                                     //color de trazado del objeto actual
                                     gBuffer.setColor(objeto.getColorTrazado());
                                     //trazado del objeto en el buffer de imagenTextura imagenBuffer1
                                     gBuffer.drawPolygon((Polygon) objeto.getObjeto());
                                     break;
                    case TEXTO:      //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 sel
                                     //atributo de stroke del objeto actual
                                     gBuffer.setStroke(objeto.getStroke());
                                     //asignación al objeto Graphics2D g2ImagenBuffer1
                                     //del buffer de imagenTextura imagenBuffer1 del
                                     //color de trazado del objeto actual
                                     gBuffer.setColor(objeto.getColorTrazado());
                                     //visualización del objeto en el buffer de imagenTextura imagenBuffer1
                                     gBuffer.drawString(objeto.getTexto(),
                                                objeto.getPosicionTexto()[0],
                                                objeto.getPosicionTexto()[1]);
                                     break;
                    case IMAGEN:     //lectura del fichero imagenTextura correspondiente al objeto actual 
                                     //almacenándola en el buffer de imagenTextura imagenFichero
                                     try {
                                        imagenFichero = ImageIO.read(objeto.getFicheroImagen());
                                     }catch (IOException error) {
                                         System.out.println("ERROR: "+error.toString());
                                     }
                                     //transferir la imagenTextura desde el buffer imagenFichero al
                                     //buffer imagenBuffer1 asociado al objeto Graphics2D 
                                     //g2ImagenBuffer escalando acorde a las dimensiones del contenedor JPanel de visualización
                                     gBuffer.drawImage(imagenFichero.getScaledInstance(getWidth(), getHeight(), 0),0,0,this);
                                     break;
                }
            }

            //visualización en el contenedor JPanel AreaVisualización del
            //contenido del buffer de imagenTextura imagenBuffer1 o imageBuffer2,
            //en función de si es visualización sólida o no, a través del
            //objeto Graphics2D g2
            if (visualizacionSolida) {
                g2.drawImage(imagenBuffer2,null,0,0);
            }
            else {
                g2.drawImage(imagenBuffer1,null,0,0);
            }

        }
        
        //MÉTODO QUE LLEVA A CABO EL PINTADO DEL ÁREA DE DIBUJO DEL COLOR DE
        //FONDO ACTIVO
        private void pintarFondo() {
             g2.setColor(colorFondo);
             g2.fillRect(0,0,getWidth(), getHeight());
        }
 
        //MÉTODO QUE LLEVA A CABO LA GRABACIÓN EL CONTENIDO DEL BUFFER DE
        //IMAGEN imagenBuffer1 EN UN FICHERO DE IMAGEN FORMATO JPG
        //FICHERO IMAGEN FORMATO JPG
        public void grabarImagen(File fichero) {
            try {
                ImageIO.write(imagenBuffer1, "jpg",fichero);                   
            } catch (IOException ex) {
                        System.out.println("ERROR grabando la imagen: " + ex.getMessage());
            }
        }
    }  
    


    //introducción del nombre y selección interactiva del directorio del fichero a salvar
    private File obtenerDirectorio() {    
        JFileChooser ventanaSeleccion=new JFileChooser();
        ventanaSeleccion.setDialogTitle("SELECCIONA/ESPECIFICA EL FICHERO");   
        int op = ventanaSeleccion.showSaveDialog(this);
        if(op==JFileChooser.APPROVE_OPTION) {
            //obtención del nombre del fichero
            return ventanaSeleccion.getSelectedFile();
        }
        return null;
    }

    private class mouseListenerCustom implements MouseListener{

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
    
    

////////////////////////////////////////////////////////////////////////////////
//                            MANIPULADORES DE EVENTOS                        //
////////////////////////////////////////////////////////////////////////////////

////////MANIPULADOR DE EVENTOS manipuladorEventosGeneral
    private class manipuladorEventosCreacion implements ActionListener {
        Color color;
        @Override
        public void actionPerformed(ActionEvent evento)  { 
            switch (evento.getActionCommand()) {
                case "GUARDAR COMO"    :File fichero=obtenerDirectorio();
                                        if (fichero!=null) {
                                         visualizador.grabarImagen(fichero);
                                        }
                                        break;
                case "ABRIR"           ://selección interactiva del fichero imagen a abrir
                                        JFileChooser ventanaSeleccionAbrir=new JFileChooser();  
                                        File ficheroImagenAbrir=null;
                                        if (ventanaSeleccionAbrir.showOpenDialog(ventana)==JFileChooser.APPROVE_OPTION) {
                                                ficheroImagenAbrir=ventanaSeleccionAbrir.getSelectedFile();
                                        }
                                        //si la selección del fichero imagen ha sido correcta
                                        if (ficheroImagenAbrir!=null) {
                                            //generación del nuevo ObjetoGrafico del tipo IMAGEN
                                            objeto=new ObjetoGrafico(ObjetoGrafico.tipoObjetoGrafico.IMAGEN,ficheroImagenAbrir);
                                            //asignación TRUE a la variable creacionObjeto para controlar la generación de
                                            //un ObjetoGrafico con miras a su visualización
                                            creacionObjeto=true;
                                        }
                                        break;
                case "SALIR"           :System.exit(0);
                                        break;
            }
    }}
}
