package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import model.ColourType;

import com.google.common.collect.ImmutableMap;

public class SystemProperties
{
	public static final String ae = "\u00E4";
	public static final String oe = "\u00F6";
	public static final String ue = "\u00FC";

	public static final String Ae = "\u00C4";
	public static final String Oe = "\u00D6";
	public static final String Ue = "\u00DC";
	
	public static final Dimension SCREEN_RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SCREEN_WIDTH = (int) SCREEN_RESOLUTION.getWidth();
	public static final int SCREEN_HEIGHT = (int) SCREEN_RESOLUTION.getHeight();
	
	public static final Color WHITE = Color.WHITE;
	
	public static final Map<ColourType, Color> COLOUR_MAP = ImmutableMap.<ColourType, Color>builder()
	        .put(ColourType.BLACK, Color.BLACK)
	        .put(ColourType.RED, Color.RED)
	        .put(ColourType.GREEN, Color.GREEN)
	        .put(ColourType.BLUE, Color.BLUE)
	        .put(ColourType.YELLOW, Color.YELLOW)
	        .put(ColourType.ORANGE, new Color(255,125,0))
	        .build();
	
	public static final Map<String, ColourType> ACTION_COMMAND_COLOUR_MAP = ImmutableMap.<String, ColourType> builder()
			.put(ColourType.BLACK.getColourTypeName(), ColourType.BLACK)
			.put(ColourType.RED.getColourTypeName(), ColourType.RED)
			.put(ColourType.GREEN.getColourTypeName(), ColourType.GREEN)
			.put(ColourType.BLUE.getColourTypeName(), ColourType.BLUE)
			.put(ColourType.YELLOW.getColourTypeName(), ColourType.YELLOW)
			.put(ColourType.ORANGE.getColourTypeName(), ColourType.ORANGE).build();
}
