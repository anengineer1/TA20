package gui_elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyMainWindow extends JFrame {
	private JPanel content_pane;
	private JLabel label_altura;
	private JLabel label_peso;
	private JLabel label_IMC;
	private JTextField textfield_altura_metros;
	private JTextField textfield_peso_kg;
	private JTextField textfield_resultado;
	private JButton button_IMC;
	private ActionListener button_action_IMC;
	private FocusListener textfield_listener_altura;
	private FocusListener textfield_listener_peso;
	
	public MyMainWindow() {
		// Init GUI
		initialize();
	}

	private void initialize() {
		this.setTitle("Indice masa corporal");
		this.setBounds(100, 100, 530, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addButtonsActions();
		this.addListenersToForceDoubles();
		this.fillFrame();

		this.setVisible(true);

	}

	private void fillFrame() {
		this.content_pane = new JPanel();
		this.content_pane.setLayout(null);
		this.content_pane.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.setContentPane(this.content_pane);

		this.label_altura = new JLabel("Altura (metros)");
		this.label_altura.setBounds(10, 10, 110, 30);
		this.content_pane.add(this.label_altura);

		this.textfield_altura_metros = new JTextField();
		this.textfield_altura_metros.setBounds(120, 10, 120, 30);
		this.content_pane.add(this.textfield_altura_metros);

		this.label_peso = new JLabel("Peso (kg)");
		this.label_peso.setBounds(260, 10, 70, 30);
		this.content_pane.add(this.label_peso);

		this.textfield_peso_kg = new JTextField();
		this.textfield_peso_kg.setBounds(330, 10, 120, 30);
		this.content_pane.add(this.textfield_peso_kg);

		this.button_IMC = new JButton("IMC");
		this.button_IMC.setBounds(10, 70, 80, 30);
		this.button_IMC.addActionListener(this.button_action_IMC);
		this.content_pane.add(this.button_IMC);

		this.label_IMC = new JLabel("IMC");
		this.label_IMC.setBounds(100, 70, 30, 30);
		this.content_pane.add(this.label_IMC);

		this.textfield_resultado = new JTextField();
		this.textfield_resultado.setBounds(130, 70, 120, 30);
		this.textfield_resultado.setEditable(false);
		this.content_pane.add(this.textfield_resultado);

		// Want to make sure the user writes a double
		this.textfield_altura_metros.addFocusListener(this.textfield_listener_altura);
		this.textfield_peso_kg.addFocusListener(this.textfield_listener_peso);
	}

	private void calculateAndShowIMC() {
		this.textfield_resultado.setText(Double.toString(Double.valueOf(this.textfield_peso_kg.getText())
				/ Math.pow(Double.valueOf(this.textfield_altura_metros.getText()), 2)));
	}
	
	private void addListenersToForceDoubles() {
		this.textfield_listener_altura = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String input = textfield_altura_metros.getText();
				Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
				if (!pattern.matcher(input).matches()) {
					textfield_altura_metros.setText("");
					textfield_resultado.setText("Valor inválido");
				}
			}
		};
		
		this.textfield_listener_peso = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String input = textfield_altura_metros.getText();
				Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
				if (!pattern.matcher(input).matches()) {
					textfield_peso_kg.setText("");
					textfield_resultado.setText("Valor inválido");
				}
			}
		};
	}

	private void addButtonsActions() {
		this.button_action_IMC = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					calculateAndShowIMC();
				} catch (NumberFormatException ex) {
					textfield_altura_metros
							.dispatchEvent(new FocusEvent(textfield_altura_metros, FocusEvent.FOCUS_LOST));
					textfield_peso_kg.dispatchEvent(new FocusEvent(textfield_peso_kg, FocusEvent.FOCUS_LOST));
				}
			}
		};
	}
}