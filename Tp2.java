import java.util.Scanner;
import java.util.Random;

public class Tp2 {

    static Scanner leer = new Scanner (System.in);
    
    
    public static void main(String[] args) {
        int A, M;
        String[] materias;
        String[] alumnos;       //Declaramos las variables globales para usarlas en todoel programa
        int[][] notas;      //notas [] []
        
        
        
        
        do {
            System.out.println("Ingrese la cantidad de Materias a ingresar");
            M = leer.nextInt();
        } while (M<1 || M>=10);
        materias = IngresarDato(M);
        
        do {
            System.out.println("Ingrese la cantidad de Alumnos a ingresar");
            A = leer.nextInt();
        } while (M<1 || M>=30);
        alumnos = IngresarDato(A);
        
        
        notas = llenarNotas(A, M);
        
        menu(A, M, notas, alumnos, materias);

        
        
        
        
  
    }
    
    
    
    public static String[] IngresarDato(int N){
        
        String[] arreglo = new String[N];
        leer.nextLine();
        
        for (int i = 0; i < N; i++) {
            System.out.print("Ingrese el nombre: ");
            arreglo[i] = leer.nextLine();
            arreglo[i] = arreglo[i].toLowerCase();
            System.out.println(i);
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







    public static void menu(int A, int M, int[][] notas,String[] alumnos, String[]materias){
       char opcionMenu; 
        
        System.out.println("Elija una de las siguientes opciones: \n"+
                            "\n"+
                            "A.- Calcular el promedio de todos los alumnos\n"+
                            "B.- Mostrar las calificaciones de todos los alumnos\n"+
                            "C.- Buscar y mostrar las notas de cada estudiante\n"+
                            "D.- Mostrar los estudiantes destacados\n"+
                            "                                                \n"+
                            "E.- SALIR\n");


        
        boolean salir = false;

        while (!salir) {
            

            do {
            
                System.out.println("ingrese la opcion deseada");
                opcionMenu = leer.next().charAt(0);
                opcionMenu = Character.toLowerCase(opcionMenu);

                } while (opcionMenu!='a' || opcionMenu!='b' ||opcionMenu!='c' ||opcionMenu!='d' ||opcionMenu!='e');




            switch (opcionMenu) {
                case 'a':
                    promedio(A, M, notas, opcionMenu, alumnos);
                    break;

                case 'b':
                    b(A, M, notas, materias, alumnos);     
                    break;
                        
                case 'c':
                    c(alumnos, A, M,notas);
                    break;

                case 'd':
                        promedio(A, M, notas, opcionMenu, alumnos);
                        
                    break;

                case 'e':
                    salir = true;
                    System.out.println("Hasta la proxima!");
                    break;

            
                default:
                    break;
            }




        }
    }


    public static void promedio(int A,int M, int[][] notas, char opcionMenu, String[] alumnos){
        
        int[] promedios = new int[A];
        int promedio = 0;

        for(int i=0; i<A;i++){

            for(int j=0; j<M;i++){

                promedio += notas[i][j];

            }

            promedios[i] += promedio;
            promedios[i] = promedios[i]/M;
            
        }

        if(opcionMenu == 'a'){
            a(alumnos, promedios);
        }else{
            d(alumnos, promedios);
        }


    }



    public static void a(String[] alumnos, int[] promedios){
        for(int i=0; i<alumnos.length;i++){

            System.out.println("El alumno "+alumnos[i]+" tiene un promedio de :"+promedios[i]);
        }
    }

    public static void d(String[] alumnos, int[] promedios){
        
        System.out.println("Alumnos destacado");
        
        for(int i=0; i<alumnos.length;i++){

            if(promedios[i]>9){

                System.out.println(alumnos[i]);

            }

        }
    }

    public static void b(int A, int M, int[][]notas, String[]materias, String[]alumnos){

        int NotaMasAlta = 0;
        int ubicacion = 0;

        for(int i=0; i<M ; i++){
            for(int j=0; i<A ; j++){

                if (notas[j][i] >= NotaMasAlta) {
                    ubicacion = j;
                }


            }

            System.out.println("La nota mas alta en "+materias[i]+" es para "+alumnos[i]+" con "+notas[ubicacion][i]);

        }

    }

    public static void c(String[]alumnos, int A, int M, int[][]notas){

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


    }

    
    
}
