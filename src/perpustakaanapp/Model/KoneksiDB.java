/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaanapp.Model;


import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author JamiatAbdillah.Net
 */
public class KoneksiDB {
    public static Connection koneksi;
    public Statement st;
   
    public static Connection ambilKoneksi(){
        if(koneksi==null){
            try{
                String server="jdbc:mysql://localhost:3306/perpustakaandb";
                String user="root";
                String password="4dmin4mi";
                Class.forName("com.mysql.jdbc.Driver");
                koneksi=DriverManager.getConnection(server,user,password);               
            }catch(Exception x){
                JOptionPane.showMessageDialog(null,"Koneksi GATOT, Pesan error \n"+x);
            }                        
        }
        return koneksi;
    }
    
    public void koneksi(){
            try{
                String server="jdbc:mysql://localhost:3306/perpustakaandb";
                String user="root";
                String password="4dmin4mi";
                Class.forName("com.mysql.jdbc.Driver");
                koneksi=DriverManager.getConnection(server,user,password);  
                st=koneksi.createStatement();
            }catch(Exception x){
                JOptionPane.showMessageDialog(null,"Koneksi GATOT, Pesan error \n"+x);
            }          
    }
    
    public void tutupKoneksi(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
            st.close();
            koneksi.close();
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,"Tutup Koneksi Gagal, Pesan error \n"+x);
        }
    }
     
    public ResultSet ambilData(String sql){
        ResultSet rs=null;
        try{
            koneksi();
            rs=st.executeQuery(sql);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,"Ambil Data Gagal, Pesan error : \n"+x);
        }
        return rs;
    }
    
    public void simpanData(String sql){
        try{            
            koneksi();
            st.executeUpdate(sql);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,"Simpan Data Gagal, Pesan error : \n"+x);
        }        
    }
}

