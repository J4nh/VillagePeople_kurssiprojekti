/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author janih
 */
public class LaskujenHallintaController implements Initializable {
    
    private Lasku m_lasku = new Lasku();
    private Connection m_conn;
    
    @FXML
    private TextField tfLaskuID;

    @FXML
    private TextField tfAsiakasID;

    @FXML
    private TextField tfVarausID;
    
    @FXML
    private TextField tfNimi;

    @FXML
    private TextField tfLahiosoite;

    @FXML
    private TextField tfPostitoimipaikka;

    @FXML
    private TextField tfPostinumero;

    @FXML
    private TextField tfSumma;

    @FXML
    private TextField tfAlv;

    @FXML
    private TextField tfErapaiva;

    @FXML
    private RadioButton rdbtnMaksettu;

    @FXML
    private RadioButton rdbtnMaksamatta;

    @FXML
    private Button btnLisaaLasku;

    @FXML
    private Button btnMuutaLasku;

    @FXML
    private Button btnPoistaLasku;

    @FXML
    private Button btnHaeLasku;

    @FXML
    private Button btnHaeKaikkiLaskut;

    @FXML
    private Button btnHaeMaksamattomatLaskut;

    @FXML
    private Button btnSulje;
    
    @FXML
    private ToggleGroup tgTila;
    
    @FXML
    private DatePicker dpErapaiva;
    
    
    @FXML
    private void lisaaLasku(ActionEvent event) {
        
        int tila = 0;
        
        if (tgTila.getSelectedToggle()==rdbtnMaksettu) {
            tila = 1;
        }
        if (tgTila.getSelectedToggle()==rdbtnMaksamatta) {
            tila = 2;
        }
        
        int laskuID = Integer.parseInt(tfLaskuID.getText());
        int varausID = Integer.parseInt(tfVarausID.getText());
        int asiakasID = Integer.parseInt(tfAsiakasID.getText()); 
        String nimi = tfNimi.getText();
        String lahiosoite = tfLahiosoite.getText();
        String postitoimipaikka = tfPostitoimipaikka.getText();
        String postinumero = tfPostinumero.getText();
        double summa = Double.parseDouble(tfSumma.getText());
        double alv = Double.parseDouble(tfAlv.getText());
        
        //String erapaiva = tfErapaiva.getText();
        //Date erapaivaSQL = java.sql.Date.valueOf(erapaiva);
        
        java.sql.Date erapaivaSQL = java.sql.Date.valueOf(dpErapaiva.getValue());
        
        
        Kanta.lisaaLasku(Kanta.conn, laskuID, varausID, asiakasID, nimi, lahiosoite, postitoimipaikka, postinumero, summa, alv, erapaivaSQL, tila);
    }
    
    @FXML
    private void muutaLasku(ActionEvent event) {
        
        int tila = 0;
        
        if (tgTila.getSelectedToggle()==rdbtnMaksettu) {
            tila = 1;
        }
        if (tgTila.getSelectedToggle()==rdbtnMaksamatta) {
            tila = 2;
        }
        
        int laskuID = Integer.parseInt(tfLaskuID.getText());
        int varausID = Integer.parseInt(tfVarausID.getText());
        int asiakasID = Integer.parseInt(tfAsiakasID.getText()); 
        String nimi = tfNimi.getText();
        String lahiosoite = tfLahiosoite.getText();
        String postitoimipaikka = tfPostitoimipaikka.getText();
        String postinumero = tfPostinumero.getText();
        double summa = Double.parseDouble(tfSumma.getText());
        double alv = Double.parseDouble(tfAlv.getText());
        
        //String erapaiva = tfErapaiva.getText();
        //Date erapaivaSQL = java.sql.Date.valueOf(erapaiva);
        
        java.sql.Date erapaivaSQL = java.sql.Date.valueOf(dpErapaiva.getValue());
        
        
        Kanta.muutaLasku(Kanta.conn, laskuID, varausID, asiakasID, nimi, lahiosoite, postitoimipaikka, postinumero, summa, alv, erapaivaSQL, tila);
    }
    
    @FXML
    private void poistaLasku(ActionEvent event) {
        int laskuID = Integer.parseInt(tfLaskuID.getText());
       
        Kanta.poistaLasku(m_conn, laskuID);
    }
    
    
    @FXML
    private void suljeLomake(ActionEvent event) {
        Stage stage = 
                (Stage) btnSulje.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void tyhjennaLomake(ActionEvent event) {
        tfLaskuID.setText("");
        tfAsiakasID.setText("");
        tfVarausID.setText("");
        tfNimi.setText("");
        tfLahiosoite.setText("");
        tfPostitoimipaikka.setText("");
        tfPostinumero.setText("");
        tfSumma.setText("");
        tfAlv.setText("");
        dpErapaiva.setValue(LocalDate.now());
        rdbtnMaksamatta.setSelected(false);
        rdbtnMaksettu.setSelected(false);
    }
    
    @FXML
    private void haeLasku(ActionEvent event) {
        m_lasku = null;
        int laskuID = Integer.parseInt(tfLaskuID.getText());
       
        try {
            m_lasku = Lasku.haeLaskuntiedot(Kanta.conn, laskuID);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Laskua ei loydy.");
            alert.showAndWait();
        }
        if (m_lasku.getLaskuID()== 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Laskua ei loydy.");
            alert.showAndWait();
        } else {
            
        tfLaskuID.setText(String.valueOf(m_lasku.getLaskuID()));
        tfAsiakasID.setText(String.valueOf(m_lasku.getAsiakasID()));
        tfVarausID.setText(String.valueOf(m_lasku.getVarausId()));
        tfNimi.setText(m_lasku.getNimi());
        tfLahiosoite.setText(m_lasku.getOsoite());
        tfPostitoimipaikka.setText(m_lasku.getPostitoimipaikka());
        tfPostinumero.setText(m_lasku.getPostinro());
        tfSumma.setText(String.valueOf(m_lasku.getSumma()));
        tfAlv.setText(String.valueOf(m_lasku.getAlv()));
        dpErapaiva.setValue(m_lasku.getErapaiva().toLocalDate());

        int nappula = m_lasku.getTila();
        System.out.println(nappula);
        System.out.println(m_lasku.getLaskuID());
        
            if (nappula == 1) {
                rdbtnMaksamatta.setSelected(false);
                rdbtnMaksettu.setSelected(true);
            } else {
                if (nappula == 2) {
                rdbtnMaksamatta.setSelected(true);
                rdbtnMaksettu.setSelected(false);
                }
            }
        }
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
