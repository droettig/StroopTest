package view;

import java.awt.Dimension;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.ColourType;

public class ColourSpinner
{
	private ColourType parentColour;
	private ColourType spinnerColour;
	private JSpinner spinner;

	public ColourSpinner(ColourType parentColour, ColourType spinnerColour)
	{
		this.parentColour = parentColour;
		this.spinnerColour = spinnerColour;
		spinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		spinner.setName(spinnerColour.getColourTypeName());
		spinner.setPreferredSize(new Dimension(35,25));
	}

	public ColourType getParentColour()
	{
		return parentColour;
	}

	public ColourType getSpinnerColour()
	{
		return spinnerColour;
	}

	public JSpinner getSpinner()
	{
		return spinner;
	}

	public Integer getValue()
	{
		return (Integer) this.spinner.getModel().getValue();
	}
	
	public void setValue(Integer value)
	{
		this.spinner.setValue(value);
	}
}
