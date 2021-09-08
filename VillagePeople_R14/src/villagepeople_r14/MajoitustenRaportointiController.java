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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
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
public class MajoitustenRaportointiController implements Initializable {
    private Connection m_conn;
    
    @FXML
    private TextField tfToimipisteID;
    
    @FXML
    private DatePicker datepickerAlku;

    @FXML
    private DatePicker datepickerLoppu;

    @FXML
    private TableView<MajoitusRaportti> table;

    @FXML
    private TableColumn<MajoitusRaportti, Integer> col_varausid;

    @FXML
    private TableColumn<MajoitusRaportti, Integer> col_asiakasid;

    @FXML
    private TableColumn<MajoitusRaportti, String> col_varauspvm;

    @FXML
    private TableColumn<MajoitusRaportti, Date> col_alkupvm;

    @FXML
    private TableColumn<MajoitusRaportti, Date> col_loppupvm;
    
    @FXML
    private Button btnSulje;

     
    ObservableList<MajoitusRaportti> oblist = FXCollections.observableArrayList();
    
    @FXML
    private void haeMajoitusRaportti(ActionEvent event) {
        
        
            int toimipisteId = Integer.parseInt(tfToimipisteID.getText());
            
            java.sql.Date sqlDateAlku =java.sql.Date.valueOf(datepickerAlku.getValue());
            java.sql.Date sqlDateLoppu =java.sql.Date.valueOf(datepickerLoppu.getValue());
            
            
            
        try {
            
            String sql = (("SELECT varaus_id, asiakas_id, varattu_pvm, varattu_alkupvm, varattu_loppupvm "
                        + "FROM Varaus WHERE toimipiste_id = ? AND varattu_alkupvm >= ? AND varattu_alkupvm <=?"));
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = Kanta.conn.prepareStatement(sql);
            ps.setInt(1, toimipisteId);
            ps.setDate(2, sqlDateAlku);
            ps.setDate(3, sqlDateLoppu);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                oblist.add(new MajoitusRaportti(rs.getInt("varaus_id"), rs.getInt("asiakas_id"),
                        rs.getString("varattu_pvm"), rs.getDate("varattu_alkupvm"), rs.getDate("varattu_loppupvm")));
            }
            
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(MajoitustenRaportointiController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            col_varausid.setCellValueFactory(new PropertyValueFactory<MajoitusRaportti, Integer>("varausID"));
            col_asiakasid.setCellValueFactory(new PropertyValueFactory<MajoitusRaportti, Integer>("asiakasId"));
            col_varauspvm.setCellValueFactory(new PropertyValueFactory<MajoitusRaportti, String>("varattupvm"));
            col_alkupvm.setCellValueFactory(new PropertyValueFactory<MajoitusRaportti, Date>("alkupvm"));
            col_loppupvm.setCellValueFactory(new PropertyValueFactory<MajoitusRaportti, Date>("loppupvm"));

            table.setItems(oblist);
            
            System.out.println("tiedot haettu nappi");
            
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
    
    @FXML
    private void suljeLomake(ActionEvent event) {
        Stage stage = 
                (Stage) btnSulje.getScene().getWindow();
        stage.close();
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
    
}
