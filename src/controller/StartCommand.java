package controller;

import org.apache.logging.log4j.LogManager;

import model.TimerObservable;

/**
 * StartCommand Class
 * 
 * This class acts as the command object for the start button on the UI. It
 * initializes the receiver or listener of its command event.
 * 
 * execute() - It executes the start game command.
 * 
 */

public class StartCommand implements Command {
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(StartCommand.class);
	private Object currReceiver;
	private TimerObservable timerObsv;

	public StartCommand(Object currReceiver) {
		log.info("StartCommand : Enter");
		if (currReceiver instanceof TimerObservable){
			this.currReceiver = currReceiver;
		}
		log.info("StartCommand : Exit");
	}

	/*
	 * @return returns the receiver for this command.
	 */
	public Object getCurrReceiver() {
		return currReceiver;
	}

	/*
	 * @param currReceiver - Acts as the receiver for this command object
	 */
	public void setCurrReceiver(Object currReceiver) {
		this.currReceiver = currReceiver;
	}

	/*
	 * Method to execute the start game command
	 */
	@Override
	public void execute() {
		Utility.getInstance().setGameFlag(1);
		((TimerObservable) currReceiver).computeAndNotify();
	}

}
