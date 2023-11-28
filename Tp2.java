import java.util.Scanner;
import java.util.Random;

public class Tp2 {

    static Scanner leer = new Scanner (System.in);
    
    
    public static void main(String[] args) {
        int A, M;
        String[] materias;
        String[] alumnos;       //Declaramos las variables globales para usarlas en todoel programa
        int[][] notas;      //notas [] []
        char opcionMenu;
        boolean salir = false;
        
        
        
        
        do {
            System.out.println("Ingrese la cantidad de Materias a ingresar");
            M = leer.nextInt();
        } while (M<1 || M>10);
        materias = IngresarDato(M);
        System.out.println();


        
        do {
            System.out.println("Ingrese la cantidad de Alumnos a ingresar");
            A = leer.nextInt();
        } while (M<1 || M>30);
        alumnos = IngresarDato(A);
        System.out.println();


        notas = llenarNotas(A, M);


        do {

            opcionMenu = menu();

            for (int i = 0; i < notas.length; i++) {
                for (int j = 0; j < notas[i].length; j++) {
                    System.out.print(notas[i][j]+" ");
                }
                System.out.println(" ");
            }

           switch (opcionMenu) {
                case 'a':
                    A_MostrarPromedios(notas, alumnos);
                    break;

                case 'b':
                    B_MostrarCalificacionesMasAltasyBajas(notas, materias);     
                    break;
                        
                case 'c':
                    /*c(alumnos, A, M,notas);*/
                    break;

                case 'd':
                        D_EstudiantesDestacados(notas, alumnos);
                        
                    break;

                case 'e':
                    salir = true;
                    System.out.println("Hasta la proxima!");
                    

            
                default:
                    break;
            }

        } while (!salir);
        
        
        
  
    }
    
    
    
    public static String[] IngresarDato(int N){
        
        String[] arreglo = new String[N];
        leer.nextLine();
        
        for (int i = 0; i < N; i++) {
            System.out.print("Ingrese el nombre: ");
            arreglo[i] = leer.nextLine();
            arreglo[i] = arreglo[i].toLowerCase();
            
        }
        
        return arreglo;
        
    }
   
    
    
    public static int[][] llenarNotas(int A, int M){
        
        Random random = new Random();
        
        int [][] notas = new int [A][M];
        
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < M; j++) {
                
                notas[i][j] = random.nextInt(10)+1;    
            }
            
        }
        
        return notas;
        
    }







    public static char menu(){
       char opcionMenu; 
        System.out.println();
        System.out.println("Elija una de las siguientes opciones: \n"+
                            "\n"+
                            "A.- Calcular y mostrar el promedio de todos los alumnos\n"+
                            "B.- Mostrar la calificacion mas alta y baja de cada asignatura\n"+
                            "C.- Buscar un estudiante y mostrar sus notas\n"+
                            "D.- Mostrar los estudiantes destacados\n"+
                            "                                                \n"+
                            "E.- SALIR\n");

        do {
            
            System.out.println("ingrese la opcion deseada");
            opcionMenu = leer.next().charAt(0);
            opcionMenu = Character.toLowerCase(opcionMenu);
            //System.out.println(opcionMenu);

        } while (opcionMenu!='a' && opcionMenu!='b' && opcionMenu!='c' && opcionMenu!='d' && opcionMenu!='e');
        System.out.println();

        

        return opcionMenu;
    }


    
    
    private static double[] CalcularPromedios(int[][] notas) {
       
        double[] promedio=new double[notas.length];
       
 
        for (int i = 0; i < notas.length; i++) {
            double suma = 0;
            for (int j = 0; j < notas[i].length; j++) {
                suma += notas[i][j];
             
            }
            promedio[i] = suma / notas[i].length;
        }
            
        return promedio;

    }



    private static void A_MostrarPromedios(int[][] notas, String[] alumnos) {

        double[] promedio = CalcularPromedios(notas);
        
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println(alumnos[i]+": "+String.format("%.2f", promedio[i]) );
        }
    }
 

    private static void D_EstudiantesDestacados(int[][] notas, String[] alumnos) {

        double[] promedio = CalcularPromedios(notas);
        boolean promedioExcelente = false;
        
        for (int i = 0; i < alumnos.length; i++) {

            if (promedio[i] > 9) {
                System.out.println(alumnos[i]+": "+promedio[i]);
                promedioExcelente = true;
            }
     
        }
        
        if(!promedioExcelente){
            System.out.println("No hay nadie con un promedio mayor a 9. A esforzarse mas!");
        }
        
    }


    private static void B_MostrarCalificacionesMasAltasyBajas(int notasF [][], String materiasF []){

       
        
        for (int j = 0; j < notasF[0].length; j++) {
            int auxNotaMasBaja = 10;
            int auxNotaMasAlta = 0;
            
            for (int i = 0; i < notasF.length; i++) {
                if (notasF[i][j] < auxNotaMasBaja) 
                    auxNotaMasBaja = notasF[i][j];
                if (notasF[i][j] > auxNotaMasAlta)
                    auxNotaMasAlta = notasF[i][j];
            }
            System.out.println();
            System.out.println("Materia: " + materiasF[j]);
            System.out.println("Nota mas baja: " + auxNotaMasBaja);
            System.out.println("Nota mas alta: " + auxNotaMasAlta);
            System.out.println();
        }
    }




    /*public static void c(String[]alumnos, int A, int M, int[][]notas){

        leer.nextLine();
        String pedirAlumnos=leer.nextLine();
        pedirAlumnos = pedirAlumnos.toLowerCase();
        int ubicacion=0;
        boolean encontro = false;

        for(int i =0;i<A;i++){
            
            if(alumnos[i]==pedirAlumnos){

                ubicacion = i;
                System.out.println("Notas de "+alumnos+" ");
                encontro = true;

            }
            i++;
        }

        if(encontro){
            for(int j = 0; j<M;j++){
                System.out.print(notas[ubicacion][j]);
            }
        }else{
            System.out.println("No se encontro ningun alumno con ese nombre");
        }


    }*/

    
    
}
