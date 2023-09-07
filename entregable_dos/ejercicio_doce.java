package entregable_dos;

import java.util.Scanner;

public class ejercicio_doce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el valor de A: ");
        double a = scanner.nextInt();
        System.out.print("Introduzca el valor de B: ");
        double b = scanner.nextInt();
        System.out.print("Introduzca el valor de C: ");
        double c = scanner.nextInt();

        scanner.close();

        // nuevamente, si se deseara real precision de la solucion es recomendable usar BigDecimal
        double discriminante = Math.pow(b, 2) - 4 * a * c;

        if (discriminante < 0) {
            System.out.printf("La ecuacion %.2fx^2+%.2fx+%.2f = 0 no tiene soluciones", a, b, c);
        } else {
            double solucion_1 = (-1 * b + Math.sqrt(discriminante)) / (2 * a);
            double solucion_2 = (-1 * b - Math.sqrt(discriminante)) / (2 * a);
            System.out.printf("Las soluciones de la ecuacion %.2fx^2+%.2fx+%.2f = 0 son x=%.2f, x=%.2f", a, b, c, solucion_1, solucion_2);
        }
    }
}
