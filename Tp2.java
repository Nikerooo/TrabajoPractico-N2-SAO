import java.util.Scanner;
import java.util.Random;        //Declaramos las librerias


public class Tp2 {

    static Scanner leer = new Scanner (System.in);
    
    
    
    public static void main(String[] args) {
        int A, M;
        String[] materias;
        String[] alumnos;       // Declaramos las materias y los alumnos para guardar los datos
        int[][] notas;      // Declaramos la matriz notas para guardar las notas
        char opcionMenu;
        
        
        Bienvenida();       //Llamamos al logo bienvenida 
        
        
        do {
            System.out.println("Ingrese la cantidad de Materias a ingresar");   //Pedimos datos
            M = leer.nextInt();
        } while (M<1 || M>10);              //verificamos que el dato este entre 1 y 10
        materias = IngresarDato(M);         // Cargamos el array con los datos de la funcion ingresar datos
        System.out.println();               // Damos un espacio


        
        do {
            System.out.println("Ingrese la cantidad de Alumnos a ingresar");
            A = leer.nextInt();         //Pedimos la cantidad de alumnos que se van a ingresar
        } while (M<1 || M>30);      //Verificamos datos
        alumnos = IngresarDato(A);
        System.out.println();


        notas = llenarNotas(A, M);      //Llenamos la matriz de las notas de forma aleatoria


        do {

            opcionMenu = menu();        // Guardamos la opcion que elija el usuario en la variable opcion Menu

            /*for (int i = 0; i < notas.length; i++) {
                for (int j = 0; j < notas[i].length; j++) {         Recorremos la matriz para ver las notas, Solo para testear
                    System.out.print(notas[i][j]+" ");
                }
                System.out.println(" ");
            }*/

           switch (opcionMenu) {            //Armamos un switch para las opciones del menu
                case 'a':
                    A_MostrarPromedios(notas, alumnos);     // Llamamos a la funcion de Mostrar Promedios
                    break;

                case 'b':
                    B_MostrarCalificacionesMasAltasyBajas(notas, materias);          // Llamamos a la funcion de mostrar la calificacion mas alta y baja
                    break;
                        
                case 'c':
                    C_BuscarEstudiantes(notas,alumnos);
                    break;

                case 'd':
                        D_EstudiantesDestacados(notas, alumnos);             // Llamamos a la funcion de Alumnos destacados
                        
                    break;

                case 'e':
                    System.out.println("Hasta la proxima!");

            
                default:
                    break;
            }

        } while (opcionMenu != 'e');       //Se verifica si el usuario quiere salir o no
        
        
        leer.close();       // Cerramos el Scanner
  
    }
    
    
    
    public static String[] IngresarDato(int N){     // Pedimos el tamaño de la matriz que vayamos a usar 
        
        String[] arreglo = new String[N];   // Array provisional para ir guardando los datos que el usuario introduce
        leer.nextLine();    // Situamos el Scanner dentro de esta funcion
        
        for (int i = 0; i < N; i++) {
            System.out.print("Ingrese el nombre: ");
            arreglo[i] = leer.nextLine();               //Se guardan los datos que introduzca el usuario
            arreglo[i] = arreglo[i].toLowerCase();      // Transformamos todo en minusculas para evitar problemas con mayusculas al momento de comparar
            
        }
        
        return arreglo;     // Devuelve el array completo
        
    }
   
    
    
