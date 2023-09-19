# Tercer entregable POO

__Estudiante__: Jhonatan David Asprilla Arango
__CC__: 1018222341
__Usuario UNAL__: jasprilla

__Repositorio Github__: https://github.com/jdaar/POO-UNAL

# Punto 1

## Cap 3. Numeral 18
```java
package entregable_tres;

import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

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
        JFrame frame = new JFrame("Informacion empleado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(700, 250);

        GridLayout layout = new GridLayout(6, 1);
        GridLayout layout_field = new GridLayout(1, 2);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);

        JPanel codigo_empleado_field = new JPanel(layout_field);
        JLabel codigo_empleado_label = new JLabel("Codigo del empleado");
        JTextField codigo_empleado_input = new JTextField(126);

        codigo_empleado_field.add(codigo_empleado_label);
        codigo_empleado_field.add(codigo_empleado_input);

        JPanel nombres_empleado_field = new JPanel(layout_field);
        JLabel nombres_empleado_label = new JLabel("Nombres del empleado");
        JTextField nombres_empleado_input = new JTextField(126);

        nombres_empleado_field.add(nombres_empleado_label);
        nombres_empleado_field.add(nombres_empleado_input);

        JPanel horas_trabajadas_empleado_field = new JPanel(layout_field);
        JLabel horas_trabajadas_empleado_label = new JLabel("Horas trabajadas del empleado");
        JTextField horas_trabajadas_empleado_input = new JTextField(126);

        horas_trabajadas_empleado_field.add(horas_trabajadas_empleado_label);
        horas_trabajadas_empleado_field.add(horas_trabajadas_empleado_input);

        JPanel precio_horas_empleado_field = new JPanel(layout_field);
        JLabel precio_horas_empleado_label = new JLabel("Precio de la horas del empleado");
        JTextField precio_horas_empleado_input = new JTextField(126);

        precio_horas_empleado_field.add(precio_horas_empleado_label);
        precio_horas_empleado_field.add(precio_horas_empleado_input);

        JPanel retencion_fuente_empleado_field = new JPanel(layout_field);
        JLabel retencion_fuente_empleado_label = new JLabel("Porcentaje (0 a 1) de la retencion en fuente del empleado");
        JTextField retencion_fuente_empleado_input = new JTextField(126);

        retencion_fuente_empleado_field.add(retencion_fuente_empleado_label);
        retencion_fuente_empleado_field.add(retencion_fuente_empleado_input);

        JButton subir_formulario_button = new JButton("Mostrar informacion del empleado");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(codigo_empleado_input.getText());
                String[] nombres = codigo_empleado_input.getText().split(" ");
                BigDecimal horas_trabajadas = new BigDecimal(horas_trabajadas_empleado_input.getText());
                BigDecimal precio_horas = new BigDecimal(precio_horas_empleado_input.getText());
                BigDecimal retencion_fuente = new BigDecimal(retencion_fuente_empleado_input.getText());
                Empleado empleado = new Empleado(codigo, nombres, horas_trabajadas, precio_horas, retencion_fuente);
                JOptionPane.showMessageDialog(null, empleado.toString());
            }
        });

        content_pane.add(codigo_empleado_field);
        content_pane.add(nombres_empleado_field);
        content_pane.add(horas_trabajadas_empleado_field);
        content_pane.add(precio_horas_empleado_field);
        content_pane.add(retencion_fuente_empleado_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}
```

## Cap 3. Numeral 19

```java
package entregable_tres;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        JFrame frame = new JFrame("Triangulo equilatero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 100);

        GridLayout layout = new GridLayout(2, 1);
        GridLayout layout_field = new GridLayout(1, 2);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);

        JPanel lado_field = new JPanel(layout_field);
        JLabel lado_label = new JLabel("Lado del triangulo equilatero");
        JTextField lado_input = new JTextField(126);

        lado_field.add(lado_label);
        lado_field.add(lado_input);

        JButton subir_formulario_button = new JButton("Calcular perímetro, altura y el área del triángulo");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double lado = Double.parseDouble(lado_input.getText());
                TrianguloEquilatero triangulo = new TrianguloEquilatero(lado);
                JOptionPane.showMessageDialog(null, String.format(
                        "El perimetro del triangulo es: %f\nEl area del triangulo es: %f\nLa altura del triangulo es: %f",
                        triangulo.calcular_perimetro(), triangulo.calcular_area(), triangulo.calcular_altura()));
            }
        });

        content_pane.add(lado_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}
```

## Cap 4. Numeral 7

