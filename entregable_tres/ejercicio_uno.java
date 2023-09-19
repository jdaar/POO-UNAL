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
