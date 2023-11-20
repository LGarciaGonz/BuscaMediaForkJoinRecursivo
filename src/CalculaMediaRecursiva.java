import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

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

public class CalculaMediaRecursiva extends RecursiveTask<Double> {

    private static final int UMBRAL = 100;
    private static final int LONGITUD_ARRAY = 1_000;
    private int[] a_Array;
    private int a_Inicio, a_Fin;

    public CalculaMediaRecursiva() {

    }

    public CalculaMediaRecursiva(int[] p_Array, int p_Inicio, int p_Fin) {
        this.a_Array = p_Array;
        this.a_Inicio = p_Inicio;
        this.a_Fin = p_Fin;
    }

    private double calcularMediaRec() {
        int medio = (a_Inicio + a_Fin) / 2;
        CalculaMediaRecursiva l_Tarea1 = new CalculaMediaRecursiva(a_Array, a_Inicio, medio);
        CalculaMediaRecursiva l_Tarea2 = new CalculaMediaRecursiva(a_Array, medio, a_Fin);

        l_Tarea1.fork();
        l_Tarea2.fork();

        return (l_Tarea1.join() + l_Tarea2.join());
    }

    private double calcularMediaSec() {
        double l_Total = 0;

        for (int l_Contador = a_Inicio; l_Contador < a_Fin; l_Contador++) {
            l_Total += a_Array[l_Contador];
        }

        return l_Total;
    }


    @Override
    protected Double compute() {

        double l_Resultado = 0.0;

        if ((a_Fin - a_Inicio) <= UMBRAL) {
            l_Resultado = calcularMediaSec();
        } else {
            l_Resultado = calcularMediaRec();
        }

        return l_Resultado;
    }

    public static int[] rellenarArray() {

        int[] l_ArrayNumeros = new int[LONGITUD_ARRAY];       // Crear array del tamaño establecido.

        // Recorrer las posiciones para rellenar el array con los índices.
        for (int l_Contador = 0; l_Contador < l_ArrayNumeros.length; l_Contador++) {
            l_ArrayNumeros[l_Contador] = l_Contador;
        } // for()

        return l_ArrayNumeros;      // Retornar el array ya rellenado con los índices.
    } // rellenarArray()


    public static void main(String[] args) {

        CalculaMediaRecursiva l_Tarea = new CalculaMediaRecursiva();
        int[] l_ArrayNumeros = rellenarArray();
        int l_InicioArray = 0;
        int l_FinArray = l_ArrayNumeros.length;
        double l_ResultadoInvoke = 0.0;
        double l_ResultadoJoin = 0.0;
        long l_TiempoInicial = System.currentTimeMillis();
        ForkJoinPool l_Pool = new ForkJoinPool();

        System.out.println("Inicio del cálculo de la media.");

        l_Tarea = new CalculaMediaRecursiva(l_ArrayNumeros, l_InicioArray, l_FinArray);
        l_ResultadoInvoke = l_Pool.invoke(l_Tarea);
        l_ResultadoJoin = l_Tarea.join();

        System.out.println("Milisegundos empleados: " + (System.currentTimeMillis() - l_TiempoInicial));
        System.out.println("La media según 'invoke' es: " + (l_ResultadoInvoke / l_ArrayNumeros.length));
        System.out.println("Coincide con la media según 'join' que es: " + (l_ResultadoJoin / l_ArrayNumeros.length));
    }
}


//
//    private static final int UMBRAL = 150;
//    private static final int LONGITUD = 1_000;
//    private int a_InicioArray = 0, a_FinArray = LONGITUD;
//    private int[] a_ArrayNumeros = null;
//
//
//    // ASIGNACIÓN DE VALORES -------------------------------------
//    public CalculaMediaRecursiva(int a_InicioArray, int a_FinArray, int[] a_ArrayNumeros) {
//        this.a_InicioArray = a_InicioArray;
//        this.a_FinArray = a_FinArray;
//        this.a_ArrayNumeros = a_ArrayNumeros;
//    }
//
//    public CalculaMediaRecursiva() {
//
//    } // Clase CalculaMedia
//
//    /**
//     * compute()
//     * Si < Umbral -> recursivo()
//     * Sino -> iterativo()
//     * retornar (media)
//     * <p>
//     * main()
//     * tarea1()
//     */
//
//    public static void main(String[] args) {
//
//        // TÍTULO ----------------
//        System.out.println("\n******[ CÁLCULO DE MEDIAS FORK-JOIN ITERATIVO Y RECURSIVO ]******");
//
//        // DECLARACIÓN DE VARIABLES --------------------------
//        int[] l_ArrayNumeros = null;
//        int l_InicioArray = 0, l_MitadArray = LONGITUD / 2, l_FinArray = LONGITUD;
//        double l_PrimeraMedia, l_SegundaMedia;
//
//        l_ArrayNumeros = rellenarArray();           // Llamada para rellenar el array de números.
//
//    }
//
//    public static int[] rellenarArray() {
//
//        int[] l_ArrayNumeros = new int[LONGITUD];       // Crear array del tamaño establecido.
//
//        // Recorrer las posiciones para rellenar el array con los índices.
//        for (int l_Contador = 0; l_Contador < l_ArrayNumeros.length; l_Contador++) {
//            l_ArrayNumeros[l_Contador] = l_Contador;
//        } // for()
//
//        return l_ArrayNumeros;      // Retornar el array ya rellenado con los índices.
//    } // rellenarArray()
//
//    @Override
//    protected Short compute() {
//        return null;
//    }
//}