import java.util.ArrayList;
import java.util.Scanner;

class atmusers {
  String name;
  String username;
  String account_num;
  String pin;
  float bank_balance = 0f;
  ArrayList<String> History = new ArrayList<>();
  static Scanner sc = new Scanner(System.in);

  public static boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) 
      return false;
    }
    return true;
  }


  // For set Name Properties
  public String setName() {
    String name = "";
    boolean seted = false;
    while (!seted) {
      name = sc.nextLine();
      for (int i = 0; i < name.length(); i++) {
        if (!Character.isLetter(name.charAt(i))) {
          seted = false;
          break;
        }
        seted = true;
      }
      if (seted == false) {
        System.out.print("\033[31m"); 
        System.out.println("Inavalid name .... !");
        System.out.println("\033[0m");
        System.out.print("Enter Proper name : ");
      }
    }
    return name;
  }


  // For set Username Properties
  public String setUserName() {
    String username = "";
    boolean seted = false;
    while (!seted) {
      username = sc.nextLine();
      if (isNumeric(username) == false) {
        seted = true;
        return username;
      }

      System.out.print("\033[31m"); 
      System.out.println("Inavalid Username .... !");
      System.out.println("\033[0m");
      System.out.print("Enter Proper Username : ");
    }
    return username;
  }


  // For set Account Number Properties
  public String setAccountNumber() {
    String account_num = "";
    boolean seted = false;
    while (!seted) {
      account_num = sc.nextLine();
      if (!isNumeric(account_num)) {
        System.out.print("\033[31m"); 
        System.out.println("Account Number must be a Digit");
        System.out.println("\033[0m");
        seted = false;
      } 
      else if (account_num.length() != 12) {
        System.out.print("\033[31m");
        System.out.println("Account Number must be of 12 Digit");
        System.out.println("\033[0m");
        seted = false;
      } 
      else {
        seted = true;
        return account_num;
      }
      System.out.print("Enter Account Number Again : ");
    }
    return account_num;
  }


  //For set Protection Pin properties
  public String setPin() {
    String pin = "";
    boolean seted = false;
    while (!seted) {
      pin = sc.nextLine();
      if (!isNumeric(pin)) {
        System.out.print("\033[31m"); 
        System.out.println("ATM Pin must be a Digit");
        System.out.println("\033[0m");
        seted = false;
      } 
      else if (pin.length() != 4) {
        System.out.print("\033[31m"); 
        System.out.println("Pin must be of 4 Digit");
        System.out.println("\033[0m");
        seted = false;
      } 
      else {
        seted = true;
        return pin;
      }
      System.out.print("Enter ATM Pin Again : ");
    }
    return pin;
  }


  //Register user
  public void signup() {

    System.out.print("\nEnter Your Name : ");
    this.name = setName();
    System.out.print("\nEnter Your Username : ");
    this.username = setUserName();
    System.out.print("\nEnter Your Account Number : ");
    this.account_num = setAccountNumber();
    System.out.print("\nEnter Your ATM Pin : ");
    this.pin = setPin();

    System.out.print("\033[32m \033[3m"); 
    System.out.println("\nSuccessflly Registered\n");
    System.out.print("\033[0m"); 
  }


  //Sign in user
  public boolean Login_user() {
    boolean islogin = false;

    while (!islogin) {
      System.out.print("\nEnter your Username : ");
      String inputname = sc.nextLine();
      System.out.print("Enter ATM pin : ");
      String inputpin = sc.nextLine();

      if (inputname.equals(this.username) && inputpin.equals(this.pin)) {
        System.out.print("\033[32m"); 
        System.out.println("\nSuccesfully Login...\n\n");
        System.out.print("\033[0m");
        islogin = true;
      } 
      else {
        System.out.print("\033[31m"); 
        System.out.println("\nInvalid Details");
        System.out.print("\033[0m");
        islogin = false;
      }
    }
    return islogin;
  }
}



class ATM_Activity extends atmusers {

  static Scanner sc = new Scanner(System.in);


  // For Check the Balance of Account
  public float checkbank_balance() {
    return bank_balance;
  }


  // For Transaction History
  public void trancation_Histoy() {
    System.out.print("\n\033[36m");
    System.out.print("\033[4m");
    System.out.println("Trancation History:");
    System.out.print("\033[0m");
    for (String i : History) {
      System.out.println(i);
    }
    System.out.println(" \n");
  }


