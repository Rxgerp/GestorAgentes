import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner; // Clase scanner para leer la entrada del usuario

/**
 *  PROYECTO DE JAVA QUE MANEJA UNA COLECCIÓN DE AGENTES DEL VIDEOJUEGO (VALORANT)
 * ROGER PALOMINO OSORIO (10666600924)
 * Este programa permite seleccionar agentes del videojuego Valorant, eliminarlos, modificarlos y mostrarlos
*/ 

/**
 * Esta es la clase principal que nos ayuda a manejar y probar
 * nuestra colección de Agentes de Valorant de forma interactiva.
 */
public class GestorAgentes {

    private List<AgenteValorant> agentes;
    private Scanner scanner; // Se crea variable para la entrada del usuario


    /**
     * Constructor del Gestor de Agentes.
     * Inicializa la lista de agentes y el objeto Scanner para leer del teclado.
     */
    public GestorAgentes() {
        this.agentes = new ArrayList<>();
        this.scanner = new Scanner(System.in); // Configuramos el Scanner para leer desde la consola
    }

    /**
     * Agrega un nuevo Agente a la colección.
     * @param agente El AgenteValorant a añadir.
     */
    public void agregarAgente(AgenteValorant agente) {
        if (agente != null) {
            this.agentes.add(agente);
            System.out.println("¡Agente '" + agente.getNombre() + "' agregado al roster!");
        } else {
            System.out.println("No se puede agregar un Agente nulo.");
        }
    }

    /**
     * Busca un Agente por su nombre.
     * @param nombre El nombre del Agente a buscar.
     * @return El AgenteValorant encontrado, o null si no está.
     */
    public AgenteValorant buscarAgentePorNombre(String nombre) {
        for (AgenteValorant agente : agentes) {
            if (agente.getNombre().equalsIgnoreCase(nombre)) {
                return agente;
            }
        }
        return null; // Si no lo encuentra, devuelve null
    }

    /**
     * Elimina un Agente de la colección por su nombre.
     * @param nombre El nombre del Agente a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminarAgentePorNombre(String nombre) {
        AgenteValorant agenteAEliminar = buscarAgentePorNombre(nombre);
        if (agenteAEliminar != null) {
            this.agentes.remove(agenteAEliminar);
            System.out.println("Agente '" + nombre + "' eliminado del roster.");
            return true;
        } else {
            System.out.println("No se pudo eliminar: Agente '" + nombre + "' no encontrado.");
            return false;
        }
    }

    /**
     * Muestra todos los Agentes en la colección.
     */
    public void mostrarTodosLosAgentes() {
        if (agentes.isEmpty()) {
            System.out.println("¡Tu roster de Agentes de Valorant está vacío!");
            return;
        }
        System.out.println("\n Roster de Agentes de Valorant ");
        for (AgenteValorant agente : agentes) {
            System.out.println(agente);
        }
        System.out.println("----------------------------------------");
    }

    // --- Clase AgenteValorant ---
    /**
     * Representa un único Agente de Valorant con sus detalles.
     */
    public static class AgenteValorant {
        private String nombre;
        private String rol;
        private List<String> habilidades;

        public AgenteValorant(String nombre, String rol, List<String> habilidades) {
            this.nombre = nombre;
            this.rol = rol;
            this.habilidades = new ArrayList<>(habilidades);
        }

        public String getNombre() { return nombre; }
        public String getRol() { return rol; }
        public List<String> getHabilidades() { return new ArrayList<>(habilidades); }

        public void setRol(String rol) {
            this.rol = rol;
        }

        @Override
        public String toString() {
            return "Agente: " + nombre +
                   " | Rol: " + rol +
                   " | Habilidades: " + String.join(", ", habilidades);
        }
    }

