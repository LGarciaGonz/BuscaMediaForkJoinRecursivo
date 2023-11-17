import java.util.concurrent.RecursiveTask;

/**********************************************************************************************************************************************
 *   APLICACIÓN: "Tema1TAREA4BuscaMediaForkJoinRecursivo"                                                                                     *
 **********************************************************************************************************************************************
 *   PROGRAMACIÓN DE SERVICIOS Y PROCESOS 2DAM                                                                                                *
 **********************************************************************************************************************************************
 *   @author S.García                                                                                                                        *
 *   @version 1.0 - Versión inicial del programa.                                                                                             *
 *   @since 15NOV2023                                                                                                                       *
 **********************************************************************************************************************************************
 *   COMENTARIOS:                                                                                                                             *
 *      - Calcula la media total de los valores de un array, de forma recursiva hasta un determinado umbral, y luego de forma iterativa.      *
 *      - Uso del framework Fork-Join de Java, recursividad e iteratividad.                                                                   *
 **********************************************************************************************************************************************/

public class CalculaMediaRecursiva extends RecursiveTask<Short> {

    private static final int UMBRAL = 150;
    private static final int LONGITUD = 1_000;
    private int a_InicioArray = 0, a_FinArray = LONGITUD;
    private int[] a_ArrayNumeros = null;


    // ASIGNACIÓN DE VALORES -------------------------------------
    public CalculaMediaRecursiva(int a_InicioArray, int a_FinArray, int[] a_ArrayNumeros) {
        this.a_InicioArray = a_InicioArray;
        this.a_FinArray = a_FinArray;
        this.a_ArrayNumeros = a_ArrayNumeros;
    }

    public CalculaMediaRecursiva() {

    } // Clase CalculaMedia

    /**
     * compute()
     * Si < Umbral -> recursivo()
     * Sino -> iterativo()
     * retornar (media)
     * <p>
     * main()
     * tarea1()
     */

    public static void main(String[] args) {

        // TÍTULO ----------------
        System.out.println("\n******[ CÁLCULO DE MEDIAS FORK-JOIN ITERATIVO Y RECURSIVO ]******");

        // DECLARACIÓN DE VARIABLES --------------------------
        int[] l_ArrayNumeros = null;
        int l_InicioArray = 0, l_MitadArray = LONGITUD / 2, l_FinArray = LONGITUD;
        double l_PrimeraMedia, l_SegundaMedia;

        l_ArrayNumeros = rellenarArray();           // Llamada para rellenar el array de números.


    }

    public static int[] rellenarArray() {

        int[] l_ArrayNumeros = new int[LONGITUD];       // Crear array del tamaño establecido.

        // Recorrer las posiciones para rellenar el array con los índices.
        for (int l_Contador = 0; l_Contador < l_ArrayNumeros.length; l_Contador++) {
            l_ArrayNumeros[l_Contador] = l_Contador;
        } // for()

        return l_ArrayNumeros;      // Retornar el array ya rellenado con los índices.
    } // rellenarArray()

    @Override
    protected Short compute() {
        return null;
    }
}