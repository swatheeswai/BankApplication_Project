public class Employee extends User {
    private int employeeId;
    private String department;
    private double salary;
    public Employee(String name,String email_id,String dob,long phoneNo,String password,int employeeId,String department,double salary){
        super(name,email_id,dob,phoneNo,password); 
        this.employeeId = employeeid;
        this.department = department;
        this.salary = salary;
        }
     public void setEmployeeId(String employeeId){
             this.employee = employee;
             }
     public void setDepartment(String department){
            this.department = department;
            }
     public void setSalary(double salary){
            this.salary = salary;
            }
     public String getName(){
          return name;
          }
     public int getEmployeeId(){
            return employeeId;
            }
     public String getDepartment(){
            return department;
            }
     public double getSalary(){
            return salary;
            }
     public String toString(){
           return "{" + employeeId + "," + department + "," + salary+"}";
      }
   }
   
         

