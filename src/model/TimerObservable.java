package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.Timer;

import org.apache.logging.log4j.LogManager;

import controller.Utility;
import event.Collision;
import event.KeyboardPress;
import event.TimeChange;
import observer.GameBoardPanel;

public class TimerObservable extends Observable {

	private Timer timer;
	private int currentSecond, currentMinute;
	private String timeForDisplayClock;
	private int timerTracker = 0;
	private Object clockTime;

	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(TimerObservable.class);
	
	public TimerObservable() {
		log.info("TimerObservable : TimerObservable() : Enter");
		this.timer = new Timer(5, null);
		log.info("TimerObservable : TimerObservable() : Exit");
	}

	public void computeAndNotify() {
		log.info("TimerObservable : computeAndNotify : Enter");
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				clockTime = updateDisplayClock();
				if(Utility.getInstance().getGameFlag() == 1)
				{
					TimeChange.getInstance().associateAction(null);
					Collision.getInstance().associateAction(null);
				}
				else
					timer.stop();
				setChanged();
				notifyObservers(clockTime);
			}
		});
		timer.setDelay(5);
		timer.restart();
		log.info("TimerObservable : computeAndNotify : Exit");
	}

	/*
	 * Function to increment the minute value of the clock.
	 */
	private void refresh() {
		currentMinute++;
		currentSecond = 0;
	}

	public void start() {
		currentMinute++;
	}

	/*
	 * Function to reset the clock
	 */
	public void reset() {
		currentMinute = -1;
		currentSecond = 0;
		timeForDisplayClock = "00:00";
		timerTracker = 0;
	}

	/*
	 * Function to update the display of the clock in the panel according to
	 * timer tick.
	 * 
	 * @return returns the string to be set in the time label of the panel.
	 */
	public String updateDisplayClock() {
		timerTracker++;
		if (timerTracker >= 150) {
			if (currentSecond == 60) {
				refresh();
			}
			timeForDisplayClock = String.format("%02d:%02d", currentMinute,
					currentSecond);
			currentSecond++;
			timerTracker = 0;
		}
		return timeForDisplayClock;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
}
