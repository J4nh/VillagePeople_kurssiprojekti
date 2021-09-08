/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author janih
 */
public class ValikkoController implements Initializable {
    
     private Connection m_conn;

    @FXML
    private Button btnMokkiVarausHallinta;

    @FXML
    private Button btnPalveluHallinta;

    @FXML
    private Button btnToimipisteHallinta;

    @FXML
    private Button btnAsiakasHallinta;

    @FXML
    private Button btnLaskujenHallinta;

    @FXML
    private Button btnMajoitusRaportointi;

    @FXML
    private Button btnPalveluidenRaportointi;

    @FXML
    private Button btnLaskutus;

    @FXML
    private Button btnLopeta;
    
    
    @FXML
    private void avaaPalveluHallinta(ActionEvent event) throws IOException {
        Parent root = 
                FXMLLoader.load(getClass().getResource("PalveluidenHallinta.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Palveluiden hallinta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
     @FXML
    private void avaaMokkivaraustenHallinta(ActionEvent event) throws IOException {
        Parent root = 
                FXMLLoader.load(getClass().getResource("Mokkivarausten hallinta.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Mökkien hallinta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
@FXML
    private void avaaAsiakasHallinta(ActionEvent event) throws IOException {
        Parent root = 
                FXMLLoader.load(getClass().getResource("Asiakas hallinta.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Asiakas Hallinta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    private void avaaToimipistedenHallinta(ActionEvent event) throws IOException {
        Parent root =
                FXMLLoader.load(getClass().getResource("ToimipisteidenHallinta.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Toimipisteiden hallinta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
     @FXML
    private void avaaLaskujenHallinta(ActionEvent event) throws IOException {
        Parent root = 
                FXMLLoader.load(getClass().getResource("LaskujenHallinta.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Laskujen hallinta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

  
    @FXML
    private void avaaVarauksenPalvelut(ActionEvent event) throws IOException {
        Parent root =
                FXMLLoader.load(getClass().getResource("VarauksenPalvelut.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Varauksen palveluiden hallinta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
    @FXML
    private void avaaMajoitustenRaportointi(ActionEvent event) throws IOException {
        Parent root =
                FXMLLoader.load(getClass().getResource("MajoitustenRaportointi.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Majoitusten raportointi");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
    @FXML
    private void avaaPalveluidenRaportointi(ActionEvent event) throws IOException {
        Parent root =
                FXMLLoader.load(getClass().getResource("PalveluidenRaportointi.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Palveluiden raportointi");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
    
    @FXML
    private void lopetaValikko(ActionEvent event) {
        System.exit(0);
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
