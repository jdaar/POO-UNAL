package entregable_dos;

import java.util.Scanner;

public class ejercicio_diez {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el primer numero: ");
        float a = scanner.nextInt();
        System.out.print("Introduzca el segundo numero: ");
        float b = scanner.nextInt();
        System.out.print("Introduzca el tercer numero: ");
        float c = scanner.nextInt();
        System.out.print("Introduzca el cuarto numero: ");
        float d = scanner.nextInt();

        scanner.close();

        // Se revisa si el primer valor es el diferente y de no ser el caso se va revisando de uno en uno para identificar
        String indice_diferente = (b == d && b == c && b == d) ? "primer" : a != b ? "segundo" : (b != c ? "tercer": "cuarto");
        float valor_diferente = (b == d && b == c && b == d) ? a : (a != b ? b : (b != c ? c : d));

        System.out.printf("Se identifico que el %s numero que se introdujo fue el diferente con el valor %2f", indice_diferente, valor_diferente);
    }
}
