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
public class KategoriModel {
   String id,nama;
   KoneksiDB db=null;
   
   public KategoriModel(){
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
    
    public void tambah(){        
        String sql="INSERT INTO KATEGORI VALUES (null,'"+nama+"')";
        db.simpanData(sql);
        db.tutupKoneksi(null);
    }
    
    public void edit(){
        String sql="UPDATE KATEGORI SET NAMA='"+nama+"' WHERE ID='"+id+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }
    
    public void hapus(){
        String sql="DELETE FROM KATEGORI WHERE ID='"+id+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }
    
    public List tampil(){
        List<KategoriModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM KATEGORI";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                KategoriModel km=new KategoriModel();
                km.setId(rs.getString("id"));
                km.setNama(rs.getString("nama"));
                data.add(km);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }
        
        return data;
    }

    public List cariIdKategori(String kategori){
        List<KategoriModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM KATEGORI WHERE NAMA='"+kategori+"'";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                KategoriModel km=new KategoriModel();
                km.setId(rs.getString("id"));
                km.setNama(rs.getString("nama"));
                data.add(km);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }
        
        return data;
    }    
}
