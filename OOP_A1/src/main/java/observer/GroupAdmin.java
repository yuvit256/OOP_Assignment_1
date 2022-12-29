package observer;
import java.util.ArrayList;

public class GroupAdmin implements Sender
{
    private UndoableStringBuilder usb;
    private ArrayList<Member> members = new ArrayList<Member>();

    public GroupAdmin(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    public void notifyObservers() { //Updates the observer for changes
		for (Member o : members){o.update(this.usb);}
	} // going throw the list with for each and use the func update so everyone is the same now

    // Adding a member to the list
    public void register(Member obj){
        this.members.add(obj);
    } // adding a Member to the list

    // Removing the member from the list
    public void unregister(Member obj){ // removing a member from the list
        if(this.members.isEmpty()){ // checking if the list is empty if so print to the user because he cant remove something that doesn't exist
            System.out.println("The list is empty plz register first");
        }
        else{
            this.members.remove(obj);
        }
    }

    //Inserts the string into this character sequence.
    //*@param offset The index in which we want to insert str string to our sequence.
    //* @param str The string we want to insert.
    public void insert(int offset, String obj){ 
       this.usb.insert(offset, obj);
       notifyObservers(); //Updates the observer for changes
    }

    // Appends the specified string to this character sequence.
    // @param str A string to append to our "text" class field.
    public void append(String obj){
        this.usb.append(obj);
        notifyObservers(); //Updates the observer for changes
    }
    
    /**
     * Removes the characters in a substring of this sequence.
     * The substring begins at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     */
    public void delete(int start, int end){
        this.usb.delete(start, end);
        notifyObservers(); //Updates the observer for changes
    }

    // Undo changes that were made to the StringBuilder field by going one step back in the stack history.
    public void undo(){
        this.usb.undo();
        notifyObservers(); //Updates the observer for changes
    }

}


