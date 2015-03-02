package observer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Utility;
import model.SpriteModel;

/**
 * GameBoardPanel class
 * 
 * Panel which shows the game sprites.
 * 
 * @author Team 2
 *
 */
@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel implements Observer {

	private ArrayList<SpriteModel> spriteList;
	private ImageIcon backgroundImage;

	public ImageIcon getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(ImageIcon image) {
		this.backgroundImage = image;
	}

	public GameBoardPanel() {
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.spriteList = new ArrayList<SpriteModel>();
		this.setPreferredSize(new Dimension(470, 940));
	}

	public void addSprite(SpriteModel sprite) {
		spriteList.add(sprite);
	}

	public void removeSprite(String name) {
		for (SpriteModel sprite : spriteList) {
			if (sprite.getName().equals(name)) {
				spriteList.remove(sprite);
				break;
			}
		}

	}

	public ArrayList<SpriteModel> getSpriteList() {
		return spriteList;
	}

	public void setSpriteList(ArrayList<SpriteModel> spriteList) {
		this.spriteList = spriteList;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(this.getBackgroundImage().getImage(), 0, 0,
				this.getWidth(), this.getHeight(), this);
		
		//When the Game is lost
		if((Utility.getInstance().getGameFlag() == 2)) {
			Font fontOfDisplayString = new Font("Dialog", Font.BOLD, 24);
			g.setFont(fontOfDisplayString);
			g.drawString("YOU LOST !! GAME OVER !!", 100, 200);
		}
		
		//When the Game is Won
		else if((Utility.getInstance().getGameFlag() == 3)) {
			Font fontOfDisplayString = new Font("Dialog", Font.BOLD, 24);
			g.setFont(fontOfDisplayString);
			g.drawString("YOU WON !! GAME OVER", 100, 200);
		}
		
		//When the Game continues
		else
		{
			for (SpriteModel sprite : spriteList) {
				if (!(sprite.isDestroyFlagEnabled())) {
					g.drawImage(sprite.getImage(), sprite.getXPosition(),
						sprite.getYPosition(), this);
				}
			}
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();

	}

}
