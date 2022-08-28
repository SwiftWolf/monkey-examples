package org.monkey.examples;

import java.math.BigInteger;

import org.monkey.JmeArray;
import org.monkey.JmeBufferUtils;
import org.monkey.JmeProperties;

/**
 * Clase encargado de ejemplificar las creación, exportacion e importacion de los
 * datos de un 'juego' echo con <code>JME3</code> utilizando la biblioteca <b>Monkey</b>.
 * <p>
 * <b>IMPORTANTE: Algo a tener en cuenta, es si utilizan anotaciones para la
 * exportación de datos, la lectura se convertiría un jn {@link JmeProperties}, por
 * lo cual deberá generar un nuevo objeto.</b></p>
 * 
 * @author wil
 * @version monkeyv1.6.0
 * 
 * @since 1.0.0
 */
public class MonkeyBuilder {
    
    /**
     * Método principal de la ejemplificación encargado de gestionar las
     * salidas y entradas de datos.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        // inicialismos nuestro constructor.
        MonkeyBuilder mb = new MonkeyBuilder();
        
        // ejemplo de una propiedad simple.
        mb.simpleMonkeyProperties();
        
        // ejemplo de una propiedad de arreglos simples.
        mb.simpleMonkeyArray();
        
        // ejemplo de un propiedad anidada.
        // Normalmente asi se veria los datos de un
        // juego real.
        mb.monkeyData();
        
        
        // Como leer o importar los datos de un
        // juego utilizando jme3 y monkey.
        
        // importación de los datos de propiedades simple
        // generado anteriormente.
        JmeProperties simpleProperties = MonkeyUtils.monkeyImporter("example-simpleMonkeyProperties.j3p");
        
        // vista previa por consola.
        System.out.println("<< Simple Properties >>\n");
        System.out.println(simpleProperties.toString(1) + "\n\n");
        
        // importación de los datos de propiedades-arreglo simple
        // generado anteriormente.
        JmeArray simpleArray = MonkeyUtils.monkeyImporter("example-simpleMonkeyArray.j3p");
        
         // vista previa por consola.
        System.out.println("<< Simple Array >>\n");
        System.out.println(simpleArray.toString(1) + "\n\n");
        
        // importación de los datos de un juego.
        JmeProperties userData = MonkeyUtils.monkeyImporter("example-monkey.j3p");
        
         // vista previa por consola.
        System.out.println("<< Monkey UserData >>\n");
        System.out.println(userData.toString(1));
    }
    
    /**
     * Método encargado de generar una objeto de propiedades que simula
     * los datos de un juego.
     */
    public void monkeyData() {
        // Objeto con anotaciones.
        MonkeyAnnotation myAnnotation = new MonkeyAnnotation();
        myAnnotation.setLevel(MonkeyAnnotation.Level.A);    // establecemos el nivel,
        myAnnotation.setGold(1560);                         // el oro que tiene actualmente,
        myAnnotation.setScore(596);                         // y los puntos acumulado.
        
        /* Generamos un objeto 'Savable' personalizado por
         * nosotros, que puede ser cualquier objeto que implemente
         * la interfaz que proporciona jme3:'Savable'. */
        
        /* Esto es muy útil para exportar información concreta
         * o que actúan de manera especial que los objetos 'JmeProperties' y 'JmeArray'
         * no puedan hacer o guardar. */
        MonkeySavable mySavable = new MonkeySavable("[ jme3 ] :Monkey", new int[] {1, 2, 5});
        
        
        // iniciamos con nuestro árbol de datos. 
        JmeProperties root = new JmeProperties(myAnnotation);  // raíz del árbol.
        JmeProperties parent = new JmeProperties();            // parde de todos los
                                                                // datos que generemos.
                                                                
        // Agregamos datos al nodo padre.
        parent.put("name", "Monkey");
        parent.put("version", 1.0f);
        parent.put("mySavable", mySavable);
        
        // Generamos un objeto arreglo e inicializamos con un
        // arreglo de 2 dimensiones la cual lo tomará como 2 nuevos 'JmeArray'.
        JmeArray userData = new JmeArray(new Object[][] {
            {'a', 'b', 'c', false, true, MonkeyAnnotation.Level.B},
            {'x', 'y', 'z', true,  true, MonkeyAnnotation.Level.C}            
        });
        
        // Agregamos los datos al aerglo.
        userData.put(12L);
        userData.putAll(new BigInteger[] {
            new BigInteger("1000018366272"),
            new BigInteger("10027367")
        });
        
        // Agregamos el areglo al nodo padre.
        parent.put("userData", userData);
        
        // Agregamos el nodo padre a la raíz.
        root.put("monkey.parent", parent);
        
        // Exportamos el objeto.
        MonkeyUtils.monkeyExporter(root, "example-monkey.j3p");
    }
    
    /**
     * Genera un objeto de propiedades simple.
     */
    public void simpleMonkeyProperties() {
        JmeProperties jp = new JmeProperties();
        jp.put("monkey.string", "Monkey-v1.0.0");
        jp.put("monkey.number", 150);
        jp.put("monkey.charBuff", JmeBufferUtils.createCharBuffer('a', 'b', 'c'));
        jp.put("monkey.shortBuff", JmeBufferUtils.createShortBuffer((short)1, (short)2, (short)4));
        
        // Exportamos el objeto.
        MonkeyUtils.monkeyExporter(jp, "example-simpleMonkeyProperties.j3p");
    }
    
    /**
     * Genera un objeto arreglo de propiedades simple.
     */
    public void simpleMonkeyArray() {
        JmeArray ja = new JmeArray();
        ja.put("String");
        ja.put(false);
        ja.put(true);
        ja.put((byte) 1);
        ja.put((byte) 0);
        ja.put(10.56d);
        ja.put(30.89d);
        
        // Exportamos el objeto.
        MonkeyUtils.monkeyExporter(ja, "example-simpleMonkeyArray.j3p");
    }
}
