import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import pipe.controllers.PipeApplicationController;
import pipe.gui.CopyPasteManager;
import pipe.models.PipeApplicationModel;
import pipe.views.PipeApplicationView;

public class Pipe
{

	private Pipe(String version)
    {

        CopyPasteManager copyPaste = new CopyPasteManager();
        PipeApplicationModel applicationModel = new PipeApplicationModel(version);
        PipeApplicationController applicationController = new PipeApplicationController(copyPaste, applicationModel);
        new PipeApplicationView(applicationController, applicationModel);
    }
    public static void main(String args[])
    {
        Runnable runnable = pipeRunnable();
        SwingUtilities.invokeLater(runnable);
    }

	protected static Runnable pipeRunnable()
	{
		return new Runnable() {
            public void run()
            {
				new Pipe("v4.3.0");
            }
        };
	}
    protected static void runPipeForTesting() throws InterruptedException, InvocationTargetException
    {
    	SwingUtilities.invokeAndWait(pipeRunnable()); 
    }
}