```java
package entregable_tres;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ejercicio_tres {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cual es mayor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 150);

        GridLayout layout = new GridLayout(3, 1);
        GridLayout layout_field = new GridLayout(1, 2);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);

        JPanel a_field = new JPanel(layout_field);
        JLabel a_label = new JLabel("Valor de A");
        JTextField a_input = new JTextField(126);

        a_field.add(a_label);
        a_field.add(a_input);

        JPanel b_field = new JPanel(layout_field);
        JLabel b_label = new JLabel("Valor de B");
        JTextField b_input = new JTextField(126);

        b_field.add(b_label);
        b_field.add(b_input);

        JButton subir_formulario_button = new JButton("Cual es mayor?");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = Double.parseDouble(a_input.getText());
                double b = Double.parseDouble(b_input.getText());
                JOptionPane.showMessageDialog(null, a < b ? "B es mayor que A" : "A es mayor que B");
            }
        });

        content_pane.add(a_field);
        content_pane.add(b_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}
```

## Cap 4. Numeral 10

```java
package entregable_tres;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        return patrimonio.compareTo(Estudiante.umbral) > 0 && estrato > 3
                ? new BigDecimal("50000").add(patrimonio.multiply(new BigDecimal("0.03")))
                : new BigDecimal("50000");
    }
}

public class ejercicio_cuatro {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calcular matricula");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 200);

        GridLayout layout = new GridLayout(5, 1);
        GridLayout layout_field = new GridLayout(1, 2);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);

        JPanel numero_inscripcion_field = new JPanel(layout_field);
        JLabel numero_inscripcion_label = new JLabel("Numero de inscripcion");
        JTextField numero_inscripcion_input = new JTextField(126);

        numero_inscripcion_field.add(numero_inscripcion_label);
        numero_inscripcion_field.add(numero_inscripcion_input);

        JPanel nombres_field = new JPanel(layout_field);
        JLabel nombres_label = new JLabel("Nombres");
        JTextField nombres_input = new JTextField(126);

        nombres_field.add(nombres_label);
        nombres_field.add(nombres_input);

        JPanel patrimonio_field = new JPanel(layout_field);
        JLabel patrimonio_label = new JLabel("Patrimonio");
        JTextField patrimonio_input = new JTextField(126);

        patrimonio_field.add(patrimonio_label);
        patrimonio_field.add(patrimonio_input);

        JPanel estrato_field = new JPanel(layout_field);
        JLabel estrato_label = new JLabel("Estrato");
        JTextField estrato_input = new JTextField(126);

        estrato_field.add(estrato_label);
        estrato_field.add(estrato_input);

        JButton subir_formulario_button = new JButton("Calcular matricula");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero_inscripcion = Integer.parseInt(numero_inscripcion_input.getText());
                String[] nombres = nombres_input.getText().split(" ");
                BigDecimal patrimonio = new BigDecimal(patrimonio_input.getText());
                int estrato = Integer.parseInt(estrato_input.getText());
                Estudiante estudiante = new Estudiante(numero_inscripcion, nombres, patrimonio, estrato);
                JOptionPane.showMessageDialog(null, 
                    String.format("El estudiante con numero de inscripcion %s y nombre %s debe pagar $%s en matricula\n", estudiante.numero_inscripcion, String.join(" ", estudiante.nombres), estudiante.calcular_pago_matricula().toString())
                );
            }
        });

        content_pane.add(numero_inscripcion_field);
        content_pane.add(nombres_field);
        content_pane.add(patrimonio_field);
        content_pane.add(estrato_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}
```

## Cap 4. Numeral 22

```java
package entregable_tres;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ejercicio_cinco {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calcular salario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 200);

        GridLayout layout = new GridLayout(4, 1);
        GridLayout layout_field = new GridLayout(1, 2);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);


        JPanel nombres_field = new JPanel(layout_field);
        JLabel nombres_label = new JLabel("Nombres del empleado");
        JTextField nombres_input = new JTextField(126);

        nombres_field.add(nombres_label);
        nombres_field.add(nombres_input);

        JPanel salario_field = new JPanel(layout_field);
        JLabel salario_label = new JLabel("Salario por hora del empleado");
        JTextField salario_input = new JTextField(126);

        salario_field.add(salario_label);
        salario_field.add(salario_input);

        JPanel horas_field = new JPanel(layout_field);
        JLabel horas_label = new JLabel("Horas trabajadas por el empleado");
        JTextField horas_input = new JTextField(126);

        horas_field.add(horas_label);
        horas_field.add(horas_input);

        JButton subir_formulario_button = new JButton("Calcular salario");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombres_input.getText();
                BigDecimal salario_hora = new BigDecimal(salario_input.getText());
                BigDecimal horas = new BigDecimal(horas_input.getText());
                JOptionPane.showMessageDialog(null, 
                    String.format(salario_hora.multiply(horas).compareTo(BigDecimal.valueOf(450000)) > 0 ? "Nombre del empleado: %s. Salario del empleado: %s\n" : "Nombre del empleado: %s\n", nombre, salario_hora.multiply(horas).toString())
                );
            }
        });

        content_pane.add(nombres_field);
        content_pane.add(salario_field);
        content_pane.add(horas_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}
```

