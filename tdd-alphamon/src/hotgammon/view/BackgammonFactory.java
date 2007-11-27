package hotgammon.view;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import javax.swing.JTextField;

/** Minidraw factory that returns proper 
 * products for a gui with backgammon graphics.
 * 
 * Author Henrik Bærbak Christensen
 *
 */

public class BackgammonFactory implements Factory {
  
  // TODO: Change visibility
  public DrawingView view;
  public Drawing drawing;

  public BackgammonFactory() {
    drawing = new BackgammonDrawing();
  }

  public DrawingView createDrawingView( DrawingEditor editor ) {
    ImageManager im = ImageManager.getSingleton();
    view = 
      new StdViewWithBackground(editor, im.getImage("board"));
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return drawing;
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    JTextField statusField = new JTextField( "Testing" );
    statusField.setBackground(new Color(65, 105, 225));
    statusField.setForeground(new Color(0xFF, 0xFF, 0xFF));
    statusField.setEditable(false);
    return statusField;
  }
}
