/**
 * Titulo: Jerarquia de Figuras
 * Descripcion: Primer ejercicio de Java
 * Autor: ISW
 * Version 1.0
 */

public abstract class Figura {
  protected int x,y;
  
  public Figura (int nx, int ny) {
    x=nx; y=ny;
  }
  public abstract double area();
  public void desplazar (int nx, int ny)
  {

    x = x + nx;
    y = y + ny;
  }

} 

class Circulo extends Figura{
  private int radio;
  public Circulo(int nx, int ny, int nr){
    super(nx,ny);
    radio=nr;
  }
  public double area(){
    return Math.PI*radio*radio;
  }
  
}

class Rectangulo extends Figura{
  private int alto, ancho;
  public Rectangulo(int nx, int ny,int al, int an){
    super(nx,ny);
    alto=al; ancho=an;
  }
  public double area(){
   return alto*ancho;
 }
}
