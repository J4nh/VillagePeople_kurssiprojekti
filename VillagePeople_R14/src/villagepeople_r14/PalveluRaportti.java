/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villagepeople_r14;

/**
 *
 * @author janih
 */
public class PalveluRaportti {
    
    int varausID, palveluID, lkm;

    public PalveluRaportti(int varausID, int palveluID, int lkm) {
        this.varausID = varausID;
        this.palveluID = palveluID;
        this.lkm = lkm;
    }

    public PalveluRaportti() {
    }

    public int getVarausID() {
        return varausID;
    }

    public void setVarausID(int varausID) {
        this.varausID = varausID;
    }

    public int getPalveluID() {
        return palveluID;
    }

    public void setPalveluID(int palveluID) {
        this.palveluID = palveluID;
    }

    public int getLkm() {
        return lkm;
    }

    public void setLkm(int lkm) {
        this.lkm = lkm;
    }

    @Override
    public String toString() {
        return "PalveluRaportti{" + "varausID=" + varausID + ", palveluID=" + palveluID + ", lkm=" + lkm + '}';
    }
    
    
    
}
