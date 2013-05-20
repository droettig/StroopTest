package utils;

import java.net.URL;

import javax.swing.ImageIcon;

public class ImageArchive
{
	private static URL newURL = Thread.currentThread().getContextClassLoader().getResource("document-new.png");
	private static URL helpURL = Thread.currentThread().getContextClassLoader().getResource("help-browser.png");
	private static URL infoURL = Thread.currentThread().getContextClassLoader().getResource("dialog-information.png");
	private static URL saveURL = Thread.currentThread().getContextClassLoader().getResource("document-save.png");
	private static URL loadRL = Thread.currentThread().getContextClassLoader().getResource("document-open.png");
	private static URL quitURL = Thread.currentThread().getContextClassLoader().getResource("system-log-out.png");
	private static URL runTestURL = Thread.currentThread().getContextClassLoader().getResource("go-next.png");
	private static URL editKeysURL = Thread.currentThread().getContextClassLoader().getResource("preferences-desktop-font.png");
	private static URL warningURL = Thread.currentThread().getContextClassLoader().getResource("dialog-warning.png");
	private static URL editTestURL = Thread.currentThread().getContextClassLoader().getResource("accessories-text-editor.png");
	private static URL stopURL = Thread.currentThread().getContextClassLoader().getResource("process-stop.png");
	private static URL testInfoURL = Thread.currentThread().getContextClassLoader().getResource("edit-find.png");
	
	public static final ImageIcon NEW = new ImageIcon(newURL);
	public static final ImageIcon HELP = new ImageIcon(helpURL);
	public static final ImageIcon INFO = new ImageIcon(infoURL);
	public static final ImageIcon SAVE = new ImageIcon(saveURL);
	public static final ImageIcon LOAD = new ImageIcon(loadRL);
	public static final ImageIcon QUIT = new ImageIcon(quitURL);
	public static final ImageIcon RUN_TEST = new ImageIcon(runTestURL);
	public static final ImageIcon EDIT_KEYS = new ImageIcon(editKeysURL);
	public static final ImageIcon WARNING = new ImageIcon(warningURL);
	public static final ImageIcon EDIT_TEST = new ImageIcon(editTestURL);
	public static final ImageIcon STOP_TEST = new ImageIcon(stopURL);
	public static final ImageIcon TEST_INFO = new ImageIcon(testInfoURL);
}
