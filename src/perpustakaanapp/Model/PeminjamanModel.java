/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaanapp.Model;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JamiatAbdillah.Net
 */
public class PeminjamanModel {
    String id,no_pinjam,tgl_pinjam,tgl_kembali,anggota_id,nama_anggota;
    KoneksiDB db=null;
    
    public PeminjamanModel(){
        db=new KoneksiDB();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo_pinjam() {
        return no_pinjam;
    }

    public void setNo_pinjam(String no_pinjam) {
        this.no_pinjam = no_pinjam;
    }

    public String getTgl_pinjam() {
        return tgl_pinjam;
    }

    public void setTgl_pinjam(String tgl_pinjam) {
        this.tgl_pinjam = tgl_pinjam;
    }

    public String getTgl_kembali() {
        return tgl_kembali;
    }

    public void setTgl_kembali(String tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }

    public String getAnggota_id() {
        return anggota_id;
    }

    public void setAnggota_id(String anggota_id) {
        this.anggota_id = anggota_id;
    }

    public String getNama_anggota() {
        return nama_anggota;
    }

    public void setNama_anggota(String nama_anggota) {
        this.nama_anggota = nama_anggota;
    }
    
    public List tampilBerdasarkanTgl(String tgl1,String tgl2){
        List<PeminjamanModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT peminjaman.id,peminjaman.no_pinjam,anggota.nama,peminjaman.tgl_pinjam,"
                    + "peminjaman.tgl_kembali FROM peminjaman INNER JOIN anggota ON "
                    + "peminjaman.anggota_id=anggota.id where "
                    + "peminjaman.tgl_pinjam>='"+tgl1+"' and "
                    + "peminjaman.tgl_pinjam<='"+tgl2+"'";            
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                PeminjamanModel pm=new PeminjamanModel();
                pm.setId(rs.getString("peminjaman.id"));
                pm.setNo_pinjam(rs.getString("peminjaman.no_pinjam"));
                pm.setNama_anggota(rs.getString("anggota.nama"));
                pm.setTgl_pinjam(rs.getString("peminjaman.tgl_pinjam"));
                pm.setTgl_kembali(rs.getString("peminjaman.tgl_kembali"));
                data.add(pm);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }
        
        return data;
    }
    
    public void tambah(){        
        String sql="INSERT INTO PEMINJAMAN VALUES (null,'"+no_pinjam+"','"+tgl_pinjam
                +"','"+tgl_kembali+"','"+anggota_id+"')";
        db.simpanData(sql);
        db.tutupKoneksi(null);
    }
    
    public void hapus(){
        String sql="DELETE FROM PEMINJAMAN WHERE ID='"+id+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }    
    
    public String buatNoPinjam(){
        String hasil=null;
        ResultSet rs=null;        
        try{
            DateFormat df=new SimpleDateFormat("MM-yy");
            String tgl=df.format(Calendar.getInstance().getTime());
            
            String sql="select * from peminjaman order by id asc";
            rs=db.ambilData(sql);
            if(rs.next()){
                rs.last();
                int nomor=Integer.parseInt(rs.getString("no_pinjam").substring(6))+1;
                hasil="P"+tgl.replace("-", "")+"-"+nomor;                
            }else{
                hasil="P"+tgl.replace("-", "")+"-1";                
            }
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan buat NoPinjam \n"+x);
        }
        return hasil;
    } 
    
    public String ambilIdTerakhir(){
        String hasil=null;
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM PEMINJAMAN ORDER BY ID ASC";
            rs=db.ambilData(sql);
            rs.last();
            hasil=rs.getString("id");
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Ambil Id gagal, Pesan error \n"+x);
        }
        return hasil;
    }
}
