package entregable_dos;

import java.math.BigDecimal;
import java.util.Scanner;

public class ejercicio_once {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el nombre del empleado: ");
        String nombre = scanner.nextLine();

        System.out.print("Introduzca el salario por hora del empleado: ");
        BigDecimal salario_hora = new BigDecimal(scanner.nextLine());

        System.out.printf(salario_hora.compareTo(BigDecimal.valueOf(450000)) > 0 ? "Nombre del empleado: %s. Salario del empleado: %s\n" : "Nombre del empleado: %s\n", nombre, salario_hora.toString());

        scanner.close();
    }
}
