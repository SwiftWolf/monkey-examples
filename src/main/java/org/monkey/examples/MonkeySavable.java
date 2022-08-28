package org.monkey.examples;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.export.Savable;

import java.io.IOException;

import java.util.Arrays;

import org.monkey.JmePropertyIgnore;

/**
 * Clase que ejemplifica la implementación de la interfaz <code>Savable</code>
 * de la API <b>jme3</b>.
 * 
 * @author wil
 * @version monkeyv1.6.0
 * 
 * @since 1.0.0
 */
public class MonkeySavable implements Savable {
    
    /** nombre o id. */
    private String name;
    
    /** areglo de versiones */
    private int[] version;

    
    private MonkeySavable() {
    }

    public MonkeySavable(String name, int[] version) {
        this.name = name;
        this.version = version;
    }

    
    @Override
    public void write(JmeExporter ex) throws IOException {
        OutputCapsule out = ex.getCapsule(this);
        
        out.write(name,    "org.monkey.name",    null);
        out.write(version, "org.monkey.version", null);
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule in = im.getCapsule(this);
        
        name    = in.readString("org.monkey.name",      name);
        version = in.readIntArray("org.monkey.version", version);
    }

    @JmePropertyIgnore
    @Override
    public String toString() {
        return "MonkeySavable{" + "name=" + name + ", version=" 
                                + Arrays.toString(version) + '}';
    }
}
