package observer;

public class ConcreteMember implements Member {//Observable

	private UndoableStringBuilder memberState;

	public void update (UndoableStringBuilder value) { //Update the member
		memberState = value; 
		System.out.println("Up-to-date usb.value() = " + memberState);
	}

	@Override
	public String toString() {
		return memberState.toString(); // Make it a String
	}
}