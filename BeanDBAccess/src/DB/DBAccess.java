
package DB;

/**
 *
 * @author nakim
 */
public interface DBAccess
{
    /**
     * Initialise la connexion
     * @return true si l'initialisation s'est bien passee, false si erreur
     */
    boolean init();
}
