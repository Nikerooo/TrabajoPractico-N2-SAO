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



        
        do {
            System.out.println("Ingrese la cantidad de Alumnos a ingresar");
            A = leer.nextInt();
        } while (M<1 || M>30);
        alumnos = IngresarDato(A);
    


        notas = llenarNotas(A, M);


        do {

            opcionMenu = menu();

           switch (opcionMenu) {
                case 'a':
                    promedios(notas, opcionMenu, alumnos, materias);
                    break;

                case 'b':
                    b(A, M, notas, materias, alumnos);     
                    break;
                        
                case 'c':
                    c(alumnos, A, M,notas);
                    break;

                case 'd':
                        promedios(notas, opcionMenu, alumnos, materias);
                        
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
            System.out.println(opcionMenu);

            } while (opcionMenu!='a' && opcionMenu!='b' && opcionMenu!='c' && opcionMenu!='d' && opcionMenu!='e');
        
        return opcionMenu;
    }


    
    
    private static void promedios(int[][] notas, char opcionMenu, String[] alumnos, String[] materias) {
       
        double[] promedio=new double[alumnos.length];
       
 
        for (int i = 0; i < alumnos.length; i++) {
            double suma = 0;
            for (int j = 0; j < materias.length; j++) {
                suma += notas[i][j];
             
            }
            promedio[i] = suma / materias.length;
        }
            if (opcionMenu=='a'){
                System.out.println("Promedios");
                opcionA(promedio, alumnos);
            } else {
                System.out.println("Los alumnos destacados son: ");
              opcionD(promedio, alumnos);
            }

    }



    private static void opcionA(double[] promedio, String[] alumnos) {

        for (int i = 0; i < alumnos.length; i++) {
            System.out.println(alumnos[i]+": "+promedio[i] );
        }
    }
 

    private static void opcionD(double[] promedio, String[] alumnos) {

        for (int i = 0; i < alumnos.length; i++) {

            if (promedio[i] > 9) {
                System.out.println(alumnos[i]+": "+promedio[i]);
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
