
package proyecto;


public class ListaDoble<TIPO> {
    private Nodo<TIPO> inicio;
    private Nodo<TIPO> fin;
    private int lon;

    public ListaDoble() {
        inicio = null;
        fin = null;
        lon = 0;
    }

    //O(N)
    public void imprimir() {
        Nodo<TIPO> temp = inicio;
        while (temp != null) {
            System.out.println("[ " + temp.getvalor() + " ]");
            temp = temp.getSiguiente();
        }
    }

    //O(1)
    public int longitud() {
        return lon;
    }

    //O(1)
    public boolean listaVacia() {
        return inicio == null;
    }

    //O(1)
    public void agregar(TIPO valor) {
        Nodo<TIPO> nuevo = new Nodo<TIPO>(valor);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            nuevo.setPrevio(fin);
            fin = nuevo;
        }
        lon++;
    }

    //O(N) --> hay que recorrer hasta la posicion
    public TIPO valorEn(int posicion) {
        if (posicion < 0 || posicion >= lon) {
            throw new RuntimeException("la posicion es invalida: " + posicion);
        }
        Nodo<TIPO> temp = inicio;
        for (int i = 0; i < posicion; i++) {
            temp = temp.getSiguiente();
        }
        return temp.getvalor();
    }

    //O(N) --> hay que recorrer hasta la posicion
    public TIPO borrarEn(int posicion) {
        //para borrar, las posiciones validas son de 0 a N-1
        if (posicion < 0 || posicion >= lon) {
            throw new RuntimeException("la posicion es invalida: " + posicion);
        }

        TIPO valorBorrado;

        if (posicion == 0) {            // borrar el primero
            valorBorrado = inicio.getvalor();
            inicio = inicio.getSiguiente();
            if (inicio == null) {       // quedo vacia
                fin = null;
            } else {
                inicio.setPrevio(null);
            }
        } else {
            Nodo<TIPO> temp = inicio;
            for (int i = 0; i < posicion; i++) {   // temp = nodo a borrar
                temp = temp.getSiguiente();
            }
            valorBorrado = temp.getvalor();
            temp.getPrevio().setSiguiente(temp.getSiguiente());
            if (temp == fin) {          // borramos el ultimo
                fin = temp.getPrevio();
            } else {
                temp.getSiguiente().setPrevio(temp.getPrevio());
            }
        }
        lon--;
        return valorBorrado;
    }
}
