package entregable_dos;

import java.util.Scanner;

public class ejercicio_seis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el primer numero: ");
        int a = scanner.nextInt();
        System.out.print("Introduzca el segundo numero: ");
        int b = scanner.nextInt();
        System.out.print("Introduzca el tercer numero: ");
        int c = scanner.nextInt();

        scanner.close();

        int biggest = a > b ? (a > c ? a : c): (b > c ? b : c);

        System.out.printf("El mayor numero entre %d, %d y %d es %d\n", a, b, c, biggest);
    }
}
