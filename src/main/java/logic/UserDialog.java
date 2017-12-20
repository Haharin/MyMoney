package logic;

import logic.parseCurrency.Currency;
import logic.parseCurrency.CurrencyList;
import logic.parseCurrency.CurrencyParser;
import logic.parseGoal.Goal;
import logic.parseGoal.GoalList;
import logic.parseGoal.GoalParser;
import logic.parseGoal.GoalWriter;
import logic.parseMoney.Money;
import logic.parseMoney.MoneyParser;
import logic.parseMoney.MoneyWriter;
import logic.parseTransaction.Transaction;
import logic.parseTransaction.TransactionList;
import logic.parseTransaction.TransactionParser;
import logic.parseTransaction.TransactionWriter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UserDialog {

    private static String LOGIN = "testUser";
    private static String PASSWORD = "5555";
    private Money wallet;
    private TransactionList transactions;
    private GoalList goals;
    private List<Currency> currencies;
    private Boolean authorisation(String login, String password){
        if (login.equals(LOGIN) && password.equals(PASSWORD)){
            return true;
        }
        return false;
    }
    private LocalDateTime dateEnter(){
        Scanner scanner = new Scanner(System.in);
        LocalDateTime tmp;
        try{
            System.out.println("Введите дату в формате дд-мм-гггг-чч-мм-сс");
            tmp = StringToDateParser.doIt(scanner.nextLine());
        }
        catch (Exception e){
            System.out.println("Ошибка в формате даты");
            tmp = dateEnter();
        }
        return tmp;
    }
    private String enterLine(){
        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine();
        if (tmp.length() < 1){
            System.out.println("Ошибка ввода");
            tmp = enterLine();
        }
        return tmp;
    }
    private void addGoal(){
        Character charTmp;
        try{
            System.out.println("Введите название цели: ");
            String name = enterLine();
            int id = goals.getGoalList().get(goals.getGoalList().size() - 1).getId() + 1;
            System.out.println("Введите сумму для достижения цели: ");
            Double amount = Double.parseDouble(enterLine());
            String date = null;
            Boolean complete = false;

            Goal tmpGoal = new Goal();
            tmpGoal.setName(name);
            tmpGoal.setId(id);
            tmpGoal.setGoalSum(amount);
            tmpGoal.setActualDateString(date);
            tmpGoal.setComplete(complete);

            System.out.println(tmpGoal);

            System.out.println("Транзакция введена верно? (y/n)");
            charTmp = enterLine().toCharArray()[0];
            if (charTmp.equals('y')){
                List<Goal> tmpGoals = goals.getGoalList();
                tmpGoals.add(tmpGoal);
                goals.setGoalList(tmpGoals);
                System.out.println("Цель " + name + " добавлена.");
            }
            else{
                System.out.println("Желаете повторить ввод? (y/n).");
                charTmp = enterLine().toCharArray()[0];
                if (charTmp.equals('y')){
                    addGoal();
                }
                else{
                    return;
                }
            }
        }
        catch (Exception e){
            System.out.println("Ошибка при добавлении цели.");
            System.out.println("Желаете повторить ввод? (y/n).");
            charTmp = enterLine().toCharArray()[0];
            if (charTmp.equals('y')){
                addGoal();
            }
            else{
                return;
            }
        }
    }
    private void addTransaction(){
        Character charTmp;
        try{
            System.out.println("Введите название транзакции: ");
            String name = enterLine();
            System.out.println("Введите описание транзакции: ");
            String description = enterLine();
            int id = transactions.getTransactionList().get(transactions.getTransactionList().size() - 1).getTransactionID() + 1;
            System.out.println("Введите название валюты: ");
            printCurrencies();
            String currency = enterCurrency();
            System.out.println("Введите сумму: ");
            Double amount = Double.parseDouble(enterLine());
            System.out.println("Взять текущую дату? (y/n)");
            String date = null;
            charTmp = enterLine().toCharArray()[0];
            if (charTmp.equals('y')){
                date = StringToDateParser.doBack(LocalDateTime.now());
            }
            else{
                try {
                    date = StringToDateParser.doBack(StringToDateParser.doIt(enterLine()));
                }
                catch (Exception e){
                    System.out.println("Ошибка формата даты!");
                }
            }
            Transaction tmpTransaction = new Transaction();
            tmpTransaction.setTransactionName(name);
            tmpTransaction.setTransactionDescriprtion(description);
            tmpTransaction.setTransactionID(id);
            tmpTransaction.setCurrency(currency);
            tmpTransaction.setTransactionAmount(amount);
            tmpTransaction.setDate(date);

            System.out.println(tmpTransaction);

            System.out.println("Транзакция введена верно? (y/n)");
            charTmp = enterLine().toCharArray()[0];
            if (charTmp.equals('y')){
                List<Transaction> tmpTransactions = transactions.getTransactionList();
                tmpTransactions.add(tmpTransaction);
                transactions.setTransactionList(tmpTransactions);
                System.out.println("Транзакция " + name + " добавлена.");
            }
            else{
                System.out.println("Желаете повторить ввод? (y/n).");
                charTmp = enterLine().toCharArray()[0];
                if (charTmp.equals('y')){
                    addTransaction();
                }
                else{
                    return;
                }
            }
        }
        catch (Exception e){
            System.out.println("Ошибка при добавлении транзакции.");
            System.out.println("Желаете повторить ввод? (y/n).");
            charTmp = enterLine().toCharArray()[0];
            if (charTmp.equals('y')){
                addTransaction();
            }
            else{
                return;
            }
        }
    }
    private String enterCurrency(){
        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine();
        Currency tmpCurrency = Currency.findCurrencyByName(tmp);
        if (tmpCurrency == null){
            System.out.println("Ошибка ввода валюты");
            tmp = enterCurrency();
        }
        return tmp;
    }
    private void completeGoal(){
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        Character tmpChar;
        tmpChar = temp.toCharArray()[0];
        if (tmpChar.equals('a')){
            addGoal();
            return;
        }
        if (tmpChar.equals('b')){
            menu();
            return;
        }
        Goal tmp = goals.findGoalById(Integer.parseInt(temp));
        if(tmp != null){
            System.out.println("Выбрана следующая цель:");
            System.out.println(tmp);
            if(!tmp.getComplete()){
                if (tmp.getGoalSum() <= wallet.getAmount()){
                    System.out.println("На вашем счету достаточно средств для достижения цели.");
                    System.out.println("Желаете выполнить? (y/n)");
                    temp = enterLine();
                    tmpChar = temp.toCharArray()[0];
                    if(tmpChar.equals('y')){
                        List<Goal> tmpGoals = goals.getGoalList();
                        Integer tmpInt = tmpGoals.lastIndexOf(tmp);
                        tmp.setComplete(true);
                        tmp.setActualDateString(StringToDateParser.doBack(LocalDateTime.now()));
                        tmpGoals.set(tmpInt, tmp);
                        goals.setGoalList(tmpGoals);
                        generateGoalTransaction(tmp);
                        wallet.update(transactions.getTransactionList());
                    }
                    else{
                        menu();
                    }
                }
                else{
                    System.out.println("На вашем счету не достаточно средств.");
                    System.out.format("Вам не хватает %.2fRUR.\n", (tmp.getGoalSum() - wallet.getAmount()));
                    System.out.println();
                    goalMenu();
                }
            }
            else{
                System.out.println("Поздравляем, вы уже достигли этой цели!");
            }
        }
        else{
            System.out.println("Цель не найдена!!");
            goalMenu();
        }
    }
    private void printCurrencies(){
        for (Currency currency:currencies) {
            System.out.println(currency.getName() + "  " + currency.getRate());
        }
    }
    private void printTransactionInPeriod(){
        System.out.println("Начало:");
        LocalDateTime startDate = dateEnter();
        System.out.println("Конец:");
        LocalDateTime endDate = dateEnter();

        for (Transaction transaction: transactions.getTransactionListInPeriod(startDate, endDate)){
            System.out.println(transaction);
            System.out.println();
        }
    }
    private void menu(){
        printOptions();
        choose();
    }
    private void goalMenu(){
        String temp;
        Scanner scanner = new Scanner(System.in);
        for(Goal goal: goals.getGoalList()){
            System.out.println(goal);
        }
        System.out.println();
        System.out.println("Укажите номер цели, добавьте новую (a) или выйдите из меню (b):");
        try{
            completeGoal();
        }
        catch (Exception e){
            System.out.println("Некорректный ввод!");
            completeGoal();
        }

    }
    private void printOptions(){
        System.out.println("Выберите действие:");
        System.out.println("1. Вывести текущий баланс.");
        System.out.println("2. Вывести все транзакции за период.");
        System.out.println("3. Меню целей.");
        System.out.println("4. Вывести текущий курс валют.");
        System.out.println("5. Добавить транзакцию.");
        System.out.println("6. Выход.");
    }
    private void generateGoalTransaction(Goal goal){
        Transaction tmpTransaction = new Transaction();
        tmpTransaction.setTransactionName("Достижение цели " + goal.getName());
        tmpTransaction.setTransactionDescriprtion("Цель номер " + goal.getId() + " достигнута");
        int id = transactions.getTransactionList().get(transactions.getTransactionList().size() - 1).getTransactionID() + 1;
        tmpTransaction.setTransactionID(id);
        tmpTransaction.setCurrency("RUR");
        tmpTransaction.setTransactionAmount((-1) * goal.getGoalSum());
        tmpTransaction.setDate(goal.getActualDateString());
        List<Transaction> tmpTransactions = transactions.getTransactionList();
        tmpTransactions.add(tmpTransaction);
        transactions.setTransactionList(tmpTransactions);
        System.out.println("Транзакция \'Достижение цели " + goal.getName() + "\' добавлена.");
    }
    private void readCurrencies(){
        CurrencyParser currencyParser = new CurrencyParser();
        CurrencyList parsedCurrencyList = currencyParser.parseList("currencies.xml");
        currencies = parsedCurrencyList.getCurrencyList();
    }
    private void readWallet(){
        MoneyParser moneyParser = new MoneyParser();
        wallet =  moneyParser.parse("wallet.xml");

    }
    private void writeWallet(){
        MoneyWriter moneyWriter = new MoneyWriter();
        moneyWriter.write(wallet, "wallet.xml");
    }
    private void readTransactions(){
        TransactionParser transactionParser = new TransactionParser();

        transactions = transactionParser.parseList("transactions.xml");
    }
    private void writeTransactions(){
        TransactionWriter transactionWriter = new TransactionWriter();
        transactionWriter.write(transactions, "transactions.xml");
    }
    private void readGoals(){
        GoalParser goalParser = new GoalParser();

        goals = goalParser.parseList("goals.xml");
    }
    private void writeGoals(){
        GoalWriter goalWriter = new GoalWriter();
        goalWriter.write(goals, "goals.xml");
    }
    private void save(){
        writeGoals();
        writeTransactions();
        writeWallet();
    }
    private void loop(){
        menu();
        System.out.println("Желаете продолжить работу? (y/n)");
        Character charTmp = enterLine().toCharArray()[0];
        if (charTmp.equals('y')){
            loop();
        }
        else{
            System.out.println("Сохранить изменения? (y/n)");
            charTmp = enterLine().toCharArray()[0];
            if (charTmp.equals('y')){
                save();
                System.out.println("Все данные сохранены! Всего хорошего, " + LOGIN + "!");
            }
            else{
                System.out.println("Вы уверены? Все изменения будут потеряны. (y - exit/n - save)");
                charTmp = enterLine().toCharArray()[0];
                if (charTmp.equals('n')){
                    save();
                }
                else{
                    System.out.println("Резульаты не сохранены. До свидания!");
                }
            }
        }
    }
    private void choose(){
        System.out.println("Выберите действие от 1 до 6:");
        Scanner scanner = new Scanner(System.in);
        String temp = enterLine();
        switch (temp.toCharArray()[0]){
            case '1':
                System.out.format("Ваш баланс: %.2f RUR.\n", wallet.getAmount());
                System.out.println("Дата последнего изменения: " + wallet.getLastUpdate());
                break;
            case '2':
                printTransactionInPeriod();
                break;
            case '3':
                goalMenu();
                break;
            case '4':
                printCurrencies();
                break;
            case '5':
                addTransaction();
                break;
            case '6':
                break;
            default:
                System.out.println("Повторите ввод");
                choose();
                break;

        }
    }
    public void askUser(){
        System.out.println("Здравствуйте, " + LOGIN + "! Введите логин:");
        Scanner scanner = new Scanner(System.in);
        String tmpLogin = scanner.nextLine();
        System.out.println("Введите пароль:");
        String tmpPassword = scanner.nextLine();
        if (authorisation(tmpLogin, tmpPassword) || true){
            readCurrencies();
            readWallet();
            readTransactions();
            readGoals();
            wallet.update(transactions.getTransactionList());
            loop();
        }
        else{
            System.out.println("Данные некорректны. Повторите ввод.");
            askUser();
        }

    }
}
