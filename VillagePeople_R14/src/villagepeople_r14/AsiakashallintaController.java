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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AsiakashallintaController implements Initializable {
    
    private Asiakas m_asiakas = new Asiakas();
    private Connection m_conn;

    @FXML
    private TextField tfAsiakasID;

    @FXML
    private TextField tfEtunimi;

    @FXML
    private TextField tfSukunimi;

    @FXML
    private TextField tfLahiosoite;

    @FXML
    private TextField tfPostitoimipaikka;
    
    @FXML
    private TextField tfPostinro;

   @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPuhelinnro;
    
    @FXML
    private Button btnLisaaTiedot;

    @FXML
    private Button btnPoistaTiedot;

    @FXML
    private Button btnMuutaTiedot;

    @FXML
    private Button btnSulje;


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
 @FXML
    private void lisaaAsiakas(ActionEvent event) throws ParseException {

      
        int AsiakasID = Integer.parseInt(tfAsiakasID.getText());
       String Etunimi = tfEtunimi.getText();
       String Sukunimi = tfSukunimi.getText();
       String Lahiosoite = tfLahiosoite.getText();
       String Postitoimipaikka = tfPostitoimipaikka.getText();
       String Postinro = tfPostinro.getText();
       String Email = tfEmail.getText();
       String Puhelinnro = tfPuhelinnro.getText();
       

  Kanta.lisaaAsiakas(Kanta.conn, AsiakasID, Etunimi, Sukunimi, Lahiosoite, Postitoimipaikka, Postinro, Email, Puhelinnro);
}
@FXML
    private void muutaAsiakas(ActionEvent event) {


        int AsiakasID = Integer.parseInt(tfAsiakasID.getText());
       String Etunimi = tfEtunimi.getText();
       String Sukunimi = tfSukunimi.getText();
       String Lahiosoite = tfLahiosoite.getText();
       String Postitoimipaikka = tfPostitoimipaikka.getText();
       String Postinro = tfPostinro.getText();
       String Email = tfEmail.getText();
       String Puhelinnro = tfPuhelinnro.getText();

Kanta.muutaAsiakas(Kanta.conn, AsiakasID, Etunimi, Sukunimi, Lahiosoite, Postitoimipaikka, Postinro, Email, Puhelinnro);
}

    @FXML
    private void suljeLomake(ActionEvent event) {
        Stage stage =
                (Stage) btnSulje.getScene().getWindow();
        stage.close();
    }



 @FXML
    private void poistaAsiakas(ActionEvent event) {
        
        int AsiakasID = Integer.parseInt(tfAsiakasID.getText());
       

        Kanta.poistaAsiakas(Kanta.conn, AsiakasID);
    }
    
    
    @FXML
    private void haeAsiakas(ActionEvent event) {
        m_asiakas = null;
        int asiakasID = Integer.parseInt(tfAsiakasID.getText());
       
        try {
            m_asiakas = Asiakas.haeAsiakkaantiedot(Kanta.conn, asiakasID);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Asiakasta ei loydy.");
            alert.showAndWait();
        }
        if (m_asiakas.getAsiakasID()== 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Asiakasta ei loydy.");
            alert.showAndWait();
        } else {
        
        tfAsiakasID.setText(String.valueOf(m_asiakas.getAsiakasID()));
        tfEtunimi.setText(m_asiakas.getEtunimi());
        tfSukunimi.setText(m_asiakas.getSukunimi());
        tfLahiosoite.setText(m_asiakas.getLahiosoite());
        tfPostitoimipaikka.setText(m_asiakas.getPostitoimipaikka());
        tfPostinro.setText(m_asiakas.getPostinro());
        tfEmail.setText(m_asiakas.getEmail());
        tfPuhelinnro.setText(m_asiakas.getPuhnro());

        }
    }

    @FXML
    private void tyhjennaLomake(ActionEvent event) {
        tfAsiakasID.setText("");
        tfEtunimi.setText("");
        tfSukunimi.setText("");
        tfLahiosoite.setText("");
        tfPostitoimipaikka.setText("");
        tfPostinro.setText("");
        tfEmail.setText("");
        tfPuhelinnro.setText("");
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