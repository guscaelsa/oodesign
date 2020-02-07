public interface Transporter<T> {
    void load(T t);
    T unload();
}
