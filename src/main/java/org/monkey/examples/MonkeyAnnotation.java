package org.monkey.examples;

import org.monkey.JmePropertyIgnore;
import org.monkey.JmePropertyName;

/**
 * Clase de prueba, donde se ejemplifica cómo podemos generar un
 * objeto guardables utilizando las siguientes anotaciones:<pre><code>
 * &#64;JmePropertyName  : Establece la clave del dato retornado.
 * &#64;JmePropertyIgnore: Ignorar el método y no lo toma en cuenta como
 *                         candidato. </code></pre>
 * <p>
 * <b>&#64;JmePropertyName:</b> Es el encargado de establecer la clave
 * del dato, esta anotación se coloca en los métodos <code>getters</code>. Si
 * no es utilizado, de manera predeterminada se toma el nombre del método
 * omitiendo <code>get, is, etc.</code>.
 * </p>
 * <p>
 * <b>&#64;JmePropertyIgnore:</b> Se coloca a los métodos '<code>getters</code>'
 * u otro para evitarlo.
 * </p>
 * 
 * @author wil
 * @version monkeyv1.6.0
 * 
 * @since 1.0.0
 */
public class MonkeyAnnotation {

    /**
     * Clase enumerada interna encargado de simular
     * un nivel del jugador.
     * 
     * @see #A Nivel principinate
     * @see #B Nivel intermedio
     * @see #C Nivel experto
     */
    public static enum Level {
        A, B , C
    }

    /** Puntage del jugador. */
    private int score;
    /** Oro del jugador. */
    private int gold;
    
    /** Nivel del jugador. */
    private Level level;
    
    /**
     * Constructor predeterminado.
     */
    public MonkeyAnnotation() {
    }

    @JmePropertyName(value = "org.monkey.score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @JmePropertyName(value = "org.monkey.gold")
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    @JmePropertyIgnore
    public String toString() {
        return "MonkeyAnnotation{" +
                "score=" + score +
                ", gold=" + gold +
                ", level=" + level +
                '}';
    }
}
