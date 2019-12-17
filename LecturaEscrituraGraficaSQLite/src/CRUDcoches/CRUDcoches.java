/*
 *  Antonio Carrasco Villegas.
 */
package CRUDcoches;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wunderton
 *     class RenderTabla implements TableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column)  { ... }
}
 */
public class CRUDcoches extends javax.swing.JFrame {
    ArrayList<Coche> coches;
    int numeroCoches = 0;
    ArrayList<Piloto> pilotos;
    int numeroPilotos = 0;
    ArrayList<Motor> motores;
    int numeroMotores = 0;
    ArrayList<Equipo> equipos;
    int numeroEquipo = 0;
    
    
    /**
     * Creates new form LecturaEscritura
     * @throws java.io.IOException
     */
    public CRUDcoches() throws IOException, SQLException, ClassNotFoundException {
        coches = new ArrayList<>();
        pilotos = new ArrayList<>();
        motores = new ArrayList<>();
        equipos = new ArrayList<>();
        initComponents();
        importarCochesSQLite();
        importarPilotosSQLite();
        importarMotoresSQLite();
        importarEquiposSQLite();
        marcajText.setEnabled(false);
        modelojText.setEnabled(false);
        caballosjText.setEnabled(false);
        kilometrosjText.setEnabled(false);
        nombrePiloto.setEnabled(false);
        sueldoPiloto.setEnabled(false);
        victoriasPiloto.setEnabled(false);
        caballos.setEnabled(false);
        numeroUsosMotor.setEnabled(false);
        kilometrosMotor.setEnabled(false);
        nombreEquipo.setEnabled(false);
        campeonatosGanadosEquipo.setEnabled(false);
        numeroEmpleados.setEnabled(false);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
 
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                try {
                    close();
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(CRUDcoches.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CRUDcoches.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CRUDcoches.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void delete(String bd) throws SQLException {
        Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
        String query = "select * from " + bd +";";
        System.out.println(query);
        try{ 
            PreparedStatement pstmt = conexion.prepareStatement(query);
//            pstmt.setString(1, c.getMarca());
//            pstmt.setString(2, c.getModelo());
//            pstmt.setInt(3, c.getCaballos());
//            pstmt.setInt(4, c.getKilometros());
            pstmt.executeUpdate();// execute the delete statement
            
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void guardarCochesSQLite() throws SQLException, ClassNotFoundException{
        try {
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
            delete("Coche");
            String sql = "INSERT INTO Coche(Marca,Modelo,Caballos,Kilometros) VALUES(?,?,?,?)";
            Statement stmt = conexion.createStatement();
            for(int i = 0; i < coches.size(); i++) {
                Coche c = coches.get(i);
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                pstmt.setString(1, c.getMarca());
                pstmt.setString(2, c.getModelo());
                pstmt.setInt(3, c.getCaballos());
                pstmt.setInt(4, c.getKilometros());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
        public void guardarPilotosSQLite() throws SQLException, ClassNotFoundException{
        try {
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
            delete("Piloto");
            String sql = "INSERT INTO Piloto(id_piloto,nombrePiloto,sueldo,numeroVictorias) VALUES(?,?,?,?)";
            Statement stmt = conexion.createStatement();
            for(int i = 0; i < pilotos.size(); i++) {
                Piloto c = pilotos.get(i);
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                pstmt.setInt(1, c.getId_piloto());
                pstmt.setString(2, c.getNombrePiloto());
                pstmt.setInt(3, c.getSueldo());
                pstmt.setInt(4, c.getNumeroVictorias());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
        public void guardarMotoresSQLite() throws SQLException, ClassNotFoundException{
        try {   
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
            delete("Motor");
            String sql = "INSERT INTO Motor(id_motor,numeroUsos,kilometros,caballos) VALUES(?,?,?,?)";
            Statement stmt = conexion.createStatement();
            for(int i = 0; i < motores.size(); i++) {
                Motor c = motores.get(i);
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                pstmt.setInt(1, c.getId_motor());
                pstmt.setInt(2, c.getNumeroUsos());
                pstmt.setInt(3, c.getKilometos());
                pstmt.setInt(4, c.getCaballos());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
        public void guardarEquiposSQLite() throws SQLException, ClassNotFoundException{
        try {
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
            delete("Equipo");
            String sql = "INSERT INTO Equipo(id_equipo,nombreEquipo,campeonatosGanados,numeroEmpleados) VALUES(?,?,?,?)";
            Statement stmt = conexion.createStatement();
            for(int i = 0; i < equipos.size(); i++) {
                Equipo c = equipos.get(i);
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                pstmt.setInt(1, c.getId_equipo());
                pstmt.setString(2, c.getNombreEquipo());
                pstmt.setInt(3, c.getCampeonatosGanados());
                pstmt.setInt(4, c.getNumeroEmpleados());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importarCochesSQLite() throws SQLException, ClassNotFoundException{
        try {
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
            Statement stmt = conexion.createStatement();
        
            String query = "select * from Coche";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String marca= rs.getString("marca");
                String model = rs.getString("modelo");
                int caballos= rs.getInt("caballos");
                int kilometros = rs.getInt("kilometros");


                Coche almacenador = new Coche(marca, model, caballos, kilometros);
                coches.add(almacenador);

            } 
        }catch (SQLException  ex) {
            System.out.println("Error en la conexión de la base de datos");
        }
        for (int x = 0; x < coches.size(); x++) {
            Coche getC = (Coche) coches.get(x);
            jTable1.setValueAt(getC.getMarca(), x, 0);
            jTable1.setValueAt(getC.getModelo(), x, 1);
            jTable1.setValueAt(getC.getCaballos(), x, 2);
            jTable1.setValueAt(getC.getKilometros(), x, 3);
            jComboBox3.addItem(getC.getModelo());
        }
    
    }
    
    public void importarPilotosSQLite() throws SQLException, ClassNotFoundException{
        try {

            Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
            Statement stmt = conexion.createStatement();
        
        
        String query = "select * from Piloto";
        
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int idPiloto= rs.getInt("id_piloto");
            String nombrePiloto = rs.getString("nombrePiloto");
            int sueldo= rs.getInt("sueldo");
            int victorias = rs.getInt("numeroVictorias");


            Piloto almacenador = new Piloto(idPiloto, nombrePiloto, sueldo, victorias);
            pilotos.add(almacenador);

            } 
        }catch (SQLException  ex) {
            System.out.println("Error en la conexión de la base de datos");
        }
        
        for (int x = 0; x < pilotos.size(); x++) {
            Piloto lectura = (Piloto) pilotos.get(x);
            jTable2.setValueAt(lectura.getId_piloto(), x, 0);
            jTable2.setValueAt(lectura.getNombrePiloto(), x, 1);
            jTable2.setValueAt(lectura.getSueldo(), x, 2);
            jTable2.setValueAt(lectura.getNumeroVictorias(), x, 3);
        }
    
    }
        
    public void importarMotoresSQLite() throws SQLException, ClassNotFoundException{
        try {

            Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
            Statement stmt = conexion.createStatement();
        
        
        String query = "select * from Motor";
        
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int idMotor= rs.getInt("id_motor");
            int numUsos = rs.getInt("numeroUsos");
            int caballs= rs.getInt("caballos");
            int kilometros = rs.getInt("kilometros");


            Motor almacenador = new Motor(idMotor, numUsos, kilometros, caballs);
            motores.add(almacenador);

            } 
        }catch (SQLException  ex) {
            System.out.println("Error en la conexión de la base de datos");
        }
        for (int x = 0; x < motores.size(); x++) {
            Motor lectura = (Motor) motores.get(x);
            jTable3.setValueAt(lectura.getId_motor(), x, 0);
            jTable3.setValueAt(lectura.getNumeroUsos(), x, 1);
            jTable3.setValueAt(lectura.getKilometos(), x, 2);
            jTable3.setValueAt(lectura.getCaballos(), x, 3);
        }
    
    }
        
    public void importarEquiposSQLite() throws SQLException, ClassNotFoundException{
        try {

            Connection conexion = DriverManager.getConnection("jdbc:sqlite:crudbd.bd");
            Statement stmt = conexion.createStatement();
        
        
        String query = "select * from Equipo";
        
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int idEquipo= rs.getInt("id_equipo");
            String nombreEquipo = rs.getString("nombreEquipo");
            int campeonatosGanados= rs.getInt("campeonatosGanados");
            int numEmpleados = rs.getInt("numeroEmpleados");


            Equipo almacenador = new Equipo(idEquipo, nombreEquipo, campeonatosGanados, numEmpleados);
            equipos.add(almacenador);

        } }catch (SQLException  ex) {
            System.out.println("Error en la conexión de la base de datos");
        }
    
    }
 
    private void importarDatosCoche() throws IOException{
        File fichero = new File("FichCoche.dat");
        if(fichero.exists()){
                try (ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero))) {
                    int i = 1;
                    try {
                        try {
                            coches = (ArrayList<Coche>) dataIS.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CRUDcoches.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (int x = 0; x < coches.size(); x++) {
                            Coche getC = (Coche) coches.get(x);
                            jTable1.setValueAt(getC.getMarca(), x, 0);
                            jTable1.setValueAt(getC.getModelo(), x, 1);
                            jTable1.setValueAt(getC.getCaballos(), x, 2);
                            jTable1.setValueAt(getC.getKilometros(), x, 3);
                            jComboBox3.addItem(getC.getModelo());
                        }
                    }catch (EOFException | StreamCorruptedException eo) {
                        //System.out.println("FIN DE LECTURA.");
                    }
            }
        }
    }
    
    private void importarDatosPiloto() throws IOException{
        File fichero = new File("FichPiloto.dat");
        if(fichero.exists()){
                try (ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero))) {
                    int i = 1;
                    try {
                        try {
                            pilotos = (ArrayList<Piloto>) dataIS.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CRUDcoches.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (int x = 0; x < pilotos.size(); x++) {
                            Piloto lectura = (Piloto) pilotos.get(x);
                            jTable2.setValueAt(lectura.getId_piloto(), x, 0);
                            jTable2.setValueAt(lectura.getNombrePiloto(), x, 1);
                            jTable2.setValueAt(lectura.getSueldo(), x, 2);
                            jTable2.setValueAt(lectura.getNumeroVictorias(), x, 3);
                        }
                    }catch (EOFException | StreamCorruptedException eo) {
                        //System.out.println("FIN DE LECTURA.");
                    }
            }
        }
    }
        
    private void importarDatosMotor() throws IOException{
        File fichero = new File("FichMotor.dat");
        if(fichero.exists()){
                try (ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero))) {
                    int i = 1;
                    try {
                        try {
                            motores = (ArrayList<Motor>) dataIS.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CRUDcoches.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (int x = 0; x < motores.size(); x++) {
                            Motor lectura = (Motor) motores.get(x);
                            jTable3.setValueAt(lectura.getId_motor(), x, 0);
                            jTable3.setValueAt(lectura.getNumeroUsos(), x, 1);
                            jTable3.setValueAt(lectura.getKilometos(), x, 2);
                            jTable3.setValueAt(lectura.getCaballos(), x, 3);
                        }
                    }catch (EOFException | StreamCorruptedException eo) {
                        //System.out.println("FIN DE LECTURA.");
                    }
            }
        }
    }
            
    private void importarDatosEquipo() throws IOException{
        File fichero = new File("FichEquipo.dat");
        if(fichero.exists()){
                try (ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero))) {
                    int i = 1;
                    try {
                        try {
                            equipos = (ArrayList<Equipo>) dataIS.readObject();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CRUDcoches.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (int x = 0; x < equipos.size(); x++) {
                            Equipo lectura = (Equipo) equipos.get(x);
                            jTable4.setValueAt(lectura.getId_equipo(), x, 0);
                            jTable4.setValueAt(lectura.getNombreEquipo(), x, 1);
                            jTable4.setValueAt(lectura.getCampeonatosGanados(), x, 2);
                            jTable4.setValueAt(lectura.getNumeroEmpleados(), x, 3);
                            jComboBox1.addItem(lectura.getNombreEquipo());
                        }
                    }catch (EOFException | StreamCorruptedException eo) {
                        //System.out.println("FIN DE LECTURA.");
                    }
            }
        }
    }
        
    public void borrarFichero(String nombreFichero) {

        File fichero = new File(nombreFichero);
        fichero.delete();
    }
    
    private void guardarCocheEnFichero(ArrayList<Coche> coches) throws FileNotFoundException, IOException {
        String nombreFichero = "FichCoche.dat";
        borrarFichero(nombreFichero);
        File fichero = new File(nombreFichero);//declara el fichero
        
        FileOutputStream fileout = new FileOutputStream(fichero,true);
        try(ObjectOutputStream dataOS = new ObjectOutputStream(fileout)) {
            try{
                dataOS.writeObject(coches);
                dataOS.flush();
                dataOS.close();
                
            } catch (FileNotFoundException f) {
                JOptionPane.showMessageDialog(null,"Error: El fichero no existe. ");
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Error: Fallo en la escritura en el fichero. ");
            }    
        }
    }
    
    private void guardarPilotoEnFichero(ArrayList<Piloto> pilotos) throws FileNotFoundException, IOException {
        String nombreFichero = "FichPiloto.dat";
        borrarFichero(nombreFichero);
        File fichero = new File(nombreFichero);//declara el fichero
        
        FileOutputStream fileout = new FileOutputStream(fichero,true);
        try(ObjectOutputStream dataOS = new ObjectOutputStream(fileout)) {
            try{
                dataOS.writeObject(pilotos);
                dataOS.flush();
                dataOS.close();
                
            } catch (FileNotFoundException f) {
                JOptionPane.showMessageDialog(null,"Error: El fichero no existe. ");
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Error: Fallo en la escritura en el fichero. ");
            }    
        }
    }
        
    private void guardarMotorEnFichero(ArrayList<Motor> motores) throws FileNotFoundException, IOException {
        String nombreFichero = "FichMotor.dat";
        borrarFichero(nombreFichero);
        File fichero = new File(nombreFichero);//declara el fichero
        
        FileOutputStream fileout = new FileOutputStream(fichero,true);
        try(ObjectOutputStream dataOS = new ObjectOutputStream(fileout)) {
            try{
                dataOS.writeObject(motores);
                dataOS.flush();
                dataOS.close();
                
            } catch (FileNotFoundException f) {
                JOptionPane.showMessageDialog(null,"Error: El fichero no existe. ");
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Error: Fallo en la escritura en el fichero. ");
            }    
        }
    }
    
    private void guardarEquipoEnFichero(ArrayList<Equipo> equipos) throws FileNotFoundException, IOException {
        String nombreFichero = "FichEquipo.dat";
        borrarFichero(nombreFichero);
        File fichero = new File(nombreFichero);//declara el fichero
        
        FileOutputStream fileout = new FileOutputStream(fichero,true);
        try(ObjectOutputStream dataOS = new ObjectOutputStream(fileout)) {
            try{
                dataOS.writeObject(equipos);
                dataOS.flush();
                dataOS.close();
                
            } catch (FileNotFoundException f) {
                JOptionPane.showMessageDialog(null,"Error: El fichero no existe. ");
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Error: Fallo en la escritura en el fichero. ");
            }    
        }
    }
    
    private void close() throws ParserConfigurationException, SQLException, ClassNotFoundException{
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del programa?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)

        guardarCochesSQLite();
        guardarPilotosSQLite();
        guardarMotoresSQLite();
        guardarEquiposSQLite();

        System.exit(0);
    }                
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        botonInsertar = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        marcajText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        modelojText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        caballosjText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        kilometrosjText = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        insertarPiloto = new javax.swing.JButton();
        modificarPiloto = new javax.swing.JButton();
        eliminarPiloto = new javax.swing.JButton();
        cancelarPiloto = new javax.swing.JButton();
        guardarPiloto = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        nombrePiloto = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        sueldoPiloto = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        victoriasPiloto = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        insertarMotor = new javax.swing.JButton();
        modificarMotor = new javax.swing.JButton();
        eliminarMotor = new javax.swing.JButton();
        guardarMotor = new javax.swing.JButton();
        cancelarMotor = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        caballos = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        numeroUsosMotor = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        kilometrosMotor = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        insertarEquipo = new javax.swing.JButton();
        modificarEquipo = new javax.swing.JButton();
        eliminarEquipo = new javax.swing.JButton();
        guardarEquipo = new javax.swing.JButton();
        cancelarEquipo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        nombreEquipo = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        campeonatosGanadosEquipo = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        numeroEmpleados = new javax.swing.JTextPane();
        jComboBox4 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Marca", "Modelo", "Caballos", "Kilometros"
            }

        ));
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        botonInsertar.setBackground(new java.awt.Color(255, 255, 255));
        botonInsertar.setText("Añadir");
        botonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInsertarActionPerformed(evt);
            }
        });

        botonActualizar.setBackground(new java.awt.Color(255, 255, 255));
        botonActualizar.setText("Modificar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        botonEliminar.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        jLabel1.setText("Marca:");

        jLabel2.setText("Modelo:");

        jLabel3.setText("Caballos:");

        jLabel4.setText("Kilometros:");

        botonGuardar.setBackground(new java.awt.Color(255, 255, 255));
        botonGuardar.setForeground(new java.awt.Color(51, 51, 51));
        botonGuardar.setText("Guardar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCancelar.setBackground(new java.awt.Color(255, 255, 255));
        botonCancelar.setForeground(new java.awt.Color(51, 51, 51));
        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(marcajText, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(caballosjText, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(modelojText, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kilometrosjText, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(botonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(82, 82, 82))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonInsertar)
                        .addGap(28, 28, 28)
                        .addComponent(botonActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonEliminar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(marcajText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(modelojText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(caballosjText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(kilometrosjText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Gestion Coches", jPanel1);

        jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        insertarPiloto.setText("Añadir");
        insertarPiloto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarPilotoActionPerformed(evt);
            }
        });

        modificarPiloto.setText("Modificar");
        modificarPiloto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarPilotoActionPerformed(evt);
            }
        });

        eliminarPiloto.setText("Eliminar");
        eliminarPiloto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPilotoActionPerformed(evt);
            }
        });

        cancelarPiloto.setText("Cancelar");
        cancelarPiloto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarPilotoActionPerformed(evt);
            }
        });

        guardarPiloto.setText("Guardar");
        guardarPiloto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarPilotoActionPerformed(evt);
            }
        });

        jTable2 = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Piloto", "Nombre Piloto", "Sueldo", "Victorias"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jScrollPane4.setViewportView(nombrePiloto);

        jScrollPane5.setViewportView(sueldoPiloto);

        jScrollPane6.setViewportView(victoriasPiloto);

        jLabel5.setText("Nombre");

        jLabel6.setText("Sueldo");

        jLabel7.setText("Victorias");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jScrollPane6))
                        .addGap(95, 95, 95)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(guardarPiloto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelarPiloto)))))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(modificarPiloto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(insertarPiloto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminarPiloto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 307, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(insertarPiloto)
                                .addGap(29, 29, 29)
                                .addComponent(modificarPiloto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eliminarPiloto))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(guardarPiloto)
                        .addComponent(cancelarPiloto)))
                .addGap(0, 57, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jTabbedPane3.addTab("", jScrollPane2);

        jTabbedPane2.addTab("Pilotos", jTabbedPane3);

        jTabbedPane5.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTable3 = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Motor", "Caballos", "Numero Usos", "Kilometros"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable3);

        insertarMotor.setText("Añadir");
        insertarMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarMotorActionPerformed(evt);
            }
        });

        modificarMotor.setText("Modificar");
        modificarMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarMotorActionPerformed(evt);
            }
        });

        eliminarMotor.setText("Eliminar");
        eliminarMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarMotorActionPerformed(evt);
            }
        });

        guardarMotor.setText("Guardar");
        guardarMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarMotorActionPerformed(evt);
            }
        });

        cancelarMotor.setText("Cancelar");
        cancelarMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarMotorActionPerformed(evt);
            }
        });

        jScrollPane8.setViewportView(caballos);

        jScrollPane9.setViewportView(numeroUsosMotor);

        jScrollPane10.setViewportView(kilometrosMotor);

        jLabel8.setText("Caballos");

        jLabel9.setText("Nº Usos");

        jLabel10.setText("Kilometros");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jScrollPane10))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(guardarMotor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancelarMotor))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(modificarMotor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(insertarMotor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eliminarMotor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(insertarMotor)
                                .addGap(37, 37, 37)
                                .addComponent(modificarMotor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eliminarMotor))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(guardarMotor)
                        .addComponent(cancelarMotor))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        jTabbedPane5.addTab("", jPanel3);

        jTabbedPane2.addTab("Motor", jTabbedPane5);

        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable4 = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Equipo", "Nombre", "Campeonatos Ganados", "Numero Empleados"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable4);

        insertarEquipo.setText("Añadir");
        insertarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarEquipoActionPerformed(evt);
            }
        });

        modificarEquipo.setText("Modificar");
        modificarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarEquipoActionPerformed(evt);
            }
        });

        eliminarEquipo.setText("Eliminar");
        eliminarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEquipoActionPerformed(evt);
            }
        });

        guardarEquipo.setText("Guardar");
        guardarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarEquipoActionPerformed(evt);
            }
        });

        cancelarEquipo.setText("Cancelar");
        cancelarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarEquipoActionPerformed(evt);
            }
        });

        jLabel11.setText("Nombre");

        jLabel12.setText("Campeonatos Ganados");

        jLabel13.setText("Numero Empleados");

        jScrollPane12.setViewportView(nombreEquipo);

        jScrollPane13.setViewportView(campeonatosGanadosEquipo);

        jScrollPane14.setViewportView(numeroEmpleados);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane14))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(124, 124, 124)
                        .addComponent(guardarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eliminarEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modificarEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(insertarEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(insertarEquipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(modificarEquipo)
                                .addGap(35, 35, 35)
                                .addComponent(eliminarEquipo))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(guardarEquipo)
                        .addComponent(cancelarEquipo)
                        .addComponent(jLabel13))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jTabbedPane4.addTab("", jPanel4);

        jTabbedPane2.addTab("Equipo", jTabbedPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public int generarId(){
        Random r = new Random();
        int idAleatorio = r.nextInt(100);
        return idAleatorio;
    }
    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            if (jTable1.getSelectedRow() != -1) {
                String codigo = (String) modelo.getValueAt(jTable1.getSelectedRow(), 1);
                int posicion = buscarEnArraylist(codigo);
                coches.remove(posicion-1);
                modelo.removeRow(jTable1.getSelectedRow());
                jTable1.addRowSelectionInterval(0,0);
                modelo=null;
                
                for (int i = 0; i < coches.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    modelo.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4"});
                    Coche getC = (Coche) coches.get(i);
                    jTable1.setValueAt(getC.getMarca(), i, 0);
                    jTable1.setValueAt(getC.getModelo(), i, 1);
                    jTable1.setValueAt(getC.getCaballos(), i, 2);
                    jTable1.setValueAt(getC.getKilometros(), i, 3);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila que desea quitar.");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed

            marcajText.setEnabled(true);
            modelojText.setEnabled(true);
            caballosjText.setEnabled(true);
            kilometrosjText.setEnabled(true);  
            botonInsertar.setEnabled(false);
            botonEliminar.setEnabled(false);
            
            
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void botonInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInsertarActionPerformed

            marcajText.setEnabled(true);
            modelojText.setEnabled(true);
            caballosjText.setEnabled(true);
            kilometrosjText.setEnabled(true);  
            botonActualizar.setEnabled(false);
            botonEliminar.setEnabled(false); 
          
    }//GEN-LAST:event_botonInsertarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            if (jTable1.getSelectedRow() != -1) {
                String modeloaBuscar = (String) modelo.getValueAt(jTable1.getSelectedRow(), 1);
                int i = buscarEnArraylist(modeloaBuscar);
                    //jTable1.addRow(0, 0, 0, 0);
                    Coche getC = (Coche) coches.get(i-1);
                    marcajText.setText(getC.getMarca());
                    modelojText.setText(getC.getModelo());
                    caballosjText.setText(Integer.toString(getC.getCaballos()));
                    kilometrosjText.setText(Integer.toString(getC.getKilometros()));

            }
    }//GEN-LAST:event_jTable1MouseClicked
    
    private void guardarInsertar(){
        Coche c = new Coche();
            
            if (marcajText.getText().equals("")
                || modelojText.getText().equals("")
                || caballosjText.getText().equals("")
                || kilometrosjText.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Rellena todos los campos para insertar un coche nuevo");

            } else {
                c.setMarca(marcajText.getText());
                c.setModelo(modelojText.getText());
                c.setCaballos(Integer.parseInt(caballosjText.getText()));
                c.setKilometros(Integer.parseInt(kilometrosjText.getText()));
                
                String modelo = modelojText.getText();
                boolean existe = existe(modelo);
                if(existe){
                    JOptionPane.showMessageDialog(null, "El coche ya existe");
                } else {
                
                coches.add(c);

                marcajText.setText("");
                modelojText.setText("");
                caballosjText.setText("");
                kilometrosjText.setText("");
                
                for (int i = 0; i < coches.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    Coche getC = (Coche) coches.get(i);
                    jTable1.setValueAt(getC.getMarca(), i, 0);
                    jTable1.setValueAt(getC.getModelo(), i, 1);
                    jTable1.setValueAt(getC.getCaballos(), i, 2);
                    jTable1.setValueAt(getC.getKilometros(), i, 3);
                    jComboBox3.addItem(c.getModelo());
                }
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                
                marcajText.setEnabled(false);
                modelojText.setEnabled(false);
                caballosjText.setEnabled(false);
                kilometrosjText.setEnabled(false);

                botonActualizar.setEnabled(true);
                botonEliminar.setEnabled(true);

                }
            }
    }
    
    private void guardarActualizar(){
        if(botonActualizar.isEnabled() == true){
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            try{
            if (jTable1.getSelectedRow() != -1) {
                String modeloCoche = (String) modelo.getValueAt(jTable1.getSelectedRow(), 1);
                int posicion = buscarEnArraylist(modeloCoche);

                if(!"".equals(marcajText.getText())){
                    coches.get(posicion -1).setMarca(marcajText.getText());
                }
                if(!"".equals(modelojText.getText())){
                    coches.get(posicion -1).setModelo(modelojText.getText());
                }
                if(!"".equals(caballosjText.getText())){
                    coches.get(posicion -1).setCaballos(Integer.parseInt(caballosjText.getText()));
                }
                if(!"".equals(kilometrosjText.getText())){
                    coches.get(posicion -1).setKilometros(Integer.parseInt(kilometrosjText.getText()));
                }
                
                
                marcajText.setText("");
                modelojText.setText("");
                caballosjText.setText("");
                kilometrosjText.setText("");
                
                
                for (int i = 0; i < coches.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    Coche getC = (Coche) coches.get(i);
                    jTable1.setValueAt(getC.getMarca(), i, 0);
                    jTable1.setValueAt(getC.getModelo(), i, 1);
                    jTable1.setValueAt(getC.getCaballos(), i, 2);
                    jTable1.setValueAt(getC.getKilometros(), i, 3);
                }
                
                marcajText.setEnabled(false);
                modelojText.setEnabled(false);
                caballosjText.setEnabled(false);
                kilometrosjText.setEnabled(false);

                botonInsertar.setEnabled(true);
                botonActualizar.setEnabled(true);
                botonEliminar.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila a actualizar.");
        }
        }
    }
        
    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        
        //INSERTAR
        if(botonInsertar.isEnabled() == true){  
            guardarInsertar();
        }
            
        //ACTUALIZAR
        if(botonActualizar.isEnabled() == true){
            guardarActualizar();
        }
        
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // TODO add your handling code here:
        marcajText.setText("");
        modelojText.setText("");
        caballosjText.setText("");
        kilometrosjText.setText("");

        marcajText.setEnabled(false);
        modelojText.setEnabled(false);
        caballosjText.setEnabled(false);
        kilometrosjText.setEnabled(false);

        botonInsertar.setEnabled(true);
        botonActualizar.setEnabled(true);
        botonEliminar.setEnabled(true); 
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void modificarPilotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarPilotoActionPerformed
    // TODO add your handling code here:
        nombrePiloto.setEnabled(true);
        sueldoPiloto.setEnabled(true);
        victoriasPiloto.setEnabled(true);
        guardarPiloto.setEnabled(true);
        cancelarPiloto.setEnabled(true);  
        insertarPiloto.setEnabled(false);
        eliminarPiloto.setEnabled(false);
            
    }//GEN-LAST:event_modificarPilotoActionPerformed

    private void insertarPilotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarPilotoActionPerformed
        nombrePiloto.setEnabled(true);
        sueldoPiloto.setEnabled(true);
        victoriasPiloto.setEnabled(true);
        guardarPiloto.setEnabled(true); 
        cancelarPiloto.setEnabled(true); 
        modificarPiloto.setEnabled(false);
        eliminarPiloto.setEnabled(false); 
          
    }//GEN-LAST:event_insertarPilotoActionPerformed

    private void modificarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarEquipoActionPerformed
        nombreEquipo.setEnabled(true);
        campeonatosGanadosEquipo.setEnabled(true);
        numeroEmpleados.setEnabled(true); 
        cancelarEquipo.setEnabled(true); 
        insertarEquipo.setEnabled(false);
        eliminarEquipo.setEnabled(false); 
    }//GEN-LAST:event_modificarEquipoActionPerformed

    private void modificarMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarMotorActionPerformed
        numeroUsosMotor.setEnabled(true);
        kilometrosMotor.setEnabled(true);
        caballos.setEnabled(true); 
        cancelarMotor.setEnabled(true); 
        eliminarMotor.setEnabled(false); 
        insertarMotor.setEnabled(false);
    }//GEN-LAST:event_modificarMotorActionPerformed

    private void insertarMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarMotorActionPerformed
        numeroUsosMotor.setEnabled(true);
        kilometrosMotor.setEnabled(true);
        caballos.setEnabled(true); 
        cancelarMotor.setEnabled(true); 
        eliminarMotor.setEnabled(false); 
        modificarMotor.setEnabled(false); 
    }//GEN-LAST:event_insertarMotorActionPerformed

    private void insertarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarEquipoActionPerformed
        nombreEquipo.setEnabled(true);
        campeonatosGanadosEquipo.setEnabled(true);
        numeroEmpleados.setEnabled(true); 
        cancelarEquipo.setEnabled(true); 
        modificarEquipo.setEnabled(false);
        eliminarEquipo.setEnabled(false); 
    }//GEN-LAST:event_insertarEquipoActionPerformed

    private void eliminarPilotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPilotoActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
            if (jTable2.getSelectedRow() != -1) {
                String busqueda = (String) modelo.getValueAt(jTable2.getSelectedRow(), 1);
                int posicion = buscarPilotoEnArraylist(busqueda);
                pilotos.remove(posicion-1);
                modelo.removeRow(jTable2.getSelectedRow());
                jTable2.addRowSelectionInterval(0,0);
                modelo=null;
                
                for (int i = 0; i < pilotos.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    modelo.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4"});
                    Piloto getC = (Piloto) pilotos.get(i);
                    jTable2.setValueAt(getC.getId_piloto(), i, 0);
                    jTable2.setValueAt(getC.getNombrePiloto(), i, 1);
                    jTable2.setValueAt(getC.getSueldo(), i, 2);
                    jTable2.setValueAt(getC.getNumeroVictorias(), i, 3);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila que desea quitar.");
        }
    }//GEN-LAST:event_eliminarPilotoActionPerformed

    private void eliminarMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarMotorActionPerformed
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
            if (jTable3.getSelectedRow() != -1) {
                String busqueda = (String) modelo.getValueAt(jTable3.getSelectedRow(), 1);
                int posicion = buscarEnArraylist(busqueda);
                coches.remove(posicion-1);
                modelo.removeRow(jTable3.getSelectedRow());
                jTable3.addRowSelectionInterval(0,0);
                modelo=null;
                
                for (int i = 0; i < coches.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    modelo.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4"});
                    Motor getC = (Motor) motores.get(i);
                    jTable3.setValueAt(getC.getId_motor(), i, 0);
                    jTable3.setValueAt(getC.getCaballos(), i, 1);
                    jTable3.setValueAt(getC.getNumeroUsos(), i, 2);
                    jTable3.setValueAt(getC.getKilometros(), i, 3);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila que desea quitar.");
        }
    }//GEN-LAST:event_eliminarMotorActionPerformed

    private int buscarEquipoEnArraylist(int id){
        int i;
        boolean encontrado = false;
        for (i = 0; i < equipos.size() && encontrado != true; i++) {
            if(equipos.get(i).getId_equipo() == id){
                encontrado = true;
            }
        }
        return i;
    }
        
    private void eliminarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEquipoActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable4.getModel();
            if (jTable4.getSelectedRow() != -1) {
                int busqueda = (int) modelo.getValueAt(jTable4.getSelectedRow(), 1);
                int posicion = buscarEquipoEnArraylist(busqueda);
                equipos.remove(posicion-1);
                modelo.removeRow(jTable3.getSelectedRow());
                jTable3.addRowSelectionInterval(0,0);
                modelo=null;
                
                for (int i = 0; i < equipos.size(); i++) {
                    modelo.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "Column 4"});
                    Equipo getC = (Equipo) equipos.get(i);
                    jTable3.setValueAt(getC.getId_equipo(), i, 0);
                    jTable3.setValueAt(getC.getNombreEquipo(), i, 1);
                    jTable3.setValueAt(getC.getCampeonatosGanados(), i, 2);
                    jTable3.setValueAt(getC.getNumeroEmpleados(), i, 3);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila que desea quitar.");
        }
    }//GEN-LAST:event_eliminarEquipoActionPerformed

    private void guardarInsertarEquipo(){
        Equipo e = new Equipo();
            
            if (nombreEquipo.getText().equals("")
                || campeonatosGanadosEquipo.getText().equals("")
                || numeroEmpleados.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Rellena todos los campos para insertar un coche nuevo");

            } else {
                int id = generarId();
                
                e.setId_equipo(id);
                e.setNombreEquipo(nombreEquipo.getText());
                e.setCampeonatosGanados(Integer.parseInt(campeonatosGanadosEquipo.getText()));
                e.setNumeroEmpleados(Integer.parseInt(numeroEmpleados.getText()));
                
                
                boolean existe = existeEquipo(id);
                if(existe){
                    guardarInsertarEquipo();
                    //JOptionPane.showMessageDialog(null, "El coche ya existe");
                } else {
                
                equipos.add(e);

                nombreEquipo.setText("");
                campeonatosGanadosEquipo.setText("");
                numeroEmpleados.setText("");
                
                for (int i = 0; i < equipos.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    Equipo getC = (Equipo) equipos.get(i);
                    jTable4.setValueAt(getC.getId_equipo(), i, 0);
                    jTable4.setValueAt(getC.getNombreEquipo(), i, 1);
                    jTable4.setValueAt(getC.getCampeonatosGanados(), i, 2);
                    jTable4.setValueAt(getC.getNumeroEmpleados(), i, 3);
                }
                
                jComboBox1.addItem(e.getNombreEquipo());
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                
                nombreEquipo.setEnabled(false);
                campeonatosGanadosEquipo.setEnabled(false);
                numeroEmpleados.setEnabled(false);

                modificarEquipo.setEnabled(true);
                eliminarEquipo.setEnabled(true);
                }
            }
    }
    

    
    private void modificarInsertarEquipo(){
        if(modificarEquipo.isEnabled() == true){
            DefaultTableModel modelo = (DefaultTableModel) jTable4.getModel();
            try{
            if (jTable4.getSelectedRow() != -1) {
                int id = (int) modelo.getValueAt(jTable4.getSelectedRow(), 0);
                int posicion = buscarEquipoEnArraylist(id);

                if(!"".equals(nombreEquipo.getText())){
                    equipos.get(posicion -1).setNombreEquipo(nombreEquipo.getText());
                }
                if(!"".equals(campeonatosGanadosEquipo.getText())){
                    equipos.get(posicion -1).setCampeonatosGanados(Integer.parseInt(campeonatosGanadosEquipo.getText()));
                }
                if(!"".equals(numeroEmpleados.getText())){
                    equipos.get(posicion -1).setNumeroEmpleados(Integer.parseInt(numeroEmpleados.getText()));
                }
                
                nombreEquipo.setText("");
                campeonatosGanadosEquipo.setText("");
                numeroEmpleados.setText("");
                
                
                for (int i = 0; i < pilotos.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    Piloto getC = (Piloto) pilotos.get(i);
                    jTable4.setValueAt(getC.getId_piloto(), i, 0);
                    jTable4.setValueAt(getC.getNombrePiloto(), i, 1);
                    jTable4.setValueAt(getC.getSueldo(), i, 2);
                    jTable4.setValueAt(getC.getNumeroVictorias(), i, 3);
                }
                
                nombrePiloto.setEnabled(false);
                sueldoPiloto.setEnabled(false);
                victoriasPiloto.setEnabled(false);

                modificarPiloto.setEnabled(true);
                eliminarPiloto.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila a actualizar.");
        }
        }
    }
    
    private void guardarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarEquipoActionPerformed
        // TODO add your handling code here:
        if(insertarEquipo.isEnabled() == true){  
            guardarInsertarEquipo();
        }
        
        if(insertarEquipo.isEnabled() == true){  
            modificarInsertarEquipo();
        }
    }//GEN-LAST:event_guardarEquipoActionPerformed

    private void cancelarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarEquipoActionPerformed
        // TODO add your handling code here:

        nombreEquipo.setText("");
        campeonatosGanadosEquipo.setText("");
        numeroEmpleados.setText("");
        
        nombreEquipo.setEnabled(false);
        campeonatosGanadosEquipo.setEnabled(false);
        numeroEmpleados.setEnabled(false);

        insertarEquipo.setEnabled(true);
        modificarEquipo.setEnabled(true);
        eliminarEquipo.setEnabled(true); 
    }//GEN-LAST:event_cancelarEquipoActionPerformed
    
    private int buscarMotorEnArraylist(int id){
        int i;
        boolean encontrado = false;
        for (i = 0; i < motores.size() && encontrado != true; i++) {
            if(motores.get(i).getId_motor() == id){
                encontrado = true;
            }
        }
        return i;
    }
            
    private void guardarInsertarMotor() {
        Motor m = new Motor();
            
            if (caballos.getText().equals("")
                || numeroUsosMotor.getText().equals("")
                || kilometrosMotor.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Rellena todos los campos para insertar un coche nuevo");

            } else {
                int id = generarId();
                m.setId_motor(id);
                m.setCaballos(Integer.parseInt(caballos.getText()));
                m.setNumeroUsos(Integer.parseInt(numeroUsosMotor.getText()));
                m.setKilometros(Integer.parseInt(kilometrosMotor.getText()));
                
                
                
                
                boolean existe = existeMotor(id);
                if(existe){
                    guardarInsertarMotor();
                    JOptionPane.showMessageDialog(null, "El motor ya existe");
                } else {
                
                motores.add(m);

                caballos.setText("");
                numeroUsosMotor.setText("");
                kilometrosMotor.setText("");
                
                for (int i = 0; i < motores.size(); i++) {
                    Motor getC = (Motor) motores.get(i);
                    jTable3.setValueAt(getC.getId_motor(), i, 0);
                    jTable3.setValueAt(getC.getCaballos(), i, 1);
                    jTable3.setValueAt(getC.getNumeroUsos(), i, 2);
                    jTable3.setValueAt(getC.getKilometros(), i, 3);
                }
                
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                
                caballos.setEnabled(false);
                numeroUsosMotor.setEnabled(false);
                kilometrosMotor.setEnabled(false);

                modificarMotor.setEnabled(true);
                eliminarMotor.setEnabled(true);

                }
            }
    }
    
    private void modificarInsertarMotor(){
         if(modificarMotor.isEnabled() == true){
            DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
            try{
            if (jTable3.getSelectedRow() != -1) {
                int id = (int) modelo.getValueAt(jTable3.getSelectedRow(), 0);
                int posicion = buscarMotorEnArraylist(id);

                if(!"".equals(caballos.getText())){
                    motores.get(posicion -1).setCaballos(Integer.parseInt(caballos.getText()));
                }
                if(!"".equals(numeroUsosMotor.getText())){
                    motores.get(posicion -1).setNumeroUsos(Integer.parseInt(numeroUsosMotor.getText()));
                }
                if(!"".equals(kilometrosMotor.getText())){
                    motores.get(posicion -1).setKilometros(Integer.parseInt(kilometrosMotor.getText()));
                }
                
                caballos.setText("");
                numeroUsosMotor.setText("");
                kilometrosMotor.setText("");
                
                
                for (int i = 0; i < motores.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    Motor getC = (Motor) motores.get(i);
                    jTable3.setValueAt(getC.getId_motor(), i, 0);
                    jTable3.setValueAt(getC.getCaballos(), i, 1);
                    jTable3.setValueAt(getC.getNumeroUsos(), i, 2);
                    jTable3.setValueAt(getC.getKilometros(), i, 3);
                }
                
                caballos.setEnabled(false);
                numeroUsosMotor.setEnabled(false);
                kilometrosMotor.setEnabled(false);

                modificarMotor.setEnabled(true);
                eliminarMotor.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila a actualizar.");
        }
        }
    }
        
    private void guardarMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarMotorActionPerformed
        // TODO add your handling code here:
        if(insertarMotor.isEnabled() == true){  
            guardarInsertarMotor();
        }
        
        if(insertarMotor.isEnabled() == true){  
            modificarInsertarMotor();
        }
    }//GEN-LAST:event_guardarMotorActionPerformed

    private void cancelarMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarMotorActionPerformed
        // TODO add your handling code here:
        
        caballos.setText("");
        numeroUsosMotor.setText("");
        kilometrosMotor.setText("");
        
        caballos.setEnabled(false);
        numeroUsosMotor.setEnabled(false);
        kilometrosMotor.setEnabled(false);

        insertarMotor.setEnabled(true);
        modificarMotor.setEnabled(true);
        eliminarMotor.setEnabled(true); 
    }//GEN-LAST:event_cancelarMotorActionPerformed

    
    private void guardarInsertarPiloto() {
        Piloto p = new Piloto();
            
        if (nombrePiloto.getText().equals("")
            || sueldoPiloto.getText().equals("")
            || victoriasPiloto.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Rellena todos los campos para insertar un coche nuevo");

        } else {
            int id = generarId();
            p.setId_piloto(id);
            p.setNombrePiloto(nombrePiloto.getText());
            p.setSueldo(Integer.parseInt(sueldoPiloto.getText()));
            p.setNumeroVictorias(Integer.parseInt(victoriasPiloto.getText()));

            boolean existe = existePiloto(id);
            if(existe){
                guardarInsertarPiloto();
                //JOptionPane.showMessageDialog(null, "El piloto ya existe");
            } else {
                
            pilotos.add(p);

            nombrePiloto.setText("");
            sueldoPiloto.setText("");
            victoriasPiloto.setText("");

            for (int i = 0; i < pilotos.size(); i++) {
                //jTable1.addRow(0, 0, 0, 0);
                Piloto getC = (Piloto) pilotos.get(i);
                jTable2.setValueAt(getC.getId_piloto(), i, 0);
                jTable2.setValueAt(getC.getNombrePiloto(), i, 1);
                jTable2.setValueAt(getC.getSueldo(), i, 2);
                jTable2.setValueAt(getC.getNumeroVictorias(), i, 3);
            }

            JOptionPane.showMessageDialog(null, "Datos Guardados");

            nombrePiloto.setEnabled(false);
            sueldoPiloto.setEnabled(false);
            victoriasPiloto.setEnabled(false);

            modificarPiloto.setEnabled(true);
            eliminarPiloto.setEnabled(true);

            }
        }
    }

    private void guardarActualizarPiloto() {
        if(botonActualizar.isEnabled() == true){
            DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
            try{
            if (jTable2.getSelectedRow() != -1) {
                String nombre = (String) modelo.getValueAt(jTable2.getSelectedRow(), 1);
                int posicion = buscarPilotoEnArraylist(nombre);

                if(!"".equals(nombrePiloto.getText())){
                    pilotos.get(posicion -1).setNombrePiloto(nombrePiloto.getText());
                }
                if(!"".equals(sueldoPiloto.getText())){
                    pilotos.get(posicion -1).setSueldo(Integer.parseInt(sueldoPiloto.getText()));
                }
                if(!"".equals(victoriasPiloto.getText())){
                    pilotos.get(posicion -1).setNumeroVictorias(Integer.parseInt(victoriasPiloto.getText()));
                }
                
                
                nombrePiloto.setText("");
                sueldoPiloto.setText("");
                victoriasPiloto.setText("");
                
                
                for (int i = 0; i < pilotos.size(); i++) {
                    //jTable1.addRow(0, 0, 0, 0);
                    Piloto getC = (Piloto) pilotos.get(i);
                    jTable2.setValueAt(getC.getId_piloto(), i, 0);
                    jTable2.setValueAt(getC.getNombrePiloto(), i, 1);
                    jTable2.setValueAt(getC.getSueldo(), i, 2);
                    jTable2.setValueAt(getC.getNumeroVictorias(), i, 3);
                }
                
                nombrePiloto.setEnabled(false);
                sueldoPiloto.setEnabled(false);
                victoriasPiloto.setEnabled(false);

                modificarPiloto.setEnabled(true);
                eliminarPiloto.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila a actualizar.");
        }
        }
    }
    
    private void guardarPilotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarPilotoActionPerformed
        // TODO add your handling code here:
                
        //INSERTAR
        if(insertarPiloto.isEnabled() == true){  
            guardarInsertarPiloto();
        }
            
        //ACTUALIZAR
        if(modificarPiloto.isEnabled() == true){
            guardarActualizarPiloto();
        }
        
    }//GEN-LAST:event_guardarPilotoActionPerformed

    private void cancelarPilotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarPilotoActionPerformed
        // TODO add your handling code here:

        nombrePiloto.setText("");
        sueldoPiloto.setText("");
        victoriasPiloto.setText("");
        
        nombrePiloto.setEnabled(false);
        sueldoPiloto.setEnabled(false);
        victoriasPiloto.setEnabled(false);

        insertarPiloto.setEnabled(true);
        modificarPiloto.setEnabled(true);
        eliminarPiloto.setEnabled(true); 
    }//GEN-LAST:event_cancelarPilotoActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
            if (jTable2.getSelectedRow() != -1) {
                String nombre = (String) modelo.getValueAt(jTable2.getSelectedRow(), 1);
                int i = buscarPilotoEnArraylist(nombre);
                    //jTable1.addRow(0, 0, 0, 0);
                    Piloto getC = (Piloto) pilotos.get(i-1);
                    nombrePiloto.setText(getC.getNombrePiloto());
                    sueldoPiloto.setText(Integer.toString(getC.getSueldo()));
                    victoriasPiloto.setText(Integer.toString(getC.getNumeroVictorias()));
            }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
                // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
            if (jTable3.getSelectedRow() != -1) {
                int idmotor = (int) modelo.getValueAt(jTable3.getSelectedRow(), 0);
                int i = buscarMotorEnArraylist(idmotor);
                    //jTable1.addRow(0, 0, 0, 0);
                    Motor getC = (Motor) motores.get(i-1);
                    numeroUsosMotor.setText(Integer.toString(getC.getNumeroUsos()));
                    kilometrosMotor.setText(Integer.toString(getC.getKilometos()));
                    caballos.setText(Integer.toString(getC.getCaballos()));
            }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
                // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) jTable4.getModel();
            if (jTable4.getSelectedRow() != -1) {
                int idequipo = (int) modelo.getValueAt(jTable4.getSelectedRow(), 0);
                int i = buscarEquipoEnArraylist(idequipo);
                    //jTable1.addRow(0, 0, 0, 0);
                    Equipo getC = (Equipo) equipos.get(i-1);
                    nombreEquipo.setText(getC.getNombreEquipo());
                    campeonatosGanadosEquipo.setText(Integer.toString(getC.getCampeonatosGanados()));
                    numeroEmpleados.setText(Integer.toString(getC.getNumeroEmpleados()));
            }
    }//GEN-LAST:event_jTable4MouseClicked
    private int buscarPilotoEnArraylist(String busqueda) {
        int i;
        boolean encontrado = false;
        for (i = 0; i < pilotos.size() && encontrado != true; i++) {
            if(pilotos.get(i).getNombrePiloto().equals(busqueda)){
                encontrado = true;
            }
        }
        return i;
    }
        
    private int buscarEnArraylist(String modelo){
        int i;
        boolean encontrado = false;
        for (i = 0; i < coches.size() && encontrado != true; i++) {
            if(coches.get(i).getModelo().equals(modelo)){
                encontrado = true;
            }
        }
        return i;
    }
    
    private boolean existe(String modelo){
        
        boolean encontrado = false;
        for (int i = 0; i < coches.size() && encontrado != true; i++) {
            if(coches.get(i).getModelo().equals(modelo)){
                encontrado = true;
            }
        }
        return encontrado;
    }
    
    private boolean existePiloto(int id){
        
        boolean encontrado = false;
        for (int i = 0; i < pilotos.size() && encontrado != true; i++) {
            if(pilotos.get(i).getId_piloto() == id){
                encontrado = true;
            }
        }
        return encontrado;
    }
        
    private boolean existeMotor(int id){
        
        boolean encontrado = false;
        for (int i = 0; i < motores.size() && encontrado != true; i++) {
            if(motores.get(i).getId_motor() == id){
                encontrado = true;
            }
        }
        return encontrado;
    }
            
    private boolean existeEquipo(int id){
        
        boolean encontrado = false;
        for (int i = 0; i < equipos.size() && encontrado != true; i++) {
            if(equipos.get(i).getId_equipo() == id){
                encontrado = true;
            }
        }
        return encontrado;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException, SQLException, ClassNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
            CRUDcoches c = new CRUDcoches();
            Image im = Toolkit.getDefaultToolkit().getImage("icons8_car_16.png");
            c.setIconImage(im);
            c.setTitle("Gestion Coches");
            c.setVisible(true);

        try {
             javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRUDcoches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            c.setVisible(true);
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonInsertar;
    private javax.swing.JTextPane caballos;
    private javax.swing.JTextField caballosjText;
    private javax.swing.JTextPane campeonatosGanadosEquipo;
    private javax.swing.JButton cancelarEquipo;
    private javax.swing.JButton cancelarMotor;
    private javax.swing.JButton cancelarPiloto;
    private javax.swing.JButton eliminarEquipo;
    private javax.swing.JButton eliminarMotor;
    private javax.swing.JButton eliminarPiloto;
    private javax.swing.JButton guardarEquipo;
    private javax.swing.JButton guardarMotor;
    private javax.swing.JButton guardarPiloto;
    private javax.swing.JButton insertarEquipo;
    private javax.swing.JButton insertarMotor;
    private javax.swing.JButton insertarPiloto;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextPane kilometrosMotor;
    private javax.swing.JTextField kilometrosjText;
    private javax.swing.JTextField marcajText;
    private javax.swing.JTextField modelojText;
    private javax.swing.JButton modificarEquipo;
    private javax.swing.JButton modificarMotor;
    private javax.swing.JButton modificarPiloto;
    private javax.swing.JTextPane nombreEquipo;
    private javax.swing.JTextPane nombrePiloto;
    private javax.swing.JTextPane numeroEmpleados;
    private javax.swing.JTextPane numeroUsosMotor;
    private javax.swing.JTextPane sueldoPiloto;
    private javax.swing.JTextPane victoriasPiloto;
    // End of variables declaration//GEN-END:variables

}
