package atmproj;
public class Account {
    private String cardNumber;
    private int balance;
    private int transactionPos=0;
    private int point=0;
    private String transaction[] = {"","","","",""};

    Account() {
        cardNumber="123";
        balance=0;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }
    public int getBalance() {
        return this.balance;
    }
    public void addBalance(int number) {
        this.balance = this.balance + number;
    }
    public void subBalance(int number) {
        this.balance = this.balance - number;
    }

    public String addTransactionHistory(String operation, String amount) {

        transactionPos = transactionPos%5;
        transaction[transactionPos]=((transactionPos+1)+"- "+operation+" $"+amount);
        point = transactionPos;
        return transaction[transactionPos++];

    }
    public String nextTransactionHistory() {
        if(point<4)
        {
            point++;
        }
        if(transaction[point].equals("")) return transaction[--point];
        return transaction[point];

    }

    public boolean isValidCard(String card) {
        if (card.equals(cardNumber)) {
            return true;
        }
        else return false;
    }
    public String prevTransactionHistory() {
        if(point>0)
        {
            point--;
        }
        return transaction[point];


    }

}