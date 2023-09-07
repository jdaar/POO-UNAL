package entregable_dos;

import java.math.BigDecimal;
import java.util.Scanner;

enum Bola {
    BLANCA("blanca", BigDecimal.valueOf(0)),
    VERDE("verde", BigDecimal.valueOf(0.1)),
    AMARILLA("amarilla", BigDecimal.valueOf(0.25)),
    AZUL("azul", BigDecimal.valueOf(0.5)),
    ROJA("roja", BigDecimal.valueOf(1));

    public String nombre;
    public BigDecimal descuento;

    private Bola(String nombre, BigDecimal descuento) {
        this.nombre = nombre;
        this.descuento = descuento;
    }
}

public class ejercicio_ocho {
    public static void main(String[] args) {
        Bola[] bolas = { Bola.BLANCA, Bola.VERDE, Bola.AMARILLA, Bola.AZUL, Bola.ROJA };

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el valor de la compra: ");
        BigDecimal valor_compra = new BigDecimal(scanner.nextLine());
        System.out.print("Introduzca el color de la bola: ");
        String color_bola = scanner.nextLine().strip().toLowerCase();

        scanner.close();
        
        for (Bola bola : bolas) {
            if (bola.nombre.equals(color_bola)) {
                System.out.printf("El valor a pagar es $%s\n", valor_compra.subtract(valor_compra.multiply(bola.descuento)));
                System.exit(0);
            }
        }

        System.out.println("No introdujo un valor valido para el color de la bola");
        System.exit(1);
    }
}
