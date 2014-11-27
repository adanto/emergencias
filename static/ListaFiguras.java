
/**
 * Titulo: Lista de Figuras
 * Descripcion: Primer ejercicio de Java
 * Autor: ISW
 * Version 1.0
 */

class Nodo {
  Figura contenido;
  Nodo siguiente;
  Nodo(Figura f, Nodo n){
    contenido = f;
    siguiente = n;
  }
}
public class ListaFiguras {
  private Nodo cabeza, cola, activo;
  public ListaFiguras() {
    cabeza=null;
    cola=null;
    activo=null;
  }
  public void insertar(Figura f)
// a–ade una figura a la lista
  {

        Nodo nuevo = new Nodo(f,null);

        if (cabeza==null) { // primera figura en la lista
                cabeza=nuevo;
                cola=nuevo;
                activo=nuevo;
        }
        else {// hay al menos una figura en la lista
                cola.siguiente=nuevo;
                cola=nuevo;
                activo=nuevo;
        }
  }
  public void dibujar()
// dibuja las figuras de la lista
  {
        activo=cabeza;
        while (activo!=null) {
                (activo.contenido).dibujar();
                // invoca al método 'dibujar()' de la figura correspondiente
                activo=(activo.siguiente);
        }
        activo=cola;
  }

  public void area()
// calcula el area de las figuras de la lista
{
      activo=cabeza;
      while (activo!=null) {
              System.out.println("El area de la figura es: " +  (activo.contenido).area());
              // invoca al mŽtodo 'area()' de la figura correspondiente
              activo=(activo.siguiente);
              }
      activo=cola;
}


  public void borrar()
  {
        while (cabeza!=null) {
                activo=cabeza;
                cabeza=activo.siguiente;
                activo.contenido=null;
                activo=null;
        }
  }

}
