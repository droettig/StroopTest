package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import model.ColourType;
import model.Word;
import model.WordFactory;

import org.apache.commons.lang3.StringUtils;

import utils.SystemProperties;

public class LetterPanel extends JLabel implements Observer
{
	private static final long serialVersionUID = 8704052423293477159L;

	private Color currentColour;
	private String currentWordString;
	private Font font;
	private int xEnd;
	private int yEnd;

	public LetterPanel()
	{
		currentColour = SystemProperties.WHITE;

		font = new Font("Arial", Font.PLAIN, 1);

		xEnd = (int) (this.getWidth() * (2.0 / 3.0));
		yEnd = (int) (this.getHeight() * (3.0 / 5.0));
	}

	public void hideWord()
	{
		this.currentColour = SystemProperties.WHITE;
		this.currentWordString = StringUtils.EMPTY;
		this.setText(currentWordString);
		this.setForeground(currentColour);
	}

	public void showWord(Word nextWord)
	{
		this.currentColour = SystemProperties.COLOUR_MAP.get(nextWord.getColourOfFont());
		this.currentWordString = WordFactory.getDescriptionOfColour(nextWord.getColourOfWord());

		this.scaleFont();

		this.setFont(font);
		this.setText(currentWordString);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setForeground(this.currentColour);
	}

	private void scaleFont()
	{
		Font tmp = new Font("Arial", Font.BOLD, 300);
		Graphics g = this.getGraphics();
		FontMetrics metrics = g.getFontMetrics(tmp);

		int wordWidth = metrics.stringWidth(this.currentWordString);
		int wordHeight = metrics.getHeight();
		int fontSize = tmp.getSize();

		xEnd = (int) (this.getWidth() * (3.0 / 4.0));
		yEnd = (int) (this.getHeight() * (3.0 / 5.0));

		Rectangle rec = new Rectangle(xEnd, yEnd);

		while (!rec.contains(wordWidth, wordHeight))
		{
			fontSize -= 5;
			tmp = new Font("Arial", Font.PLAIN, fontSize);
			metrics = g.getFontMetrics(tmp);
			wordWidth = metrics.stringWidth(this.currentWordString);
			wordHeight = metrics.getHeight();
		}

		this.font = tmp;
	}

	
	public void update(Observable obs, Object obj)
	{
		Word currentWord = (Word) obj;

		this.currentColour = SystemProperties.COLOUR_MAP.get(currentWord.getColourOfFont());
		this.currentWordString = WordFactory.getDescriptionOfColour(ColourType.BLACK);
	}
}
