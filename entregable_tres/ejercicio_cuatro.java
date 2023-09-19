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
