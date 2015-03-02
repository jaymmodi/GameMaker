package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import model.SpriteModel;
import model.TimerObservable;
import view.GameMakerView;
import event.AssociateEvent;
import event.Events;
import event.KeyboardPress;

@SuppressWarnings("serial")
public class GameMakerController {

	private GameMakerView theView;
	private SaveableObject saveableObject;
	private SaveGameMakerState saveGameMakerState;
	private LoadGameMakerState loadGameMakerState;
	private SpriteModel sprite;
	private HashMap<String, ArrayList<String>> eventActionDetails;
	private TimerObservable timerObs;
	private Command theCommand;
	private int flag;
	private AssociateEvent associateEvent;
	private ArrayList<String> spriteNames = new ArrayList<String>();
	private HashMap<Integer, String> spriteImagePathMap;
	private HashMap<Integer, String> backgroudImagePathMap;

	public GameMakerController(GameMakerView theView) {

		this.saveableObject = new SaveableObject();
		this.saveGameMakerState = new SaveGameMakerState();
		this.loadGameMakerState = new LoadGameMakerState();

		this.theView = theView;
		this.theView.addAssociateListener(new AssociateListener());
		this.theView.addCreateListener(new CreateSpriteListener());
		this.theView.addDeleteListener(new DeleteSpriteListener());
		this.theView.addEventsListener(new EventsListener());
		this.theView.addSaveSpriteListener(new SaveSpriteListener());
		this.theView.addLoadSpriteListener(new LoadSpriteLister());

		this.theView.addClockCheckBoxListener(new ClockCheckBoxListener());
		this.theView.addBackgroundsListener(new BackgroundsListener());

		this.theView.addPlayGameListener(new PlayGameListener());
		this.theView.getGameBoardPanel().addKeyListener(new KeyBoardListener());

		this.saveGameMakerState = new SaveGameMakerState();
		this.saveableObject = new SaveableObject();
		this.spriteImagePathMap = new HashMap<Integer, String>();
		this.backgroudImagePathMap = new HashMap<Integer, String>();
		this.populateBackgroundImageMap(this.backgroudImagePathMap);
		this.populateSpriteImageMap(this.spriteImagePathMap);

		this.saveGameMakerState.setFileName("save.txt");

		this.loadGameMakerState.setFileName("save.txt");
	}

	public void populateSpriteImageMap(HashMap<Integer, String> imagePathMap) {
		imagePathMap.put(0, "img/fire_ball.gif");
		imagePathMap.put(1, "img/paddle.gif");
		imagePathMap.put(2, "img/tile.gif");
	}

	public void populateBackgroundImageMap(
			HashMap<Integer, String> backgroudImagePathMap) {

		backgroudImagePathMap.put(0, "img/default_background.png");
		backgroudImagePathMap.put(1, "img/background1.png");
		backgroudImagePathMap.put(2, "img/background2.png");

	}

	public void changeGamePanelViewForWinLost(int gameFlag) {
		if ((gameFlag == 2) || (gameFlag == 3)) {
			theView.getGameBoardPanel().repaint();
		}
	}

