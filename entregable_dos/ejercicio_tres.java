package entregable_dos;

import java.util.Scanner;

// Para evitar problemas por nombramiento ambiguo en el paquete
class TrianguloEjercicioTres {
    public double lado_uno;
    public double lado_dos;
    public double lado_tres;

    public TrianguloEjercicioTres(double lado_uno, double lado_dos, double lado_tres) {
        this.lado_uno = lado_uno;
        this.lado_dos = lado_dos;
        this.lado_tres = lado_tres;
    }

    public double calcular_perimetro() {
        return lado_uno + lado_dos + lado_tres;
    }

    public double calcular_semiperimetro() {
        return calcular_perimetro() / 2;
    };  

    public double calcular_area() {
        // @see https://es.wikipedia.org/wiki/F%C3%B3rmula_de_Her%C3%B3n
        double s = this.calcular_semiperimetro();
        return Math.sqrt(s * (s - lado_uno) * (s - lado_dos) * (s - lado_tres));
    };
}


public class ejercicio_tres {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca la longitud del lado 1: ");
        double lado_uno = scanner.nextDouble();

        System.out.print("Introduzca la longitud del lado 1: ");
        double lado_dos = scanner.nextDouble();

        System.out.print("Introduzca la longitud del lado 1: ");
        double lado_tres = scanner.nextDouble();

        scanner.close();

        TrianguloEjercicioTres triangulo = new TrianguloEjercicioTres(lado_uno, lado_dos, lado_tres);

        System.out.printf(
                "El perimetro del triangulo es: %f\nEl area del triangulo es: %f\nEl semiperimetro del triangulo es: %f",
                triangulo.calcular_perimetro(), triangulo.calcular_area(), triangulo.calcular_semiperimetro());
    }
}
