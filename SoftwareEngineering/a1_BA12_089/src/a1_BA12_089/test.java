package a1_BA12_089;

import utils.NotPossibleException;

public class test {
	public static void main(String[] args) {
		UndergradStudent studentlist[] = new UndergradStudent[2];
		try {
			
			
			studentlist[1] = new UndergradStudent(1000000, "Huy", "0123456789", "Thai Nguyen");
			studentlist[0] = new UndergradStudent(1000, "Huy", "0123456789", "Thai Nguyen");
			

			
		} catch(NotPossibleException e) {
			e.printStackTrace();
		}
		for(UndergradStudent e : studentlist) {
			System.out.println(e);
		}
		
		
	}
}
