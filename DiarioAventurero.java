import java.util.Scanner;

// Clase para las misiones, como una estructura para guardar datos
class Mision {
    String nombre; // El título de la misión
    String fecha; // La fecha en que se completó
    String descripcion; // Lo que pasó en la misión
    Mision siguiente; // Apunta a la siguiente misión

    // Constructor para crear una misión nueva
    public Mision(String nombre, String fecha, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.siguiente = null; // Al inicio no hay siguiente
    }
}
// Clase Diario, que es como el TDA que guarda las misiones
class Diario {
    private Mision cabeza; // La primera misión en la lista
    // Constructor para empezar el diario vacío
    public Diario() {
        this.cabeza = null;
    }

    // Método para agregar una misión nueva al final
    public void registrarMision(String nombre, String fecha, String descripcion) {
        Mision nuevaMision = new Mision(nombre, fecha, descripcion);
        if (cabeza == null) { // Si no hay misiones, esta es la primera
            cabeza = nuevaMision;
        } else { // Si ya hay, la ponemos al final
            Mision actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevaMision;
        }
        System.out.println("Misión '" + nombre + "' registrada exitosamente!");
    }

    // Método para borrar la última misión
    public void eliminarUltimaMision() {
        if (cabeza == null) { // Si no hay nada, no hacemos nada
            System.out.println("El diario está vacío!");
            return;
        }
        if (cabeza.siguiente == null) { // Si solo hay una, la borramos
            System.out.println("Misión '" + cabeza.nombre + "' eliminada!");
            cabeza = null;
            return;
        }
        Mision actual = cabeza; // Buscamos el penúltimo nodo
        while (actual.siguiente.siguiente != null) {
            actual = actual.siguiente;
        }
        System.out.println("Misión '" + actual.siguiente.nombre + "' eliminada!");
        actual.siguiente = null; // Quitamos el último
    }
    // Método para enseñar todas las misiones
    public void mostrarMisiones() {
        if (cabeza == null) { // Si está vacío, avisamos
            System.out.println("No hay misiones registradas.");
            return;
        }
        System.out.println("\n=== Diario de Misiones ===");
        Mision actual = cabeza;
        int contador = 1; // Para numerar las misiones
        while (actual != null) {
            System.out.println(contador + ". " + actual.nombre + " (" + actual.fecha + ")");
            System.out.println("   Descripción: " + actual.descripcion);
            actual = actual.siguiente;
            contador++;
        }
    }

    // Método para buscar una misión por título o fecha
    public void buscarMision(String criterio, String valor) {
        Mision actual = cabeza;
        boolean encontrada = false; //Bandera para saber si la encontramos
        while (actual != null) {
            if ((criterio.equalsIgnoreCase("titulo") && actual.nombre.equalsIgnoreCase(valor)) ||
                (criterio.equalsIgnoreCase("fecha") && actual.fecha.equals(valor))) {
                System.out.println("Misión encontrada: " + actual.nombre + " (" + actual.fecha + ")");
                System.out.println("   Descripción: " + actual.descripcion);
                encontrada = true;
                break; // Salimos si la encontramos
            }
            actual = actual.siguiente;
        }
        if (!encontrada) {
            System.out.println("Misión no encontrada con " + criterio + ": " + valor);
        }
    }
    // Método para editar una misión
    public void editarMision(String nombre, String nuevaFecha, String nuevaDesc) {
        Mision actual = cabeza;
        while (actual != null) { //Buscamos la misión por título
            if (actual.nombre.equalsIgnoreCase(nombre)) {
                actual.fecha = nuevaFecha; // Cambiamos la fecha
                actual.descripcion = nuevaDesc; // Cambiamos la descripción
                System.out.println("Misión '" + nombre + "' actualizada!");
                return; //Salimos si la editamos
            }
            actual = actual.siguiente;
        }
        System.out.println("Misión '" + nombre + "' no encontrada para editar.");
    }
}

// Clase principal, aquí corre todo
public class DiarioAventurero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Para leer lo que escribe el usuario
        Diario diario = new Diario(); // Creamos el diario
        boolean salir = false; // Controlamos el menú

        // Bucle para el menú
        while (!salir) {
            System.out.println("\n=== Diario del Aventurero ===");
            System.out.println("1. Registrar nueva misión");
            System.out.println("2. Eliminar última misión");
            System.out.println("3. Mostrar todas las misiones");
            System.out.println("4. Buscar misión");
            System.out.println("5. Editar misión");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiamos el enter

            switch (opcion) {
                case 1: // Registrar una misión nueva
                    System.out.print("Ingresa el título de la misión: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingresa la fecha (21/12/2005): ");
                    String fecha = scanner.nextLine();
                    System.out.print("Ingresa la descripción: ");
                    String descripcion = scanner.nextLine();
                    diario.registrarMision(titulo, fecha, descripcion);
                    break;
                case 2: // Eliminar la última misión
                    diario.eliminarUltimaMision();
                    break;
                case 3: // Mostrar todas las misiones
                    diario.mostrarMisiones();
                    break;
                case 4: // Buscar misión
                    System.out.print("¿Buscar por título o fecha? (titulo/fecha): ");
                    String criterio = scanner.nextLine();
                    if (criterio.equalsIgnoreCase("titulo")) {
                        System.out.print("Ingresa el título a buscar: ");
                        String valor = scanner.nextLine();
                        diario.buscarMision("titulo", valor);
                    } else if (criterio.equalsIgnoreCase("fecha")) {
                        System.out.print("Ingresa la fecha a buscar (21/12/2005): ");
                        String valor = scanner.nextLine();
                        diario.buscarMision("fecha", valor);
                    } else {
                        System.out.println("Criterio no válido. Usa 'titulo' o 'fecha'.");
                    }
                    break;
                case 5: // Editar misión
                    System.out.print("Ingresa el título de la misión a editar: ");
                    String tituloEditar = scanner.nextLine();
                    System.out.print("Ingresa la nueva fecha (21/12/2005): ");
                    String nuevaFecha = scanner.nextLine();
                    System.out.print("Ingresa la nueva descripción: ");
                    String nuevaDesc = scanner.nextLine();
                    diario.editarMision(tituloEditar, nuevaFecha, nuevaDesc);
                    break;
                case 6: // Salir del programa
                    salir = true;
                    System.out.println("¡Hasta la próxima aventura Guerrero!");
                    break;
                default:
                    System.out.println("Esta opción no es válida. Intenta de nuevo.");
            }
        }
        scanner.close(); // Cerramos el scanner
    }
}
