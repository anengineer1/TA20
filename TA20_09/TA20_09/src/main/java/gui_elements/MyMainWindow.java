package gui_elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class MyMainWindow extends JFrame {
	private final int ROWS = 4;
	private final int COLS = 4;

	private Color selected_color_1;
	private Color selected_color_2;

	private int[] selected_position1;
	private int[] selected_position2;

	private Timer timer;

	private JPanel content_pane;
	private JToggleButton[][] toggle_buttons;
	private ArrayList<Color> mycolors;
	private JPanel toggle_button_panel;

	private ItemListener action_button_pressed;

	public MyMainWindow() {
		this.selected_color_1 = Color.GRAY;
		this.selected_color_2 = Color.DARK_GRAY;

		this.selected_position1 = new int[2];
		this.selected_position1[0] = -1;
		this.selected_position1[1] = -1;

		this.selected_position2 = new int[2];
		this.selected_position2[0] = -1;
		this.selected_position2[1] = -1;

		// Init attributes
		this.toggle_buttons = new JToggleButton[this.ROWS][this.COLS];

		// choosing 8 colors
		this.mycolors = new ArrayList<Color>();
		this.mycolors.add(Color.BLUE);
		this.mycolors.add(Color.BLACK);
		this.mycolors.add(Color.YELLOW);
		this.mycolors.add(Color.GREEN);
		this.mycolors.add(Color.CYAN);
		this.mycolors.add(Color.RED);
		this.mycolors.add(Color.PINK);
		this.mycolors.add(Color.ORANGE);

		// in order to have color for all the togglebutton
		this.mycolors.addAll(this.mycolors);

		// Now we are gonna shufle the list to make it random
		Collections.shuffle(this.mycolors);

		// Init GUI
		initialize();
	}

	private void initialize() {
		this.setTitle("Juego de formar parejas");
		this.setBounds(100, 100, 530, 580);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fillFrame();

		this.setVisible(true);

	}

	private void fillFrame() {
		this.content_pane = new JPanel(new FlowLayout());
		this.content_pane.setBounds(0, 0, this.getWidth(), this.getHeight());

		this.toggle_button_panel = new JPanel(new GridLayout(ROWS, COLS));
		for (int i = 0; i < toggle_buttons.length; i++) {
			for (int j = 0; j < toggle_buttons[i].length; j++) {
				this.toggle_buttons[i][j] = new JToggleButton();
				this.toggle_buttons[i][j].setContentAreaFilled(false);
				this.toggle_buttons[i][j].setOpaque(true);
				this.toggle_button_panel.add(this.toggle_buttons[i][j]);
				this.toggle_buttons[i][j].addActionListener(this.createActionListener(i, j));
			}
		}
		// Add the panels to the frame
		this.add(this.content_pane, BorderLayout.NORTH);
		this.add(this.toggle_button_panel, BorderLayout.CENTER);

	}

	private ActionListener createActionListener(int i, int j) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (selected_color_1 == Color.GRAY) {
					selected_position1[0] = i;
					selected_position1[1] = j;
					selected_color_1 = mycolors.get(i * COLS + j);
					toggle_buttons[i][j].setBackground(selected_color_1);
				} else if (selected_color_2 == Color.DARK_GRAY) {
					selected_position2[0] = i;
					selected_position2[1] = j;
					selected_color_2 = mycolors.get(i * COLS + j);
					toggle_buttons[i][j].setBackground(selected_color_2);
//					try {
//						TimeUnit.SECONDS.sleep(1);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					if (selected_color_1 == selected_color_2) {
						toggle_buttons[selected_position1[0]][selected_position1[1]].setVisible(false);
						toggle_buttons[selected_position2[0]][selected_position2[1]].setVisible(false);
					} else {
						timer = new Timer(1000, null);
						timer.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								toggle_buttons[selected_position1[0]][selected_position1[1]].setSelected(false);
								toggle_buttons[selected_position2[0]][selected_position2[1]].setSelected(false);
								toggle_buttons[selected_position1[0]][selected_position1[1]].setBackground(null);
								toggle_buttons[selected_position2[0]][selected_position2[1]].setBackground(null);
								timer.stop();
							}
						});
						timer.start();
					}
					selected_color_1 = Color.GRAY;
					selected_color_2 = Color.DARK_GRAY;
				} else {

				}

			}
		};
		return listener;
	}

}