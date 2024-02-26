import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bank";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    private static Connection connection;

    static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

class BankAccount {
    private int accountId;
    private String accountNumber;
    private double balance;

    // Getters and setters

    public BankAccount(int accountId, String accountNumber, double balance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}

class Customer {
    private int customerId;
    private String name;
    private String address;
    private List<BankAccount> accounts;

    // Getters and setters

    public Customer(int customerId, String name, String address) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public void removeAccount(BankAccount account) {
        accounts.remove(account);
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }
}

class Transaction {
    private int transactionId;
    private BankAccount account;
    private double amount;
    private String type;

    // Getters and setters

    public Transaction(int transactionId, BankAccount account, double amount, String type) {
        this.transactionId = transactionId;
        this.account = account;
        this.amount = amount;
        this.type = type;
    }
}

class BankAnimation {
    public static void animate() {
        // Implement your animation logic here
        System.out.println("Animating bank operations...");
    }
}

public class BankManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    performTransaction();
                    break;
                case 3:
                    BankAnimation.animate();
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Bank Management System");
        System.out.println("----------------------");
        System.out.println("1. Create Customer");
        System.out.println("2. Perform Transaction");
        System.out.println("3. Bank Animation");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createCustomer() {
        // Get customer details from the user
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        // Create a new customer
        Customer customer = new Customer(getNextCustomerId(), name, address);

        // Save the customer to the database
        saveCustomer(customer);

        System.out.println("Customer created successfully!");
    }

    private static void saveCustomer(Customer customer) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO customers (name, address) VALUES (?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                customer.setCustomerId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getNextCustomerId() {
        int nextId = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MAX(customer_id) FROM customers")){
            if (resultSet.next()) {
                nextId = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    private static void performTransaction() {
        // Get account number from the user
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        // Retrieve the account from the database
        BankAccount account = getAccountByNumber(accountNumber);

        if (account != null) {
            // Get transaction details from the user
            System.out.print("Enter transaction amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter transaction type (Deposit/Withdraw): ");
            String type = scanner.nextLine();

            // Create a new transaction
            Transaction transaction = new Transaction(getNextTransactionId(), account, amount, type);

            // Save the transaction to the database
            saveTransaction(transaction);

            System.out.println("Transaction performed successfully!");
        } else {
            System.out.println("Account not found!");
        }
    }

    private static BankAccount getAccountByNumber(String accountNumber) {
        BankAccount account = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ?")) {

            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                double balance = resultSet.getDouble("balance");
                account = new BankAccount(accountId, accountNumber, balance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    private static void saveTransaction(Transaction transaction) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO transactions (account_id, amount, type) VALUES (?, ?, ?)")) {

            statement.setInt(1, transaction.getAccount().getAccountId());
            statement.setDouble(2, transaction.getAmount());
            statement.setString(3, transaction.getType());
            statement.executeUpdate();

            // Update the account balance
            if (transaction.getType().equalsIgnoreCase("deposit")) {
                transaction.getAccount().setBalance(transaction.getAccount().getBalance() + transaction.getAmount());
            } else if (transaction.getType().equalsIgnoreCase("withdraw")) {
                transaction.getAccount().setBalance(transaction.getAccount().getBalance() - transaction.getAmount());
            }

            // Update the account balance in the database
            updateAccountBalance(transaction.getAccount());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateAccountBalance(BankAccount account) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE accounts SET balance = ? WHERE account_id = ?")) {

            statement.setDouble(1, account.getBalance());
            statement.setInt(2, account.getAccountId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getNextTransactionId() {
        int nextId = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MAX(transaction_id) FROM transactions")) {

            if (resultSet.next()) {
                nextId = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }
}