/**
 * An object which can transport other objects of a specific type
 * <p>
 *     Has methods to load and unload objects of the transported type. The
 *     internals of how transported objects are stored are up to the implementer
 *     class; there may be e.g. runtime constraints and a maximum capacity. The
 *     order in which objects are loaded and unloaded is also defined by the
 *     implementer.
 * </p>
 *
 * @param <T> The type that this Transporter can Transport.
 */
public interface Transporter<T> {
    /**
     * Load (start transporting) an object <code>t</code>
     */
    void load(T t);

    /**
     * Unload (stop transporting) an object and return it
     */
    T unload();
}
