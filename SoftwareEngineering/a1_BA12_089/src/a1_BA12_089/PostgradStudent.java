package a1_BA12_089;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
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
 * @object A typical UndergradStudent is S=<i,n,p,a,g>,where id(i), name(n),
 * 						phoneNumber(p), address(a), gpa(g)
 * 
 * @abstract_properties
 * 	mutable(id)=false /\ optional(id)=false /\ min(id)==10^8+1 /\ max(id)=10^9 /\
 * 	mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * 	mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 * 	mutable(address)=true /\ optional(address)=false /\ length(address)=100 /\
 * 	mutable(gpa)=true /\ optional(gpa)=false /\ min(gpa)=0.0 /\ max(gpa)=4.0
 * 
 * 
 * @author huy-nguyen-quoc
 * 
 * @version 1
 */

public class PostgradStudent extends Student{
	@DomainConstraint(type="Float", mutable=true, optional=false, min=MIN_GPA, max=MAX_GPA)
	private float gpa;

	//constants
	private static final int minID = 100000001;
	private static final int maxID = 1000000000;
	private static final float MIN_GPA = 0.0f;
	private static final float MAX_GPA = 4.0f;
	
	/**
	 * @effects <pre>
	 * 	if id, name, phoneNumber, address are valid
	 * 		initialize this as <id, name, phoneNumber, address>
	 * 	else
	 * 		initialize this as <> and inform error
	 * </pre>*/
	@DOpt(type=OptType.Constructor)
	public PostgradStudent(@AttrRef("id") int id,
						@AttrRef("name") String name,
						@AttrRef("phoneNumber") String phoneNumber,
						@AttrRef("address") String address,
						@AttrRef("gpa") float gpa) throws NotPossibleException {
		super(id, name, phoneNumber, address);
		if (!validateID(id)) {
			throw new NotPossibleException("Student.init: Invalid student id" + id);
		}
		if (!validateGPA(gpa)) {
			throw new NotPossibleException("Student.init: Invalid student gpa " + gpa);
		}
		this.gpa = gpa;
	}
	
	/**
	 * @effects <pre>
	 * 	if gpa is valid
	 * 		this.gpa = gpa
	 * 		return true
	 * 	else 
	 * 		return false
	 * </pre>*/
	@DOpt(type=OptType.Mutator)
	@AttrRef("gpa")
	public boolean setGPA(float gpa) {
		if (validateGPA(gpa)) {
			this.gpa = gpa;
			return true;
		}
		return false;
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
	
	/**
	 * @effects <pre>
	 * 	if gpa is valid
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>*/
	private boolean validateGPA(float gpa) {
		return (gpa >= MIN_GPA && gpa <= MAX_GPA);
	}
}
