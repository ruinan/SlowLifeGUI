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
        // The method is to convert an Int to String then repeatly copy the result then concate 
        MainPanel mp = new MainPanel();
        Method privateConvertToInt = MainPanel.class.getDeclaredMethod("convertToInt", java.lang.Integer.TYPE);
        privateConvertToInt.setAccessible(true);
        int returnvalue = 0;
        try {//catch the exception
            returnvalue = (int)privateConvertToInt.invoke(mp, 1000000000);// give the method a value
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(returnvalue,1000000000);//check the returned value 
        
        try {//catch the exception
            returnvalue = (int)privateConvertToInt.invoke(mp, -1000000000 );// give the method a value
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(returnvalue,1000000000);
        
        try {//catch the exception
            returnvalue = (int)privateConvertToInt.invoke(mp, 0);// give the method a value
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(returnvalue,0);
    }
    
    @Test 
    public void testBackup() throws NoSuchFieldException{
        //  Here, I still used Reflection in order to use private declarated field.
        Cell[][] cells = new Cell[1][1];//When cell has value
        Cell c = new Cell();//create a cell 
        c.setAlive(true);
        cells[0][0] = c;
        
        //private int _size = 0;
        MainPanel mp = new MainPanel(1);
        
        mp.setCells(cells);//set cell[][]
        mp.backup();//execute the method;then backcells should have value
        
        
        //private Cell[][] _backupCells;
        Field privateStringField = MainPanel.class.getDeclaredField("_backupCells");
        privateStringField.setAccessible(true);
        Cell[][] fieldvalue = new Cell[1][1]; 
        
        try {
            fieldvalue= (Cell[][]) privateStringField.get(mp);//extract  private field
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MainPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(mp.getCells()[0][0].getAlive());
        System.out.println(fieldvalue[0][0].getAlive());
       
        // because of I used "clone" to copy array, so acturally the time to copy the array will shorter than that used for loop
        assertTrue(fieldvalue[0][0].getAlive());//The activity status of _cell has been set to the true. because the primary value is false.
       
        
         
        MainPanel mp2 = new MainPanel(1);// when I initialize the mp2, the _cell are all be set  false
        mp2.backup();
        Cell[][] fieldvalue2 = new Cell[1][1];
        try {
            fieldvalue2= (Cell[][]) privateStringField.get(mp2);//extract  private field
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MainPanelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(!(fieldvalue2[0][0].getAlive()));
        
    }
    
    // runContinues use manual test
}