  // For Withdrow the Money from ATM
  public void withdraw() {
    System.out.println("\033[36m" + "Withdraw:" + "\033[0m\n");
    System.out.print("Enter Amount : ");
    float amount = sc.nextFloat();

    if (checkbank_balance() < amount) {
      System.out.print("\033[31m");
      System.out.print("Not Suffient bank_balance Available in your Account");
      System.out.print("\033[0m");
    } else {
      System.out.print("\033[36m");
      System.out.print("Succesfully Withdraw\n");
      System.out.print("\033[0m");
      bank_balance = bank_balance - amount;

      String trancation = amount + "Rs. " + "Withdrawed.  ";
      History.add(trancation);
    }
  }


  // For Deposit the Money to ATM
  public void deposit_rupees() {
    System.out.println("\033[36m" + "Deposit:" + "\033[0m\n");
    System.out.print("Enter your Amount : ");
    float amount = sc.nextFloat();

    System.out.print("\033[32m");
    System.out.println("Deposite Successfully ");
    System.out.print("\033[0m");
    bank_balance = bank_balance + amount;

    String trancation =  amount + "Rs. " + "Deposited.";
    History.add(trancation);
  }


  // For the Transfer Money to another account
  public void transfer_amount() {
    System.out.println("\033[36m" + "Transfer:" + "\033[0m\n");
    System.out.print("Enter Receipent's Account No : ");
    String accno;
    accno = setAccountNumber();
    System.out.print("Enter Amount : ");
    float amount = sc.nextFloat();

    if (checkbank_balance() < amount) {
      System.out.print("\033[31m");
      System.out.print("Not Suffient Balance to Transfer ");
      System.out.print("Transaction Fail....!");
      System.out.print("\033[0m");
    } else {
      System.out.print("\033[32m");
      System.out.print("Transfer Succesfully..");
      System.out.print("\033[0m");
      bank_balance = bank_balance - amount;

      String trancation = amount + "Rs. " + "Tranfered to Account no. " + accno;
      History.add(trancation);
    }
  }
}



public class Project_ATM {
   static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) 
  {
    atmusers user = new atmusers();
    ATM_Activity act = new ATM_Activity();

    System.out.println();
    System.out.println("---------------");
    System.out.print("\033[33m"); 
    System.out.print("WELCOME TO ATM");
    System.out.println("\033[0m"); 
    System.out.println("---------------");

    System.out.print("\033[4m");
    System.out.print("Register to Yourself");
    System.out.println("\033[0m \n");
    user.signup();

    System.out.println("\n1.Login  \n2.Exit");
    System.out.print("Enter Your Choice : ");
    int choice_num = sc.nextInt();

    while (choice_num != 1 && choice_num != 2) {
      System.out.print("\033[31m"); 
      System.out.println("Invalid Choice");
      System.out.println("\033[0m");

      System.out.print("Enter Proper choice : ");
      choice_num = sc.nextInt();
    }

    if (choice_num == 1) {
      user.Login_user();

      while (true) {
        System.out.println(
          "1. Account bank_balance \n2. Deposit \n3. Withdraw \n4. Tranfer \n5. Transaction History \n6. Exit \n"
        );
        System.out.print("Which type of activity you want to do: ");
        int choice = sc.nextInt();
        System.out.println();

        switch (choice) {
          case 1:
            System.out.println(
             "\033[36m" + "Account bank_balance : \n" +
              act.checkbank_balance() +
              "\033[0m\n"
            );
            break;
          case 2:
            act.deposit_rupees();
            System.out.println("\n");
            break;
          case 3:
            act.withdraw();
            System.out.println("\n");
            break;
          case 4:
            act.transfer_amount();
            System.out.println("\n");
            break;
          case 5:
            act.trancation_Histoy();
            break;
          case 6:
            System.out.print("\033[33m"); 
            System.out.print("\033[3m"); 
            System.out.println("\nThank you for visit.");
            System.out.println("\033[0m");
            System.exit(0);
            break;
          default:
            System.out.print("\033[31m"); 
            System.out.println("Invalid Choice ....!");
            System.out.println("\033[0m");
            break;
        }
      }
    } 
    
    if (choice_num == 2) {
      System.out.print("\033[33m"); 
      System.out.print("\033[3m"); 
      System.out.println("Thank you for visit.");
      System.out.println("\033[0m");
      System.exit(0);
    }
  }
}
