/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author janih
 */
public class PalveluidenRaportointiController implements Initializable {
    private Connection m_conn;

    @FXML
    private DatePicker datepickerAlku;

    @FXML
    private DatePicker datepickerLoppu;

    @FXML
    private Button btnSulje;

    @FXML
    private TableView<PalveluRaportti> table;

    @FXML
    private TableColumn<PalveluRaportti, Integer> col_varausid;

    @FXML
    private TableColumn<PalveluRaportti, Integer> col_palveluid;

    @FXML
    private TableColumn<PalveluRaportti, Integer> col_lkm;

    @FXML
    private TextField tfToimipisteID;
    
    ObservableList<PalveluRaportti> oblist = FXCollections.observableArrayList();
    
    @FXML
    private void haePalveluRaportti(ActionEvent event){
        
        int toimipisteId = Integer.parseInt(tfToimipisteID.getText());
            
        java.sql.Date sqlDateAlku =java.sql.Date.valueOf(datepickerAlku.getValue());
        java.sql.Date sqlDateLoppu =java.sql.Date.valueOf(datepickerLoppu.getValue());
        
        try {
           String sql = ("SELECT varaus_id,palvelu_id,lkm\n" +
"FROM Varauksen_palvelut\n" +
"WHERE varaus_id IN (SELECT varaus_id\n" +
"FROM Varaus WHERE Varaus.toimipiste_id = ? AND varattu_alkupvm >= ? AND varattu_alkupvm <=?);");
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = Kanta.conn.prepareStatement(sql);
            ps.setInt(1, toimipisteId);
            ps.setDate(2, sqlDateAlku);
            ps.setDate(3, sqlDateLoppu);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                oblist.add(new PalveluRaportti(rs.getInt("varaus_id"),rs.getInt("palvelu_id"),rs.getInt("lkm")));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(MajoitustenRaportointiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_varausid.setCellValueFactory(new PropertyValueFactory<PalveluRaportti, Integer>("varausID"));
        col_palveluid.setCellValueFactory(new PropertyValueFactory<PalveluRaportti, Integer>("palveluID"));
        col_lkm.setCellValueFactory(new PropertyValueFactory<PalveluRaportti, Integer>("lkm"));
        
        table.setItems(oblist);
    }
    
    @FXML
    private void tyhjennaTable(ActionEvent event) {
        table.getItems().clear();
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
    private void suljeLomake(ActionEvent event) {
        Stage stage = 
                (Stage) btnSulje.getScene().getWindow();
        stage.close();
    }
    
}
