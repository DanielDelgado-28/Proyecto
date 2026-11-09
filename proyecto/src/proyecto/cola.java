
package proyecto;


public class cola<TIPO> {
   private ListaDoble<TIPO> lista;

    public cola() {
        lista = new ListaDoble<TIPO>();
    }

    //O(1)
    public void push(TIPO valor) {
        lista.agregar(valor);
    }

    //O(1)
    public TIPO peek() {
        if (lista.listaVacia()) {
            throw new RuntimeException("la fila esta vacia");
        }
        return lista.valorEn(0); // el frente es la posicion 0
    }

    //O(1) --> borrarEn(0) no recorre nada
    public TIPO pop() {
        if (lista.listaVacia()) {
            throw new RuntimeException("la fila esta vacia");
        }
        return lista.borrarEn(0); // borra el frente y regresa su valor
    }

    //O(1)
    public boolean estaVacia() {
        return lista.listaVacia();
    }

    //O(N)
    public void imprimir() {
        lista.imprimir();
    }
}