    public static int[][] llenarNotas(int A, int M){
        
        Random random = new Random();       //Creamos la variable Random
            
        int [][] notas = new int [A][M];        
        
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < M; j++) {
                
                notas[i][j] = random.nextInt(10)+1;         //El random elije entre 10 numeros (0 a 9) pero sumamos uno al total (1 a 10)  
            }
            
        }
        
        return notas;       // Devolvemos la matriz notas completas
        
    }







    public static char menu(){
       char opcionMenu; 
        System.out.println();           // Espacio
        System.out.println("Elija una de las siguientes opciones: \n"+
                            "\n"+
                            "A.- Calcular y mostrar el promedio de todos los alumnos\n"+            //Mostramos las opciones del menu
                            "B.- Mostrar la calificacion mas alta y baja de cada asignatura\n"+
                            "C.- Buscar un estudiante y mostrar sus notas\n"+
                            "D.- Mostrar los estudiantes destacados\n"+
                            "                                                \n"+
                            "E.- SALIR\n");

        do {
            
            System.out.println("ingrese la opcion deseada");
            opcionMenu = leer.next().charAt(0);             // Guardamos el char que ingrese el usuario
            opcionMenu = Character.toLowerCase(opcionMenu);       // Lo ponemos en minusculas para evitar problemas al comparar

        } while (opcionMenu!='a' && opcionMenu!='b' && opcionMenu!='c' && opcionMenu!='d' && opcionMenu!='e');  //Verificamos que no se ingresen datos erroneos
        System.out.println();

        

        return opcionMenu;  // Devolvemos un char 
    }


    
    
    private static double[] CalcularPromedios(int[][] notas) {      // Recibimos las notas
       
        double[] promedio=new double[notas.length];     // Array del tamaño notas (A)
       
 
        for (int i = 0; i < notas.length; i++) {
            double suma = 0;                                 // Creamos e inicializamos un acumulador
            for (int j = 0; j < notas[i].length; j++) {
                suma += notas[i][j];                         // Sumamos al acumulador las notas
             
            }
            promedio[i] = suma / notas[i].length;       // Al total de suma, lo dividimos por la cantidad de filas (CantAlumnos)
            //Los indices de promedio coinciden con su estudiante al respecto
        }
            
        return promedio;        // Retorna el array con los promedios

    }



    private static void A_MostrarPromedios(int[][] notas, String[] alumnos) {       //Recibe la matriz notas y el Array alumnos

        double[] promedio = CalcularPromedios(notas);   // Se recibe el array promedios
        
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println(alumnos[i]+": "+String.format("%.2f", promedio[i]) );        // Se muestran todos los promedios
        }
    }
 

    private static void D_EstudiantesDestacados(int[][] notas, String[] alumnos) {      //Recibe la matriz notas y el Array alumnos

        double[] promedio = CalcularPromedios(notas);       // Se recibe el array promedios
        boolean promedioExcelente = false;      // Bandera que indica si se encuentra un promedio mayor a 9
        
        for (int i = 0; i < alumnos.length; i++) {

            if (promedio[i] > 9) {      // Si se encuentra un promedio mayor a 9 entra
                System.out.println(alumnos[i]+": "+promedio[i]);        // Se muestra el alumno con su promedio
                promedioExcelente = true;   // Se indica que se encontro un alumno con promedio mayor a 9
            }
     
        }
        
        if(!promedioExcelente){     // Si se encontro el alumno, no hace falta mostrar el mensaje. Si no, se indica
            System.out.println("No hay nadie con un promedio mayor a 9. A esforzarse mas!");
        }
        
    }


    private static void B_MostrarCalificacionesMasAltasyBajas(int notasF [][], String materiasF []){

       
        
        for (int j = 0; j < notasF[0].length; j++) {        // Recorremos las columnas
            int auxNotaMasBaja = 10;    
            int auxNotaMasAlta = 0;     // Variables que guardan el valor mas bajo y mas alto que se encuentran
            
            for (int i = 0; i < notasF.length; i++) {   // Recorremos las filas

                if (notasF[i][j] < auxNotaMasBaja)  // Si la nota es mas baja a la guardada en nota mas baja entra
                    auxNotaMasBaja = notasF[i][j];  // Se guarda la nueva nota mas baja

                if (notasF[i][j] > auxNotaMasAlta)  // Si la nota es mas alta a la guardada en nota mas alta entra
                    auxNotaMasAlta = notasF[i][j];  // Se guarda la nueva nota mas alta
            }
            System.out.println();
            System.out.println("Materia: " + materiasF[j]);     // Se muestra la materias
            System.out.println("Nota mas baja: " + auxNotaMasBaja);     // Se muestra la nota mas baja
            System.out.println("Nota mas alta: " + auxNotaMasAlta);     // Se muestra la nota mas alta
            System.out.println();
        }
        
    }




    private static void C_BuscarEstudiantes(int [][] notasF, String [] estudiantesF) {      // Se reciben las notas y los estudiantes
        
        leer.nextLine();
        System.out.println("Ingrese el nombre del alumno a consultar:");
        String alumnoElegido= leer.nextLine();          // Se piden y se leen 

        Boolean coincideAlumno=false;       // Se crea la bandera

        int i=0;    // Se inicializa y crea un contador
        
        while (!coincideAlumno && i<notasF.length){     // Mientras coincidenciaAlumno sea falso e i sea menor a la cant de filas de nota hacer:
            
            if (estudiantesF[i].equals(alumnoElegido)) {    // Se comparan los nombres de los alumnos con el nombre ingresado, si coinciden entra
                
                coincideAlumno = true;      // Se encontro coincidencia entre los alumnos guardados y que buscan
                System.out.println("Notas del Alumno:"+estudiantesF[i]+":");    // SSe muestra el nombre del alumno

                for (int j = 0; j < notasF[i].length; j++) {

                    System.out.print(notasF[i][j] + " ");       // Se muestran todas las notas

                }
                System.out.println();

            }else i++;  // Si no se encuentra el alumno se le suma uno al contador  

        } 
        
        if (!coincideAlumno) {          // Si el alumno ingresado no se encuentra en la lista anteriormente cargada se muestra el siguiente mensaje y se repite todo
          System.out.println("No se encontro alumno con ese nombre");  
        }
    }


    private static void Bienvenida(){       // Cuando llaman a la Bienvenida, se muestra el logo y un mensaje

        System.out.println("                                                    \n" +
        "                                                    \n" +
        "                  =#@@@@@@@@@@%*-                   \n" +
        "              -%@@@@@@=.                            \n" +
        "            +@@@@@%:   .+##@@@@@@%*-                \n" +
        "          +@@@@@*               :%@@@@*             \n" +
        "         @@@@@%                    :@@@@%           \n" +
        "        @@@@@#      ...........      -@@@@+         \n" +
        "       %@@@@%      -@@@@@@@@@@@        @@@@#        \n" +
        "      *@@@@@       -@@@@:               @@@@+       \n" +
        "      @@@@@+       -@@@@                -@@@@       \n" +
        "     :@@@@@        -@@@@#****=           @@@@=      \n" +
        "     -@@@@@        -@@@@@@@@@=           @@@@+      \n" +
        "     :@@@@@.       -@@@@                 @@@@-      \n" +
        "      @@@@@+       -@@@@                =@@@@       \n" +
        "      *@@@@@       -@@@@-......         @@@@+       \n" +
        "       %@@@@%      -@@@@@@@@@@@=       @@@@#        \n" +
        "        @@@@@%                       =@@@@=         \n" +
        "         @@@@@@.                   -@@@@%           \n" +
        "          =@@@@@#               =@@@@@+             \n" +
        "            =@@@@@@:    =#@@@@@@@%*:                \n" +
        "              .#@@@@@@*:           .                \n" +
        "                  -#%@@@@@@@@@%*:                   \n" +
        "                                                    \n" +
        "                                                    ");
                
                
                System.out.println("  _____     _             _ _                   _       \n" +
        " | ____|___| |_ _   _  __| (_) __ _ _ __       | |_ ___ \n" +
        " |  _| / __| __| | | |/ _` | |/ _` | '_ \\ _____| __/ __|\n" +
        " | |___\\__ \\ |_| |_| | (_| | | (_| | | | |_____| |_\\__ \\\n" +
        " |_____|___/\\__|\\__,_|\\__,_|_|\\__,_|_| |_|      \\__|___/\n" +
        "                                                        ");
        
                
                
                
                System.out.println(""
                        + "\n"
                        + "\n"
                        + "\n"
                        + "\n"
                        + "\n"
                        + "~~~~~~ BIENVENIDOS! ~~~~~\n"
                        + "\n"
                        + "Pulse enter para continuar: ");
                
                leer.nextLine();


    }
    
    
}