	class CreateSpriteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (theView.getSpriteName().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Please enter sprite name!!");
				else if (spriteNames.contains(theView.getSpriteName())
						&& !spriteNames.isEmpty())
					JOptionPane.showMessageDialog(
							null,
							"Sprite name already taken!! Try "
									+ theView.getSpriteName() + "1 or "
									+ theView.getSpriteName() + "a");
				else if (theView.getSpriteXPosition().isEmpty()
						|| !Pattern.matches("^[0-9]*$",
								theView.getSpriteXPosition()))
					JOptionPane.showMessageDialog(null,
							"Enter valid value for x-position of sprite!!");
				else if (theView.getSpriteYPosition().isEmpty()
						|| !Pattern.matches("^[0-9]*$",
								theView.getSpriteYPosition()))
					JOptionPane.showMessageDialog(null,
							"Enter valid value for y-position of sprite!!");
				else {
					spriteNames.add(theView.getSpriteName());
					eventActionDetails = new HashMap<String, ArrayList<String>>();
					sprite = new SpriteModel(theView.getSpriteName(),
							Integer.parseInt(theView.getSpriteXPosition()),
							Integer.parseInt(theView.getSpriteYPosition()),
							theView.getImageSelected(),
							theView.getImageSelectedIndex(),
							theView.isDisplayFlagView(), eventActionDetails);

					sprite.setRectangleTest(Integer.parseInt(theView.getSpriteXPosition()),
									Integer.parseInt(theView.getSpriteYPosition()),theView.getImageSelected().getWidth(null),theView.getImageSelected().getHeight(null));
					theView.getGameBoardPanel().addSprite(sprite);

					theView.getGameBoardPanel().repaint();
					theView.getAssociateButton().setEnabled(true);
					theView.getEventsList().setEnabled(true);
				}
			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
			}

		}
	}

	class AssociateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String eventName;
			ArrayList<String> actionList = new ArrayList<String>();
			eventActionDetails = sprite.getEventActionDetails();
			try {
				if (theView.getEventsList().getSelectedItem().toString()
						.equals("None"))
					JOptionPane.showMessageDialog(null,
							"Select event from the event list!!");
				else if (theView.getActionList().isSelectionEmpty())
					JOptionPane.showMessageDialog(null,
							"Select action from the action list!!");
				if (flag == 0) {
					theView.getActivityTextArea().append(
							theView.getSpriteName());
					flag = 1;
				}
				if (theView.getEventSubTypeList().isSelectionEmpty()) {
					theView.getActivityTextArea().append(
							"\n" + theView.getEventSelected() + "->"
									+ theView.getActionSelected());
					eventName = theView.getEventSelected();
				} else {
					theView.getActivityTextArea().append(
							"\n" + theView.getEventSelected() + "->"
									+ theView.getEventSubTypeSelected() + "->"
									+ theView.getActionSelected());

					eventName = theView.getEventSelected() + "-"
							+ theView.getEventSubTypeSelected();
				}

				if (eventActionDetails.containsKey(eventName))
					actionList = eventActionDetails.get(eventName);

				actionList.add(theView.getActionSelected());
				eventActionDetails.put(eventName, actionList);

				sprite.setEventActionDetails(eventActionDetails);

				for (Events event : Events.values()) {
					if (event.name().equals(
							theView.getEventSelected().toUpperCase())) {
						associateEvent = new AssociateEvent(event.getValue());
						associateEvent.attachEvent(sprite);
					}
				}

				if (!(theView.getEventSubTypeList().isSelectionEmpty())) {
					for (SpriteModel sprite1 : theView.getGameBoardPanel()
							.getSpriteList()) {
						if (sprite1.getName().equalsIgnoreCase(
								theView.getEventSubTypeSelected()))
							associateEvent.attachEvent(sprite1);
					}
				}

				theView.getPlayGameButton().setEnabled(true);

				theView.getEventsList().setSelectedIndex(0);
				theView.getEventSubTypeList().setListData(new Object[0]);
				theView.getActionList().setListData(new Object[0]);
			} catch (Exception ex) {

			}
		}

	}

	class DeleteSpriteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				theView.getGameBoardPanel().removeSprite(
						theView.getSpriteName());
				theView.clearUserInput();
				spriteNames.remove(theView.getSpriteName());
				theView.getGameBoardPanel().repaint();
			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
			}
		}
	}

	class ClockCheckBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			try {
				JCheckBox cb = (JCheckBox) event.getSource();
				if (cb.isSelected()) {
					saveableObject.setTimerCheckIndicator(true);
					theView.getGameBoardPanel().setPreferredSize(
							new Dimension(470, 890));
					theView.getGamePanel().add(theView.getGameBoardPanel(),
							BorderLayout.NORTH);
					theView.getGamePanel().add(theView.getClockPanel(),
							BorderLayout.SOUTH);
					theView.getGamePanel().validate();
				} else {
					saveableObject.setTimerCheckIndicator(false);
					theView.getGamePanel().remove(theView.getClockPanel());
					theView.getGameBoardPanel().setPreferredSize(
							new Dimension(470, 940));
					theView.getGamePanel().add(theView.getGameBoardPanel());
					theView.validate();
				}
			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
			}
		}
	}

	class BackgroundsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// 0 : Default Background
			// 1 : Background Setting 1
			// 2 : Background Setting 2
			try {
				if (theView.getBackgroundSelected().equals("Background 1")) {
					theView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader()
									.getResource("img/background1.png")));
					saveableObject.setBackgroundImageIndicator(1);
				} else if (theView.getBackgroundSelected().equals(
						"Background 2")) {
					theView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader()
									.getResource("img/background2.png")));
					saveableObject.setBackgroundImageIndicator(2);
				} else {
					theView.getGameBoardPanel()
							.setBackgroundImage(
									new ImageIcon(
											getClass()
													.getClassLoader()
													.getResource(
															"img/default_background.png")));
					saveableObject.setBackgroundImageIndicator(0);
				}
				theView.getGamePanel().add(theView.getGameBoardPanel());
				theView.getGameBoardPanel().repaint();
				theView.validate();

			} catch (Exception ex) {

			}

		}

	}

	class EventsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				String[] collisionEventSubType = spriteNames
						.toArray(new String[spriteNames.size()]);

				String[] collisionAction = new String[] { "Disappear",
						"ChangeDirection", "Sound" };
				String[] keyboardAction = new String[] { "LeftMove",
						"RightMove" };
				String[] timeChangeAction = new String[] { "Move" };

				if (theView.getEventSelected() == "Collision") {
					theView.getEventSubTypeList().setListData(
							collisionEventSubType);
					theView.getActionList().setListData(collisionAction);
				} else if (theView.getEventSelected() == "KeyboardPress") {
					theView.getEventSubTypeList().setListData(new Object[0]);
					theView.getActionList().setListData(keyboardAction);
				} else if (theView.getEventSelected() == "TimeChange") {
					theView.getEventSubTypeList().setListData(new Object[0]);
					theView.getActionList().setListData(timeChangeAction);
				} else {
					theView.getEventSubTypeList().setListData(new Object[0]);
					theView.getActionList().setListData(new Object[0]);
				}
			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
			}

		}

	}

	class SaveSpriteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				if (theView.getGameBoardPanel().getSpriteList().isEmpty())
					JOptionPane.showMessageDialog(null, "Nothing to save!!");
				else {
					saveableObject.setSpriteList(theView.getGameBoardPanel()
							.getSpriteList());
					saveGameMakerState.setSaveableObjects(saveableObject);

					saveGameMakerState.save();
					theView.clearUserInput();
					flag = 0;
				}

			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
			}

		}

	}
	
	class LoadSpriteLister implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				SaveableObject loadableObject = new SaveableObject();
				loadableObject = loadGameMakerState.load();
				ArrayList<SpriteModel> loadedSpriteList = loadableObject
						.getSpriteList();

				spriteNames = new ArrayList<String>();

				// load the images back in to the loaded spritelist
				loadedSpriteList = processAfterLoad(loadedSpriteList);
				theView.getGameBoardPanel().setSpriteList(loadedSpriteList);
				for (SpriteModel sprite : loadedSpriteList) {
					addSpriteAsEventListener(sprite, loadedSpriteList);
					spriteNames.add(sprite.getName());
				}

				setGameMakerTimerCheck(loadableObject);
				int backgroundGameIndex = loadableObject
						.getBackgroundImageIndicator();
				setGamePlayerBackgroundImage(backgroundGameIndex);
				setGameMakerBackgroundText(backgroundGameIndex);

				theView.getGameBoardPanel().repaint();
				theView.getPlayGameButton().setEnabled(true);

			} catch (Exception ex) {
				ex.printStackTrace();

			}

		}

	}

	class PlayGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			timerObs = new TimerObservable();
			theView.getGameBoardPanel().requestFocusInWindow();

			StartCommand startCmd;	

			timerObs.addObserver((Observer) theView.getGameBoardPanel());
			timerObs.addObserver((Observer) theView.getClockPanel());


			startCmd = new StartCommand(timerObs);
			setTheCommand(startCmd);
			press();
			theView.getPlayGameButton().setEnabled(false);
			theView.getLoadSpriteButton().setEnabled(false);
			

		}
	}

	class KeyBoardListener extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				KeyboardPress.getInstance().associateAction("Left Key");
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				KeyboardPress.getInstance().associateAction("Right Key");
		}
	}

	private void setGameMakerTimerCheck(SaveableObject loadableObject) {

		if (loadableObject.isTimerCheckIndicator())
			theView.getClockCheckBox().doClick();

	}

	private void setGamePlayerBackgroundImage(int backGroundImageIndex) {

		String getImagePath = backgroudImagePathMap.get(backGroundImageIndex);
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(
				getImagePath));
		theView.getGameBoardPanel().setBackgroundImage(icon);
	}

	//add the events list back after loading
	public void addSpriteAsEventListener(SpriteModel sprite,
			ArrayList<SpriteModel> spriteList) {
		HashMap<String, ArrayList<String>> hashMap = sprite
				.getEventActionDetails();

		for (Events event : Events.values()) {
			for (String eventName : hashMap.keySet()) {
				String[] eventNameSplit = eventName.split("-");
				if (event.name().equals(eventNameSplit[0].toUpperCase())) {
					associateEvent = new AssociateEvent(event.getValue());
					associateEvent.attachEvent(sprite);
				}

				if (eventNameSplit.length > 1) {
					for (SpriteModel sprite2 : spriteList) {
						if (eventNameSplit[1].equalsIgnoreCase(sprite2
								.getName()))
							associateEvent.attachEvent(sprite2);
					}
				}
			}
		}
	}

	//copy back the images to the loaded sprites
	public ArrayList<SpriteModel> processAfterLoad(
			ArrayList<SpriteModel> loadedSpriteList) {

		for (SpriteModel sprite : loadedSpriteList) {
			int imageIndex = sprite.getImagePathIndicator();
			String imagePath = spriteImagePathMap.get(imageIndex);
			ImageIcon icon = new ImageIcon(getClass().getClassLoader()
					.getResource(imagePath));
			sprite.setImage(icon.getImage());
		}

		return loadedSpriteList;
	}
	
	//set the Background text after Load
	public void setGameMakerBackgroundText(int backgroundGameIndex) {
		theView.getBackgroundList().setSelectedIndex(backgroundGameIndex);
		
	}

	public Command getTheCommand() {
		return theCommand;
	}

	public void setTheCommand(Command theCommand) {
		this.theCommand = theCommand;
	}

	public void press() {
		theCommand.execute();
	}
}
