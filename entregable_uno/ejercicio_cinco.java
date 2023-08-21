package entregable_uno;

import java.util.Scanner;

public class ejercicio_cinco {
    /**
     * Programa para conseguir el area y longitud de la circunferencia a partir del
     * radio de un circulo
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el radio del circulo: ");
        double radio = scanner.nextDouble();

        scanner.close();

        double area = Math.PI * Math.pow(radio, 2);
        double longitud = 2 * Math.PI * radio;

        System.out.println("Area del circulo: " + area);
        System.out.println("Longitud del circulo: " + longitud);
    }
}
