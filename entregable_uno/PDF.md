# Entregable #1

> Todos los entregables se encuentran en Github bajo el repositorio jdaar/POO-UNAL
> __[Ir a Github](https://github.com/jdaar/POO-UNAL)__

## Ejercicio #1

> __Enunciado__
>
> Ejercicio Resuelto No 4
Mirar Páginas 48 a 49 del libro de Lógica de Programación de Efraín Oviedo –
Archivo PDF Entregado por correo. Nota: Leer enunciado completo y entender
algoritmo.

```java
package  entregable_uno;

import  java.util.Scanner;

class  ejercicio_uno  {
/**
 * Programa para determinar la edad de los hijos de la madre de Juan
 */
	public  static  void  main(String[]  args)  {
		Scanner  scanner  =  new  Scanner(System.in);
		
		// Se asume que no se puede tener edades decimales (la unidad que se recibe es
		// años enteros)

		System.out.print("Introduzca la edad de juan: ");
		int  edad_juan  =  scanner.nextInt();

		scanner.close();
		
		// No tiene sentido aceptar edades negativas

		if  (edad_juan  <  0)  {
				System.out.println("La edad de juan no puede ser negativa");
				System.exit(1);
		}
		
		// Como la unidad es años enteros no se toma en cuenta la parte decimal, por lo
		// tanto vamos para el piso del valor

		int  edad_alberto  =  (int)  ((edad_juan  *  2)  /  3);
		int  edad_ana  =  (int)  ((edad_juan  *  4)  /  3);
		int  edad_madre  =  edad_juan  +  edad_alberto  +  edad_ana;
  
		System.out.println("La edad de Juan es: "  +  edad_juan);
		System.out.println("La edad de Alberto es: "  +  edad_alberto);
		System.out.println("La edad de Ana es: "  +  edad_ana);
		System.out.println("La edad de la madre es: "  +  edad_madre);
	}
}
```

## Ejercicio #2

> __Enunciado__
>
> Ejercicio Resuelto No 5
Mirar Páginas 49 a 50 del libro de Lógica de Programación de Efraín Oviedo –
Archivo PDF Entregado por correo. Nota: Leer enunciado completo y entender
algoritmo

```java
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
```

## Ejercicio #3 

> __Enunciado__
> 
> Ejercicio Propuesto No 12
Mirar Página 50 del libro de Lógica de Programación de Efraín Oviedo – Archivo PDF
Entregado por correo. Nota: Leer enunciado completo.

```java
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
```

## Ejercicio #4

> __Enunciado__
>
> Ejercicio Propuesto No 14
Mirar Página 50 del libro de Lógica de Programación de Efraín Oviedo – Archivo PDF
Entregado por correo. Nota: Leer enunciado completo.

```java
package entregable_uno;

import java.util.Scanner;

public class ejercicio_cuatro {
    /**
     * Programa para conseguir el cuadrado y cubo de un numero
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca un numero: ");
        float numero = scanner.nextFloat();

        scanner.close();

        System.out.println("Cuadrado del numero: " + Math.pow(numero, 2));
        System.out.println("Cubo del numero: " + Math.pow(numero, 3));
    }
}
```

## Ejercicio #5

> __Enunciado__
>
> Ejercicio Propuesto No 17
Mirar Página 50 del libro de Lógica de Programación de Efraín Oviedo – Archivo PDF
Entregado por correo. Nota: Leer enunciado completo.

```java
package entregable_uno;

import java.util.Scanner;

public class ejercicio_cinco {
    /**
     * Programa para conseguir el area y longitud de la circunferencia a partir del
     * radio de un circulo
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduzca el radio del circulo: ");
        double radio = scanner.nextDouble();

        scanner.close();

        double area = Math.PI * Math.pow(radio, 2);
        double longitud = 2 * Math.PI * radio;

        System.out.println("Area del circulo: " + area);
        System.out.println("Longitud del circulo: " + longitud);
    }
}
```