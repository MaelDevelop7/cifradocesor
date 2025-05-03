package main.java.com.p10;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al programa de desplazamiento de caracteres.");

        // 1. Interacción con el usuario
        String accion = elegirAccion(scanner);
        File archivoEntrada = seleccionarArchivoEntrada(scanner);
        int desplazamiento = obtenerDesplazamiento(scanner);
        if (accion.equals("d")) {
            desplazamiento = -desplazamiento; // Inversión automática
        }
        String nombreArchivoSalida = obtenerNombreArchivoSalida(scanner, archivoEntrada.getName(), accion);

        // 2. Procesamiento
        if (archivoEntrada != null && nombreArchivoSalida != null) {
            try {
                procesarArchivo(archivoEntrada, nombreArchivoSalida, desplazamiento);
                System.out.println("El archivo " + nombreArchivoSalida + " ha sido creado exitosamente.");
            } catch (IOException e) {
                System.err.println("Error al procesar el archivo: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Programa finalizado.");
    }

    static String elegirAccion(Scanner scanner) {
        String accion;
        do {
            System.out.print("¿Desea cifrar (c) o descifrar (d) el archivo? ");
            accion = scanner.nextLine().toLowerCase();
        } while (!accion.equals("c") && !accion.equals("d"));
        return accion;
    }

    static int obtenerDesplazamiento(Scanner scanner) {
        int desplazamiento;
        while (true) {
            System.out.print("Ingrese el valor del desplazamiento (ej: 3): ");
            try {
                desplazamiento = Integer.parseInt(scanner.nextLine());
                return desplazamiento;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }

    static File seleccionarArchivoEntrada(Scanner scanner) {
        File directorioActual = new File("src/ficheros");
        File[] archivos = directorioActual.listFiles();
        List<File> archivosTexto = new ArrayList<>();

        System.out.println("\nArchivos .txt disponibles en el directorio:");
        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".txt")) {
                archivosTexto.add(archivo);
            }
        }

        if (archivosTexto.isEmpty()) {
            System.out.println("No se encontraron archivos .txt en este directorio.");
            return null;
        }

        for (int i = 0; i < archivosTexto.size(); i++) {
            System.out.println((i + 1) + ". " + archivosTexto.get(i).getName());
        }

        int opcion;
        while (true) {
            System.out.print("Seleccione el número del archivo de entrada: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= archivosTexto.size()) {
                    return archivosTexto.get(opcion - 1);
                } else {
                    System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
    }

    static String obtenerNombreArchivoSalida(Scanner scanner, String nombreArchivoEntrada, String accion) {
        String nombreBase = nombreArchivoEntrada.substring(0, nombreArchivoEntrada.lastIndexOf('.'));
        String extension = ".txt";
        String prefijo = accion.equals("c") ? "cifrado_" : "descifrado_";
        String sugerido = prefijo + nombreBase + extension;

        while (true) {
            System.out.print("\nIngrese el nombre del archivo de salida (sugerido: " + sugerido + "): ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                nombre = sugerido;
            }
            File archivoSalida = new File("src/ficheros/" + nombre);
            if (!archivoSalida.exists()) {
                return "src/ficheros/" + nombre;
            } else {
                System.out.println("El archivo '" + nombre + "' ya existe. Escriba otro nombre.");
            }
        }
    }

    static void procesarArchivo(File archivoEntrada, String nombreArchivoSalida, int desplazamiento) throws IOException {
        try (FileReader fr = new FileReader(archivoEntrada);
             FileWriter fw = new FileWriter(nombreArchivoSalida)) {

            int caracter;
            while ((caracter = fr.read()) != -1) {
                char c = (char) caracter;
                char cifrado = cifrarCaracter(c, desplazamiento);
                fw.write(cifrado);
            }
        }
    }

    static char cifrarCaracter(char c, int desplazamiento) {
        if (Character.isUpperCase(c)) {
            return (char) ((c - 'A' + desplazamiento + 26) % 26 + 'A');
        } else if (Character.isLowerCase(c)) {
            return (char) ((c - 'a' + desplazamiento + 26) % 26 + 'a');
        } else {
            return c; // No cifra espacios, puntuación, etc.
        }
    }
}
