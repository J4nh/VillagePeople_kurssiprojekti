package villagepeople_r14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tomi Kiiskinen
 */

public class Toimipiste {

    int toimipiste_id;
    String nimi;
    String lahiosoite;
    String postitoimipaikka;
    String postinro;

    public Toimipiste() {
    }

    public Toimipiste(int toimipiste_id, String nimi, String lahiosoite, String postitoimipaikka, String postinro){
        this.toimipiste_id = toimipiste_id;
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
    }

    // Setterit
    public void setToimipiste_id(int tID){
        toimipiste_id = tID;
    }
    public void setNimi(String n){
        nimi = n;
    }
    public void setLahiosoite(String lo){
        lahiosoite = lo;
    }
    public void setPostitoimipaikka(String ptp) {
        this.postitoimipaikka = ptp;
    }
    public void setPostinro(String pnro){
        postinro = pnro;
    }

    // Getterit
    public int getToimipiste_id(){
        return toimipiste_id;
    }
    public String getNimi(){
        return nimi;
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

    public static Toimipiste haeToimipisteenTiedot(Connection c, int toimipiste_id) throws SQLException{

        String sql = ("SELECT * FROM Toimipiste WHERE toimipiste_id = ?");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, toimipiste_id);
            rs = ps.executeQuery();
            if (rs == null) {
                throw new Exception("Toimipistettä ei löydy");
            }
        } catch (Exception e) {
        }

        Toimipiste haeToimipiste = new Toimipiste();

        try {

            if (rs.next()) {

                haeToimipiste.setToimipiste_id(rs.getInt("toimipiste_id"));
                haeToimipiste.setNimi(rs.getString("nimi"));
                haeToimipiste.setLahiosoite(rs.getString("lahiosoite"));
                haeToimipiste.setPostitoimipaikka(rs.getString("postitoimipaikka"));
                haeToimipiste.setPostinro(rs.getString("postinro"));
            }

            System.out.println("Toimipisteen "+toimipiste_id+" tiedot haettu");
        } catch (SQLException e) {
            throw e;
        }
        return haeToimipiste;
    }

}
