package entregable_uno;

import java.util.Scanner;

public class ejercicio_cuatro {
    /**
     * Programa para conseguir el cuadrado y cubo de un numero
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca un numero: ");
        float numero = scanner.nextFloat();

        scanner.close();

        System.out.println("Cuadrado del numero: " + Math.pow(numero, 2));
        System.out.println("Cubo del numero: " + Math.pow(numero, 3));
    }
}
