package entregable_dos;

import java.util.Scanner;

public class ejercicio_trece {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el primer valor: ");
        double a = scanner.nextInt();
        System.out.print("Introduzca el segundo valor: ");
        double b = scanner.nextInt();
        System.out.print("Introduzca el tercer valor: ");
        double c = scanner.nextInt();

        scanner.close();

        System.out.printf("El valor mas grande entre [%.2f, %.2f, %.2f] es %.2f", a, b, c, a > b ? a : (b > c ? b : c));
    }
}
