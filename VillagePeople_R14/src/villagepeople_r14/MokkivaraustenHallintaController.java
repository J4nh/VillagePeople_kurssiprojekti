package villagepeople_r14;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author Tomi Kiiskinen
 */
public class MokkivaraustenHallintaController implements Initializable {

    private Connection m_conn;
    private Varaus m_varaus = new Varaus();

    @FXML
    private TextField tfVarausID;

    @FXML
    private TextField tfAsiakasID;

    @FXML
    private TextField tfToimipisteID;

    @FXML
    private TextField tfVarausPVM;

    @FXML
    private TextField tfVahvistusPVM;

    @FXML
    private TextField tfVarauksenAlkuPVM;

    @FXML
    private TextField tfVarauksenLoppuPVM;

    @FXML
    private Button btnSulje;

    @FXML
    private Button btnLisaaTiedot;

    @FXML
    private Button btnPoistaTiedot;

    @FXML
    private Button btnMuutaTiedot;

    @FXML
    private Button btnTyhjenna;

    @FXML
    private Button btnHaeMokinTiedot;

     @FXML
    private DatePicker dpAlkupvm;

    @FXML
    private DatePicker dpLoppupvm;


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
    private void lisaaMokki(ActionEvent event) {


        int varausID = Integer.parseInt(tfVarausID.getText());
        int asiakasID = Integer.parseInt(tfAsiakasID.getText());
        int toimipisteID = Integer.parseInt(tfToimipisteID.getText());

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String varausPVM = sdf.format(cal.getTime());

        //String varauksenAlkuPVM = tfVarauksenAlkuPVM.getText();
        //java.sql.Date varauksenAlkuPVMSQL = java.sql.Date.valueOf(varauksenAlkuPVM);
        
        //String varauksenLoppuPVM = tfVarauksenLoppuPVM.getText();
        //java.sql.Date varauksenLoppuPVMSQL = java.sql.Date.valueOf(varauksenLoppuPVM);
        
        java.sql.Date varauksenAlkuPVMSQL = java.sql.Date.valueOf(dpAlkupvm.getValue());
        
        java.sql.Date varauksenLoppuPVMSQL = java.sql.Date.valueOf(dpLoppupvm.getValue());

        Kanta.lisaaMokki(Kanta.conn, varausID, asiakasID, toimipisteID, varausPVM, varauksenAlkuPVMSQL, varauksenLoppuPVMSQL);
    }

    @FXML
    private void muutaMokki(ActionEvent event) {


        int varausID = Integer.parseInt(tfVarausID.getText());
        int asiakasID = Integer.parseInt(tfAsiakasID.getText());
        int toimipisteID = Integer.parseInt(tfToimipisteID.getText());
        //String vahvistus = tfVarausPVM.getText();
        
        //String varauksenAlkuPVM = tfVarauksenAlkuPVM.getText();
        //java.sql.Date varauksenAlkuPVMSQL = java.sql.Date.valueOf(varauksenAlkuPVM);
        
        //String varauksenLoppuPVM = tfVarauksenLoppuPVM.getText();
        //java.sql.Date varauksenLoppuPVMSQL = java.sql.Date.valueOf(varauksenLoppuPVM);
        
        java.sql.Date varauksenAlkuPVMSQL = java.sql.Date.valueOf(dpAlkupvm.getValue());
        
        java.sql.Date varauksenLoppuPVMSQL = java.sql.Date.valueOf(dpLoppupvm.getValue());


        Kanta.muutaMokki(Kanta.conn, varausID, asiakasID, toimipisteID, varauksenAlkuPVMSQL, varauksenLoppuPVMSQL);
    }

    @FXML
    private void poistaMokki(ActionEvent event) {
        int palveluID = Integer.parseInt(tfVarausID.getText());

        Kanta.poistaMokki(m_conn, palveluID);
    }



    @FXML
    private void suljeLomake(ActionEvent event) {
        Stage stage =
                (Stage) btnSulje.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void tyhjennaLomake(ActionEvent event) {
        tfVarausID.setText("");
        tfAsiakasID.setText("");
        tfToimipisteID.setText("");
        tfVarausPVM.setText("");
        // vahvistus?
        //tfVarauksenAlkuPVM.setText("");
        //tfVarauksenLoppuPVM.setText("");
        dpAlkupvm.setValue(LocalDate.now());
        dpLoppupvm.setValue(LocalDate.now());
        
    }

    @FXML
    private void haeVaraus(ActionEvent event) {
        m_varaus = null;
        int palveluID = Integer.parseInt(tfVarausID.getText());

        try {
            m_varaus = Varaus.haeVarauksentiedot(Kanta.conn, palveluID);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Varausta ei loydy.");
            alert.showAndWait();
        }
        if (m_varaus.getVarausID() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Varausta ei loydy.");
            alert.showAndWait();
        } else {

            tfVarausID.setText(String.valueOf(m_varaus.getVarausID()));
            tfAsiakasID.setText(String.valueOf(m_varaus.getAsiakasID()));
            tfToimipisteID.setText(String.valueOf(m_varaus.getToimipisteID()));
            tfVarausPVM.setText(m_varaus.getVarattu_PVM());
            //tfVarauksenAlkuPVM.setText(m_varaus.getVarattu_alkuPVM());
            //tfVarauksenLoppuPVM.setText(m_varaus.getVarattu_loppuPVM());
            dpAlkupvm.setValue(m_varaus.getVarattu_alkuPVM().toLocalDate());
            dpLoppupvm.setValue(m_varaus.getVarattu_loppuPVM().toLocalDate());
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