## Cap 4. Numeral 23

```java
package entregable_tres;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ejercicio_seis {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Resolver ecuaciones de segundo grado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 200);

        GridLayout layout = new GridLayout(4, 1);
        GridLayout layout_field = new GridLayout(1, 2);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);


        JPanel a_field = new JPanel(layout_field);
        JLabel a_label = new JLabel("Valor de A");
        JTextField a_input = new JTextField(126);

        a_field.add(a_label);
        a_field.add(a_input);

        JPanel b_field = new JPanel(layout_field);
        JLabel b_label = new JLabel("Valor de B");
        JTextField b_input = new JTextField(126);

        b_field.add(b_label);
        b_field.add(b_input);

        JPanel c_field = new JPanel(layout_field);
        JLabel c_label = new JLabel("Valor de C");
        JTextField c_input = new JTextField(126);

        c_field.add(c_label);
        c_field.add(c_input);

        JButton subir_formulario_button = new JButton("Resolver ecuacion");
        subir_formulario_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = Double.parseDouble(a_input.getText());
                double b = Double.parseDouble(b_input.getText());
                double c = Double.parseDouble(c_input.getText());
                double discriminante = Math.pow(b, 2) - 4 * a * c;

                String output = "";
                if (discriminante < 0) {
                    output = String.format("La ecuacion %.2fx^2+%.2fx+%.2f = 0 no tiene soluciones", a, b, c);
                } else {
                    double solucion_1 = (-1 * b + Math.sqrt(discriminante)) / (2 * a);
                    double solucion_2 = (-1 * b - Math.sqrt(discriminante)) / (2 * a);
                    output = String.format("Las soluciones de la ecuacion %.2fx^2+%.2fx+%.2f = 0 son x=%.2f, x=%.2f", a, b, c, solucion_1, solucion_2);
                }
                JOptionPane.showMessageDialog(null, output);
            }
        });

        content_pane.add(a_field);
        content_pane.add(b_field);
        content_pane.add(c_field);
        content_pane.add(subir_formulario_button);
        frame.setVisible(true);
    }
}
```

# Punto 2

## Codigo

