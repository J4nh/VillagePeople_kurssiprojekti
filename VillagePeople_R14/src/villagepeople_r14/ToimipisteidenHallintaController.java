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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author jesse
 */
public class ToimipisteidenHallintaController implements Initializable {
    
    private Connection m_conn;
    private Toimipiste m_toimipiste = new Toimipiste();
    
    @FXML
    private TextField tfNimi;

    @FXML
    private TextField tfToimipisteID;

    @FXML
    private TextField tfLahiosoite;

    @FXML
    private TextField tfPostitoimipaikka;
    
    @FXML
    private TextField tfPostinumero;

    @FXML
    private Button btnLisaaTiedot;

    @FXML
    private Button btnMuutaTiedot;
    
    @FXML
    private Button btnPoistaTiedot;

    @FXML
    private Button btnSulje;

    @FXML
    private Button btnTyhjenna;

    @FXML
    private Button btnHaeToimipisteenTiedot;
 
    
    
    /**
     * Metodi jolla yhdistetaan tietokantaan
     * @throws java.sql.SQLException
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
    private void lisaaToimipiste(ActionEvent event) {

        int toimipisteID = Integer.parseInt(tfToimipisteID.getText());
        String nimi = tfNimi.getText();
        String lahiosoite = tfLahiosoite.getText();
        String postitoimipaikka = tfPostitoimipaikka.getText();
        String pnumero = tfPostinumero.getText();
     
        Kanta.lisaaToimipiste(Kanta.conn, toimipisteID, nimi, lahiosoite, postitoimipaikka, pnumero);
        
    }

    @FXML
    private void muutaToimipiste(ActionEvent event) {

        int toimipisteID = Integer.parseInt(tfToimipisteID.getText());
        String nimi = tfNimi.getText();
        String lahiosoite = tfLahiosoite.getText();
        String postitoimipaikka = tfPostitoimipaikka.getText();
        String pnumero = tfPostinumero.getText();

        Kanta.muutaToimipiste(Kanta.conn, toimipisteID, nimi, lahiosoite, postitoimipaikka, pnumero);
    }

    @FXML
    private void poistaToimipiste(ActionEvent event) {
        int palveluID = Integer.parseInt(tfToimipisteID.getText());

        Kanta.poistaTomipiste(m_conn, palveluID);
    }

    @FXML
    private void tyhjennaLomake(ActionEvent event) {
        tfToimipisteID.setText("");
        tfNimi.setText("");
        tfLahiosoite.setText("");
        tfPostitoimipaikka.setText("");
        tfPostinumero.setText("");
    }

    @FXML
    private void suljeLomake(ActionEvent event) {
        Stage stage =
                (Stage) btnSulje.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void haeToimipiste(ActionEvent event) {
        m_toimipiste = null;
        int toimipiste_id = Integer.parseInt(tfToimipisteID.getText());

        try {
            m_toimipiste = Toimipiste.haeToimipisteenTiedot(Kanta.conn, toimipiste_id);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimipisteen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Toimipistettä ei löydy.");
            alert.showAndWait();
        }
        if (m_toimipiste.getToimipiste_id() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimipisteen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Toimipistettä ei löydy.");
            alert.showAndWait();
        } else {

            tfToimipisteID.setText(String.valueOf(m_toimipiste.getToimipiste_id()));
            tfNimi.setText(m_toimipiste.getNimi());
            tfLahiosoite.setText(m_toimipiste.getLahiosoite());
            tfPostitoimipaikka.setText(m_toimipiste.getPostitoimipaikka());
            tfPostinumero.setText(m_toimipiste.getPostinro());
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

