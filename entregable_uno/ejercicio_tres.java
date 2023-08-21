package entregable_uno;

import java.util.Scanner;

public class ejercicio_tres {
    /**
     * Programa para calcular el salario bruto, retencion en la fuente y salario
     * neto de un empleado
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca la cantidad de horas trabajadas: ");
        int horas_trabajadas = scanner.nextInt();

        scanner.close();

        int valor_hora = (int) (50000 / 48);

        int salario_bruto = horas_trabajadas * valor_hora;
        int retencion_fuente = (int) (salario_bruto * 0.125f);
        int salario_neto = salario_bruto - retencion_fuente;

        System.out.println("Salario bruto: " + salario_bruto);
        System.out.println("Salario neto: " + salario_neto);
        System.out.println("Retencion en la fuente: " + retencion_fuente);
    }
}
