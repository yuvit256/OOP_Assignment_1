package observer;

public class ConcreteMember implements Member {//Observable

	private UndoableStringBuilder memberState;

	public UndoableStringBuilder getMemberState() { // return the member
		return memberState; 
	}

	public void update (UndoableStringBuilder value) { //Update the member
		memberState = value; 
		System.out.println("Up-to-date usb.value() = " + memberState);
	}
	
}