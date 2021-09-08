/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;

/**
 *
 * @author janih
 */
public class Lasku {
    int laskuID = 0;
    int varausID = 0;
    int asiakasID = 0;
    String nimi;
    String osoite;
    String postitoimi;
    String postinro;
    double summa = 0;
    double alv = 0;
    Date erapaiva;
    int tila;
    
    public Lasku(int laskuID, int varausID, int asiakasID, String nimi, String osoite, 
            String postitoimi, String postinro, double summa, double alv, Date erapaiva, int tila){
        
        this.laskuID = laskuID;
        this.varausID = varausID;
        this.asiakasID = asiakasID;
        this.nimi = nimi;
        this.osoite = osoite;
        this.postitoimi = postitoimi;
        this.postinro = postinro;
        this.summa = summa;
        this.alv = alv;
        this.erapaiva = erapaiva;
        this.tila = tila;
    }
    
    public Lasku(){
        
    }
    
    //Setterit
    public void setLaskuID(int laskuId){
        this.laskuID = laskuId;
    }
    public void setVarausID(int varausId){
        this.varausID = varausId;
    }
    public void setAsiakasID(int asiakasId){
        this.asiakasID = asiakasId;
    }
    public void setNimi(String nimi){
        this.nimi = nimi;
    }
    public void setOsoite(String osoite){
        this.osoite = osoite;
    }
    public void setPostitoimipaikka(String postitoimi){
        this.postitoimi = postitoimi;
    }
    public void setPostinro(String postinro){
        this.postinro = postinro;
    }
    public void setSumma(double summa){
        this.summa = summa;
    }
    public void setAlv(double alv){
        this.alv = alv;
    }
    public void setErapaiva(Date erapaiva){
        this.erapaiva = erapaiva;
    }
    public void setTila(int tila){
        this.tila = tila;
    }
    
    //Getterit
    public int getLaskuID (){
        return laskuID;
    }
    public int getVarausId (){
        return varausID;
    }
    public int getAsiakasID (){
        return asiakasID;
    }
    public String getNimi (){
        return nimi;
    }
    public String getOsoite (){
        return osoite;
    }
    public String getPostitoimipaikka (){
        return postitoimi;
    }
    public String getPostinro (){
        return postinro;
    }
    public double getSumma (){
        return summa;
    }
    public double getAlv (){
        return alv;
    }
    public Date getErapaiva (){
        return erapaiva;
    }
    public int getTila (){
        return tila;
    }
    
    
    public static Lasku haeLaskuntiedot(Connection c, int laskuID) throws SQLException{
        String sql = ("SELECT * FROM Lasku WHERE lasku_id = ?");
            PreparedStatement ps = null;
            ResultSet rs = null;
            
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, laskuID);
            rs = ps.executeQuery();
            if (rs == null) {
				throw new Exception("Laskua ei loydy");
            }
        } catch (Exception e) {
        }
        
        
        Lasku haeLasku = new Lasku();
            
        try {
            
            if (rs.next()) {
               haeLasku.setLaskuID(rs.getInt("lasku_id"));
               
               haeLasku.setVarausID(rs.getInt("varaus_id"));
               
               haeLasku.setAsiakasID(rs.getInt("asiakas_id"));
               
               haeLasku.setNimi(rs.getString("nimi"));
               
               haeLasku.setOsoite(rs.getString("lahiosoite"));
               
               haeLasku.setPostitoimipaikka(rs.getString("postitoimipaikka"));
               
               haeLasku.setPostinro(rs.getString("postinro"));
               
               haeLasku.setSumma(rs.getDouble("summa"));

               haeLasku.setAlv(rs.getDouble("alv"));

               haeLasku.setErapaiva(rs.getDate("erapaiva"));
               
               haeLasku.setTila(rs.getInt("tila"));
           }
            
            System.out.println("Laskun "+laskuID+" tiedot haettu");
            
        } catch (SQLException e) {
            throw e;
        }
       return haeLasku;
    }
    
}
