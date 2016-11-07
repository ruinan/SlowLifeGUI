/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author ruinan
 */
public class MainPanelTest {
    
    
    
   

    @Test
    public void testConvertToInt() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException{
        // Here, I use Reflection to test private method, it also could be test private fields.
        
        MainPanel mp = new MainPanel();
        Method privateConvertToInt = MainPanel.class.getDeclaredMethod("convertToInt", java.lang.Integer.TYPE);
        privateConvertToInt.setAccessible(true);
        int returnvalue = 0;
        try {//catch the exception
            returnvalue = (int)privateConvertToInt.invoke(mp, 1);// give the method a value
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(returnvalue,1);//check the returned value 
        
    }
    
    @Test
    public void testBackup() throws NoSuchFieldException{
        //  Here, I still used Reflection in order to use private declarated field.
        MainPanel mp = new MainPanel();
        
        Cell[][] cells = new Cell[1][1];
        Cell c = new Cell();//create a cell 
        c.setText("test");
        cells[0][0] = c;
        
        mp.setCells(cells);//set cell[][]
        mp.backup();//execute the method;then backcells should have value
        
        Field privateStringField = MainPanel.class.getDeclaredField("_backupCells");
        privateStringField.setAccessible(true);
        Cell[][] fieldvalue = null;  
        
        try {
            fieldvalue= (Cell[][]) privateStringField.get(mp);//extract  private field
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MainPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(mp.getCells()[0][0].getText());
        System.out.println(fieldvalue[0][0].getText());
        
        //assertTrue(mp.getCells()[0][0].getText().equals(fieldvalue[0][0].getText()));
        // because of I used "clone" to copy array, so aturally 
        assertTrue(Arrays.equals(mp.getCells(),fieldvalue));// the array should be as same as the original one
        assertEquals(fieldvalue[0][0].getText(),"test");//the value of the field which received copied value should be identical with the primary value.
        
        
        
        cells = null;
        mp.setCells(cells);//set cell[][]
        mp.backup();
        
        try {
            fieldvalue= (Cell[][]) privateStringField.get(mp);//extract  private field
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MainPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(fieldvalue != null);
        
    }
    
    // runContinues use manual test
}
