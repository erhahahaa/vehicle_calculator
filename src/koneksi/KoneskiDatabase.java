package koneksi; 
import java.sql.*;
import javax.swing.JOptionPane;
public class KoneskiDatabase {
    Connection cn;
    public static Connection BukaKoneksi(){
     try{
         Class.forName("com.mysql.jdbc.Driver");
         Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/kalkulator_mobil", "root", "");
         return cn;
     }catch (Exception e){
         JOptionPane.showMessageDialog(null, e);
         return null;
     }
    }
}
