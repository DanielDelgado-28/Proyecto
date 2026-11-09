
package proyecto;


public class libro {
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private int anio;
    private boolean prestado;   // false = disponible, true = prestado

    public libro(String titulo, String autor, String isbn, String genero, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.anio = anio;
        this.prestado = false;  // todo libro nuevo esta disponible
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnio() {
        return anio;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    //asi se imprime el libro cuando se usa println
    @Override
    public String toString() {
        String estado;
        if (prestado) {
            estado = "PRESTADO";
        } else {
            estado = "Disponible";
        }
        return titulo + " | " + autor + " | " + genero + " | " + anio + " | ISBN: " + isbn + " | " + estado;
    }
}
