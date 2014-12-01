import java.io.File;
import javax.swing.filechooser.FileFilter;
/*
 * CSVFilter.java
 *
 * Created on 29 de Julho de 2006, 15:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class CSVFilter extends FileFilter {
    
    /** Creates a new instance of CSVFilter */
    public CSVFilter() {
    }

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return false;
        }

        String extension = f.getAbsolutePath().substring(f.getAbsolutePath().length()-4, f.getAbsolutePath().length());
        if (extension != null) {
            if (extension.toLowerCase().equals(".csv")){
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "Comma Separated Value Files (*.csv)";
    }  
}
