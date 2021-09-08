/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

import java.sql.*;

import javafx.scene.control.Alert;

/**
 *
 * @author janih
 */
public class Kanta {
    static Connection conn = avaaYhteys(
            "jdbc:mariadb://maria.westeurope.cloudapp.azure.com"
                    + ":3306/karelia_R14_VillagePeople?user=opiskelija&password=opiskelija"
    );;

    /**
     * Metodi jolla avataan yhteys tietokantaan
     */
    public static Connection avaaYhteys(String connString) {
        try {
            Connection yhteys = DriverManager.getConnection(connString);
            System.out.println("\t>> Yhteys ok");
            return yhteys;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodi jolla suljetaan yhteys tietokantaan
     */
    public static void suljeYhteys(Connection c) {
        try {
            if(c!=null)
                c.close();
            System.out.println("\t>> Tietokantayhteys suljettu");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        conn = avaaYhteys(
                "jdbc:mariadb://maria.westeurope.cloudapp.azure.com"
                        + ":3306/karelia_R14_VillagePeople?user=opiskelija&password=opiskelija"
        );

        suljeYhteys(conn);
    }


    public static void lisaaPalvelu(Connection c, int palveluID, int toimipisteID, String nimi, int tyyppi, String kuvaus, double hinta, double alv){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO Palvelu (palvelu_id, toimipiste_ID, nimi, tyyppi, kuvaus, hinta, alv) VALUES(?,?,?,?,?,?,?);"
                   
                    
            );
            ps.setInt(1, palveluID);
            ps.setInt(2, toimipisteID);
            ps.setString(3, nimi);
            ps.setInt(4, tyyppi);
            ps.setString(5, kuvaus);
            ps.setDouble(6, hinta);
            ps.setDouble(7, alv);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Palvelun tietojen lisaaminen");
		alert.setHeaderText("Toiminto ok.");
		alert.setContentText("Palvelun tiedot lisatty tietokantaan.");
		alert.showAndWait();
            System.out.println("Palvelun "+nimi+" tiedot lisatty");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Palvelun tietojen lisaaminen");
		alert.setHeaderText("Tietokantavirhe");
		alert.setContentText("Palvelun tietojen lisääminen ei onnistu.");
		alert.showAndWait();
            e.printStackTrace();
        }
    }
    
    
    public static void muutaPalvelu(Connection c, int palveluID, int toimipisteID, String nimi, int tyyppi, String kuvaus, double hinta, double alv){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE Palvelu SET palvelu_id = ?, toimipiste_id = ?, nimi = ?, tyyppi= ?, kuvaus = ?, hinta = ?, alv = ? WHERE palvelu_id =?;"      
            );
            ps.setInt(1, palveluID);
            ps.setInt(2, toimipisteID);
            ps.setString(3, nimi);
            ps.setInt(4, tyyppi);
            ps.setString(5, kuvaus);
            ps.setDouble(6, hinta);
            ps.setDouble(7, alv);
            ps.setInt(8, palveluID);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Palvelun tietojen muuttaminen");
		alert.setHeaderText("Ok.");
		alert.setContentText("Palvelun tiedot muutettu.");
		alert.showAndWait();
            System.out.println("Palvelun "+palveluID+" tiedot muutettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Palvelun tietojen muuttaminen");
		alert.setHeaderText("Tietokantavirhe");
		alert.setContentText("Palvelun tietojen muuttaminen ei onnistu.");
		alert.showAndWait();
            e.printStackTrace();
        }
    }
    
     public static void poistaPalvelu(Connection c, int palveluID){
        try {
            PreparedStatement ps = c.prepareStatement(
            "DELETE FROM Palvelu WHERE palvelu_id = ? ;"
            );
            ps.setInt(1, palveluID);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Palvelun tietojen poistaminen");
		alert.setHeaderText("Toiminto ok.");
		alert.setContentText("Palvelun tiedot poistettu.");
		alert.showAndWait();
            System.out.println("Palvelun "+palveluID+" tiedot poistettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Palvelun tietojen poistaminen");
		alert.setHeaderText("Tietokantavirhe");
		alert.setContentText("Palvelun tietojen poistaminen ei onnistu.");
		alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void lisaaMokki(Connection c, int varausID, int asiakasID, int toimipisteID, String varausPVM, java.sql.Date varauksenAlkuPVM, java.sql.Date varauksenLoppuPVM){
        try {
            PreparedStatement ps = c.prepareStatement(

                    "INSERT INTO Varaus (varaus_id, asiakas_id, toimipiste_id, varattu_pvm, varattu_alkupvm, varattu_loppupvm) VALUES(?,?,?,?,?,?);"
                    //"INSERT INTO Varaus (varaus_id, asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm) VALUES(?,?,?,?,?,?,?);"
            );
            ps.setInt(1, varausID);
            ps.setInt(2, asiakasID);
            ps.setInt(3,toimipisteID);
            ps.setString(4, varausPVM);
            // Vahvistus pvm puutttuu
            ps.setDate(5, varauksenAlkuPVM);
            ps.setDate(6, varauksenLoppuPVM);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mökin tietojen lisaaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Mökin tiedot lisatty tietokantaan.");
            alert.showAndWait();
            System.out.println("Mökki "+varausID+" tiedot lisatty");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mökin tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Mökin tietojen lisääminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void muutaMokki(Connection c, int varausID, int asiakasID, int toimipisteID, java.sql.Date varauksenAlkuPVM, java.sql.Date varauksenLoppuPVM){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE Varaus SET varaus_id = ?, asiakas_id = ?, toimipiste_id = ?, varattu_alkupvm = ?, varattu_loppupvm = ? WHERE varaus_id =?;"
            );
            ps.setInt(1, varausID);
            ps.setInt(2, asiakasID);
            ps.setInt(3, toimipisteID);
            //ps.setString(3, varausPVM); Ei tarvitse, koska varausken paivamaaraa ei muuteta!
            // Vahvistus pvm puutttuu
            ps.setDate(4, varauksenAlkuPVM);
            ps.setDate(5, varauksenLoppuPVM);
            ps.setInt(6, varausID);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mökin tietojen muuttaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Mökin tiedot muutettu tietokantaan.");
            alert.showAndWait();
            System.out.println("Mökki "+varausID+" tiedot muutettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mökin tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Mökin tietojen muuttaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void poistaMokki(Connection c, int varausID){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM Varaus WHERE varaus_id = ? ;"
            );
            ps.setInt(1, varausID);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mökin tietojen poistaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Mökin tiedot poistettu.");
            alert.showAndWait();
            System.out.println("Mökki "+varausID+" tiedot poistettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mökin tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Mökin tietojen poistaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

 
    public static void lisaaLasku(Connection c, int laskuID, int varausID, int asiakasID, String nimi, String lahiosoite, String postitoimipaikka, String postinumero, double summa, double alv, Date erapaiva, int tila){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO Lasku (lasku_id, varaus_id, asiakas_id, nimi, lahiosoite, postitoimipaikka, postinro, summa, alv, erapaiva, tila) VALUES(?,?,?,?,?,?,?,?,?,?,?);"

            );
            ps.setInt(1, laskuID);
            ps.setInt(2, varausID);
            ps.setInt(3, asiakasID);
            ps.setString(4, nimi);
            ps.setString(5, lahiosoite);
            ps.setString(6, postitoimipaikka);
            ps.setString(7, postinumero);
            ps.setDouble(8, summa);
            ps.setDouble(9, alv);
            ps.setDate(10, erapaiva);
            ps.setInt(11, tila);


            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Laskun tietojen lisaaminen");
		alert.setHeaderText("Toiminto ok.");
		alert.setContentText("Laskun tiedot lisatty tietokantaan.");
		alert.showAndWait();
            System.out.println("Laskun "+laskuID+" tiedot lisatty");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Laskun tietojen lisaaminen");
		alert.setHeaderText("Tietokantavirhe");
		alert.setContentText("Laskun tietojen lisääminen ei onnistu.");
		alert.showAndWait();
            e.printStackTrace();
        }
    }
    

    public static void muutaLasku(Connection c, int laskuID, int varausID, int asiakasID, String nimi, String lahiosoite, String postitoimipaikka, String postinumero, double summa, double alv, Date erapaiva, int tila){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE Lasku SET lasku_id = ?, varaus_id = ?, asiakas_id= ?, nimi = ?, lahiosoite = ?, postitoimipaikka = ?, postinro = ?, summa = ?, alv = ?, erapaiva = ?, tila = ? WHERE lasku_id =?;"
            );
            ps.setInt(1, laskuID);
            ps.setInt(2, varausID);
            ps.setInt(3, asiakasID);
            ps.setString(4, nimi);
            ps.setString(5, lahiosoite);
            ps.setString(6, postitoimipaikka);
            ps.setString(7, postinumero);
            ps.setDouble(8, summa);
            ps.setDouble(9, alv);
            ps.setDate(10, erapaiva);
            ps.setInt(11, tila);
            ps.setInt(12, laskuID);

            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Laskun tietojen muuttaminen");
            alert.setHeaderText("Ok.");
            alert.setContentText("Laskun tiedot muutettu.");
            alert.showAndWait();
            System.out.println("Laskun "+laskuID+" tiedot muutettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Laskun tietojen muuttaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void poistaLasku(Connection c, int laskuID){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM Lasku WHERE lasku_id = ? ;"
            );
            ps.setInt(1, laskuID);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Laskun tietojen poistaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Laskun tiedot poistettu.");
            alert.showAndWait();
            System.out.println("Laskun "+laskuID+" tiedot poistettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Laskun tietojen poistaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    public static void lisaaAsiakas(Connection c, int AsiakasID, String Etunimi, String Sukunimi, String Lahiosoite, String Postitoimipaikka, String Postinro, String Email, String Puhelinnro){
        try {
            PreparedStatement ps = c.prepareStatement(

                    "INSERT INTO Asiakas (asiakas_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro) VALUES(?,?,?,?,?,?,?,?);"
            );
            ps.setInt(1, AsiakasID);
            ps.setString(2, Etunimi);
            ps.setString(3, Sukunimi);
            ps.setString(4, Lahiosoite);
            ps.setString(5, Postitoimipaikka);
            ps.setString(6, Postinro);
            ps.setString(7, Email);
            ps.setString(8, Puhelinnro);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Asiakkaan tietojen lisaaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Asiakkaan tiedot lisatty tietokantaan.");
            alert.showAndWait();
            System.out.println("Asiakaan "+AsiakasID+" tiedot lisatty");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakkaan tietojen lisääminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void muutaAsiakas(Connection c, int AsiakasID, String Etunimi, String Sukunimi, String Lahiosoite, String Postitoimipaikka, String Postinro, String Email, String Puhelinnro){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE Asiakas SET asiakas_id = ?, etunimi = ?, sukunimi = ?, lahiosoite = ?, postitoimipaikka = ?, postinro = ?, email = ?, puhelinnro = ? WHERE asiakas_id =?;"
            );
            ps.setInt(1, AsiakasID);
            ps.setString(2, Etunimi);
            ps.setString(3, Sukunimi);
            ps.setString(4, Lahiosoite);
            ps.setString(5, Postitoimipaikka);
            ps.setString(6, Postinro);
            ps.setString(7, Email);
            ps.setString(8, Puhelinnro);
            ps.setInt(9, AsiakasID);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Asiakkaan tiedot muokattu");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Asiakkaan tiedot muutettu tietokantaan.");
            alert.showAndWait();
            System.out.println("Asiakas "+AsiakasID+" tiedot muutettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakkaan tietojen muuttaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void poistaAsiakas(Connection c, int AsiakasID){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM Asiakas WHERE asiakas_id = ? ;"
            );
            ps.setInt(1, AsiakasID);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Asiakkaan tietojen poistaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Asiakkkan tiedot poistettu.");
            alert.showAndWait();
            System.out.println("Asiakkaan "+AsiakasID+" tiedot poistettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakkaan tietojen poistaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    static void lisaaToimipiste(Connection c, int toimipiste_id, String nimi, String lahiosoite, String postitoimipaikka, String postinro) {
        try {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO Toimipiste (toimipiste_id, nimi, lahiosoite, postitoimipaikka, postinro) VALUES(?,?,?,?,?);"

            );
            ps.setInt(1, toimipiste_id);
            ps.setString(2, nimi);
            ps.setString(3, lahiosoite);
            ps.setString(4, postitoimipaikka);
            ps.setString(5, postinro);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Toimipisteen tietojen lisaaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Toimipisteen tiedot lisatty tietokantaan.");
            alert.showAndWait();
            System.out.println("Toimipisteen "+nimi+" tiedot lisatty");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimipisteen tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Toimipisteen tietojen lisääminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void muutaToimipiste(Connection c, int toimipiste_id, String nimi, String lahiosoite, String postitoimipaikka, String postinro){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE Toimipiste SET toimipiste_id = ?, nimi = ?, lahiosoite = ?, postitoimipaikka = ?, postinro = ? WHERE toimipiste_id =?;"
            );
            ps.setInt(1, toimipiste_id);
            ps.setString(2, nimi);
            // Vahvistus pvm puutttuu
            ps.setString(3, lahiosoite);
            ps.setString(4, postitoimipaikka);
            ps.setString(5, postinro);
            ps.setInt(6, toimipiste_id);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Toimipisteen tietojen muuttaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Toimipisteen tiedot muutettu tietokantaan.");
            alert.showAndWait();
            System.out.println("Toimipiste "+toimipiste_id+" tiedot muutettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimipiste tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Toimipisteen tietojen muuttaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void poistaTomipiste(Connection c, int toimipiste_id){
        try {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM Toimipiste WHERE toimipiste_id = ? ;"
            );
            ps.setInt(1, toimipiste_id);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Toimipisteen tietojen poistaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Toimipisteen tiedot poistettu.");
            alert.showAndWait();
            System.out.println("Mökki "+toimipiste_id+" tiedot poistettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimipisteen tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Toimipisteen tietojen poistaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void lisaaVarausPalvelu(Connection c, int varausID, int palveluID, int lkm) {
        try {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO Varauksen_palvelut (varaus_id, palvelu_id, lkm) VALUES(?,?,?);"

            );
            ps.setInt(1, varausID);
            ps.setInt(2, palveluID);
            ps.setInt(3, lkm);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varauksen tietojen lisaaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Varauksen palvelut tiedot lisatty tietokantaan.");
            alert.showAndWait();
            System.out.println("Varauksen "+varausID+" palvelut lisatty");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Varauksen palveluiden tietojen lisääminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    static void muutaVarausPalvelu(Connection c, int varausID, int palveluID, int lkm) {
        try {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE Varauksen_palvelut SET varaus_id = ?, palvelu_id = ?, lkm = ?;"
            );
            ps.setInt(1, varausID);
            ps.setInt(2, palveluID);
            ps.setInt(3, lkm);
            ps.executeQuery();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varauksen tietojen muuttaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Varauksen palveluiden tiedot muutettu tietokantaan.");
            alert.showAndWait();
            System.out.println("Varauksen "+varausID+" palveluiden tiedot muutettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Varauksen palveluiden tietojen muuttaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    static void poistaVarausPalvelu(Connection c, int varausID, int palveluID) {
        try {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM Varauksen_palvelut WHERE varaus_id = ? AND palvelu_id = ?;"
            );
            ps.setInt(1, varausID);
            ps.setInt(2, palveluID);
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varauksen tietojen poistaminen");
            alert.setHeaderText("Toiminto ok.");
            alert.setContentText("Varauksen palveluiden tiedot poistettu.");
            alert.showAndWait();
            System.out.println("Varauksen "+varausID+" palveluiden tiedot poistettu");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Varauksen palveluiden tietojen poistaminen ei onnistu.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    
}
