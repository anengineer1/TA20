package gui_elements;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyMainWindow extends JFrame {
	private JPanel content_pane;
	private JLabel hello_world;

	public MyMainWindow() {
		initialize();
	}

	private void initialize() {
		this.setTitle("TA20_01");
		this.setBounds(100, 100, 510, 230);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fillFrame();

		this.setVisible(true);

	}

	private void fillFrame() {
		this.content_pane = new JPanel();
		this.content_pane.setLayout(new BoxLayout(content_pane, BoxLayout.Y_AXIS));
		this.setContentPane(this.content_pane);

		this.hello_world = new JLabel("Hola mundo");
		this.hello_world.setAlignmentX(CENTER_ALIGNMENT);
		this.hello_world.setAlignmentY(CENTER_ALIGNMENT);
		this.content_pane.add(this.hello_world);

	}
}
