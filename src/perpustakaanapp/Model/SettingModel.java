/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaanapp.Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JamiatAbdillah.Net
 */
public class SettingModel {
    String max_buku,batas_pinjam,denda;
    KoneksiDB db=null;
    
    public SettingModel(){
        db=new KoneksiDB();
    }

    public String getMax_buku() {
        return max_buku;
    }

    public void setMax_buku(String max_buku) {
        this.max_buku = max_buku;
    }

    public String getBatas_pinjam() {
        return batas_pinjam;
    }

    public void setBatas_pinjam(String batas_pinjam) {
        this.batas_pinjam = batas_pinjam;
    }

    public String getDenda() {
        return denda;
    }

    public void setDenda(String denda) {
        this.denda = denda;
    }
    
    public void edit(){
        String sql="UPDATE SETTING SET MAX_BUKU='"+max_buku
                +"',BATAS_PINJAM='"+batas_pinjam+"',DENDA='"+denda+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }
     
    public List tampil(){
        List<SettingModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM SETTING";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                SettingModel sm=new SettingModel();
                sm.setMax_buku(rs.getString("max_buku"));
                sm.setBatas_pinjam(rs.getString("batas_pinjam"));
                sm.setDenda(rs.getString("denda"));
                data.add(sm);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }
        
        return data;
    }    
}
