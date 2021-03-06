package pipe.actions.file;

import org.junit.Before;
import org.junit.Test;
import pipe.controllers.PipeApplicationController;
import pipe.gui.ApplicationSettings;

import javax.swing.*;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class OpenActionTest {
    OpenAction openAction;
    PipeApplicationController mockController;

    @Before
    public void setUp()
    {
        openAction = new OpenAction();
        mockController = mock(PipeApplicationController.class);
        ApplicationSettings.register(mockController);
    }

    @Test
    public void actionPerformed()
    {
        //TODO: Needs a re-write for testing getFile() properly so can remove
        // set test file
        File mockFile = mock(File.class);
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isFile()).thenReturn(true);
        when(mockFile.canRead()).thenReturn(true);
        openAction.setFileForTesting(mockFile);

        openAction.actionPerformed(null);
        verify(mockController).createNewTabFromFile(mockFile, false);
    }

    //TODO: Need to test dialog box error

    @Test
    public void setShortDescription()
    {
        Object shortDescription = openAction.getValue(Action.SHORT_DESCRIPTION);
        assertEquals("Open", shortDescription);
    }

    @Test
    public void setKeyboardShortcut()
    {
        Object acceleratorKey = openAction.getValue(Action.ACCELERATOR_KEY);
        KeyStroke stroke = KeyStroke.getKeyStroke("ctrl O");
        assertEquals(stroke, acceleratorKey);
    }
}
