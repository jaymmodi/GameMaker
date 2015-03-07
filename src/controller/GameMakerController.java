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
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;

import main.GameMaker;
import model.SpriteModel;
import model.TimerObservable;
import observer.GameBoardPanel;
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

	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(GameMakerController.class);
	
	public GameMakerController(GameMakerView theView) {
        log.info("GameMakerController : Enter");
		this.saveableObject = new SaveableObject();
		this.saveGameMakerState = new SaveGameMakerState();
		this.loadGameMakerState = new LoadGameMakerState();

		this.theView = theView;
		this.theView.addAssociateListener(new AssociateListener());
		this.theView.addCreateListener(new CreateSpriteListener(theView, theView.getGameBoardPanel()));
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
		
		log.info("GameMakerController : Exit");
	}

	public void populateSpriteImageMap(HashMap<Integer, String> imagePathMap) {
		log.info("GameMakerController : populateSpriteImageMap : Enter");
		imagePathMap.put(0, "img/fire_ball.gif");
		imagePathMap.put(1, "img/paddle.gif");
		imagePathMap.put(2, "img/tile.gif");
		imagePathMap.put(3, "img/frog.png");
		log.info("GameMakerController : populateSpriteImageMap : Exit");
	}
	
//	public void populateImages(){
//		File dir = new File(".");
//		gameList = new ArrayList<File>(Arrays.asList(dir.listFiles(new FilenameFilter() {
//			@Override
//			public boolean accept(File dir, String name) {
//				return name.endsWith(".ser"); // or something else
//			}
//		}
//	}
//	

	public void populateBackgroundImageMap(HashMap<Integer, String> backgroudImagePathMap) {
		log.info("GameMakerController : populateBackgroundImageMap : Enter");
		backgroudImagePathMap.put(0, "img/default_background.png");
		backgroudImagePathMap.put(1, "img/background1.png");
		backgroudImagePathMap.put(2, "img/background2.png");
		backgroudImagePathMap.put(3, "img/Frogger.png");
		log.info("GameMakerController : populateBackgroundImageMap : Exit");

	}

	public void changeGamePanelViewForWinLost(int gameFlag) {
		log.info("GameMakerController : changeGamePanelViewForWinLost : Enter");
		log.debug("GameMakerController : changeGamePanelViewForWinLost : gameFlag - "+gameFlag);
		if ((gameFlag == 2) || (gameFlag == 3)) {
			theView.getGameBoardPanel().repaint();
		}
		log.info("GameMakerController : changeGamePanelViewForWinLost : Exit");
	}

