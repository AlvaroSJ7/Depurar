import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorEstudiantes {

    // Calcula la nota media de un estudiante
    public static double calcularNotaMedia(Estudiante estudiante) {

        double[] notas = estudiante.getNotas();

        if (notas.length == 0) {
            return -1;
        }

        double suma = 0;

        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }

        return suma / notas.length;
    }

    // Encuentra al estudiante con la mejor nota media
    public static Estudiante encontrarMejorEstudiante(Estudiante[] estudiantes) {

        if (estudiantes == null || estudiantes.length == 0) {
            return null;
        }

        Estudiante mejor = null;
        double mejorNota = -1;

        for (Estudiante estudiante : estudiantes) {
            double media = calcularNotaMedia(estudiante);

            if (media > mejorNota) {
                mejorNota = media;
                mejor = estudiante;
            }
        }

        return mejor;
    }

    // Guarda los resultados en un fichero
    public static void guardarResultados(Estudiante[] estudiantes, String rutaFichero) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaFichero))) {

            for (Estudiante estudiante : estudiantes) {

                if (estudiante.getNotas().length == 0) {
                    writer.write("Nombre: " + estudiante.getNombre() +
                            ", Nota Media: Sin notas");
                } else {
                    double media = calcularNotaMedia(estudiante);
                    writer.write("Nombre: " + estudiante.getNombre() +
                            ", Nota Media: " + media);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero: " + e.getMessage());
        }
    }
}
