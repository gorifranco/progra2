/*
CLASE ObjetoGrafico

AGLUTINA LAS DECLARACIONES Y FUNCIONALIDADES PARA LA INSTANCIACIÓN Y GESTIÓN DE
OBJETOS GRÁFICOS (lineas, rectangulos, elipses, polígonos, texto e imágenes)

TALLER 2 - PROGRAMACIÓN II - CURSO 2020-2021 - UIB
autor: Juan Montes de Oca
 */

package tallergrafico_2020_21;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class ObjetoGrafico  {
    //DECLARACIONES
    //declaración tipo enumerado para representar los diferentes objetos gráficos
    //tenidos en cuenta
    enum tipoObjetoGrafico {LINEA, RECTANGULO, ELIPSE, POLIGONO, TEXTO, IMAGEN} 
    //DECLARACIONES DE ATRIBUTOS
    private tipoObjetoGrafico tipo;
    private String texto;
    private File ficheroImagen;
    //atributo de la interface Shape objeto para representar a los objetos gráficos
    //cuyas clases implementan a Shape (Line2D, Rectangle2D, Ellipse2D y Polygon,
    //de esta forma, conseguimos unificarlos a nivel de atributo
    private Shape objeto;
    //atributo para representar el atributo Paint del contexto de renderizado, 
    //asignado a la visualización/dibujo del objeto gráfico en su creación
    private Paint paint;
    //atributo para representar el atributo color del trazado utilizado
    //en la visualización/dibujo del objeto gráfico en su creación
    private Color colorTrazado;
    //atributo para representar el atributo Stroke del contexto de renderizado, 
    //asignado a la visualización/dibujo del objeto gráfico en su creación
    private BasicStroke stroke;
    //declaración array de String correspondientes a los parámetros de creación
    //del objeto gráfico procedentes de un proceso de lectura y pasados a través
    //de uno de los constructores
    private String [] datos;

    //MÉTODOS CONSTRUCTORES
    //constructor dirigido para la instanciación de todos los objetos gráficos
    //menos el objeto gráfico IMAGEN
    //A través del parámetro dDatos se suministran los parámetros de creación
    //de cada uno de los diferentes objetos gráficos a instanciar
    public ObjetoGrafico(tipoObjetoGrafico dtipo, String [] dDatos) {
        tipo=dtipo;
        datos=dDatos;
        switch (tipo) {
            case LINEA:         creacionObjetoLinea();
                                break;
            case RECTANGULO:    creacionObjetoRectangulo();
                                break;
            case ELIPSE:        creacionObjetoElipse();
                                break;
            case POLIGONO:      creacionObjetoPoligono();
                                break;
            case TEXTO:         texto=datos[0];
                                break;
        }
    }
    //constructor para la instanciación de objetos graficos IMAGEN
    public ObjetoGrafico(tipoObjetoGrafico dtipo,File dficheroImagen) {
        tipo=dtipo;
        ficheroImagen=dficheroImagen;
    }
    
    //método para la creación de un objeto gráfico LINE2D y su posterior
    //casting a objeto Shape
    private void creacionObjetoLinea() {
        Line2D linea=new Line2D.Float(Integer.parseInt(datos[0]),
                                      Integer.parseInt(datos[1]),
                                      Integer.parseInt(datos[2]),
                                      Integer.parseInt(datos[3]));
        objeto=(Shape) linea;
    }

    //método para la creación de un objeto gráfico Rectangle2D y su posterior
    //casting a objeto Shape    
    private void creacionObjetoRectangulo() {
        Rectangle2D rectangulo=new Rectangle2D.Float(Integer.parseInt(datos[0]),
                                      Integer.parseInt(datos[1]),
                                      Integer.parseInt(datos[2]),
                                      Integer.parseInt(datos[3]));
        objeto=(Shape) rectangulo;
    }

    //método para la creación de un objeto gráfico Ellipse2D y su posterior
    //casting a objeto Shape     
    private void creacionObjetoElipse() {
        Ellipse2D elipse=new Ellipse2D.Float(Integer.parseInt(datos[0]),
                                      Integer.parseInt(datos[1]),
                                      Integer.parseInt(datos[2]),
                                      Integer.parseInt(datos[3]));
        objeto=(Shape) elipse;
    }
 
    //método para la creación de un objeto gráfico Polygon y su posterior
    //casting a objeto Shape         
    private void creacionObjetoPoligono() {
        int [] coordenadasX=new int[datos.length/2];
        int [] coordenadasY=new int[datos.length/2];
        for (int indice1=0,indice2=0;indice1<datos.length-1;indice1=indice1+2,indice2++) {
            coordenadasX[indice2]=Integer.parseInt(datos[indice1]);
            coordenadasY[indice2]=Integer.parseInt(datos[indice1+1]);
        }
        Polygon poligono=new Polygon();
        for (int indice=0;indice<coordenadasX.length;indice++) {
            poligono.addPoint(coordenadasX[indice], coordenadasY[indice]);
        }
        
        objeto=(Shape) poligono;
    }
 
    //MÉTODOS GET's Y SET's
    public tipoObjetoGrafico getTipo() {
        return tipo;
    }
    
    public File getFicheroImagen() {
        return ficheroImagen;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public int [] getPosicionTexto() {
        int [] coordenadas={Integer.parseInt(datos[1]),Integer.parseInt(datos[2])};
        return coordenadas;
    }
    
    public Color getColorTrazado() {
        return colorTrazado;
    }
    
    public Paint getPaint() {
        return paint;
    }
    public void setColorTrazado(Color dcolor) {
        colorTrazado=dcolor;
    }
    
    public void setPaint(Paint dpaint) {
        paint=dpaint;
    }   
    
    public BasicStroke getStroke() {
        return stroke;
    }
    
    public void setStroke(BasicStroke dstroke) {
        stroke=dstroke;
    }

    public Shape getObjeto() {
        return objeto;
    }
}
