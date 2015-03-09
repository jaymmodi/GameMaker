package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import model.SpriteModel;
import model.TimerObservable;
import observer.GameBoardPanel;

import org.apache.logging.log4j.LogManager;

import view.GameMakerView;
import event.AssociateEvent;
import event.Events;
import event.KeyboardPress;

public class GameMakerController {

	private GameMakerView gameMakerView;
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

	static org.apache.logging.log4j.Logger log = LogManager.getLogger(GameMakerController.class);

	public GameMakerController(GameMakerView theView) {
		log.info("GameMakerController : Enter");
		this.saveableObject = new SaveableObject();
		this.saveGameMakerState = new SaveGameMakerState();
		this.loadGameMakerState = new LoadGameMakerState();

		this.gameMakerView = theView;
		this.gameMakerView.addAssociateListener(new AssociateListener());
		this.gameMakerView.addCreateListener(new CreateSpriteListener(theView, theView.getGameBoardPanel()));
		this.gameMakerView.addDeleteListener(new DeleteSpriteListener());
		this.gameMakerView.addEventsListener(new EventsListener());
		this.gameMakerView.addSaveSpriteListener(new SaveSpriteListener());
		this.gameMakerView.addLoadSpriteListener(new LoadSpriteLister());

		this.gameMakerView.addClockCheckBoxListener(new ClockCheckBoxListener());
		this.gameMakerView.addBackgroundsListener(new BackgroundsListener());

		this.gameMakerView.addPlayGameListener(new PlayGameListener());
		this.gameMakerView.getGameBoardPanel().addKeyListener(new KeyBoardListener());

		this.saveGameMakerState = new SaveGameMakerState();
		this.saveableObject = new SaveableObject();
		this.spriteImagePathMap = new HashMap<Integer, String>();
		this.backgroudImagePathMap = new HashMap<Integer, String>();
		this.populateBackgroundImageMap(this.backgroudImagePathMap);
		this.populateSpriteImageMap(this.spriteImagePathMap);

		this.saveGameMakerState.setFileName("save.txt");

		this.loadGameMakerState.setFileName("save.txt");

		log.info("GameMakerController : Exit");
	}

	/*
	 * This method is to hard code every background image on view. This is again used while saving the instance of the game.
	 *
	 */
	public void populateSpriteImageMap(HashMap<Integer, String> imagePathMap) {
		log.info("GameMakerController : populateSpriteImageMap : Enter");
		imagePathMap.put(0, "img/fire_ball.gif");
		imagePathMap.put(1, "img/paddle.gif");
		imagePathMap.put(2, "img/tile.gif");
		imagePathMap.put(3, "img/frog.png");
		log.info("GameMakerController : populateSpriteImageMap : Exit");
	}

	/*
	 * This method is to hard code every background image on view. This is again used while saving the instance of the game.
	 *
	 */
	public void populateBackgroundImageMap(HashMap<Integer, String> backgroudImagePathMap) {
		log.info("GameMakerController : populateBackgroundImageMap : Enter");
		backgroudImagePathMap.put(0, "img/default_background.png");
		backgroudImagePathMap.put(1, "img/background1.png");
		backgroudImagePathMap.put(2, "img/background2.png");
		backgroudImagePathMap.put(3, "img/Frogger.png");
		log.info("GameMakerController : populateBackgroundImageMap : Exit");

	}

	/*
	 * This method is to change the state of the game when the game is reached game win or game loose state.
	 *
	 */
	public void changeGamePanelViewForWinLost(int gameFlag) {
		log.info("GameMakerController : changeGamePanelViewForWinLost : Enter");
		log.debug("GameMakerController : changeGamePanelViewForWinLost : gameFlag - " + gameFlag);
		if ((gameFlag == 2) || (gameFlag == 3)) {
			gameMakerView.getGameBoardPanel().repaint();
		}
		log.info("GameMakerController : changeGamePanelViewForWinLost : Exit");
	}


	class AssociateListener implements ActionListener {
		final org.apache.logging.log4j.Logger logAL = LogManager.getLogger(AssociateListener.class);

