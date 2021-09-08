/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

import java.sql.Date;

/**
 *
 * @author janih
 */
public class MajoitusRaportti {
    
    int varausID, asiakasId;
    String varattupvm;
    Date alkupvm, loppupvm;

    public MajoitusRaportti(int varausID, int asiakasId, String varattupvm, Date alkupvm, Date loppupvm) {
        this.varausID = varausID;
        this.asiakasId = asiakasId;
        this.varattupvm = varattupvm;
        this.alkupvm = alkupvm;
        this.loppupvm = loppupvm;
    }
    
    public MajoitusRaportti(){
        
    }

    public int getVarausID() {
        return varausID;
    }

    public void setVarausID(int varausID) {
        this.varausID = varausID;
    }

    public int getAsiakasId() {
        return asiakasId;
    }

    public void setAsiakasId(int asiakasId) {
        this.asiakasId = asiakasId;
    }

    public String getVarattupvm() {
        return varattupvm;
    }

    public void setVarattupvm(String varattupvm) {
        this.varattupvm = varattupvm;
    }

    public Date getAlkupvm() {
        return alkupvm;
    }

    public void setAlkupvm(Date alkupvm) {
        this.alkupvm = alkupvm;
    }

    public Date getLoppupvm() {
        return loppupvm;
    }

    public void setLoppupvm(Date loppupvm) {
        this.loppupvm = loppupvm;
    }

    @Override
    public String toString() {
        return "MajoitusRaportti{" + "varausID=" + varausID + ", asiakasId=" + asiakasId + ", varattupvm=" + varattupvm + ", alkupvm=" + alkupvm + ", loppupvm=" + loppupvm + '}';
    }
    
    
}
