package gui_elements;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyMainWindow extends JFrame {
	private JPanel content_pane;
	private JLabel label_events;
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
		this.content_pane.setLayout(null);
		this.content_pane.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.setContentPane(this.content_pane);

		this.label_events = new JLabel("Eventos:");
		this.label_events.setBounds(10, this.getHeight() / 2, 80, 30);
		this.content_pane.add(this.label_events);

		this.textarea_list_events = new JTextArea();
		this.textarea_list_events.setBounds(100, 10, this.getWidth() - 120, this.getHeight() - 60);
		this.content_pane.add(this.textarea_list_events);

		this.textarea_list_events.append("Ventana creada\n");

		// this section handles the events of the window
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				textarea_list_events.append("Redimensionado\n");
			}

			public void componentMoved(ComponentEvent e) {
				textarea_list_events.append("Movido\n");
			}
		});

		this.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == MAXIMIZED_BOTH) {
					textarea_list_events.append("Ventana maximizada\n");
				} else if (e.getNewState() == NORMAL) {
					textarea_list_events.append("Ventana restaurada\n");
				} else if (e.getNewState() == ICONIFIED) {
					textarea_list_events.append("Ventana minimizada\n");
				}
			}
		});

		this.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				textarea_list_events.append("Ventana con foco\n");
			}

			public void windowLostFocus(WindowEvent e) {
				textarea_list_events.append("Ventana sin foco\n");
			}
		});

	}

}