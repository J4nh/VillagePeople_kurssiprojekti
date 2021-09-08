/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;


public class Palvelu {
    int palveluID = 0;
    int toimipisteId = 0;
    String nimi;
    int tyyppi = 0;
    String kuvaus;      
    double hinta= 0;
    double alv = 0; 
    
    public Palvelu(int palveluID,int toimipisteId, String nimi, int tyyppi, String kuvaus, 
            double hinta, double alv){
        this.palveluID = palveluID;
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;       
    }
    public Palvelu(){
        
    }
     
    //Setterit
    public void setPalveluID(int pID){
        palveluID = pID;
    }
    
    public void setToimipisteID(int tID){
        toimipisteId = tID;
    }
    
    public void setNimi(String nim){
        nimi = nim;
    }
    public void setTyyppi(int typ){
        tyyppi = typ;
    }
    public void setKuvaus(String kuv){
        kuvaus = kuv;
    }
    
    public void setHinta(double hin){
        hinta = hin;
    }
    public void setAlv(double al){
        alv = al;
    }
    
    //Getterit
    
    public int getPalveluID(){
        return palveluID;
    }
    
    public int getToimipisteID(){
        return toimipisteId;
    }
    
    public String getNimi(){
        return nimi;
    }
    
    public int getTyyppi(){
        return tyyppi;
    }
    
    public String getKuvaus(){
        return kuvaus;
    }
    public double getHinta(){
        return hinta;
    }
    public double getAlv(){
        return alv;
    }

    public static Palvelu haePalveluntiedot(Connection c, int palveluID) throws SQLException{
        
            String sql = ("SELECT * FROM Palvelu WHERE palvelu_id = ?");
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, palveluID);
            rs = ps.executeQuery();
            if (rs == null) {
				throw new Exception("Palvelua ei loydy");
            }
        } catch (Exception e) {
        }
        
        Palvelu haePalvelu = new Palvelu();
    
            
        try {
            
            if (rs.next()) {
               haePalvelu.setPalveluID(rs.getInt("palvelu_id"));
               
               haePalvelu.setToimipisteID(rs.getInt("toimipiste_id"));
               
               haePalvelu.setNimi(rs.getString("nimi"));
               
               haePalvelu.setTyyppi(rs.getInt("tyyppi"));

               haePalvelu.setKuvaus(rs.getString("kuvaus"));

               haePalvelu.setHinta( rs.getDouble("hinta"));
               
               haePalvelu.setAlv(rs.getDouble("alv"));
           }
            
            System.out.println("Palvelun "+palveluID+" tiedot haettu");
        } catch (SQLException e) {
            throw e;
        }
       return haePalvelu;
    }
    //Palveluiden lisääminen/muokkaaminen/poistaminen tapahtuu Kanta.java luokassa.
}
