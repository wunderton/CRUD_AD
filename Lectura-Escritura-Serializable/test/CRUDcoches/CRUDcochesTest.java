/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDcoches;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wunderton
 */
public class CRUDcochesTest {
    
    public CRUDcochesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class CRUDcoches.
     */
    @Test
    public void testConnect() throws IOException {
        System.out.println("connect");
        CRUDcoches instance = new CRUDcoches();
        instance.connect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importarDatosDB4Ocoches method, of class CRUDcoches.
     */
    @Test
    public void testImportarDatosDB4Ocoches() throws IOException {
        System.out.println("importarDatosDB4Ocoches");
        CRUDcoches instance = new CRUDcoches();
        instance.importarDatosDB4Ocoches();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importarDatosDB4Opilotos method, of class CRUDcoches.
     */
    @Test
    public void testImportarDatosDB4Opilotos() throws IOException {
        System.out.println("importarDatosDB4Opilotos");
        CRUDcoches instance = new CRUDcoches();
        instance.importarDatosDB4Opilotos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importarDatosDB4Omotores method, of class CRUDcoches.
     */
    @Test
    public void testImportarDatosDB4Omotores() throws IOException {
        System.out.println("importarDatosDB4Omotores");
        CRUDcoches instance = new CRUDcoches();
        instance.importarDatosDB4Omotores();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importarDatosDB4Oequipos method, of class CRUDcoches.
     */
    @Test
    public void testImportarDatosDB4Oequipos() throws IOException {
        System.out.println("importarDatosDB4Oequipos");
        CRUDcoches instance = new CRUDcoches();
        instance.importarDatosDB4Oequipos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarDatosDB4O method, of class CRUDcoches.
     */
    @Test
    public void testGuardarDatosDB4O() throws IOException {
        System.out.println("guardarDatosDB4O");
        CRUDcoches instance = new CRUDcoches();
        instance.guardarDatosDB4O();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of borrarFichero method, of class CRUDcoches.
     */
    @Test
    public void testBorrarFichero() throws IOException {
        System.out.println("borrarFichero");
        String nombreFichero = "";
        CRUDcoches instance = new CRUDcoches();
        instance.borrarFichero(nombreFichero);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importarCochesXML method, of class CRUDcoches.
     */
    @Test
    public void testImportarCochesXML() throws IOException {
        System.out.println("importarCochesXML");
        CRUDcoches instance = new CRUDcoches();
        instance.importarCochesXML();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importarPilotosXML method, of class CRUDcoches.
     */
    @Test
    public void testImportarPilotosXML() throws IOException {
        System.out.println("importarPilotosXML");
        CRUDcoches instance = new CRUDcoches();
        instance.importarPilotosXML();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importarMotorXML method, of class CRUDcoches.
     */
    @Test
    public void testImportarMotorXML() throws IOException {
        System.out.println("importarMotorXML");
        CRUDcoches instance = new CRUDcoches();
        instance.importarMotorXML();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importarEquipoXML method, of class CRUDcoches.
     */
    @Test
    public void testImportarEquipoXML() throws IOException {
        System.out.println("importarEquipoXML");
        CRUDcoches instance = new CRUDcoches();
        instance.importarEquipoXML();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generarId method, of class CRUDcoches.
     */
    @Test
    public void testGenerarId() throws IOException {
        System.out.println("generarId");
        CRUDcoches instance = new CRUDcoches();
        int expResult = 0;
        int result = instance.generarId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class CRUDcoches.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        CRUDcoches.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
