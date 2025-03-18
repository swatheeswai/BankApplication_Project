import java.sql.Timestamp;
class Transaction{
    private int transactionId;
    private int userId;
    private int accountNumber;
    private TransactionType transactionType;
    private double amount;
    private Timestamp transactionDate;
  
    public Transaction(int userId,int accountNumber,TransactionType transactionType,double amount){
                this.userId = userId;
                this.accountNumber = accountNumber;
                this.transactionType = transactionType;
                this.amount = amount;
                this.transactionDate = new Timestamp(System.currentTimeMillis());
    }
    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
        }
    public int getTransactionId(){
          return transactionId;
          }
    public int getUserId(){
          return userId;
          }
     public int getAccountNumber(){
          return accountNumber;
          }
     public TransactionType getTransactionType(){
          return transactionType;
          }
     public double getAmount(){
         return amount;
         }
     public TimeStamp getTransactionDate(){
         return transactionDate;
         }
   
      }
