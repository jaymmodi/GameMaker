package observer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GameClockPanel class
 * 
 * Shows the clock in the game frame
 * 
 * @author Team 2
 *
 */
public class GameClockPanel extends JPanel implements Observer {

	private JLabel clockLabel = new JLabel("00:00");

	public GameClockPanel() {
		this.setPreferredSize(new Dimension(470, 50));
		this.add(clockLabel);
		this.setBackground(Color.BLACK);
		clockLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		clockLabel.setForeground(Color.white);
		clockLabel.setText("00:00");
	}

	//Updates the Clock based on the timer of the observable
	@Override
	public void update(Observable observable, Object clockTime) {
		if(clockTime != null)
			clockLabel.setText(clockTime.toString());
	}
}