		@SuppressWarnings({ "unchecked"})
		@Override
		public void actionPerformed(ActionEvent e) {
			logAL.info("AssociateListener : actionPerformed : Enter");
			final String eventName;
			ArrayList<String> actionList = new ArrayList<String>();
			eventActionDetails = sprite.getEventActionDetails();
			try {
				if (gameMakerView.getEventsList().getSelectedItem().toString().equals("None"))
					JOptionPane.showMessageDialog(null, "Select event from the event list!!");
				else if (gameMakerView.getActionList().isSelectionEmpty())
					JOptionPane.showMessageDialog(null, "Select action from the action list!!");
				if (flag == 0) {
					gameMakerView.getActivityTextArea().append(gameMakerView.getSpriteName());
					flag = 1;
				}
				if (gameMakerView.getEventSubTypeList().isSelectionEmpty()) {
					gameMakerView.getActivityTextArea().append("\n" + gameMakerView.getEventSelected() + "->" + gameMakerView.getActionSelected());
					eventName = gameMakerView.getEventSelected();
				} else {
					gameMakerView.getActivityTextArea().append(
							"\n" + gameMakerView.getEventSelected() + "->" + gameMakerView.getEventSubTypeSelected() + "->"
									+ gameMakerView.getActionSelected());

					eventName = gameMakerView.getEventSelected() + "-" + gameMakerView.getEventSubTypeSelected();
				}

				logAL.debug("AssociateListener : actionPerformed : eventName - " + eventName);
				if (eventActionDetails.containsKey(eventName))
					actionList = eventActionDetails.get(eventName);

				actionList.add(gameMakerView.getActionSelected());
				eventActionDetails.put(eventName, actionList);

				sprite.setEventActionDetails(eventActionDetails);

				for (Events event : Events.values()) {
					if (event.name().equals(gameMakerView.getEventSelected().toUpperCase())) {
						associateEvent = new AssociateEvent(event.getValue());
						associateEvent.attachEvent(sprite);
					}
				}

				if (!(gameMakerView.getEventSubTypeList().isSelectionEmpty())) {
					for (SpriteModel sprite1 : gameMakerView.getGameBoardPanel().getSpriteList()) {
						if (sprite1.getName().equalsIgnoreCase(gameMakerView.getEventSubTypeSelected()))
							associateEvent.attachEvent(sprite1);
					}
				}

				gameMakerView.getPlayGameButton().setEnabled(true);

				gameMakerView.getEventsList().setSelectedIndex(0);
				gameMakerView.getEventSubTypeList().setListData(new Object[0]);
				gameMakerView.getActionList().setListData(new Object[0]);
			} catch (Exception ex) {

			}
			logAL.info("AssociateListener : actionPerformed : Enter");
		}

	}

	/*
	 * Delete Button Listener
	 *
	 */
	class DeleteSpriteListener implements ActionListener {
		final org.apache.logging.log4j.Logger logDSL = LogManager.getLogger(DeleteSpriteListener.class);

		@Override
		public void actionPerformed(ActionEvent e) {
			logDSL.info("DeleteSpriteListener : actionPerformed : Enter");
			try {
				logDSL.info("DeleteSpriteListener : actionPerformed : spriteName being deleted - " + gameMakerView.getSpriteName());
				gameMakerView.getGameBoardPanel().removeSprite(gameMakerView.getSpriteName());
				gameMakerView.clearUserInput();
				spriteNames.remove(gameMakerView.getSpriteName());
				gameMakerView.getGameBoardPanel().repaint();
			} catch (Exception ex) {
				gameMakerView.displayErrorMessage(ex.toString());
			}
			logDSL.info("DeleteSpriteListener : actionPerformed : Exit");
		}
	}

	/*
	 * ClockCheckBox Button Listener
	 *
	 */
	class ClockCheckBoxListener implements ActionListener {
		final org.apache.logging.log4j.Logger logCCL = LogManager.getLogger(ClockCheckBoxListener.class);

		public void actionPerformed(ActionEvent event) {
			logCCL.info("ClockCheckBoxListener : actionPerformed : Enter");
			try {
				JCheckBox cb = (JCheckBox) event.getSource();
				if (cb.isSelected()) {
					saveableObject.setTimerCheckIndicator(true);
					gameMakerView.getGameBoardPanel().setPreferredSize(new Dimension(470, 890));
					gameMakerView.getGamePanel().add(gameMakerView.getGameBoardPanel(), BorderLayout.NORTH);
					gameMakerView.getGamePanel().add(gameMakerView.getClockPanel(), BorderLayout.SOUTH);
					gameMakerView.getGamePanel().validate();
				} else {
					saveableObject.setTimerCheckIndicator(false);
					gameMakerView.getGamePanel().remove(gameMakerView.getClockPanel());
					gameMakerView.getGameBoardPanel().setPreferredSize(new Dimension(470, 940));
					gameMakerView.getGamePanel().add(gameMakerView.getGameBoardPanel());
					gameMakerView.validate();
				}
			} catch (Exception ex) {
				gameMakerView.displayErrorMessage(ex.toString());
			}
			logCCL.info("ClockCheckBoxListener : actionPerformed : Exit");
		}
	}

