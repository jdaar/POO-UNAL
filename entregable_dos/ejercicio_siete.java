package entregable_dos;

import java.math.BigDecimal;
import java.util.Scanner;

class Trabajador {
    String[] nombres;
    int horas_trabajadas;
    BigDecimal valor_hora;

    public Trabajador(String[] nombres, int horas_trabajadas, BigDecimal valor_hora) {
        this.nombres = nombres;
        this.horas_trabajadas = horas_trabajadas;
        this.valor_hora = valor_hora;
    }

    public BigDecimal calcular_salario() {
        return this.valor_hora.multiply(BigDecimal.valueOf(horas_trabajadas));
    }
}

public class ejercicio_siete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca los nombres separados por espacio: ");
        String[] nombres = scanner.nextLine().split(" ");
        System.out.print("Introduzca las horas trabajadas: ");
        int horas_trabajadas = scanner.nextInt();

        scanner.nextLine();
        
        System.out.print("Introduzca el valor de la hora: ");
        BigDecimal valor_hora = new BigDecimal(scanner.nextLine());

        scanner.close();

        Trabajador trabajador = new Trabajador(nombres, horas_trabajadas, valor_hora);

        System.out.printf("El trabajador con nombre %s devenga $%s\n", String.join(" ", trabajador.nombres), trabajador.calcular_salario().toString());
    }
}