    /**
     * Este es el método principal que ejecuta el menú interactivo.
     */
    public static void main(String[] args) {
        GestorAgentes gestor = new GestorAgentes();
        boolean salir = false;

        // precargar algunos agentes para no empezar de cero
        gestor.agregarAgente(new AgenteValorant("Jett", "Duelista", Arrays.asList("Turboviento", "Ascensión", "Ráfaga", "Tormenta de Cuchillas")));
        gestor.agregarAgente(new AgenteValorant("Sage", "Centinela", Arrays.asList("Orbe Lento", "Orbe Curativo", "Muro de Orbes", "Resurrección")));
        gestor.agregarAgente(new AgenteValorant("Omen", "Controlador", Arrays.asList("Paranoia", "Velo Tenebroso", "Paso Sombrío", "De las Sombras")));


        // El bucle principal del menú
        while (!salir) {
            System.out.println("\n--- Menú de Gestión de Agentes de Valorant ---");
            System.out.println("1. Añadir Agente");
            System.out.println("2. Buscar Agente");
            System.out.println("3. Eliminar Agente");
            System.out.println("4. Modificar Rol de Agente");
            System.out.println("5. Mostrar Todos los Agentes");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            String opcionStr = gestor.scanner.nextLine(); // Leemos la opción del usuario
            int opcion;

            try {
                opcion = Integer.parseInt(opcionStr); // Convertimos la opción a número
            } catch (NumberFormatException e) {
                System.out.println("¡Opción inválida! Por favor, ingresa un número.");
                continue; // Volvemos al inicio del bucle
            }

            switch (opcion) {
                case 1: // Añadir Agente
                    System.out.print("Nombre del Agente: ");
                    String nombreAdd = gestor.scanner.nextLine();
                    System.out.print("Rol del Agente (Duelista, Centinela, etc.): ");
                    String rolAdd = gestor.scanner.nextLine();
                    System.out.print("Habilidades del Agente (separadas por comas, ej. habilidad1,habilidad2): ");
                    String habilidadesStr = gestor.scanner.nextLine();
                    List<String> habilidadesList = Arrays.asList(habilidadesStr.split(","));
                    gestor.agregarAgente(new AgenteValorant(nombreAdd, rolAdd, habilidadesList));
                    break;

                case 2: // Buscar Agente
                    System.out.print("Ingresa el nombre del Agente a buscar: ");
                    String nombreSearch = gestor.scanner.nextLine();
                    AgenteValorant agenteEncontrado = gestor.buscarAgentePorNombre(nombreSearch);
                    if (agenteEncontrado != null) {
                        System.out.println("¡Agente encontrado!");
                        System.out.println(agenteEncontrado);
                    } else {
                        System.out.println("Agente '" + nombreSearch + "' no está en el roster.");
                    }
                    break;

                case 3: // Eliminar Agente
                    System.out.print("Ingresa el nombre del Agente a eliminar: ");
                    String nombreDelete = gestor.scanner.nextLine();
                    gestor.eliminarAgentePorNombre(nombreDelete);
                    break;

                case 4: // Modificar Rol de Agente
                    System.out.print("Ingresa el nombre del Agente al que quieres cambiar el rol: ");
                    String nombreModificar = gestor.scanner.nextLine();
                    AgenteValorant agenteModificar = gestor.buscarAgentePorNombre(nombreModificar);
                    if (agenteModificar != null) {
                        System.out.print("Ingresa el NUEVO rol para " + agenteModificar.getNombre() + ": ");
                        String nuevoRol = gestor.scanner.nextLine();
                        agenteModificar.setRol(nuevoRol);
                        System.out.println("Rol de " + agenteModificar.getNombre() + " actualizado a " + nuevoRol + ".");
                    } else {
                        System.out.println("Agente '" + nombreModificar + "' no encontrado para modificar.");
                    }
                    break;

                case 5: // Mostrar Todos
                    gestor.mostrarTodosLosAgentes();
                    break;

                case 6: // Salir
                    salir = true;
                    System.out.println("Saliendo del programa. ¡Hasta pronto!");
                    break;

                default: // Opción no reconocida
                    System.out.println("Opción no válida. Por favor, elige un número del 1 al 6.");
                    break;
            }
        }
        gestor.scanner.close(); // ¡Importante cerrar el Scanner cuando ya no lo necesitamos!
    }
}