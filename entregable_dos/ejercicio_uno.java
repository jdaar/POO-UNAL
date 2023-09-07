package entregable_dos;

import java.math.BigDecimal;
import java.util.Scanner;

class Empleado {
    public int codigo;
    public String[] nombres;
    // Se utiliza el tipo BigDecimal en vez de float o double ya que es peligroso operar cantidades que necesitan una alta precision con estos tipos (como en este caso, el dinero)
    // @see https://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html
    public BigDecimal horas_trabajadas;
    public BigDecimal precio_hora;
    public BigDecimal retencion_fuente;

    public Empleado(int codigo, String[] nombres, BigDecimal horas_trabajadas, BigDecimal precio_hora, BigDecimal retencion_fuente) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.horas_trabajadas = horas_trabajadas;
        this.precio_hora = precio_hora;
        this.retencion_fuente = retencion_fuente;
    }

    public String toString() {
        BigDecimal salario_bruto = horas_trabajadas.multiply(precio_hora);
        return String.format("El empleado %s con el codigo %d tiene un salario bruto de %s y neto de %s", String.join(" ", nombres), codigo, salario_bruto.toString(), salario_bruto.subtract(salario_bruto.multiply(retencion_fuente)).toString());
    }
}

public class ejercicio_uno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el codigo del empleado: ");
        int codigo = scanner.nextInt();
        
        // Para consumir el newline (\n) que no fue consumido por scanner.nextInt, esto es para evitar que el siguiente scanner.nextLine capture unicamente este caracter en vez de esperar por el input del usuario
        scanner.nextLine();

        System.out.print("Introduzca los nombres del empleado separados por espacio: ");
        String[] nombres = scanner.nextLine().split(" ");

        System.out.print("Introduzca las horas trabajadas por el empleado: ");
        BigDecimal horas_trabajadas = new BigDecimal(scanner.nextLine());

        System.out.print("Introduzca el precio de la hora del empleado: ");
        BigDecimal precio_hora = new BigDecimal(scanner.nextLine());

        System.out.print("Introduzca el porcentaje de retencion en la fuente (el valor debe estar entre 0 y 1): ");
        BigDecimal retencion_fuente = new BigDecimal(scanner.nextLine());

        scanner.close();

        Empleado empleado = new Empleado(codigo, nombres, horas_trabajadas, precio_hora, retencion_fuente);
        System.out.println(empleado.toString());
    }
}