package entregable_uno;

public class ejercicio_dos {
    /**
     * Ejercicio 2
     */
    public static void main(String[] args) {
        int suma = 0;
        int x = 20;
        int y = 40;

        suma += x;

        x += Math.pow(y, 2);
        suma += x/y;

        System.out.println("El valor de la suma es: " + suma);
    }
}
