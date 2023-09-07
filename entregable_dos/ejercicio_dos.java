package entregable_dos;

import java.util.Scanner;

class Triangulo {
    public double lado_uno;
    public double lado_dos;
    public double lado_tres;

    public double calcular_perimetro() {
        return lado_uno + lado_dos + lado_tres;
    }

    public double calcular_area() {
        // @see https://es.wikipedia.org/wiki/F%C3%B3rmula_de_Her%C3%B3n
        double s = this.calcular_perimetro() / 2;
        return Math.sqrt(s * (s - lado_uno) * (s - lado_dos) * (s - lado_tres));
    };

    public double calcular_altura() {
        // Sabiendo que el area de un triangulo esta definida por A=(1/2)*base*altura
        // despejamos altura=2*A/base
        // Se asume que la base del triangulo es el primer lado (lado_uno)
        return 2 * calcular_area() / lado_uno;
    }
}

class TrianguloEquilatero extends Triangulo {
    public TrianguloEquilatero(double lado) {
        this.lado_uno = lado;
        this.lado_dos = lado;
        this.lado_tres = lado;
    }
}

public class ejercicio_dos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca la longitud de los lados del triangulo equilatero: ");
        double lado = scanner.nextDouble();

        scanner.close();

        TrianguloEquilatero triangulo = new TrianguloEquilatero(lado);

        System.out.printf(
                "El perimetro del triangulo es: %f\nEl area del triangulo es: %f\nLa altura del triangulo es: %f",
                triangulo.calcular_perimetro(), triangulo.calcular_area(), triangulo.calcular_altura());
    }
}
