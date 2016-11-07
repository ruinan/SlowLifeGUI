package Program;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Cell extends JButton {

   private boolean _beenAlive = false;

   private int _maxSize = 10000;

   public Cell() {
   	super(" ");
   	setFont(new Font("Courier", Font.PLAIN, 12));
   	addActionListener(new CellButtonListener());
   }

   public Cell(boolean alive) {
      super(" ");
   	setFont(new Font("Courier", Font.PLAIN, 12));
   	addActionListener(new CellButtonListener());
   	setAlive(alive);
   }

   public void resetBeenAlive() {
      _beenAlive = false;
   }

   public void reset() {
      resetBeenAlive();
      setAlive(false);
   }

   public boolean getAlive() {
      String text = getText();
      return (text.equals("A"));// change x to alive
   }

   public String toString() {
       
       // it is not necessarily create a repeative string, then extract only a section of it.
//      String toReturn = new String("");
//      String currentState = getText();
//      for (int j = 0; j < _maxSize; j++) {
//         toReturn += currentState;
//      }
//      if (toReturn.substring(0,1).equals("A")) {
//         return toReturn.substring(0,1);
//      } else {
//         return ".";
//      }
        String currentState = this.getText();
        
        if(currentState != null && currentState.equals("A") ){
            return "A";
        }
        else{
            return ".";
        }
   }

   public void setAlive(boolean a) {
	// note that "if (a)" and "if (a == true)"
	// really say the same thing!
   	if (a) {
            _beenAlive = true;
            setText("A");
            setBackground(Color.RED);
   	} else {
            setText(" ");
            if (_beenAlive) {
                setBackground(Color.GREEN);
            } 
            else {
                setBackground(Color.GRAY);
            }
   	}
   	setContentAreaFilled(true);
        setOpaque(true);
   }



   class CellButtonListener implements ActionListener {

	// Every time we click the button, it will perform
	// the following action.

   	public void actionPerformed(ActionEvent e) {
   	    Cell source = (Cell) e.getSource();
   	    String currentText = source.getText();
   	    resetBeenAlive();
   	    if (currentText.equals(" ")) {
   		        setAlive(true);
   	    } else if (currentText.equals("A")) {
   		        setAlive(false);
   	    } else {
   		// This shouldn't happen
   		    setAlive(false);
   	    }
   	}

    }

}
