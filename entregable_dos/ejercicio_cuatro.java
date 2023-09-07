package entregable_dos;

import java.util.Scanner;

public class ejercicio_cuatro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el valor A: ");
        double a = scanner.nextDouble();
        System.out.print("Introduzca el valor B: ");
        double b = scanner.nextDouble();

        scanner.close();

        System.out.println(a < b ? "B es mayor que A" : "A es mayor que B");
    }
}
