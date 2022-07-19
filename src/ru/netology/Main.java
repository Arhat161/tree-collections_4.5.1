package ru.netology;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        PhoneBook myPhoneBook = new PhoneBook("Мои контакты");

        Contact[] contacts = {
                new Contact("Иван", "+7(911)1234567"),
                new Contact("Петр", "+7(904)7654321"),
                new Contact("Дмитрий", "+7(918)7531590")
        };

        Contact[] anotherContacts = {
                new Contact("Денис", "+7(985)1824567"),
                new Contact("Вася", "+7(938)7654417"),
                new Contact("Юля", "+7(964)96521478")
        };

        myPhoneBook.createGroup("Работа");
        myPhoneBook.createGroup("Семья");
        myPhoneBook.createGroup("Хобби");

        myPhoneBook.addContactsInGroup(contacts, "Работа");
        myPhoneBook.addContactsInGroup(anotherContacts, "Семья");

        System.out.println("Список Контактов в Телефонной Книге:");
        myPhoneBook.showAllContactsInAllGroups();

        System.out.println("Создаем пропущенные вызовы...\n");

        MissedCalls firstMissedCall = new MissedCalls("+7(911)1234567", LocalDateTime.now()); // Иван <Работа>
        Thread.sleep(2000);
        MissedCalls secondMissedCall = new MissedCalls("+7(964)96521478", LocalDateTime.now()); // Юлия <Семья>
        Thread.sleep(2000);
        MissedCalls thirdMissedCall = new MissedCalls("+7(918)7531590", LocalDateTime.now()); // Дмитрий <Работа>
        Thread.sleep(2000);
        MissedCalls forthMissedCall = new MissedCalls("+7(900)9004900", LocalDateTime.now()); // неизвестный номер

        MissedCalls.addMissedCallInMissedCallsList(firstMissedCall, myPhoneBook);
        MissedCalls.addMissedCallInMissedCallsList(secondMissedCall, myPhoneBook);
        MissedCalls.addMissedCallInMissedCallsList(thirdMissedCall, myPhoneBook);
        MissedCalls.addMissedCallInMissedCallsList(forthMissedCall, myPhoneBook);


        System.out.println("Список пропущенных вызовов в порядке поступления: \n");
        MissedCalls.showAllMissedCallsInMissedCallsList(myPhoneBook);

    }
}
