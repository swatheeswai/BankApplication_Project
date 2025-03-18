public class User {
   private String name;
   private String email_id;
   private String dob;
   private long phoneNo;
   private String password;

    public User(){}
    
   // Constructor
   public User(String name, String email_id, String dob, long phoneNo, String password) {
       this.name = name;
       this.email_id = email_id;
       this.dob = dob;
       this.phoneNo = phoneNo;
       this.password = password;
   }

   // Setter methods
   public void setName(String name) { 
          this.name = name; 
   }
   public void setEmail_id(String email_id) { 
   this.email_id = email_id; 
   }
   public void setDob(String dob) { 
   this.dob = dob; 
   } 
   public void setPhoneNo(long phoneNo) { 
   this.phoneNo = phoneNo; 
   }
   public void setPassword(String password) { 
   this.password = password;
    }

   // Getter methods
   public String getName() { 
   return name; 
   }
   public String getEmail_id() {
    return email_id; 
    }
   public String getPassword() { 
   return password; 
   }
   public String getDob() {
    return dob; 
    }
   public long getPhoneNo() { 
   return phoneNo; 
   }

   // toString method
   public String toString() {
       return "{" + name + "," + email_id + "," + dob + "," + phoneNo + "," + password + "}";
   }
}

