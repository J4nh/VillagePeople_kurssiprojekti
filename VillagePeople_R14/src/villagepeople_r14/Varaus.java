package villagepeople_r14;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tomi Kiiskinen
 */

public class Varaus {
    int varausID = 0;
    int asiakasID = 0;
    int toimipisteID = 0;
    String varattu_PVM;
    Date varattu_alkuPVM;
    Date varattu_loppuPVM;

    public Varaus(){
    }

    public Varaus(int varausID, int asiakasID, int toimipisteID, String varattu_PVM, Date varattu_alkuPVM, Date varattu_loppuPVM){
        this.varausID = varausID;
        this.asiakasID = asiakasID;
        this.toimipisteID = toimipisteID;
        this.varattu_PVM = varattu_PVM;
        this.varattu_alkuPVM = varattu_alkuPVM;
        this.varattu_loppuPVM = varattu_loppuPVM;
    }


    //Setterit
    public void setVarausID(int vID){
        varausID = vID;
    }
    public void setAsiakasID(int aID){
        asiakasID = aID;
    }
    public void setToimipisteID(int tID){
        toimipisteID = tID;
    }
    public void setVarattu_PVM(String v_PVM){
        varattu_PVM = v_PVM;
    }
    public void setVarattu_alkuPVM(Date v_APVM){
        varattu_alkuPVM = v_APVM;
    }
    public void setVarattu_loppuPVM(Date v_LPVM){
        varattu_loppuPVM = v_LPVM;
    }

    //Getterit
    public int getVarausID(){
        return varausID;
    }
    public int getAsiakasID() {
        return asiakasID;
    }
    public int getToimipisteID(){
        return toimipisteID;
    }
    public String getVarattu_PVM(){
        return varattu_PVM;
    }
    public Date getVarattu_alkuPVM(){
        return varattu_alkuPVM;
    }
    public Date getVarattu_loppuPVM(){
        return varattu_loppuPVM;
    }


    public static Varaus haeVarauksentiedot(Connection c, int varausID) throws SQLException{

        String sql = ("SELECT * FROM Varaus WHERE varaus_id = ?");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, varausID);
            rs = ps.executeQuery();
            if (rs == null) {
                throw new Exception("Varausta ei loydy");
            }
        } catch (Exception e) {
        }

        Varaus haeVaraus = new Varaus();

        try {

            if (rs.next()) {

                haeVaraus.setVarausID(rs.getInt("varaus_id"));
                haeVaraus.setAsiakasID(rs.getInt("asiakas_id"));
                haeVaraus.setToimipisteID(rs.getInt("toimipiste_id"));
                haeVaraus.setVarattu_PVM(rs.getString("varattu_pvm"));
                haeVaraus.setVarattu_alkuPVM(rs.getDate("varattu_alkupvm"));
                haeVaraus.setVarattu_loppuPVM(rs.getDate("varattu_loppupvm"));
            }

            System.out.println("Varaus "+varausID+" tiedot haettu");
        } catch (SQLException e) {
            throw e;
        }
        return haeVaraus;
    }
}
