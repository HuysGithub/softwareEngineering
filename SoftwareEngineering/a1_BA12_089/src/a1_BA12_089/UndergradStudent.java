package a1_BA12_089;

import utils.AttrRef;
import utils.DOpt;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview	
 * 
 * @attributes
 * 	id			Integer
 * 	name			String
 * 	phoneNumber	String
 * 	address		String
 * 
 * @object A typical UndergradStudent is S=<i,n,p,a>,where id(i), name(n),
 * 						phoneNumber(p), address(a)
 * 
 * @abstract_properties
 * 	mutable(id)=false /\ optional(id)=false /\ min(id)==10^5 /\ max(id)=10^8 /\
 * 	mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * 	mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 * 	mutable(address)=true /\ optional(address)=false /\ length(address)=100 
 * 
 * @author huy-nguyen-quoc
 * 
 * @version 1
 */

public class UndergradStudent extends Student{
	
	//constants
	private static final int minID = 100000;
	private static final int maxID = 100000000;

	/**
	 * @effects <pre>
	 * 	if id, name, phoneNumber, address are valid
	 * 		initialize this as <id, name, phoneNumber, address>
	 * 	else
	 * 		initialize this as <> and inform error
	 * </pre>*/
	@DOpt(type=OptType.Constructor)
	public UndergradStudent(@AttrRef("id") int id,
							@AttrRef("name") String name,
							@AttrRef("phoneNumber") String phoneNumber,
							@AttrRef("address") String address) throws NotPossibleException {
		
		super(id, name, phoneNumber, address);
		if (!validateID(id)) {
			throw new NotPossibleException("Student.init: Invalid student id " + id);
		}
	}
	
	/**
	 * @effects <pre>
	 * 	if id is valid
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>*/
	@Override
	protected boolean validateID(int id) {
		return (id >= minID && id <= maxID);
	}

}
