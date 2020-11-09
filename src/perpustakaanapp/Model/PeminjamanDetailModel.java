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
public class PeminjamanDetailModel {
    String id_pinjam,id_buku,kode,judul,pengarang,penerbit;
    KoneksiDB db=null;
    
    public PeminjamanDetailModel(){
        db=new KoneksiDB();
    }

    public String getId_pinjam() {
        return id_pinjam;
    }

    public void setId_pinjam(String id_pinjam) {
        this.id_pinjam = id_pinjam;
    }

    public String getId_buku() {
        return id_buku;
    }

    public void setId_buku(String id_buku) {
        this.id_buku = id_buku;
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
    
    public void tambah(){        
        String sql="INSERT INTO PEMINJAMAN_DETAIL VALUES ('"+id_pinjam+"','"+id_buku+"')";
        db.simpanData(sql);
        db.tutupKoneksi(null);
    }
    
    public void hapus(){
        String sql="DELETE FROM PEMINJAMAN_DETAIL WHERE ID_PINJAM='"+id_pinjam+"'";
        db.simpanData(sql);
        db.tutupKoneksi(null);        
    }     
    
    public List tampilBuku(String noPinjam){
        List<PeminjamanDetailModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT buku.kode,buku.judul,buku.pengarang,buku.penerbit "
                    + "FROM peminjaman_detail INNER JOIN peminjaman ON "
                    + "peminjaman.id=peminjaman_detail.id_pinjam INNER JOIN buku ON "
                    + "peminjaman_detail.id_buku=buku.id where peminjaman.no_pinjam='"+noPinjam+"'";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                PeminjamanDetailModel pdm=new PeminjamanDetailModel();
                pdm.setKode(rs.getString("kode"));
                pdm.setJudul(rs.getString("judul"));
                pdm.setPengarang(rs.getString("pengarang"));
                pdm.setPenerbit(rs.getString("penerbit"));
                data.add(pdm);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }
        
        return data;
    }    
    
    public List cariBuku(String noPinjam){
        List<PeminjamanDetailModel> data=new ArrayList<>();
        ResultSet rs=null;
        try{
            String sql="SELECT * from peminjaman_detail where id_pinjam='"+noPinjam+"'";
            rs=db.ambilData(sql);
                        
            while(rs.next()){                   
                PeminjamanDetailModel pdm=new PeminjamanDetailModel();
                pdm.setId_pinjam(rs.getString("id_pinjam"));
                pdm.setId_buku(rs.getString("id_buku"));
                data.add(pdm);
            }               
            db.tutupKoneksi(rs);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Tampil data gagal, Pesan Error : \n"+x);
        }
        
        return data;
    }       
}
