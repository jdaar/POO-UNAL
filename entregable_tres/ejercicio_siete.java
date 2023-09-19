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
