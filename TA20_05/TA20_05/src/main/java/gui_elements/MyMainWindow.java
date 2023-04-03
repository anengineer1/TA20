package gui_elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyMainWindow extends JFrame {
	private JPanel content_pane;
	private JButton button_for_events;
	private JTextArea textarea_list_events;

	public MyMainWindow() {
		// Init GUI
		initialize();
	}

	private void initialize() {
		this.setTitle("Ventana");
		this.setBounds(100, 100, 530, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fillFrame();

		this.setVisible(true);

	}

	private void fillFrame() {
		this.content_pane = new JPanel();
		this.content_pane.setLayout(new BoxLayout(this.content_pane, BoxLayout.Y_AXIS));
		this.setContentPane(this.content_pane);

		this.button_for_events = new JButton("Imprimir");
		this.button_for_events.setAlignmentX(CENTER_ALIGNMENT);
		this.content_pane.add(this.button_for_events);

		this.textarea_list_events = new JTextArea();
		this.textarea_list_events.setAlignmentX(CENTER_ALIGNMENT);
		this.content_pane.add(this.textarea_list_events);

		// this section is o handle button events
		button_for_events.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textarea_list_events.append("Click realizado\n");
			}
		});

		button_for_events.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				textarea_list_events.append("Mouse encima del botón\n");
			}

			public void mouseExited(MouseEvent e) {
				textarea_list_events.append("Mouse fuera del botón\n");
			}

			public void mousePressed(MouseEvent e) {
				textarea_list_events.append("Botón apredado\n");
			}

			public void mouseReleased(MouseEvent e) {
				textarea_list_events.append("Botón soltado\n");
			}
		});

	}

}