	/*
	 * Background Button Listener
	 *
	 */
	class BackgroundsListener implements ActionListener {
		final org.apache.logging.log4j.Logger logBL = LogManager.getLogger(BackgroundsListener.class);

		@Override
		public void actionPerformed(ActionEvent e) {
			logBL.info("BackgroundsListener : actionPerformed : Enter");
			// 0 : Default Background
			// 1 : Background Setting 1
			// 2 : Background Setting 2
			try {
				if (gameMakerView.getBackgroundSelected().equals("Background 1")) {
					gameMakerView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/background1.png")));
					saveableObject.setBackgroundImageIndicator(1);
				} else if (gameMakerView.getBackgroundSelected().equals("Background 2")) {
					gameMakerView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/background2.png")));
					saveableObject.setBackgroundImageIndicator(2);
				} else if (gameMakerView.getBackgroundSelected().equals("Frogger")) {
					gameMakerView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/Frogger.png")));
					saveableObject.setBackgroundImageIndicator(3);
				} else if (gameMakerView.getBackgroundSelected().equals("Background 3")) {
					gameMakerView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/background3.png")));
					saveableObject.setBackgroundImageIndicator(3);
				} else {
					gameMakerView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/default_background.png")));
					saveableObject.setBackgroundImageIndicator(0);
				}
				gameMakerView.getGamePanel().add(gameMakerView.getGameBoardPanel());
				gameMakerView.getGameBoardPanel().repaint();
				gameMakerView.validate();

			} catch (Exception ex) {

			}
			logBL.info("BackgroundsListener : actionPerformed : Exit");
		}

	}

