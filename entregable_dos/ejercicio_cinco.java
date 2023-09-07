package entregable_dos;

import java.math.BigDecimal;
import java.util.Scanner;

class Estudiante {
    public static BigDecimal umbral = new BigDecimal("2000000");

    public int numero_inscripcion;
    public String[] nombres;
    public BigDecimal patrimonio;
    public int estrato;

    public Estudiante(int numero_inscripcion, String[] nombres, BigDecimal patrimonio, int estrato) {
        this.numero_inscripcion = numero_inscripcion;
        this.nombres = nombres;
        this.patrimonio = patrimonio;
        this.estrato = estrato;
    }

    public BigDecimal calcular_pago_matricula() {
        return patrimonio.compareTo(Estudiante.umbral) > 0 && estrato > 3 ? new BigDecimal("50000").add(patrimonio.multiply(new BigDecimal("0.03"))) : new BigDecimal("50000");
    }
}

public class ejercicio_cinco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el numero de inscripcion: ");
        int numero_inscripcion = scanner.nextInt();
        
        scanner.nextLine();

        System.out.print("Introduzca los nombres separados por espacio: ");
        String[] nombres = scanner.nextLine().split(" ");
        System.out.print("Introduzca el patrimonio: ");
        BigDecimal patrimonio = new BigDecimal(scanner.nextLine());
        System.out.print("Introduzca el estrato: ");
        int estrato = scanner.nextInt();

        scanner.close();

        Estudiante estudiante = new Estudiante(numero_inscripcion, nombres, patrimonio, estrato);

        System.out.printf("El estudiante con numero de inscripcion %s y nombre %s debe pagar $%s en matricula\n", estudiante.numero_inscripcion, String.join(" ", estudiante.nombres), estudiante.calcular_pago_matricula().toString());
    }
}
