public class help {
    public static <T> void print(T... args) {
        for(T pts: args) {
            System.out.print(pts);
            System.out.print(" ");
        }
        System.out.println();
    }
}
