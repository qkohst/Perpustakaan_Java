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
public class PenggunaModel {
    String id,nama,telp,alamat,username,password,hakakses;
    KoneksiDB db=null;
    
    public PenggunaModel(){
        db=new KoneksiDB();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHakakses() {
        return hakakses;
    }

    public void setHakakses(String hakakses) {
        this.hakakses = hakakses;
    }
    
    public List cariLogin(String user,String pass){
        List<PenggunaModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM PENGGUNA WHERE USERNAME='"+user+"' AND PASSWORD='"+pass+"'";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                PenggunaModel pm=new PenggunaModel();
                pm.setId(rs.getString("id"));
                pm.setNama(rs.getString("nama"));
                pm.setTelp(rs.getString("telp"));
                pm.setAlamat(rs.getString("alamat"));
                pm.setUsername(rs.getString("username"));
                pm.setPassword(rs.getString("password"));   
                pm.setHakakses(rs.getString("hak_akses"));   
                data.add(pm);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Login gagal, Pesan Error : \n"+x);
        }        
        return data;
    }     
}
