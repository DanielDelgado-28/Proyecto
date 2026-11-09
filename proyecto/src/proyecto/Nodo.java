
package proyecto;


public class Nodo <TIPO>{
 private TIPO valor;
    private Nodo<TIPO> siguiente;
    private Nodo<TIPO> previo;

    public Nodo(TIPO valor) {//poner el valor a guardar, al momento de crear el nodo
        this.valor = valor;
        this.siguiente = null;//esto marca el final de la lista
        this.previo = null;
    }

    public TIPO getvalor() {
        return valor;
    }

    public Nodo<TIPO> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<TIPO> siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo<TIPO> getPrevio() {
        return previo;
    }

    public void setPrevio(Nodo<TIPO> previo) {
        this.previo = previo;
    }
}
