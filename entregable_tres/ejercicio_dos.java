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
