package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.TransferHandler;

import main.Constants;
//import main.TimerObservable;
import observer.GameBoardPanel;
import observer.GameClockPanel;

import org.apache.logging.log4j.LogManager;

/**
 * GaeMakerView class
 * 
 * Class responsible for loading GameMaker UI
 * 
 * @author Team 2
 *
 */
public class GameMakerView extends JFrame {

	private JPanel userInputpanel = new JPanel();
	private JPanel spritePanel = new JPanel();
	private JPanel featurePanel = new JPanel();
	private JPanel eventPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private GameBoardPanel gameBoardPanel = new GameBoardPanel();
	private GameClockPanel clockPanel = new GameClockPanel();

	public static final DataFlavor DATA_FLAVOUR = new DataFlavor(ImageIcon.class, "Sprite");

	private Image selectedImage;

	private boolean displayFlagView;

	private Object[] imageStrings = new Object[] { new ImageIcon(getClass().getClassLoader().getResource("img/st2.png")),
			new ImageIcon(getClass().getClassLoader().getResource("img/car.png")),
			new ImageIcon(getClass().getClassLoader().getResource("img/truck.png")),
			new ImageIcon(getClass().getClassLoader().getResource("img/frog.png")),
			new ImageIcon(getClass().getClassLoader().getResource("img/fire_ball.gif")),
			new ImageIcon(getClass().getClassLoader().getResource("img/paddle.gif")),
			new ImageIcon(getClass().getClassLoader().getResource("img/monster1.png")),
			new ImageIcon(getClass().getClassLoader().getResource("img/monster2.png")),
			new ImageIcon(getClass().getClassLoader().getResource("img/win_game.png")),
			new ImageIcon(getClass().getClassLoader().getResource("img/tile.gif")) };

	private String[] eventStrings = new String[] { "None", "KeyboardPress", "TimeChange", "Collision" };

	private String[] backgroundImages = new String[] { "None", "Background 1", "Background 2", "Frogger", "Background 3" };

	public String[] getBackgroundImages() {
		return backgroundImages;
	}

	private JLabel spriteNameLabel = new JLabel("Sprite Name");
	private JTextField spriteName = new JTextField(10);

	private JLabel spriteImageLabel = new JLabel("Image");
	private JList imagesList = new JList(imageStrings);

	private int spriteXPosition;
	private int spriteYPosition;

	private JButton deleteSpriteButton = new JButton("Delete");
	private JButton saveSpriteButton = new JButton("Save");
	private JButton loadSpriteButton = new JButton("Load");

	private JButton playGameButton = new JButton("Play");

	private JLabel eventTypeLabel = new JLabel("Event Type");
	private JComboBox eventsList = new JComboBox(eventStrings);

	private JLabel eventSubTypeLabel = new JLabel("Event SubType");
	private JList eventSubTypeList = new JList();

	private JLabel soundSelectionLabel = new JLabel("Sound");
	private JList soundSelectionList = new JList();

	private JLabel backgroundSelectionLabel = new JLabel("Background");
	private JComboBox backgroundList = new JComboBox(backgroundImages);

	private JLabel clockCheckBoxLabel = new JLabel("Add Clock?");
	private JCheckBox clockCheckBox = new JCheckBox();

	private JLabel actionLabel = new JLabel("Action");
	private JList actionList = new JList();

	private JButton associateButton = new JButton("Associate");

	private JLabel activityLabel = new JLabel("Activity");
	private JTextArea activityTextArea = new JTextArea(5, 20);

	private JLabel backgroundImage = new JLabel();

	static org.apache.logging.log4j.Logger log = LogManager.getLogger(GameMakerView.class);

	public GameMakerView() {
		log.info("GameMakerView : Enter");
		setDisplayFlagView(true);

		this.setLayout(new GridLayout(0, 2));

		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		userInputpanel.setLayout(new GridLayout(4, 0));

		GridBagLayout layout = new GridBagLayout();
		spritePanel.setLayout(layout);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 0, 5);

