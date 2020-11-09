/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaanapp.Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JamiatAbdillah.Net
 */
public class BukuModel {
    String id,kode,judul,pengarang,penerbit,isbn,stok,kategori_id,nama_kategori;

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }
    KoneksiDB db=null;
    
    public BukuModel(){
        db=new KoneksiDB();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        this.kategori_id = kategori_id;
    }
    
    
    public void tambah(){        
        String sql="INSERT INTO BUKU VALUES (null,'"+kode+"','"+judul+"','"+pengarang
                   +"','"+penerbit+"','"+isbn+"','"+stok+"','"+kategori_id+"')";
        db.simpanData(sql);
        db.tutupKoneksi(null);
    }
    
    public void edit(){
        String sql="UPDATE BUKU SET KODE='"+kode+"',JUDUL='"+judul
                   +"',PENGARANG='"+pengarang+"',PENERBIT='"+penerbit
                   +"',ISBN='"+isbn+"',STOK='"+stok+"',KATEGORI_ID='"+kategori_id
                   +"' WHERE ID='"+id+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }
    
    public void hapus(){
        String sql="DELETE FROM BUKU WHERE ID='"+id+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }
    
    public List tampil(){
        List<BukuModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT buku.*,kategori.* FROM BUKU inner join KATEGORI on BUKU.kategori_id=KATEGORI.id ORDER BY BUKU.ID DESC";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                BukuModel bm=new BukuModel();
                bm.setId(rs.getString("buku.id"));
                bm.setKode(rs.getString("kode"));
                bm.setJudul(rs.getString("judul"));
                bm.setPengarang(rs.getString("pengarang"));
                bm.setPenerbit(rs.getString("penerbit"));
                bm.setIsbn(rs.getString("isbn"));
                bm.setStok(rs.getString("stok"));
                bm.setKategori_id(rs.getString("kategori_id"));
                bm.setNama_kategori(rs.getString("kategori.nama"));
                data.add(bm);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }
        
        return data;
    }
    
    public List cariBuku(String cari){
        List<BukuModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM BUKU WHERE KODE LIKE '%"+cari+"%' OR JUDUL"
                    + " LIKE '%"+cari+"%'";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                BukuModel bm=new BukuModel();
                bm.setId(rs.getString("buku.id"));
                bm.setKode(rs.getString("kode"));
                bm.setJudul(rs.getString("judul"));
                bm.setPengarang(rs.getString("pengarang"));
                bm.setPenerbit(rs.getString("penerbit"));
                bm.setIsbn(rs.getString("isbn"));
                bm.setStok(rs.getString("stok"));
                data.add(bm);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "cari data gagal, Pesan Error : \n"+x);
        }
        
        return data;
    }    
    
    public void updateStokBuku(String id,int jmlPinjam){
        try{
            String sql="SELECT * FROM BUKU WHERE ID='"+id+"'";
            ResultSet rs=db.ambilData(sql);
            if(rs.next()){
                int stokAkhir=rs.getInt("stok")-jmlPinjam;
                String sql2="UPDATE BUKU SET STOK='"+stokAkhir+"' WHERE ID='"+id+"'";
                db.simpanData(sql2);
                db.tutupKoneksi(rs);
            }
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahaan update \n"+x);
        }
    }
    
    public void kembalikanStokBuku(String id,int jmlPinjam){
        try{
            String sql="SELECT * FROM BUKU WHERE ID='"+id+"'";
            ResultSet rs=db.ambilData(sql);
            if(rs.next()){
                int stokAkhir=rs.getInt("stok")+jmlPinjam;
                String sql2="UPDATE BUKU SET STOK='"+stokAkhir+"' WHERE ID='"+id+"'";
                db.simpanData(sql2);
                db.tutupKoneksi(rs);
            }
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahaan update \n"+x);
        }
    }    
}
