package entregable_uno;

import java.util.Scanner;

class ejercicio_uno {
    /**
     * Programa para determinar la edad de los hijos de la madre de Juan
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Se asume que no se puede tener edades decimales (la unidad que se recibe es
        // años enteros)

        System.out.print("Introduzca la edad de juan: ");
        int edad_juan = scanner.nextInt();

        scanner.close();

        // No tiene sentido aceptar edades negativas

        if (edad_juan < 0) {
            System.out.println("La edad de juan no puede ser negativa");
            System.exit(1);
        }

        // Como la unidad es años enteros no se toma en cuenta la parte decimal, por lo
        // tanto vamos para el piso del valor

        int edad_alberto = (int) ((edad_juan * 2) / 3);
        int edad_ana = (int) ((edad_juan * 4) / 3);
        int edad_madre = edad_juan + edad_alberto + edad_ana;

        System.out.println("La edad de Juan es: " + edad_juan);
        System.out.println("La edad de Alberto es: " + edad_alberto);
        System.out.println("La edad de Ana es: " + edad_ana);
        System.out.println("La edad de la madre es: " + edad_madre);
    }
}