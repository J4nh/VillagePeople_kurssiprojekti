/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author janih
 */

public class PalveluidenHallintaController implements Initializable {
    
    private Palvelu m_palvelu = new Palvelu();
    private Connection m_conn;
    
    @FXML
    private TextField tfNimi;

    @FXML
    private TextField tfPalveluID;

    @FXML
    private TextField tfToimipisteID;

    @FXML
    private TextField tfHinta;

    @FXML
    private TextField tfAlv;

    @FXML
    private RadioButton rdbtnSafari;

    @FXML
    private RadioButton rdbtnLiikunta;

    @FXML
    private RadioButton rdbtnAjelu;

    @FXML
    private Button btnLisaaTiedot;

    @FXML
    private Button btnPoistaTiedot;

    @FXML
    private Button btnMuutaTiedot;
    
    @FXML
    private TextArea taKuvaus;
    
    @FXML
    private Button btnSulje;
    
    @FXML
    private ToggleGroup tgTyyppi;
    
    @FXML
    private Button btnHaePalvelunTiedot;
    
     @FXML
    private Button btnTyhjenna;
    
    
    @FXML
    private void lisaaPalvelu(ActionEvent event) {
        
        
        int tyyppi = 0;
        
        if (tgTyyppi.getSelectedToggle()==rdbtnSafari) {
            tyyppi = 1;
        }
        if (tgTyyppi.getSelectedToggle()==rdbtnLiikunta) {
            tyyppi = 2;
        }
        if (tgTyyppi.getSelectedToggle()==rdbtnAjelu) {
            tyyppi = 3;
        }
    
        int palveluID = Integer.parseInt(tfPalveluID.getText());
        
        int toimipisteID = Integer.parseInt(tfToimipisteID.getText());
        
        String nimi = tfNimi.getText();

        String kuvaus = taKuvaus.getText();
        
        double hinta = Double.parseDouble(tfHinta.getText());
        
        double alv = Double.parseDouble(tfAlv.getText());
        
       
        Kanta.lisaaPalvelu(Kanta.conn, palveluID, toimipisteID, nimi, tyyppi, kuvaus, hinta, alv);
        
    }
    
    @FXML
    private void muutaPalvelu(ActionEvent event) {
        

        int tyyppi = 0;
        
        if (tgTyyppi.getSelectedToggle()==rdbtnSafari) {
            tyyppi = 1;
        }
        if (tgTyyppi.getSelectedToggle()==rdbtnLiikunta) {
            tyyppi = 2;
        }
        if (tgTyyppi.getSelectedToggle()==rdbtnAjelu) {
            tyyppi = 3;
        }
    
        int palveluID = Integer.parseInt(tfPalveluID.getText());
        int toimipisteID = Integer.parseInt(tfToimipisteID.getText());
        String nimi = tfNimi.getText();
        String kuvaus = taKuvaus.getText();
        double hinta = Double.parseDouble(tfHinta.getText());
        double alv = Double.parseDouble(tfAlv.getText());
       
        Kanta.muutaPalvelu(Kanta.conn, palveluID, toimipisteID, nimi, tyyppi, kuvaus, hinta, alv);
    }
    
    @FXML
    private void poistaPalvelu(ActionEvent event) {
        int palveluID = Integer.parseInt(tfPalveluID.getText());
       
        Kanta.poistaPalvelu(Kanta.conn, palveluID);
    }
    
    //Sulkee lomakkeen
    @FXML
    private void suljeLomake(ActionEvent event) {
        Stage stage = 
                (Stage) btnSulje.getScene().getWindow();
        stage.close();
    }
    
    
    @FXML
    private void haePalvelu(ActionEvent event) {
        m_palvelu = null;
        int palveluID = Integer.parseInt(tfPalveluID.getText());
       
        try {
            m_palvelu = Palvelu.haePalveluntiedot(Kanta.conn, palveluID);
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Palvelun tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Palvelua ei loydy.");
            alert.showAndWait();
        }
        if (m_palvelu.getPalveluID() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Palvelun tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Palvelua ei loydy.");
            alert.showAndWait();
        } else {
        
        tfNimi.setText(m_palvelu.getNimi());
        tfPalveluID.setText(String.valueOf(m_palvelu.getPalveluID()));
        tfToimipisteID.setText(String.valueOf(m_palvelu.getToimipisteID()));
        taKuvaus.setText(m_palvelu.getKuvaus());
        tfHinta.setText(String.valueOf(m_palvelu.getHinta()));
        tfAlv.setText(String.valueOf(m_palvelu.getAlv()));
        
        int nappula = m_palvelu.getTyyppi();
            if (nappula == 1) {
                rdbtnAjelu.setSelected(false);
                rdbtnLiikunta.setSelected(false);
                rdbtnSafari.setSelected(true);
            } else {
                if (nappula == 2) {
                rdbtnAjelu.setSelected(false);
                rdbtnLiikunta.setSelected(true);
                rdbtnSafari.setSelected(false);
            } else {
                if (nappula == 3) {
                rdbtnAjelu.setSelected(true);
                rdbtnLiikunta.setSelected(false);
                rdbtnSafari.setSelected(false);
                }
            }
            }
        }
    }
    
    @FXML
    private void tyhjennaLomake(ActionEvent event) {
        tfNimi.setText("");
        tfPalveluID.setText("");
        tfToimipisteID.setText("");
        taKuvaus.setText("");
        tfHinta.setText("");
        tfAlv.setText("");
        rdbtnAjelu.setSelected(false);
        rdbtnLiikunta.setSelected(false);
        rdbtnSafari.setSelected(false);
    }
    
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
    
}
