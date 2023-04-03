package gui_elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.EuroOrPta;

public class MyMainWindow extends JFrame {
	final private double EUROS_TO_PESETAS = 166.386;
	final private String TEXT_EUROS_A_PTAS = "Euros a ptas";
	final private String TEXT_PTAS_A_EUROS = "Ptas a euros";

	private JPanel content_pane;
	private JLabel label_altura;
	private JLabel label_peso;
	private JTextField textfield_money;
	private JTextField textfield_money_converted;
	private JButton button_make_conversion;
	private JButton button_change_operation;
	private ActionListener button_action_toggle;
	private ActionListener button_action_make_operation;
	private EuroOrPta operation_to_do;

	public MyMainWindow() {
		// init vars
		this.operation_to_do = EuroOrPta.TO_EURO;

		// Init GUI
		initialize();
	}

	private void initialize() {
		this.setTitle("Convertidor de monedas");
		this.setBounds(100, 100, 530, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initActions();
		this.fillFrame();

		this.setVisible(true);

	}

	private void fillFrame() {
		this.content_pane = new JPanel();
		this.content_pane.setLayout(null);
		this.content_pane.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.setContentPane(this.content_pane);

		this.label_altura = new JLabel("Cantidad a convertir");
		this.label_altura.setBounds(10, 10, 150, 30);
		this.content_pane.add(this.label_altura);

		this.textfield_money = new JTextField();
		this.textfield_money.setBounds(160, 10, 120, 30);
		this.content_pane.add(this.textfield_money);

		this.label_peso = new JLabel("Resultado");
		this.label_peso.setBounds(300, 10, 80, 30);
		this.content_pane.add(this.label_peso);

		this.textfield_money_converted = new JTextField();
		this.textfield_money_converted.setBounds(385, 10, 120, 30);
		this.textfield_money_converted.setEditable(false);
		this.content_pane.add(this.textfield_money_converted);

		this.button_make_conversion = new JButton(this.TEXT_EUROS_A_PTAS);
		this.button_make_conversion.setBounds(10, 70, 150, 30);
		this.button_make_conversion.addActionListener(this.button_action_make_operation);
		this.content_pane.add(this.button_make_conversion);

		this.button_change_operation = new JButton("Cambiar");
		this.button_change_operation.setBounds(190, 70, 120, 30);
		this.button_change_operation.addActionListener(this.button_action_toggle);
		this.content_pane.add(this.button_change_operation);

	}

	private void toggleOperationToDo() {
		if (this.operation_to_do == EuroOrPta.TO_EURO) {
			this.operation_to_do = EuroOrPta.TO_PTAS;
			this.button_make_conversion.setText(TEXT_EUROS_A_PTAS);
		} else {
			this.operation_to_do = EuroOrPta.TO_EURO;
			this.button_make_conversion.setText(TEXT_PTAS_A_EUROS);
		}
	}

	private void calculateAndDisplayConversion() {
		Double result = Double.valueOf(this.textfield_money.getText());

		if (this.operation_to_do == EuroOrPta.TO_PTAS) {
			result *= this.EUROS_TO_PESETAS;
		} else {
			result /= this.EUROS_TO_PESETAS;
		}

		this.textfield_money_converted.setText(Double.toString(result));
	}

	private void initActions() {
		this.button_action_make_operation = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculateAndDisplayConversion();
			}
		};

		this.button_action_toggle = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOperationToDo();
			}
		};
	}

}