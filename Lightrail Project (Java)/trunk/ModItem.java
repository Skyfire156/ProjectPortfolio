/**
 * An integer value that has a rollover limit
 * at which it resets to zero.
 *
 * @author Dr. Jody Paul
 * @version 1.2 ($Id: ModItem.java 337 2016-05-01 17:28:59Z joshua $)
 */
public class ModItem {

    /** Prime value used in computing hash code. */
    private static final int HASH_MULTIPLIER = 257;

    /** The rollover limit. */
    private int limit;

    /** The current value. */
    private int value;

    /**
     * Constructs a ModItem with a specified rollover limit.
     * The current value is initialized to 0.
     * @param rolloverLimit the limit at which this item returns to 0
     */
    public ModItem(final int rolloverLimit) {
        this.limit = rolloverLimit;
        this.value = 0;
    }

    /**
     * Constructs a ModItem with a specified rollover limit
     * and initial value.
     * @param rolloverLimit the limit at which this item returns to 0
     * @param initialValue the initial value of this item
     */
    public ModItem(final int rolloverLimit, final int initialValue) {
        this.limit = rolloverLimit;
        this.value = initialValue % rolloverLimit;
    }

    /**
     * Returns the current value as an int.
     * @return the current value
     */
    public int intValue() {
        return this.value;
    }

    /**
     * Returns a string representation of the current value;
     * pads with prefixed "0" to ensure at least 2 digits.
     * @return padded rendering
     */
    @Override
    public String toString() {
        String returnString = "" + this.value;
        if (this.value < 10) {
            returnString = "0" + returnString;
        }
        return returnString;
    }

    /**
     * Increment this item by one, rolling over to zero if appropriate.
     * @return 1 if this caused a rollover; 0 otherwise
     */
    public int increment() {
        this.value = (this.value + 1) % this.limit;
        if (this.value == 0) {
            return 1;
        }
        return 0;
    }

    /**
     * Add a specified amount to this item, rolling over if appropriate.
     * @param amount the value to be added
     * @return the number of rollovers caused by this operation
     */
   public int increment(final int amount) {
        int rollovers = (this.value + amount) / this.limit;
        this.value = (this.value + amount) % this.limit;
        return rollovers;
    }

    /**
     * Replace the current value.
     * @param replacementValue the new value
     */
    public void setValue(final int replacementValue) {
        this.value = replacementValue % this.limit;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }
        @SuppressWarnings("unchecked") // Have checked explicitly.
        ModItem ciobj = (ModItem) obj;
        return (ciobj.limit == this.limit && ciobj.value == this.value);
    }

    @Override
    public int hashCode() {
        return this.limit + (HASH_MULTIPLIER * this.value);
    }
}
