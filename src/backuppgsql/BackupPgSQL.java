/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backuppgsql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author sahudy
 */
public class BackupPgSQL {

    public void run() throws InterruptedException {
        try {
            ProcessBuilder pb;        
            final Process p;        
            pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe ", "-Fc", "-h", "localhost", "-p", "5432", "-U", "postgres", "-f", "C:\\Users\\davys_000\\Desktop\\CQ_bkp.backup", "ControleDeQualidade");        
            pb.environment().put("PGPASSWORD", "postgres");        
            pb.redirectErrorStream(true);        
            p = pb.start();
            
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }
    
    public void doRestore(){  
        try{        
            ProcessBuilder pb;        
            final Process p;        
            pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_restore.exe ", "-c", "-d", "ControleDeQualidade", "-h", "localhost", "-p", "5432","-U", "postgres", "C:\\Users\\davys_000\\Desktop\\CQ_bkp.backup");        
            pb.environment().put("PGPASSWORD", "postgres");        
            pb.redirectErrorStream(true);        
            p = pb.start();         
        }catch(Exception ex){        
            JOptionPane.showMessageDialog(null, ex);        
        }  
    }  
    
}
