/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaanapp.Model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author JamiatAbdillah.Net
 */
public class LaporanModel {
    Connection koneksi;

    public LaporanModel() {
        try {
            String server = "jdbc:mysql://localhost:3306/perpustakaandb";
            String user = "root";
            String password = "4dmin4mi";
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(server, user, password);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Koneksi GATOT, Pesan error \n" + x);
        }
    }
    
    public void cetakBukuSemua(){
        try {
            InputStream sumber = getClass().getResourceAsStream("/perpustakaanapp/Laporan/LapBuku.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(sumber);
            Map params = new HashMap();
            JasperPrint jp = JasperFillManager.fillReport(jr, params, koneksi);

            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setExtendedState(viewer.getExtendedState() | 0x6);
            viewer.setVisible(true);
            viewer.setTitle("Laporan Buku Seluruh");
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan report " + ex);
        }        
    }
    
    public void cetakPerKategori(String kategori){
        try {
            InputStream sumber = getClass().getResourceAsStream("/perpustakaanapp/Laporan/LapBukuPerKategori.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(sumber);
            Map params = new HashMap();
            params.put("kategori",kategori);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, koneksi);

            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setExtendedState(viewer.getExtendedState() | 0x6);
            viewer.setVisible(true);
            viewer.setTitle("Laporan Buku Seluruh");
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan report " + ex);
        }         
    }
    
    public void cetakBuktiPinjam(String nopinjam){
        try {
            InputStream sumber = getClass().getResourceAsStream("/perpustakaanapp/Laporan/BuktiPinjam.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(sumber);
            Map params = new HashMap();
            params.put("nopinjam",nopinjam);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, koneksi);

            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setExtendedState(viewer.getExtendedState() | 0x6);
            viewer.setVisible(true);
            viewer.setTitle("Bukti Pinjam");
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan report " + ex);
        }         
    }    
}
