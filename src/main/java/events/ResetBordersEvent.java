package events;

public class ResetBordersEvent implements StroopEvent
{
	private boolean disposeDialog;

	public ResetBordersEvent(boolean disposeDialog)
	{
		this.disposeDialog = disposeDialog;
	}

	public boolean isDisposeDialog()
	{
		return disposeDialog;
	}
}