	/*
	 * Events Button Listener
	 *
	 */
	class EventsListener implements ActionListener {
		final org.apache.logging.log4j.Logger logEL = LogManager.getLogger(EventsListener.class);

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			logEL.info("EventsListener : actionPerformed : Enter");
			logEL.info("EventsListener : actionPerformed : event selected - " + gameMakerView.getEventSelected());
			try {

				String[] collisionEventSubType = spriteNames.toArray(new String[spriteNames.size()]);

				String[] collisionAction = new String[] { "Disappear", "ChangeDirection", "Sound", "GameWin", "GameLoss" };
				String[] keyboardAction = new String[] { "LeftMove", "RightMove", "UpMove", "DownMove", "Fire" };
				String[] timeChangeAction = new String[] { "Move", "AutoMoveLeft", "AutoMoveRight", "AutoMoveUp", "AutoMoveDown" };

				if (gameMakerView.getEventSelected() == "Collision") {
					gameMakerView.getEventSubTypeList().setListData(collisionEventSubType);
					gameMakerView.getActionList().setListData(collisionAction);
				} else if (gameMakerView.getEventSelected() == "KeyboardPress") {
					gameMakerView.getEventSubTypeList().setListData(new Object[0]);
					gameMakerView.getActionList().setListData(keyboardAction);
				} else if (gameMakerView.getEventSelected() == "TimeChange") {
					gameMakerView.getEventSubTypeList().setListData(new Object[0]);
					gameMakerView.getActionList().setListData(timeChangeAction);
				} else {
					gameMakerView.getEventSubTypeList().setListData(new Object[0]);
					gameMakerView.getActionList().setListData(new Object[0]);
				}
			} catch (Exception ex) {
				gameMakerView.displayErrorMessage(ex.toString());
			}
			logEL.info("EventsListener : actionPerformed : Exit");
		}

	}

	/*
	 * Save Button Listener
	 *
	 */
	class SaveSpriteListener implements ActionListener {
		final org.apache.logging.log4j.Logger logSSL = LogManager.getLogger(SaveSpriteListener.class);

		@Override
		public void actionPerformed(ActionEvent e) {
			logSSL.info("SaveSpriteListener : actionPerformed : Enter");
			try {

				if (gameMakerView.getGameBoardPanel().getSpriteList().isEmpty())
					JOptionPane.showMessageDialog(null, "Nothing to save!!");
				else {
					saveableObject.setSpriteList(gameMakerView.getGameBoardPanel().getSpriteList());
					saveGameMakerState.setSaveableObjects(saveableObject);

					saveGameMakerState.save();
					gameMakerView.clearUserInput();
					flag = 0;
				}

			} catch (Exception ex) {
				gameMakerView.displayErrorMessage(ex.toString());
			}
			logSSL.info("SaveSpriteListener : actionPerformed : Exit");
		}

	}

	/*
	 * Load Button Listener
	 *
	 */
	class LoadSpriteLister implements ActionListener {
		final org.apache.logging.log4j.Logger logLSL = LogManager.getLogger(LoadSpriteLister.class);

		@Override
		public void actionPerformed(ActionEvent e) {
			logLSL.info("LoadSpriteLister : actionPerformed : Enter");
			try {

				SaveableObject loadableObject = new SaveableObject();
				loadableObject = loadGameMakerState.load();
				ArrayList<SpriteModel> loadedSpriteList = loadableObject.getSpriteList();

				spriteNames = new ArrayList<String>();

				// load the images back in to the loaded spritelist
				loadedSpriteList = processAfterLoad(loadedSpriteList);
				gameMakerView.getGameBoardPanel().setSpriteList(loadedSpriteList);
				for (SpriteModel sprite : loadedSpriteList) {
					addSpriteAsEventListener(sprite, loadedSpriteList);
					spriteNames.add(sprite.getName());
				}

				setGameMakerTimerCheck(loadableObject);
				int backgroundGameIndex = loadableObject.getBackgroundImageIndicator();
				setGamePlayerBackgroundImage(backgroundGameIndex);
				setGameMakerBackgroundText(backgroundGameIndex);

				gameMakerView.getGameBoardPanel().repaint();
				gameMakerView.getPlayGameButton().setEnabled(true);

			} catch (Exception ex) {
				ex.printStackTrace();

			}
			logLSL.info("LoadSpriteLister : actionPerformed : Exit");
		}

	}

	/*
	 * Play Button Listener
	 *
	 */
	class PlayGameListener implements ActionListener {
		final org.apache.logging.log4j.Logger logPGL = LogManager.getLogger(LoadSpriteLister.class);

		@Override
		public void actionPerformed(ActionEvent arg0) {
			logPGL.info("PlayGameListener : actionPerformed : Enter");
			timerObs = new TimerObservable();
			gameMakerView.getGameBoardPanel().requestFocusInWindow();

			StartCommand startCmd;

			timerObs.addObserver((Observer) gameMakerView.getGameBoardPanel());
			timerObs.addObserver((Observer) gameMakerView.getClockPanel());

			startCmd = new StartCommand(timerObs);
			setTheCommand(startCmd);
			press();
			gameMakerView.getPlayGameButton().setEnabled(false);
			gameMakerView.getLoadSpriteButton().setEnabled(false);
			logPGL.info("PlayGameListener : actionPerformed : Exit");
		}
	}

	/*
	 * KeyBoard Button Listener
	 *
	 */
	class KeyBoardListener extends KeyAdapter {
		final org.apache.logging.log4j.Logger logKBL = LogManager.getLogger(LoadSpriteLister.class);

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			logKBL.info("KeyBoardListener : keyPressed : Enter");
			logKBL.info("KeyBoardListener : keyPressed : keyCode - " + e.getKeyCode());
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				KeyboardPress.getInstance().associateAction("Left Key");
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				KeyboardPress.getInstance().associateAction("Right Key");
			else if (e.getKeyCode() == KeyEvent.VK_UP)
				KeyboardPress.getInstance().associateAction("Up Key");
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				KeyboardPress.getInstance().associateAction("Down Key");
			else if (e.getKeyCode() == KeyEvent.VK_SPACE)
				KeyboardPress.getInstance().associateAction("Space Key");
			logKBL.info("KeyBoardListener : keyPressed : Exit");
		}
	}

	/*
	 * Create Button Listener
	 *
	 */
	class CreateSpriteListener extends DropTargetAdapter {
		private DropTarget dropTarget;
		private GameMakerView gameMakerView;
		private GameBoardPanel gameBoardPanel;

		// drop target listener for drag and drop functionality
		public CreateSpriteListener(GameMakerView gameMakerView, GameBoardPanel gameBoardPanel) {
			this.gameMakerView = gameMakerView;
			// this.gameData = gameData;
			this.gameBoardPanel = gameBoardPanel;

			setDropTarget(new DropTarget(gameBoardPanel, DnDConstants.ACTION_COPY, this, true, null));
		}

		@Override
		public void drop(DropTargetDropEvent event) {
			try {

				Transferable tr = event.getTransferable();

				ImageIcon icon = (ImageIcon) tr.getTransferData(GameMakerView.DATA_FLAVOUR);

				if (event.isDataFlavorSupported(GameMakerView.DATA_FLAVOUR)) {
					gameMakerView.setSelectedImage(icon.getImage());
					gameMakerView.setSpriteXPosition(event.getLocation().x);
					gameMakerView.setSpriteYPosition(event.getLocation().y);
					event.acceptDrop(DnDConstants.ACTION_COPY);
					event.dropComplete(true);
					gameMakerView.repaint();
					gameBoardPanel.repaint();

				}
				event.rejectDrop();
			} catch (Exception e) {
				e.printStackTrace();
				event.rejectDrop();
			}

			try {
				if (gameMakerView.getSpriteName().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter sprite name!!");
				else if (spriteNames.contains(gameMakerView.getSpriteName()) && !spriteNames.isEmpty())
					JOptionPane.showMessageDialog(null, "Sprite name already taken!! Try " + gameMakerView.getSpriteName() + "1 or "
							+ gameMakerView.getSpriteName() + "a");
				else {
					spriteNames.add(gameMakerView.getSpriteName());
					eventActionDetails = new HashMap<String, ArrayList<String>>();
					sprite = new SpriteModel(gameMakerView.getSpriteName(), gameMakerView.getSpriteXPosition(), gameMakerView.getSpriteYPosition(),
							gameMakerView.getSelectedImage(), gameMakerView.getImageSelectedIndex(), gameMakerView.isDisplayFlagView(),
							eventActionDetails);
					// System.out.println(theView.getSelectedImage().getSource());

					sprite.setRectangleTest(gameMakerView.getSpriteXPosition(), gameMakerView.getSpriteYPosition(), gameMakerView
							.getImageSelected().getWidth(null), gameMakerView.getImageSelected().getHeight(null));
					gameMakerView.getGameBoardPanel().addSprite(sprite);

					gameMakerView.getGameBoardPanel().repaint();
					gameMakerView.getAssociateButton().setEnabled(true);
					gameMakerView.getEventsList().setEnabled(true);
				}
			} catch (Exception ex) {
				gameMakerView.displayErrorMessage(ex.toString());
			}
		}

		public DropTarget getDropTarget() {
			return dropTarget;
		}

		public void setDropTarget(DropTarget dropTarget) {
			this.dropTarget = dropTarget;
		}
	}

	private void setGameMakerTimerCheck(SaveableObject loadableObject) {

		if (loadableObject.isTimerCheckIndicator())
			gameMakerView.getClockCheckBox().doClick();

	}

	private void setGamePlayerBackgroundImage(int backGroundImageIndex) {

		String getImagePath = backgroudImagePathMap.get(backGroundImageIndex);
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(getImagePath));
		gameMakerView.getGameBoardPanel().setBackgroundImage(icon);
	}

	// add the events list back after loading
	public void addSpriteAsEventListener(SpriteModel sprite, ArrayList<SpriteModel> spriteList) {
		HashMap<String, ArrayList<String>> hashMap = sprite.getEventActionDetails();

		for (Events event : Events.values()) {
			for (String eventName : hashMap.keySet()) {
				String[] eventNameSplit = eventName.split("-");
				if (event.name().equals(eventNameSplit[0].toUpperCase())) {
					associateEvent = new AssociateEvent(event.getValue());
					associateEvent.attachEvent(sprite);
				}

				if (eventNameSplit.length > 1) {
					for (SpriteModel sprite2 : spriteList) {
						if (eventNameSplit[1].equalsIgnoreCase(sprite2.getName()))
							associateEvent.attachEvent(sprite2);
					}
				}
			}
		}
	}

	// copy back the images to the loaded sprites
	public ArrayList<SpriteModel> processAfterLoad(ArrayList<SpriteModel> loadedSpriteList) {

		for (SpriteModel sprite : loadedSpriteList) {
			int imageIndex = sprite.getImagePathIndicator();
			String imagePath = spriteImagePathMap.get(imageIndex);
			ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));
			sprite.setImage(icon.getImage());
		}

		return loadedSpriteList;
	}

	// set the Background text after Load
	public void setGameMakerBackgroundText(int backgroundGameIndex) {
		gameMakerView.getBackgroundList().setSelectedIndex(backgroundGameIndex);

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
