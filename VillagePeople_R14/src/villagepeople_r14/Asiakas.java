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

/**
 *
 * @author janih
 */
public class Asiakas {
    int asiakasID;
    String etunimi;
    String sukunimi; 
    String lahiosoite; 
    String postitoimipaikka; 
    String postinro; 
    String email; 
    String puhelinnro;
    
    public Asiakas(){
        
    }
    
    public Asiakas(int asiakasID, String etunimi, String sukunimi, String lahiosoite, 
            String postitoimipaikka, String postinro, String email, String puhelinnro){
        this.asiakasID=asiakasID;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.email = email;
        this.puhelinnro = puhelinnro;
                
    }
    
    //Setterit
    
    public void setAsiakasID(int asiakasid){
        this.asiakasID = asiakasid;
    }
    
    public void setEtunimi(String etunimi){
        this.etunimi = etunimi;
    }
    public void setSukunimi(String sukunimi){
        this.sukunimi = sukunimi;
    }
    public void setLahiosoite(String lahiosoite){
        this.lahiosoite = lahiosoite;
    }
    public void setPostitoimipaikka(String postitoimipaikka){
        this.postitoimipaikka = postitoimipaikka;
    }
    public void setPostinro(String postinro){
        this.postinro = postinro;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPuhnro(String puhnro){
        this.puhelinnro = puhnro;
    }
    
    //getterit
    
    public int getAsiakasID(){
        return asiakasID;
    }
    public String getEtunimi(){
        return etunimi;
    }
    public String getSukunimi(){
        return sukunimi;
    }
    public String getLahiosoite(){
        return lahiosoite;
    }
    public String getPostitoimipaikka(){
        return postitoimipaikka;
    }
    public String getPostinro(){
        return postinro;
    }
    public String getEmail(){
        return email;
    }
    public String getPuhnro(){
        return puhelinnro;
    }
    
    public static Asiakas haeAsiakkaantiedot(Connection c, int asiakasID)throws SQLException{
        String sql = ("SELECT * FROM Asiakas WHERE asiakas_id = ?");
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, asiakasID);
            rs = ps.executeQuery();
            if (rs == null) {
				throw new Exception("Asiakasta ei loydy");
            }
        } catch (Exception e) {
        }
        
        Asiakas haeAsiakas = new Asiakas();
    
            
        try {
            
            if (rs.next()) {
               haeAsiakas.setAsiakasID(rs.getInt("asiakas_id"));
               
               haeAsiakas.setEtunimi(rs.getString("etunimi"));
               
               haeAsiakas.setSukunimi(rs.getString("sukunimi"));
               
               haeAsiakas.setLahiosoite(rs.getString("lahiosoite"));
               
               haeAsiakas.setPostitoimipaikka(rs.getString("postitoimipaikka"));
               
               haeAsiakas.setPostinro(rs.getString("postinro"));
               
               haeAsiakas.setEmail(rs.getString("email"));
               
               haeAsiakas.setPuhnro(rs.getString("puhelinnro"));
               
               
           }
            
            System.out.println("Asiakkaan "+asiakasID+" tiedot haettu");
        } catch (SQLException e) {
            throw e;
        }
       return haeAsiakas;
    }
}
