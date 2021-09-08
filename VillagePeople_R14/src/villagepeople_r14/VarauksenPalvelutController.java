
package villagepeople_r14;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author janih
 */
public class VarauksenPalvelutController implements Initializable {

    private Connection m_conn;
    
     @FXML
    private Button btnTyhjenna;

    @FXML
    private TextField tfVarausID;

    @FXML
    private TextField tfPalveluID;

    @FXML
    private TextField tfLkm;
    
     @FXML
    private Button btnSulje;

    
    @FXML
    private void lisaaVarausPalvelu(ActionEvent event){

        int varausID = Integer.parseInt(tfVarausID.getText());
        int palveluID = Integer.parseInt(tfPalveluID.getText());
        int lkm = Integer.parseInt(tfLkm.getText());
        
        Kanta.lisaaVarausPalvelu(Kanta.conn, varausID, palveluID, lkm);
    }
    
    @FXML
    private void muutaVarausPalvelu(ActionEvent event){

        int varausID = Integer.parseInt(tfVarausID.getText());
        int palveluID = Integer.parseInt(tfPalveluID.getText());
        int lkm = Integer.parseInt(tfLkm.getText());
        
        Kanta.muutaVarausPalvelu(Kanta.conn, varausID, palveluID, lkm);
    }
    
    @FXML
    private void poistaVarausPalvelu(ActionEvent event) {
        int varausID = Integer.parseInt(tfVarausID.getText());
         int palveluID = Integer.parseInt(tfPalveluID.getText());

        Kanta.poistaVarausPalvelu(Kanta.conn, varausID, palveluID);
    }
    
    
    @FXML
    private void suljeLomake(ActionEvent event) {
        Stage stage =
                (Stage) btnSulje.getScene().getWindow();
        stage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            yhdista ();
            Kanta.conn.setAutoCommit(true);
            System.out.println("Yhdistetty");
        } catch (SQLException se) {
            System.out.println("Virhe tietokantaa avattaessa.");
        } catch (Exception e) {
            System.out.println("1Virhe tietokantaa avattaessa.");
        }
    }    
    
    /**
     * Metodi jolla yhdistetaan tietokantaan
     */
    public  void yhdista() throws SQLException, Exception {
        m_conn = null;
        String url = "jdbc:mariadb://maria.westeurope.cloudapp.azure.com"
                + ":3306/karelia_R14_VillagePeople?user=opiskelija&password=opiskelija";
        try {
            m_conn=DriverManager.getConnection(url);
        }
        catch (SQLException e) { // tietokantaan ei yhteyttä
            m_conn = null;
            throw e;
        }
        catch (Exception e ) {
            throw e;
        }

    }
}
