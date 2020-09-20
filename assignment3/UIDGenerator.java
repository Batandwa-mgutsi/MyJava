
/**
 * The UID class provides unique IDs in the form of Strings of hexadecimal
 * characters.
 * 
 * Strictly speaking, IDs are not necessarily unique. The class can provide up
 * to 2^32 values, and after that, duplicates will appear. The class should
 * suffice for most applications.
 *
 * Id generating starts at 80000001 and increments by 1 from there on.
 * 
 * @author Stephan Jamieson
 * @version 31/7/2019
 */
public class UIDGenerator {

    private int value;

    private UIDGenerator() {
        value = Integer.MIN_VALUE;
    }

    private String next() {
        value = value + 1;
        return String.format("%08x", value);
    }

    private static UIDGenerator _generator = new UIDGenerator();

    public static String makeUID() {
        return _generator.next();
    }

    public static void reset() {
        _generator = new UIDGenerator();
    }
}
