# CashFlowMaster


# 🚀 Main Class 

The Main class is the entry point of your application. It contains the main method which starts the application. It creates a Scanner object for reading user input, a Ledger object for managing transactions, and a Reports object for generating reports. The main loop of the application displays a menu to the user and waits for their input. Depending on the user's choice, it calls different methods to handle different actions such as adding a deposit, making a payment, displaying the ledger, running reports, or exiting the application.

🕹️ Interesting parts of the Main class is the main loop where the application displays a menu to the user and waits for their input. Depending on the user's choice, it calls different methods to handle different actions. Here's the example:

![UserMenu](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/8f2c142f-c053-4575-80a5-d493f8f28242)

🚀

![UserMenu2](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/3c893fcf-3b01-4533-950b-e6272161f054)

This loop continues until the user chooses to exit the application. It's a good example of how to create a simple, text-based user interface in a console application.

# 🚀 Ledger Class

The Ledger class is responsible for managing transactions. It contains a list of Transaction objects and methods to add a transaction to the list and display the ledger. The ledger can be displayed in different ways depending on the user's choice: all transactions, deposits only, or payments only.

Here is in action 🔥

![MadePayment](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/3dadbdb1-75d1-4ecb-8db3-9fca9b8beb77)

🕹️ One of the key features of the Ledger class could be the method that adds a transaction to the ledger and the method that displays the ledger based on the user's choice.

Here is an action 🔥


![AddedDeposit](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/0c00b938-7b77-4c0a-9ff7-954d1ad5458a)

🚀

![DisplayingDeposits](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/ce059549-43af-4f39-af9a-e6887a618ffc)

In this code, the addTransaction method adds a new transaction to the ledger, and the displayLedger method displays all transactions, only deposits, or only payments based on the user's choice.

![Reports](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/e243cd6f-8953-402f-8d50-21a37c3ea500)

🚀

![Reports2](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/6812cc54-941f-43a4-9219-885df50e096e)

🕹️ An interesting part could be the runReportMenu method. This method provides a user interface for generating various reports. It uses a switch statement to handle user input and call the appropriate report generation method.

Here is in action 🔥

![ReportMenu](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/2e70355e-2350-452d-bc72-2093df080b10)

🕹️ Another interesting part is the set of methods used to generate different types of reports. These methods (generateMonthToDateReport, generatePreviousMonthReport, generateYearToDateReport, generatePreviousYearReport, searchByVendor, customSearch) each generate a specific type of report based on different criteria. 

Here is in action 🔥

![MonthToDate](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/7feee375-76fe-42da-8708-3e62bca0f349)


# 🚀 Transaction Class

🕹️ The Transaction class represents a transaction. It contains details of the transaction such as the date, time, description, vendor, and amount. It also has a method to write the transaction to a file. One of the key features of the Transaction class could be the method that writes a transaction to a file and the method that creates a file if it does not exist.

writeToTransactionFile(): This method writes the transaction data to a file. It first calls the createFileIfNotExists() method to ensure that the file exists. Then it formats the transaction data into a string and writes this string to the file. If an IOException occurs during this process, it prints an error message. 

createFileIfNotExists(): This method checks if the file specified by FILENAME exists. If the file does not exist, it creates the file. If an IOException occurs during this process, it throws the exception to be handled by the calling method.

Here is the code for these methods 🔥

![TransactionMethods](https://github.com/Volodymyr199606/CashFlowMaster/assets/166452639/517e0657-62fe-42cf-889a-9591e1ee5371)

These methods are interesting because they handle file operations, which are a common requirement in many applications. They demonstrate how to write data to a file and how to create a file if it does not exist, which are useful skills for a developer to have.




#### In general each class has a specific role in the application and they work together to manage transactions and generate reports. 🎯








### 🚀 My project is a comprehensive Accounting Ledger Application developed in Java, a robust and widely-used programming language. It's designed to manage financial transactions with ease and efficiency, making it an ideal tool for small to medium-sized businesses or individuals who want to keep track of their finances. 🚀





🔥 Key Features: 🔥




✅ Transaction Management: The application allows users to add deposits and make payments. Each transaction records the date, time, description, vendor, and amount. This information is then written to a file, ensuring that all transaction data is stored securely and can be accessed later.  

✅ Ledger Display: The application provides a ledger display feature that allows users to view all transactions, deposits, or payments. This feature makes it easy for users to review their financial activities and track their spending or income.  

✅ Report Generation: The application includes a Reports class that can generate various types of reports based on different criteria. This feature can be extremely useful for users who need to analyze their financial data for budgeting or tax purposes.  

✅ User-Friendly Interface: The application features a user-friendly console interface with clear instructions and options. This makes the application easy to use, even for those who are not tech-savvy.  

✅ Data Persistence: The application writes all transactions to a CSV file. This ensures that your data is not lost when the application is closed, and it can be easily exported for use in other applications or for backup purposes. 

✅ Robust Error Handling: The application includes robust error handling to ensure that it runs smoothly and reliably. It can handle common issues such as file I/O errors, ensuring that your data is safe. 


Please don't hesitate to reach out. Your satisfaction is important, and I'm here to assist you every step of the way! 😉