```java
package entregable_tres;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

enum TipoTriangulo {
    ISOSCELES("isosceles"),
    ESCALENO("escaleno"),
    EQUILATERO("equilatero");

    public String nombre_tipo;

    private TipoTriangulo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }
}

interface Figura {
    public double calcular_area();
    public double calcular_perimetro();
}

class Circulo implements Figura {
    public double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double calcular_area() {
        return Math.PI * Math.pow(radio, 2);
    }

    public double calcular_perimetro() {
        return 2 * Math.PI * radio;
    }
}

class TrianguloRectangulo implements Figura {
    public double base;
    public double altura;

    public TrianguloRectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcular_area() {
        return base * altura / 2;
    }

    public double calcular_perimetro() {
        return base + altura + this.calcular_hipotenusa();
    }

    public TipoTriangulo determinar_tipo() {
        return base == altura ? (altura == this.calcular_hipotenusa() ? TipoTriangulo.EQUILATERO : TipoTriangulo.ISOSCELES) : TipoTriangulo.ESCALENO;
    }

   public double calcular_hipotenusa() {
        return Math.sqrt(Math.pow(base, 2) + Math.pow(altura, 2));
   }
}

class Rectangulo implements Figura {
    protected double base;
    protected double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcular_area() {
        return this.base * this.altura;
    }

    public double calcular_perimetro() {
        return this.base * 2 + this.altura * 2;
    }

    // Se opto por usar getters y setters para delegar la definicion
    // de los valores de base y altura, de esta manera podemos definir cuadrado
    // a partir de rectangulo
    // Getters y setters
    public double get_base() {
        return base;
    }
    public void set_base(double base) {
        this.base = base;
    }
    public double get_altura() {
        return altura;
    }
    public void set_altura(double altura) {
        this.altura = altura;
    }
}

class Cuadrado extends Rectangulo {
    private double lado;

    public Cuadrado(double lado) {
        super(lado, lado);
        this.lado = lado;
    }

    // Getters y setters
    public double get_lado() {
        return lado;
    }
    public void set_lado(double lado) {
        this.altura = lado;
        this.base = lado;
        this.lado = lado;
    }
}

public class ejercicio_siete {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora formas geometricas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 200);

        GridLayout layout = new GridLayout(1, 4);
        GridLayout layout_field = new GridLayout(5, 1);
        Container content_pane = frame.getContentPane();
        content_pane.setLayout(layout);

        JPanel circulo_field = new JPanel(layout_field);
        JLabel radio_circulo_label = new JLabel("Radio del circulo");
        JTextField radio_circulo_input = new JTextField(126);
        JButton circulo_button = new JButton("Calcular area y perimetro del circulo");
        circulo_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double radio = Double.parseDouble(radio_circulo_input.getText());
                Circulo circulo = new Circulo(radio);
                JOptionPane.showMessageDialog(null, String.format("El área del círculo es = %.2f\nEl perímetro del círculo es = %.2f", circulo.calcular_area(), circulo.calcular_perimetro()));
            }
        });
        circulo_field.add(radio_circulo_label);
        circulo_field.add(radio_circulo_input);
        circulo_field.add(circulo_button);

        JPanel cuadrado_field = new JPanel(layout_field);
        JLabel lado_cuadrado_label = new JLabel("Lado del cuadrado");
        JTextField lado_cuadrado_input = new JTextField(126);
        JButton cuadrado_button = new JButton("Calcular area y perimetro del cuadrado");
        cuadrado_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double lado = Double.parseDouble(lado_cuadrado_input.getText());
                Cuadrado cuadrado = new Cuadrado(lado);
                JOptionPane.showMessageDialog(null, String.format("El área del cuadrado es = %.2f\nEl perímetro del cuadrado es = %.2f", cuadrado.calcular_area(), cuadrado.calcular_perimetro()));
            }
        });
        cuadrado_field.add(lado_cuadrado_label);
        cuadrado_field.add(lado_cuadrado_input);
        cuadrado_field.add(cuadrado_button);

        JPanel triangulo_field = new JPanel(layout_field);
        JLabel altura_triangulo_label = new JLabel("Altura del triangulo rectangulo");
        JTextField altura_triangulo_input = new JTextField(126);
        JLabel base_triangulo_label = new JLabel("Base del triangulo rectangulo");
        JTextField base_triangulo_input = new JTextField(126);
        JButton triangulo_button = new JButton("Calcular area y perimetro del triangulo rectangulo");
        triangulo_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double altura = Double.parseDouble(altura_triangulo_input.getText());
                double base = Double.parseDouble(base_triangulo_input.getText());
                TrianguloRectangulo triangulo = new TrianguloRectangulo(base, altura);
                JOptionPane.showMessageDialog(null, String.format("El área del triangulo rectangulo es = %.2f\nEl perímetro del triangulo rectangulo es = %.2f\nEl triangulo rectangulo es de tipo = %s", triangulo.calcular_area(), triangulo.calcular_perimetro(), triangulo.determinar_tipo().nombre_tipo));
            }
        });
        triangulo_field.add(base_triangulo_label);
        triangulo_field.add(base_triangulo_input);
        triangulo_field.add(altura_triangulo_label);
        triangulo_field.add(altura_triangulo_input);
        triangulo_field.add(triangulo_button);

        JPanel rectangulo_field = new JPanel(layout_field);
        JLabel altura_rectangulo_label = new JLabel("Altura del rectangulo");
        JTextField altura_rectangulo_input = new JTextField(126);
        JLabel base_rectangulo_label = new JLabel("Base del rectangulo");
        JTextField base_rectangulo_input = new JTextField(126);
        JButton rectangulo_button = new JButton("Calcular area y perimetro del rectangulo");
        rectangulo_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double altura = Double.parseDouble(altura_rectangulo_input.getText());
                double base = Double.parseDouble(base_rectangulo_input.getText());
                Rectangulo rectangulo = new Rectangulo(base, altura);
                JOptionPane.showMessageDialog(null, String.format("El área del rectangulo es = %.2f\nEl perímetro del rectangulo es = %.2f", rectangulo.calcular_area(), rectangulo.calcular_perimetro()));
            }
        });
        rectangulo_field.add(base_rectangulo_label);
        rectangulo_field.add(base_rectangulo_input);
        rectangulo_field.add(altura_rectangulo_label);
        rectangulo_field.add(altura_rectangulo_input);
        rectangulo_field.add(rectangulo_button);

        content_pane.add(circulo_field);
        content_pane.add(cuadrado_field);
        content_pane.add(triangulo_field);
        content_pane.add(rectangulo_field);
        frame.setVisible(true);
    }
}
```

## Diagrama

![[diagrama.PNG]]