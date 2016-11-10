/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruinan
 */
public class CellTest {
    
   @Test
   public void testToString(){
       Cell c = new Cell();
       // test the regular input
       c.setText("A");
       String returnValue = c.toString();
       assertEquals("A",returnValue);
       
       //test the else 
       c.setText("test");
       returnValue = c.toString();
       assertEquals(".",returnValue);
       
       //test the null
       c.setText(null);
       returnValue = c.toString();
       assertEquals(".",returnValue);
       
       
       
   }
   
    
    
    
}
