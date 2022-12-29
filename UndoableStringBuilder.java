package observer;

import java.util.Stack;

/*
Use the class you've implemented in previous assignment
 */
public class UndoableStringBuilder {

/**
 * This class implements some of StringBuilder methods with the ability to undo.
 *
 * @author Maor and Yuval
 * @version 1.0
 */

    private StringBuilder text;
    private final Stack<String> backup;

    /**
     * Constructor creates a new object of type UndoableStringBuilder.
     */
    public UndoableStringBuilder() {
        text = new StringBuilder();
        backup = new Stack<>();
    }

    /**
     * Appends the specified string to this character sequence.
     *
     * @param str A string to append to our "text" class field.
     * @return The new appended UndoableStringBuilder.
     */
    public UndoableStringBuilder append(String str) {
        text.append(str);
        backup.push(text.toString());
        return this;
    }

    /**
     * Removes the characters in a substring of this sequence.
     * The substring begins at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     *
     * @param start The beginning index, included.
     * @param end The ending index, not included.
     * @return The new edited UndoableStringBuilder.
     */
    public UndoableStringBuilder delete(int start, int end) {
        try
        {
            text.delete(start, end);
            backup.push(text.toString());
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.err.println("Your start index is negative, greater than the length of the string, or greater than end.");
        }
        return this;
    }

    /**
     * Inserts the string into this character sequence.
     * @param offset The index in which we want to insert str string to our sequence.
     * @param str The string we want to insert.
     * @return The new edited UndoableStringBuilder.
     */
    public UndoableStringBuilder insert(int offset, String str) {
        try
        {
            text.insert(offset, str);
            backup.push(text.toString());
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.err.println("Your offset in invalid");
        }
        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in the specified String.
     * The substring begins at the specified start and extends to the character at index end - 1 or to the end of the sequence if no such character exists.
     * First the characters in the substring are removed and then the specified String is inserted at start.
     * (This sequence will be lengthened to accommodate the specified String if necessary).
     * @param start The beginning index, included.
     * @param end The ending index, not included.
     * @param str The string we want to replace with.
     * @return The new edited UndoableStringBuilder.
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        try
        {
            text.replace(start, end, str);
            backup.push(text.toString());
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.err.println("Your start index is negative, greater than the length of the string, or greater than end.");
        }
        catch (NullPointerException e)
        {
            System.err.println("Null pointer was received");
        }
        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the sequence.
     * @return The new reversed UndoableStringBuilder.
     */
    public UndoableStringBuilder reverse() {
        text.reverse();
        backup.push(text.toString());
        return this;
    }

    /**
     * Undo changes that were made to the StringBuilder field by going one step back in the stack history.
     */
    public void undo() {
        if (backup.size() >= 2) {
            backup.pop();
            text = new StringBuilder(backup.peek());
        } else if (backup.size() == 1) {
            backup.pop();
            text = new StringBuilder();
        }
        // The case in which backup.size == 0 is handled either way
        // since text will point to a new StringBuilder, so we don't have to do anything.
    }

    /**
     * Prints our character sequence in our StringBuilder.
     * @return String representing the character sequence.
     */
    @Override
    public String toString() {
        return text.toString();
    }
}
