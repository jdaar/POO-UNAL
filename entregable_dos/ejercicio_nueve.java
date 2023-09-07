package entregable_dos;

import java.math.BigDecimal;
import java.util.Scanner;

class Departamento {
    public BigDecimal ventas;
    public BigDecimal salario_por_empleado;

    public Departamento(BigDecimal ventas, BigDecimal salario_por_empleado) {
        this.ventas = ventas;
        this.salario_por_empleado = salario_por_empleado;
    }

    // Si despejamos la desigualdad planteada por el problema
    // Que en este caso seria 0.3(v1+v2+v3) >= vn (donde v1,v2,v3,...,vn. n =
    // {1,2,3})
    // Veremos que podemos cambiar esta ecuacion para que tome la siguiente forma
    // 0.33(v2+v3) >= (1-0.33)(v1) para v1
    // 0.33(v1+v3) >= (1-0.33)(v2) para v2
    // 0.33(v1+v2) >= (1-0.33)(v3) para v3
    // Lo que nos permite definir la funcion como un metodo de vn
    // Esto es muy util para la mantenibilidad del codigo ya que podemos definir
    // Si se hace el aumento como un atributo de la clase
    public Boolean se_hace_aumento(Departamento[] otros_departamentos) {
        BigDecimal valor_a_comparar = BigDecimal.valueOf(0);
        for (Departamento departamento : otros_departamentos) {
            valor_a_comparar = valor_a_comparar.add(departamento.ventas.multiply(BigDecimal.valueOf(0.33)));
        }
        return this.ventas.multiply(BigDecimal.valueOf(0.77)).compareTo(valor_a_comparar) > 0;
    }
}

public class ejercicio_nueve {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Departamento[] departamentos = new Departamento[3];

        BigDecimal ventas;
        BigDecimal salario;
        for (int x = 1; x < 4; x += 1) {
            System.out.printf("Introduce las ventas del departamento %d: ", x);
            ventas = new BigDecimal(scanner.nextLine());
            System.out.printf("Introduce el salario de los empleados del departamento %d: ", x);
            salario = new BigDecimal(scanner.nextLine());

            departamentos[x - 1] = new Departamento(ventas, salario);
        }

        // 0.33(v2+v3) >= (1-0.33)(v1) para v1
        System.out.printf("El salario para los empleados del departamento 1 es $%s\n",
                (departamentos[0].se_hace_aumento(new Departamento[] { departamentos[1], departamentos[2] })
                        ? departamentos[0].salario_por_empleado.add(departamentos[0].salario_por_empleado.multiply(BigDecimal.valueOf(0.2)))
                        : departamentos[0].salario_por_empleado).toString());
        // 0.33(v1+v3) >= (1-0.33)(v2) para v2
        System.out.printf("El salario para los empleados del departamento 2 es $%s\n",
                (departamentos[1].se_hace_aumento(new Departamento[] { departamentos[0], departamentos[2] })
                        ? departamentos[1].salario_por_empleado.add(departamentos[1].salario_por_empleado.multiply(BigDecimal.valueOf(0.2)))
                        : departamentos[1].salario_por_empleado).toString());
        // 0.33(v1+v2) >= (1-0.33)(v3) para v3
        System.out.printf("El salario para los empleados del departamento 3 es $%s\n",
                (departamentos[2].se_hace_aumento(new Departamento[] { departamentos[0], departamentos[1] })
                        ? departamentos[2].salario_por_empleado.add(departamentos[2].salario_por_empleado.multiply(BigDecimal.valueOf(0.2)))
                        : departamentos[2].salario_por_empleado).toString());

        scanner.close();
    }
}
