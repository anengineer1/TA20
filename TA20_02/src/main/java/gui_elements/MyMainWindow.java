package gui_elements;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyMainWindow extends JFrame {
	private JPanel content_pane;
	private JLabel label_button_pressed;
	private JButton button_1;
	private JButton button_2;

	public MyMainWindow() {
		initialize();
	}

	private void initialize() {
		this.setTitle("Ventana con interacción");
		this.setBounds(100, 100, 430, 140);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fillFrame();

		this.setVisible(true);

	}

	private void fillFrame() {
		this.content_pane = new JPanel();
		this.content_pane.setLayout(null);
		this.content_pane.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.setContentPane(this.content_pane);

		this.label_button_pressed = new JLabel("Has pulsado:");
		this.label_button_pressed.setBounds(20, 20, 180, 30);
		this.content_pane.add(this.label_button_pressed);

		this.button_1 = new JButton("Botón 1");
		this.button_1.setBounds(220, 20, 90, 30);
		this.button_1.addActionListener(e -> this.actionButton1());
		this.content_pane.add(this.button_1);

		this.button_2 = new JButton("Botón 2");
		this.button_2.setBounds(320, 20, 90, 30);
		this.button_2.addActionListener(e -> this.actionButton2());
		this.content_pane.add(this.button_2);

	}

	private void actionButton1() {
		this.label_button_pressed.setText("Has pulsado: botón 1");
	}

	private void actionButton2() {
		this.label_button_pressed.setText("Has pulsado: botón 2");
	}
}
