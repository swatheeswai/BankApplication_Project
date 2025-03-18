public class Customer extends User {
    private int customer_id;
    private long aadhaar_No;
    private String PAN_no;
    private String Address;

    // Constructor
    public Customer(int customer_id, String name, String email_id, String dob, long phoneNo, String password, String Address, long aadhaar_No, String PAN_no) {
        super(name, email_id, dob, phoneNo, password);
        this.customer_id = customer_id;
        this.aadhaar_No = aadhaar_No;
        this.PAN_no = PAN_no;
        this.Address = Address;
    }

    // toString Method for Customer
    public String toString() {
        return "Customer{customer_id=" + customer_id +
                ", name='" + getName() + "'" +
                ", email_id='" + getEmail_id() + "'" +
                ", dob='" + getDob() + "'" +
                ", phoneNo=" + getPhoneNo() +
                ", Address='" + Address + "'" +
                ", Aadhaar_No=" + aadhaar_No +
                ", PAN_No='" + PAN_no + "'" +
                "}";
    }