		spritePanel.setBorder(BorderFactory.createTitledBorder("Sprite Panel"));

		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		spritePanel.add(spriteNameLabel, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		spritePanel.add(spriteName, gridBagConstraints);

		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		spritePanel.add(spriteImageLabel, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		imagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		imagesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		imagesList.setVisibleRowCount(1);
		imagesList.setTransferHandler(new DropBoxHandler(imagesList));
		imagesList.setDropMode(DropMode.USE_SELECTION);
		imagesList.setDragEnabled(true);
		JScrollPane scroll = new JScrollPane(imagesList);
		scroll.setPreferredSize(new Dimension(Constants.SPRITE_SELECTOR_SCROLL_WIDTH, Constants.SPRITE_SELECTOR_SCROLL_HEIGHT));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spritePanel.add(scroll, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 10;
		spritePanel.add(deleteSpriteButton, gridBagConstraints);

		featurePanel.setLayout(layout);

		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.insets = new Insets(5, 5, 5, 5);

		featurePanel.setBorder(BorderFactory.createTitledBorder("Feature Panel"));

		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.gridy = 0;
		featurePanel.add(backgroundSelectionLabel, gridBagConstraints3);

		gridBagConstraints3.gridx = 1;
		gridBagConstraints3.gridy = 0;
		featurePanel.add(backgroundList, gridBagConstraints3);

		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.gridy = 2;
		gridBagConstraints3.gridwidth = 2;
		featurePanel.add(clockCheckBoxLabel, gridBagConstraints3);

		gridBagConstraints3.gridx = 1;
		gridBagConstraints3.gridy = 2;
		featurePanel.add(clockCheckBox, gridBagConstraints3);

		eventPanel.setLayout(layout);

		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.insets = new Insets(5, 0, 5, 5);

		eventPanel.setBorder(BorderFactory.createTitledBorder("Event Panel"));

		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 0;
		eventPanel.add(eventTypeLabel, gridBagConstraints1);

		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 0;
		eventPanel.add(eventsList, gridBagConstraints1);
		eventsList.setEnabled(false);

		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 2;
		eventPanel.add(eventSubTypeLabel, gridBagConstraints1);

		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 2;
		eventPanel.add(new JScrollPane(eventSubTypeList), gridBagConstraints1);

		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.ipadx = 100;
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 4;
		eventPanel.add(actionLabel, gridBagConstraints1);

		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 4;
		JScrollPane scrollPane = new JScrollPane(actionList);
		eventPanel.add(scrollPane, gridBagConstraints1);

		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 6;
		eventPanel.add(associateButton, gridBagConstraints1);
		associateButton.setEnabled(false);

		buttonPanel.setLayout(layout);

		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.insets = new Insets(5, 0, 5, 0);

		buttonPanel.setBorder(BorderFactory.createTitledBorder("Activity Panel"));

		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 0;
		buttonPanel.add(saveSpriteButton, gridBagConstraints2);

		gridBagConstraints2.gridx = 2;
		gridBagConstraints2.gridy = 0;
		buttonPanel.add(loadSpriteButton, gridBagConstraints2);

		gridBagConstraints2.gridx = 4;
		gridBagConstraints2.gridy = 0;
		buttonPanel.add(playGameButton, gridBagConstraints2);
		playGameButton.setEnabled(false);

		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 5;
		buttonPanel.add(activityLabel, gridBagConstraints2);

		gridBagConstraints2.gridx = 1;
		gridBagConstraints2.gridy = 5;
		gridBagConstraints2.gridwidth = 2;
		activityTextArea.setEditable(false);
		buttonPanel.add(new JScrollPane(activityTextArea), gridBagConstraints2);

		userInputpanel.add(featurePanel);
		userInputpanel.add(spritePanel);
		userInputpanel.add(eventPanel);
		userInputpanel.add(buttonPanel);

		this.add(userInputpanel);

		gamePanel.setLayout(new BorderLayout());

		gamePanel.setBorder(BorderFactory.createTitledBorder("Game Panel"));
		gamePanel.setPreferredSize(new Dimension(560, 930));
		gamePanel.add(gameBoardPanel, BorderLayout.NORTH);

		this.add(gamePanel);

		gameBoardPanel.setBackgroundImage(new ImageIcon(getClass().getClassLoader().getResource("img/default_background.png")));
		log.info("GameMakerView : Exit");
	}

	public void clearUserInput() {
		log.info("clearUserInput : Enter");
		spriteName.setText(null);
		imagesList.setSelectedIndex(0);
		// spriteXPosition.setText(null);
		// spriteYPosition.setText(null);
		eventsList.setSelectedIndex(0);
		eventSubTypeList.clearSelection();
		actionList.clearSelection();
		activityTextArea.setText(null);
		associateButton.setEnabled(false);
		log.info("clearUserInput : Exit");
	}

	public String getSpriteName() {
		return spriteName.getText();
	}

	public int getSpriteXPosition() {
		return spriteXPosition;
	}

	public void setSpriteXPosition(int spriteXPosition) {
		this.spriteXPosition = spriteXPosition;
	}

	public int getSpriteYPosition() {
		return spriteYPosition;
	}

	public void setSpriteYPosition(int spriteYPosition) {
		this.spriteYPosition = spriteYPosition;
	}

	public JComboBox getEventsList() {
		return eventsList;
	}

	public String getEventSelected() {
		String eventSelected;
		eventSelected = eventsList.getSelectedItem().toString();
		return eventSelected;
	}

	public String getEventSubTypeSelected() {
		String eventSubTypeSelected;
		eventSubTypeSelected = eventSubTypeList.getSelectedValue().toString();
		return eventSubTypeSelected;
	}

	public String getBackgroundSelected() {
		String backgroundSelected;
		backgroundSelected = backgroundList.getSelectedItem().toString();
		return backgroundSelected;
	}

	public JList getActionList() {
		return actionList;
	}

	public Image getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(Image selectedImage) {
		this.selectedImage = selectedImage;
	}

	public String getActionSelected() {
		String actionSelected;
		actionSelected = actionList.getSelectedValue().toString();
		return actionSelected;
	}

	public Image getImageSelected() throws IOException {
		Image imageSelected = ((ImageIcon) imagesList.getSelectedValue()).getImage();
		return imageSelected;
	}

	public int getImageSelectedIndex() throws IOException {
		return imagesList.getSelectedIndex();
	}

	public void setEventsList(JComboBox eventsList) {
		this.eventsList = eventsList;
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	public JTextArea getActivityTextArea() {
		return activityTextArea;
	}

	public JList getEventSubTypeList() {
		return eventSubTypeList;
	}

	public JButton getPlayGameButton() {
		return playGameButton;
	}

	public void setPlayGameButton(JButton playGameButton) {
		this.playGameButton = playGameButton;
	}

	public JLabel getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(JLabel backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public JCheckBox getClockCheckBox() {
		return clockCheckBox;
	}

	public void setClockCheckBox(JCheckBox clockCheckBox) {
		this.clockCheckBox = clockCheckBox;
	}

	public JButton getAssociateButton() {
		return associateButton;
	}

	public void setAssociateButton(JButton associateButton) {
		this.associateButton = associateButton;
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public GameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}

	public void setGameBoardPanel(GameBoardPanel gameBoardPanel) {
		this.gameBoardPanel = gameBoardPanel;
	}

	public GameClockPanel getClockPanel() {
		return clockPanel;
	}

	public void setClockPanel(GameClockPanel clockPanel) {
		this.clockPanel = clockPanel;
	}

	public boolean isDisplayFlagView() {
		return displayFlagView;
	}

	public void setDisplayFlagView(boolean displayFlagView) {
		this.displayFlagView = displayFlagView;
	}

	/*
	 * ACTION LISTENERS
	 */

	public void addAssociateListener(ActionListener ae) {
		associateButton.addActionListener(ae);
	}

	public void addCreateListener(DropTargetListener de) {

	}

	public void addEventsListener(ActionListener ae) {
		eventsList.addActionListener(ae);
	}

	public void addClockCheckBoxListener(ActionListener ae) {
		clockCheckBox.addActionListener(ae);
	}

	public void addBackgroundsListener(ActionListener ae) {
		backgroundList.addActionListener(ae);
	}

	public void addSaveSpriteListener(ActionListener ae) {
		saveSpriteButton.addActionListener(ae);
	}

	public void addLoadSpriteListener(ActionListener ae) {
		loadSpriteButton.addActionListener(ae);
	}

	public void addPlayGameListener(ActionListener ae) {
		playGameButton.addActionListener(ae);
	}

	public void addDeleteListener(ActionListener ae) {
		deleteSpriteButton.addActionListener(ae);
	}

	public void setBackgroundImages(String[] backgroundImages) {
		this.backgroundImages = backgroundImages;
	}

	public JComboBox getBackgroundList() {
		return backgroundList;
	}

	public void setBackgroundList(JComboBox backgroundList) {
		this.backgroundList = backgroundList;
	}

	public JButton getLoadSpriteButton() {
		return loadSpriteButton;
	}

	public void setLoadSpriteButton(JButton loadSpriteButton) {
		this.loadSpriteButton = loadSpriteButton;
	}

}

class DropBoxHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JList imagesList;

	static org.apache.logging.log4j.Logger log = LogManager.getLogger(DropBoxHandler.class);

	DropBoxHandler(JList imagesList) {
		log.info("DropBoxHandler : Enter");
		log.debug("DropBoxHandler : imageListSize -" + imagesList.getComponentCount());
		this.imagesList = imagesList;
		log.info("DropBoxHandler : Exit");
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		return new Transferable() {
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { GameMakerView.DATA_FLAVOUR };
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return flavor.equals(GameMakerView.DATA_FLAVOUR);
			}

			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				return imagesList.getSelectedValue();
			}

		};

	}

	@Override
	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY;
	}

	public boolean canImport(TransferSupport support) {
		if (!support.isDataFlavorSupported(GameMakerView.DATA_FLAVOUR)) {
			return false;
		}

		JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
		if (dl.getIndex() == -1) {
			return false;
		} else {
			return true;
		}
	}

}
