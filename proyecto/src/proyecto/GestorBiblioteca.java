package proyecto;

import java.util.Scanner;

public class GestorBiblioteca {

    private static Scanner teclado = new Scanner(System.in);
    private static ListaDoble<libro> catalogo = new ListaDoble<libro>();
    private static cola<libro> fila = new cola<libro>();

    public static void iniciar() {
        cargarEjemplos();

        int opcion = -1;
        while (opcion != 0) {
            menu();
            opcion = leerEntero("Opcion: ");
            switch (opcion) {
                case 1: agregarlibro(); break;
                case 2: verCatalogo(); break;
                case 3: buscar(); break;
                case 4: eliminarlibro(); break;
                case 5: pedirPrestamo(); break;
                case 6: atenderPrestamo(); break;
                case 7: devolverlibro(); break;
                case 8: verFila(); break;
                case 0: System.out.println("Hasta luego"); break;
                default: System.out.println("Opcion invalida");
            }
        }
    }

    static void menu() {
        System.out.println("\n===== LIBRARY CONTENT BROWSER =====");
        System.out.println("1. Agregar libro");
        System.out.println("2. Ver catalogo");
        System.out.println("3. Buscar libro");
        System.out.println("4. Eliminar libro");
        System.out.println("5. Pedir prestamo (entra a la fila)");
        System.out.println("6. Atender siguiente prestamo");
        System.out.println("7. Devolver libro");
        System.out.println("8. Ver fila de prestamos");
        System.out.println("0. Salir");
    }

    static void cargarEjemplos() {
        catalogo.agregar(new libro("Pedro Paramo", "Juan Rulfo", "0001", "Novela", 1955));
        catalogo.agregar(new libro("1984", "George Orwell", "0002", "Ciencia ficcion", 1949));
        catalogo.agregar(new libro("El Principito", "Antoine de Saint-Exupery", "0003", "Fabula", 1943));
        catalogo.agregar(new libro("Fundacion", "Isaac Asimov", "0004", "Ciencia ficcion", 1951));
        catalogo.agregar(new libro("Don Quijote de la Mancha", "Miguel de Cervantes", "0005", "Novela", 1605));
    }

    static void agregarlibro() {
        String titulo = leerTexto("Titulo: ");
        String autor = leerTexto("Autor: ");
        String isbn = leerTexto("ISBN: ");
        String genero = leerTexto("Genero: ");
        int anio = leerEntero("Anio: ");
        catalogo.agregar(new libro(titulo, autor, isbn, genero, anio));
        System.out.println("Libro agregado exitosamente.");
    }

    static void verCatalogo() {
        if (catalogo.listaVacia()) {
            System.out.println("El catalogo esta vacio");
            return;
        }
        for (int i = 0; i < catalogo.longitud(); i++) {
            System.out.println((i + 1) + ". " + catalogo.valorEn(i));
        }
    }

    static void buscar() {
        System.out.println("Buscar por: 1. Titulo  2. Autor  3. Genero");
        int campo = leerEntero("Opcion: ");
        if (campo < 1 || campo > 3) {
            System.out.println("Opcion invalida");
            return;
        }
        String texto = leerTexto("Texto a buscar: ").toLowerCase();

        int encontradosCount = 0;
        System.out.println("\n--- Resultados de la busqueda ---");
        
        for (int i = 0; i < catalogo.longitud(); i++) {
            libro l = catalogo.valorEn(i);
            String dato = "";
            if (campo == 1) dato = l.getTitulo();
            else if (campo == 2) dato = l.getAutor();
            else if (campo == 3) dato = l.getGenero();

            if (dato.toLowerCase().contains(texto)) {
                encontradosCount++;
                System.out.println(encontradosCount + ". " + l);
            }
        }

        if (encontradosCount == 0) {
            System.out.println("No se encontraron libros con ese criterio.");
        }
    }

    static void eliminarlibro() {
        verCatalogo();
        if (catalogo.listaVacia()) return;

        int numero = leerEntero("Numero del libro a eliminar: ");
        if (numero < 1 || numero > catalogo.longitud()) {
            System.out.println("Numero invalido");
            return;
        }
        if (catalogo.valorEn(numero - 1).estaPrestado()) {
            System.out.println("No se puede eliminar: el libro esta prestado");
            return;
        }
        libro borrado = catalogo.borrarEn(numero - 1);
        System.out.println("Se elimino: " + borrado.getTitulo());
    }

    static void pedirPrestamo() {
        verCatalogo();
        if (catalogo.listaVacia()) return;

        int numero = leerEntero("Numero del libro a pedir: ");
        if (numero < 1 || numero > catalogo.longitud()) {
            System.out.println("Numero invalido");
            return;
        }
        libro l = catalogo.valorEn(numero - 1);
        fila.push(l);
        if (l.estaPrestado()) {
            System.out.println("Quedo en la fila: " + l.getTitulo() + " (esta prestado actualmente, se atendera al devolverse)");
        } else {
            System.out.println("Quedo en la fila: " + l.getTitulo());
        }
    }

    static void atenderPrestamo() {
        if (fila.estaVacia()) {
            System.out.println("No hay prestamos en espera");
            return;
        }
        libro l = fila.peek();
        if (!existeEnCatalogo(l)) {
            fila.pop();
            System.out.println("Se cancelo la solicitud: " + l.getTitulo() + " ya no esta en el catalogo");
        } else if (l.estaPrestado()) {
            System.out.println("No se puede atender: " + l.getTitulo() + " sigue prestado, hay que devolverlo primero");
        } else {
            fila.pop();
            l.setPrestado(true);
            System.out.println("Se presto exitosamente: " + l.getTitulo());
        }
    }

    static void devolverlibro() {
        verCatalogo();
        if (catalogo.listaVacia()) return;

        int numero = leerEntero("Numero del libro a devolver: ");
        if (numero < 1 || numero > catalogo.longitud()) {
            System.out.println("Numero invalido");
            return;
        }
        libro l = catalogo.valorEn(numero - 1);
        if (l.estaPrestado()) {
            l.setPrestado(false);
            System.out.println("Se devolvio correctamente: " + l.getTitulo());
        } else {
            System.out.println("Ese libro no esta prestado");
        }
    }

    static void verFila() {
        if (fila.estaVacia()) {
            System.out.println("La fila esta vacia");
        } else {
            System.out.println("Siguiente en atender: " + fila.peek());
            System.out.println("Fila completa:");
            fila.imprimir();
        }
    }

    static boolean existeEnCatalogo(libro l) {
        for (int i = 0; i < catalogo.longitud(); i++) {
            if (catalogo.valorEn(i) == l) {
                return true;
            }
        }
        return false;
    }

    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }

    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        int numero = teclado.nextInt();
        teclado.nextLine(); 
        return numero;
    }
}