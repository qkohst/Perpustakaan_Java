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
public class AnggotaModel {
    String id,no,nama,jk,telp,alamat;
    KoneksiDB db=null;
    
    public AnggotaModel(){
        db=new KoneksiDB();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
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
    
   public void tambah(){        
        String sql="INSERT INTO ANGGOTA VALUES (null,'"+no+"','"+nama+"','"+jk
                   +"','"+telp+"','"+alamat+"')";
        db.simpanData(sql);
        db.tutupKoneksi(null);
    }
    
    public void edit(){
        String sql="UPDATE ANGGOTA SET nama='"+nama
                   +"',jk='"+jk+"',telp='"+telp
                   +"',alamat='"+alamat+"' WHERE no_anggota='"+no+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }
    
    public void hapus(){
        String sql="DELETE FROM ANGGOTA WHERE no='"+no+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }
    
    public List tampil(){
        List<AnggotaModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM ANGGOTA ORDER BY ID DESC";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                AnggotaModel am=new AnggotaModel();
                am.setId(rs.getString("id"));
                am.setNo(rs.getString("no_anggota"));
                am.setNama(rs.getString("nama"));
                am.setJk(rs.getString("jk"));
                am.setTelp(rs.getString("telp"));
                am.setAlamat(rs.getString("alamat"));   
                data.add(am);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }        
        return data;
    }    
    
    public List cariAnggota(String cari){
        List<AnggotaModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM ANGGOTA WHERE NO_ANGGOTA LIKE '%"+cari+"%' OR NAMA LIKE '%"+cari+"%'";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                AnggotaModel am=new AnggotaModel();
                am.setId(rs.getString("id"));
                am.setNo(rs.getString("no_anggota"));
                am.setNama(rs.getString("nama"));
                am.setJk(rs.getString("jk"));
                am.setTelp(rs.getString("telp"));
                am.setAlamat(rs.getString("alamat"));   
                data.add(am);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }        
        return data;
    }      
}
