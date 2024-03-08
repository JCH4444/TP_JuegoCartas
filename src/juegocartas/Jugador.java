package juegocartas;

import java.util.Random;
import javax.swing.JPanel;

public class Jugador {

    public int TOTAL_CARTAS = 10;
    private final Random r = new Random();
    private final Carta[] cartas = new Carta[TOTAL_CARTAS];
    // Declarar las variables de acceso global para Trebol y cantidadTrebol
    private Carta[] cartasTrebol, cartasPica, cartasDiamante, cartasCorazon;
    private int cantidadTrebol, cantidadPica, cantidadDiamante, cantidadCorazon;
    private int[] numerosConsecutivosTrebol, numerosConsecutivosPica,
            numerosConsecutivosDiamante, numerosConsecutivosCorazon;

    public void repartir() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        for (int i = 0; i < cartas.length; i++) {
            cartas[i].mostrar(pnl, 5 + i * 40, 5);
        }
        //Ejecución del método
        pnl.repaint();
    }

    public String getGrupos() {

        cantidadTrebol = contarCartasPorPinta(cartas, Pinta.TREBOL);
        cantidadPica = contarCartasPorPinta(cartas, Pinta.PICA);
        cantidadDiamante = contarCartasPorPinta(cartas, Pinta.DIAMANTE);
        cantidadCorazon = contarCartasPorPinta(cartas, Pinta.CORAZON);

        cartasTrebol = ordenarCartasPorPinta(cartas, Pinta.TREBOL, cantidadTrebol);
        cartasPica = ordenarCartasPorPinta(cartas, Pinta.PICA, cantidadPica);
        cartasDiamante = ordenarCartasPorPinta(cartas, Pinta.DIAMANTE, cantidadDiamante);
        cartasCorazon = ordenarCartasPorPinta(cartas, Pinta.CORAZON, cantidadCorazon);

        /* Vector de contadores para almacenar la frecuencia de cada carta en un conjunto.
            El vector se inicializa con todos los valores a cero.
            Cada posición del vector representa una carta diferente.
            El valor en cada posición indica el número de veces que aparece esa carta. */
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        int totalGrupos = 0;
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                totalGrupos++;
            }
        }

        //Se identifica las cartas que se repiten al menos dos veces en un conjunto, 
        //Si es verdadero se marcan como "grupo".
        for (Carta carta : cartas) {
            int ordinal = carta.getNombre().ordinal();
            if (contadores[ordinal] >= 2) {
                carta.setFormaGrupo(true);
            }
        }

        String[] mensajesEscaleras = {
            mostrarNumerosConsecutivos(encontrarCartasConsecutivas(cartasTrebol), Pinta.TREBOL),
            mostrarNumerosConsecutivos(encontrarCartasConsecutivas(cartasPica), Pinta.PICA),
            mostrarNumerosConsecutivos(encontrarCartasConsecutivas(cartasDiamante), Pinta.DIAMANTE),
            mostrarNumerosConsecutivos(encontrarCartasConsecutivas(cartasCorazon), Pinta.CORAZON),};

        return mensajeCompleto(mensajesEscaleras, totalGrupos, contadores);

    }

    public String getPuntaje() {
        //Verificación adicional en el método, para asegurar que los arreglos
        //estén inicializados antes de intentar acceder a ellos.
        if (cartasTrebol == null || cartasPica == null || cartasDiamante == null || cartasCorazon == null) {
            return "Error: Las cartas no han sido verificadas correctamente.";
        }

        numerosConsecutivosTrebol = obtenerNumerosConsecutivos(encontrarCartasConsecutivas(cartasTrebol));
        numerosConsecutivosPica = obtenerNumerosConsecutivos(encontrarCartasConsecutivas(cartasPica));
        numerosConsecutivosDiamante = obtenerNumerosConsecutivos(encontrarCartasConsecutivas(cartasDiamante));
        numerosConsecutivosCorazon = obtenerNumerosConsecutivos(encontrarCartasConsecutivas(cartasCorazon));

        marcarNumerosConsecutivos(numerosConsecutivosTrebol, cartas, Pinta.TREBOL);
        marcarNumerosConsecutivos(numerosConsecutivosPica, cartas, Pinta.PICA);
        marcarNumerosConsecutivos(numerosConsecutivosDiamante, cartas, Pinta.DIAMANTE);
        marcarNumerosConsecutivos(numerosConsecutivosCorazon, cartas, Pinta.CORAZON);

        return mostrarPuntaje(cartas);
    }

    public static String mensajeCompleto(String[] mensajesEscaleras, int totalGrupos, int[] contadores) {
        String mensaje = "";

        //Agregar mensaje, si hay grupos encontrados
        if (totalGrupos > 0) {
            mensaje += "Los grupos encontrados fueron:\n";
            for (int i = 0; i < contadores.length; i++) {
                if (contadores[i] >= 2) {
                    mensaje += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
                }
            }
        }

        String mensajeCompleto = "";
        for (String mensajesEscalera : mensajesEscaleras) {
            mensajeCompleto += mensajesEscalera;
        }

        if (!mensajeCompleto.equals("")) {
            mensaje += "Las escaleras encontradas fueron:\n";
            // Agregar mensajes de escaleras
            for (String mensajeEscalera : mensajesEscaleras) {
                mensaje += mensajeEscalera + "\n";
            }
        }

        // Si no se encontraron grupos ni escaleras, mostrar "No hay grupos"
        if (mensaje.equals("")) {
            mensaje = "No hay grupos";
        }
        return mensaje;
    }

    public void ordenarPorBurbuja() {
        for (int i = 0; i < cartas.length - 1; i++) {
            for (int j = i + 1; j < cartas.length; j++) {
                if (cartas[i].getIndice() > cartas[j].getIndice()) {
                    Carta temp = cartas[i];
                    cartas[i] = cartas[j];
                    cartas[j] = temp;
                }
            }
        }
    }

    public Carta[] ordenarPorSeleccion(Carta cartas[]) {
        int n = cartas.length;

        // Iterar a través del arreglo
        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;

            // Encontrar el índice del elemento más pequeño en el resto del arreglo
            for (int j = i + 1; j < n; j++) {
                if (cartas[j].getIndice() < cartas[indiceMinimo].getIndice()) {
                    indiceMinimo = j;
                }
            }

            // Intercambiar el elemento más pequeño con el elemento en la posición actual
            Carta temp = cartas[indiceMinimo];
            cartas[indiceMinimo] = cartas[i];
            cartas[i] = temp;
        }

        return cartas;
    }

    public Carta[] ordenarCartasPorPinta(Carta cartas[], Pinta pinta, int numeroCartas) {
        int indicePinta = 0;
        Carta[] cartasPinta = new Carta[numeroCartas];

        for (Carta carta : cartas) {
            if (carta.getPinta() == pinta) {
                cartasPinta[indicePinta] = carta;
                indicePinta++;
                // Verifica si se han copiado cartas sufucientes.
                if (indicePinta >= numeroCartas) {
                    break;
                }
            }
        }

        return ordenarPorSeleccion(cartasPinta);
    }

    public int contarCartasPorPinta(Carta cartas[], Pinta pinta) {
        int contador = 0;
        for (Carta carta : cartas) {
            if (carta.getPinta() == pinta) {
                contador++;
            }
        }
        return contador;
    }

    public int[][] encontrarCartasConsecutivas(Carta cartas[]) {
        int[][] gruposConsecutivos = new int[cartas.length][2];
        int numGrupos = 0;
        if (cartas.length == 0) {
            return new int[0][2];
        }

        int inicioGrupo = cartas[0].getNombre().ordinal();
        int finGrupo = inicioGrupo;

        for (int i = 1; i < cartas.length; i++) {
            if (cartas[i].getNombre().ordinal() == finGrupo + 1) {
                finGrupo = cartas[i].getNombre().ordinal();
            } else {
                gruposConsecutivos[numGrupos][0] = inicioGrupo;
                gruposConsecutivos[numGrupos][1] = finGrupo;
                numGrupos++;

                inicioGrupo = finGrupo = cartas[i].getNombre().ordinal();
            }
        }

        gruposConsecutivos[numGrupos][0] = inicioGrupo;
        gruposConsecutivos[numGrupos][1] = finGrupo;
        numGrupos++;

        // Crear un nuevo arreglo con el tamaño correcto para los grupos
        int[][] resultado = new int[numGrupos][2];
        for (int i = 0; i < numGrupos; i++) {
            resultado[i][0] = gruposConsecutivos[i][0];
            resultado[i][1] = gruposConsecutivos[i][1];
        }

        return resultado;
    }

    public static String mostrarNumerosConsecutivos(int[][] grupos, Pinta pinta) {
        String resultado = "";
        NombreCarta[] nombres = NombreCarta.values(); // Obtener todos los nombres de las cartas
        
        for (int[] grupo : grupos) {
            int inicio = grupo[0] + 1;
            int fin = grupo[1] + 1;
            // Verificar si inicio y fin son diferentes
            if (inicio != fin) {
                resultado += "Escalera de " + pinta + ": ";

                // Mostrar todos los números en el rango
                for (int num = inicio; num <= fin; num++) {
                    resultado += nombres[num - 1];
                    if (num < fin) {
                        resultado += ", ";
                    }
                }

                resultado += "\n";
            }
        }
        return resultado;
    }

    public static int[] obtenerNumerosConsecutivos(int[][] grupos) {
        int[] numerosConsecutivos = new int[grupos.length * 10]; // Tamaño inicial
        int resultadoIndex = 0;

        for (int[] grupo : grupos) {
            int inicio = grupo[0] + 1;
            int fin = grupo[1] + 1;
            // Verificar si inicio y fin son diferentes
            if (inicio != fin) {
                for (int num = inicio; num <= fin; num++) {
                    if (resultadoIndex == numerosConsecutivos.length) {
                        // Aumentar el tamaño del arreglo dinámicamente
                        int[] nuevoArreglo = new int[numerosConsecutivos.length * 2];
                        for (int j = 0; j < numerosConsecutivos.length; j++) {
                            nuevoArreglo[j] = numerosConsecutivos[j];
                        }
                        numerosConsecutivos = nuevoArreglo;
                    }
                    numerosConsecutivos[resultadoIndex++] = num;
                }
            }
        }

        // Redimensionar el arreglo al tamaño real
        int[] resultado = new int[resultadoIndex];
        for (int i = 0; i < resultadoIndex; i++) {
            resultado[i] = numerosConsecutivos[i];
        }

        return resultado;
    }

    public static void marcarNumerosConsecutivos(int[] numerosConsecutivos, Carta[] cartas, Pinta pinta) {

        for (int i = 0; i < numerosConsecutivos.length; i++) {
            int num = numerosConsecutivos[i];

            for (Carta carta : cartas) {
                if (carta.getNombre().ordinal() == (num - 1) && carta.getPinta() == pinta) {
                    carta.setFormaGrupo(true);
                    break; // No necesitamos seguir buscando en las cartas
                }
            }
        }
    }

    public String mostrarPuntaje(Carta[] cartas) {
        int suma = 0;
        String cartasNoFormaGrupo = "";

        for (Carta carta : cartas) {
            if (!carta.estaEnGrupo()) {
                int ordinal = carta.getNombre().ordinal();
                // Si el ordinal es 0,10, 11 o 12, suma 10. Sino, suma el ordinal directamente.
                suma += (ordinal == 0 || (ordinal >= 10 && ordinal <= 12)) ? 10 : (ordinal + 1);
                // Agrega el nombre de la carta y su pinta al mensaje de cartas que no forman grupo.
                if (!cartasNoFormaGrupo.equals("")) {
                    cartasNoFormaGrupo += "\n";
                }
                cartasNoFormaGrupo += carta.getNombre() + " de " + carta.getPinta();
            }
        }

        // Verifica si todas las cartas forman grupo o no.
        if (cartasNoFormaGrupo.equals("")) {
            return "Todas las cartas forman grupo, su puntaje es CERO";
        } else {
            return "Su puntaje es: " + suma 
                    + "\nLas cartas que no forman grupo son:\n" + cartasNoFormaGrupo;
        }
    }
}
