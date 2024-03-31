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
 * 	name		String
 * 	phoneNumber	String
 * 	address		String
 * 
 * @object A typical Student is S=<i,n,p,a>,where id(i), name(n),
 * 						phoneNumber(p), address(a)
 * 
 * @abstract_properties
 * 	mutable(id)=false /\ optional(id)=false /\ min(id)==1 /\ max(id)=10^9 /\
 * 	mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * 	mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 * 	mutable(address)=true /\ optional(address)=false /\ length(address)=100 
 * 
 * @author huy-nguyen-quoc
 * 
 * @version 1
 */

public abstract class Student implements Comparable<Object>{
	@DomainConstraint(type="Integer", mutable=false, optional=false,min=MIN_ID, max=MAX_ID)
	private int id;
	@DomainConstraint(type="String", mutable=true, optional=false, length=MAX_LENGTH_NAME)
	private String name;
	@DomainConstraint(type="String", mutable=true, optional=false, length=MAX_LENGTH_PHONE_NUMBER)
	private String phoneNumber;
	@DomainConstraint(type="String", mutable=true, optional=false, length=MAX_LENGTH_ADDRESS)
	private String address;
	
	
	//constants
	private static final int MIN_ID = 1;
	private static final int MAX_ID = 1000000000;
	private static final int MAX_LENGTH_NAME = 50;
	private static final int MAX_LENGTH_PHONE_NUMBER = 10;
	private static final int MAX_LENGTH_ADDRESS = 100;
	
	
	
	//Constructor method
	/**
	 * @effects <pre>
	 * 	if id, name, phoneNumber, address are valid
	 * 		initialize this as <id,name,phoneNumber, address>
	 * 	else
	 * 		initialize this as <> and inform error
	 * </pre>*/
	@DOpt(type=OptType.Constructor)
	public Student(@AttrRef("id") int id,
				   @AttrRef("name") String name,
				   @AttrRef("phoneNumber") String phoneNumber,
				   @AttrRef("address") String address) throws NotPossibleException{
		if(!validateID(id)) {
			throw new NotPossibleException("Student.init: Invalid student id " + id);
		}
		if(!validateName(name)) {
			throw new NotPossibleException("Student.init: Invalid student name " + name);
		}
		if(!validatePhoneNumber(phoneNumber)) {
			throw new NotPossibleException("Student.init: Invalid student phone number "+ phoneNumber);
		}
		if(!validateAddress(address)) {
			throw new NotPossibleException("Student.init: Invalid student address " + address);
		}

		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	/**
	 * @effects return <tt>id</tt>
	 */
	@DOpt(type=OptType.Observer)
	@AttrRef("id")
	public int getID() {
		return this.id;
	}
	
	/**
	 * @effects return <tt>this.name</tt>
	 */
	@DOpt(type=OptType.Observer)
	@AttrRef("name")
	public String getName() {
		return this.name;
	}
	
	/**
	 * @effects <pre>
	 * 	if name is valid
	 * 		set this.name=name
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>*/
	@DOpt(type=OptType.Mutator)
	@AttrRef("name")
	public boolean setName(String name) {
		if (validateName(name)) {
			this.name = name;
			return true;
		}
		return false;
	}
	
	/**
	 * @effects return <tt>this.phoneNumber</tt>
	 * */
	@DOpt(type=OptType.Observer)
	@AttrRef("phoneNumber")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	/**
	 * @effects <pre>
	 * 	if phoneNumber is valid
	 * 		this.phoneNumber = phoneNumber;
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>*/
	@DOpt(type=OptType.Mutator)
	@AttrRef("phoneNumber")
	public boolean setPhoneNumber(String phoneNumber) {
		if (validatePhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
			return true;
		}
		return false;
	}
	
	/**
	 * @effects return <tt>this.address</tt>
	 * */
	@DOpt(type=OptType.Observer)
	@AttrRef("address")
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * @effects <pre>
	 * 	if address id valid
	 * 		this.address = address
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>*/
	@DOpt(type=OptType.Mutator)
	@AttrRef("address")
	public boolean setAddress(String address) {
		if (validateAddress(address)) {
			this.address = address;
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
	protected boolean validateID(int id) {
		return id>=MIN_ID && id <=MAX_ID;
	}
	
	/**
	 * @effects <pre>
	 * 	if name is valid
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>*/
	private boolean validateName(String name) {
		return (name != null &&
				name.length() > 0 &&
				name.length() <= MAX_LENGTH_NAME);
	}
	
	/**
	 * @effects <pre>
	 * 	if phoneNumber is valid
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>*/
	private boolean validatePhoneNumber(String phoneNumber) {
		return phoneNumber.length() == MAX_LENGTH_PHONE_NUMBER;
	}
	
	/**
	 * @effects <pre>
	 * 	if address is valid
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>*/
	private boolean validateAddress(String address) {
		return (address != null &&
				address.length() > 0 &&
				address.length() <= MAX_LENGTH_ADDRESS);
	}

	
	/**
	 * @effects <pre>
	 * 	if obj is instanceof Student
	 * 		Student st = (Student) obj
	 * 		return this.name.compareTo(st.getName())
	 * 	else 
	 * 		inform error
	 * </pre>*/
	@Override
	public int compareTo(Object other) {
	    if (other instanceof Student) {
	    	Student student = (Student) other;
	        return this.name.compareTo(student.getName());
	    }
	    throw new ClassCastException("Object is not an instance of Student");
	}

	
}