//	class CreateSpriteListener implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//		}
//	}

	class AssociateListener implements ActionListener {
		final org.apache.logging.log4j.Logger logAL = LogManager
				.getLogger(AssociateListener.class);
		@Override
		public void actionPerformed(ActionEvent e) {
			logAL.info("AssociateListener : actionPerformed : Enter");
			String eventName;
			ArrayList<String> actionList = new ArrayList<String>();
			eventActionDetails = sprite.getEventActionDetails();
			try {
				if (theView.getEventsList().getSelectedItem().toString().equals("None"))
					JOptionPane.showMessageDialog(null, "Select event from the event list!!");
				else if (theView.getActionList().isSelectionEmpty())
					JOptionPane.showMessageDialog(null, "Select action from the action list!!");
				if (flag == 0) {
					theView.getActivityTextArea().append(theView.getSpriteName());
					flag = 1;
				}
				if (theView.getEventSubTypeList().isSelectionEmpty()) {
					theView.getActivityTextArea().append("\n" + theView.getEventSelected() + "->" + theView.getActionSelected());
					eventName = theView.getEventSelected();
				} else {
					theView.getActivityTextArea().append(
							"\n" + theView.getEventSelected() + "->" + theView.getEventSubTypeSelected() + "->"
									+ theView.getActionSelected());

					eventName = theView.getEventSelected() + "-" + theView.getEventSubTypeSelected();
				}

				logAL.debug("AssociateListener : actionPerformed : eventName - "+eventName);
				if (eventActionDetails.containsKey(eventName))
					actionList = eventActionDetails.get(eventName);

				actionList.add(theView.getActionSelected());
				eventActionDetails.put(eventName, actionList);

				sprite.setEventActionDetails(eventActionDetails);

				for (Events event : Events.values()) {
					if (event.name().equals(theView.getEventSelected().toUpperCase())) {
						associateEvent = new AssociateEvent(event.getValue());
						associateEvent.attachEvent(sprite);
					}
				}

				if (!(theView.getEventSubTypeList().isSelectionEmpty())) {
					for (SpriteModel sprite1 : theView.getGameBoardPanel().getSpriteList()) {
						if (sprite1.getName().equalsIgnoreCase(theView.getEventSubTypeSelected()))
							associateEvent.attachEvent(sprite1);
					}
				}

				theView.getPlayGameButton().setEnabled(true);

				theView.getEventsList().setSelectedIndex(0);
				theView.getEventSubTypeList().setListData(new Object[0]);
				theView.getActionList().setListData(new Object[0]);
			} catch (Exception ex) {

			}
			logAL.info("AssociateListener : actionPerformed : Enter");
		}
		
	}

	class DeleteSpriteListener implements ActionListener {
		final org.apache.logging.log4j.Logger logDSL = LogManager
				.getLogger(DeleteSpriteListener.class);
		@Override
		public void actionPerformed(ActionEvent e) {
         logDSL.info("DeleteSpriteListener : actionPerformed : Enter");
			try {
				logDSL.info("DeleteSpriteListener : actionPerformed : spriteName being deleted - "+theView.getSpriteName());
				theView.getGameBoardPanel().removeSprite(theView.getSpriteName());
				theView.clearUserInput();
				spriteNames.remove(theView.getSpriteName());
				theView.getGameBoardPanel().repaint();
			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
			}
			logDSL.info("DeleteSpriteListener : actionPerformed : Exit");	
		}
	}

	class ClockCheckBoxListener implements ActionListener {
		final org.apache.logging.log4j.Logger logCCL = LogManager
				.getLogger(ClockCheckBoxListener.class);
		public void actionPerformed(ActionEvent event) {
            logCCL.info("ClockCheckBoxListener : actionPerformed : Enter");
			try {
				JCheckBox cb = (JCheckBox) event.getSource();
				if (cb.isSelected()) {
					saveableObject.setTimerCheckIndicator(true);
					theView.getGameBoardPanel().setPreferredSize(new Dimension(470, 890));
					theView.getGamePanel().add(theView.getGameBoardPanel(), BorderLayout.NORTH);
					theView.getGamePanel().add(theView.getClockPanel(), BorderLayout.SOUTH);
					theView.getGamePanel().validate();
				} else {
					saveableObject.setTimerCheckIndicator(false);
					theView.getGamePanel().remove(theView.getClockPanel());
					theView.getGameBoardPanel().setPreferredSize(new Dimension(470, 940));
					theView.getGamePanel().add(theView.getGameBoardPanel());
					theView.validate();
				}
			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
			}
			logCCL.info("ClockCheckBoxListener : actionPerformed : Exit");
		}
	}

	class BackgroundsListener implements ActionListener {
		final org.apache.logging.log4j.Logger logBL = LogManager
				.getLogger(BackgroundsListener.class);
		@Override
		public void actionPerformed(ActionEvent e) {
          logBL.info("BackgroundsListener : actionPerformed : Enter");
			// 0 : Default Background
			// 1 : Background Setting 1
			// 2 : Background Setting 2
			try {
				if (theView.getBackgroundSelected().equals("Background 1")) {
					theView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/background1.png")));
					saveableObject.setBackgroundImageIndicator(1);
				} else if (theView.getBackgroundSelected().equals("Background 2")) {
					theView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/background2.png")));
					saveableObject.setBackgroundImageIndicator(2);
				} else if(theView.getBackgroundSelected().equals("Frogger")) {
					theView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/Frogger.png")));
					saveableObject.setBackgroundImageIndicator(3);
				}
				else  {
					theView.getGameBoardPanel().setBackgroundImage(
							new ImageIcon(getClass().getClassLoader().getResource("img/default_background.png")));
					saveableObject.setBackgroundImageIndicator(0);
				}
				theView.getGamePanel().add(theView.getGameBoardPanel());
				theView.getGameBoardPanel().repaint();
				theView.validate();

			} catch (Exception ex) {

			}
			logBL.info("BackgroundsListener : actionPerformed : Exit");
		}

	}

	class EventsListener implements ActionListener {
		final org.apache.logging.log4j.Logger logEL = LogManager
				.getLogger(EventsListener.class);
		@Override
		public void actionPerformed(ActionEvent e) {
			logEL.info("EventsListener : actionPerformed : Enter");
			logEL.info("EventsListener : actionPerformed : event selected - "+theView.getEventSelected());
			try {
                
				String[] collisionEventSubType = spriteNames.toArray(new String[spriteNames.size()]);

				String[] collisionAction = new String[] { "Disappear", "ChangeDirection", "Sound", "GameWin", "GameLoss" };
				String[] keyboardAction = new String[] { "LeftMove", "RightMove", "UpMove", "DownMove", "Fire"};
				String[] timeChangeAction = new String[] { "Move", "AutoMoveLeft", "AutoMoveRight",
						 "AutoMoveUp" , "AutoMoveDown"};

				if (theView.getEventSelected() == "Collision") {
					theView.getEventSubTypeList().setListData(collisionEventSubType);
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
			logEL.info("EventsListener : actionPerformed : Exit");
		}

	}

	class SaveSpriteListener implements ActionListener {
		final org.apache.logging.log4j.Logger logSSL = LogManager
				.getLogger(SaveSpriteListener.class);
		@Override
		public void actionPerformed(ActionEvent e) {
			logSSL.info("SaveSpriteListener : actionPerformed : Enter");
			try {

				if (theView.getGameBoardPanel().getSpriteList().isEmpty())
					JOptionPane.showMessageDialog(null, "Nothing to save!!");
				else {
					saveableObject.setSpriteList(theView.getGameBoardPanel().getSpriteList());
					saveGameMakerState.setSaveableObjects(saveableObject);

					saveGameMakerState.save();
					theView.clearUserInput();
					flag = 0;
				}

			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
			}
			logSSL.info("SaveSpriteListener : actionPerformed : Exit");
		}

	}

	class LoadSpriteLister implements ActionListener {
		final org.apache.logging.log4j.Logger logLSL = LogManager
				.getLogger(LoadSpriteLister.class);
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
				theView.getGameBoardPanel().setSpriteList(loadedSpriteList);
				for (SpriteModel sprite : loadedSpriteList) {
					addSpriteAsEventListener(sprite, loadedSpriteList);
					spriteNames.add(sprite.getName());
				}

				setGameMakerTimerCheck(loadableObject);
				int backgroundGameIndex = loadableObject.getBackgroundImageIndicator();
				setGamePlayerBackgroundImage(backgroundGameIndex);
				setGameMakerBackgroundText(backgroundGameIndex);

				theView.getGameBoardPanel().repaint();
				theView.getPlayGameButton().setEnabled(true);

			} catch (Exception ex) {
				ex.printStackTrace();

			}
			logLSL.info("LoadSpriteLister : actionPerformed : Exit");
		}

	}

	class PlayGameListener implements ActionListener {
		final org.apache.logging.log4j.Logger logPGL = LogManager
				.getLogger(LoadSpriteLister.class);
		@Override
		public void actionPerformed(ActionEvent arg0) {
            logPGL.info("PlayGameListener : actionPerformed : Enter");
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
			logPGL.info("PlayGameListener : actionPerformed : Exit");
		}
	}

	class KeyBoardListener extends KeyAdapter {
		final org.apache.logging.log4j.Logger logKBL = LogManager
				.getLogger(LoadSpriteLister.class);
		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			logKBL.info("KeyBoardListener : keyPressed : Enter");
			logKBL.info("KeyBoardListener : keyPressed : keyCode - "+e.getKeyCode());
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
				if (theView.getSpriteName().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter sprite name!!");
				else if (spriteNames.contains(theView.getSpriteName()) && !spriteNames.isEmpty())
					JOptionPane.showMessageDialog(null, "Sprite name already taken!! Try " + theView.getSpriteName() + "1 or "
							+ theView.getSpriteName() + "a");
				else {
					spriteNames.add(theView.getSpriteName());
					eventActionDetails = new HashMap<String, ArrayList<String>>();
					sprite = new SpriteModel(theView.getSpriteName(), theView.getSpriteXPosition(), theView.getSpriteYPosition(),
							theView.getSelectedImage(), theView.getImageSelectedIndex(), theView.isDisplayFlagView(),
							eventActionDetails);
					// System.out.println(theView.getSelectedImage().getSource());

					sprite.setRectangleTest(theView.getSpriteXPosition(), theView.getSpriteYPosition(), theView
							.getImageSelected().getWidth(null), theView.getImageSelected().getHeight(null));
					theView.getGameBoardPanel().addSprite(sprite);

					theView.getGameBoardPanel().repaint();
					theView.getAssociateButton().setEnabled(true);
					theView.getEventsList().setEnabled(true);
				}
			} catch (Exception ex) {
				theView.displayErrorMessage(ex.toString());
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
			theView.getClockCheckBox().doClick();

	}

	private void setGamePlayerBackgroundImage(int backGroundImageIndex) {

		String getImagePath = backgroudImagePathMap.get(backGroundImageIndex);
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(getImagePath));
		theView.getGameBoardPanel().setBackgroundImage(icon);
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
