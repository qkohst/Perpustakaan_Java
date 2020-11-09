/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaanapp;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import perpustakaanapp.GUI.MasterKategori;
import perpustakaanapp.Model.KategoriModel;

/**
 *
 * @author JamiatAbdillah.Net
 */
public class PerpustakaanApp {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            MasterKategori mk=new MasterKategori();
            mk.setVisible(true);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahaan");
        }
        
    }
    
}
