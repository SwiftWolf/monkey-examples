package org.monkey.examples;

import com.jme3.export.Savable;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.binary.BinaryImporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Clase utilidades.
 * 
 * @author wil
 * @version monkeyv1.6.0
 * 
 * @since 1.0.0
 */
public class MonkeyUtils {    
    public static void monkeyExporter(Savable obj, String name) {
        try {
            File file = new File(System.getProperty("user.dir"), name);
            
            BinaryExporter exporter = BinaryExporter.getInstance();
            exporter.save(obj, file);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
    public static <E extends Savable> E monkeyImporter(String name) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(System.getProperty("user.dir"), name));
            
            BinaryImporter importer = BinaryImporter.getInstance();
            
            @SuppressWarnings("unchecked")
            E obj = (E) importer.load(in);
            
            return obj;
        } catch (IOException ex) {
            System.err.println("Monkey: >> " + ex.getMessage());
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
