/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author 1h3b
 */
public class cdetails {
    private int driverCin;
    private int music;
    private int climatisation;
    private int detail;

    @Override
    public String toString() {
        return "cdetails{" + "driverCin=" + driverCin + ", music=" + music + ", climatisation=" + climatisation + ", detail=" + detail + '}';
    }

    public cdetails(int driverCin, int music, int climatisation, int detail) {
        this.driverCin = driverCin;
        this.music = music;
        this.climatisation = climatisation;
        this.detail = detail;
    }
    public cdetails() {
        
    }

    public int getDriverCin() {
        return driverCin;
    }

    public void setDriverCin(int driverCin) {
        this.driverCin = driverCin;
    }

    public int isMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public int isClimatisation() {
        return climatisation;
    }

    public void setClimatisation(int climatisation) {
        this.climatisation = climatisation;
    }

    public int getDetail() {
        return detail;
    }

    public void setDetail(int detail) {
        this.detail = detail;
    }
    
}
