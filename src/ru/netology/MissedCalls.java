package ru.netology;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MissedCalls {

    private final String phone;
    private final LocalDateTime missedCallDate;

    public MissedCalls(String phone, LocalDateTime missedCallDate) {
        this.phone = phone;
        this.missedCallDate = missedCallDate;
    }

    public String getPhone() {
        return this.phone;
    }

    public LocalDateTime getMissedCallDate() {
        return this.missedCallDate;
    }

    public static void addMissedCallInMissedCallsList(MissedCalls missedCall, PhoneBook phoneBook) {
        String phone = missedCall.getPhone();
        LocalDateTime missedCallDate = missedCall.getMissedCallDate();
        Map<LocalDateTime, String> missedCallsList = phoneBook.getMissedCallsList();
        missedCallsList.put(missedCallDate, phone);
    }

    public static void showAllMissedCallsInMissedCallsList(PhoneBook phoneBook) {
        Map<LocalDateTime, String> missedCallsList = phoneBook.getMissedCallsList();
        if (missedCallsList.size() > 0) {
            for (Map.Entry<LocalDateTime, String> entry : missedCallsList.entrySet()) {
                LocalDateTime missedCallDate = entry.getKey();
                String phone = entry.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
                String formatDateTime = missedCallDate.format(formatter);
                System.out.print(formatDateTime + " => ");
                Contact contact = phoneBook.searchContactByPhone(phone);
                if (contact != null) {
                    System.out.print(contact.getName() + " ");
                    List<String> contactGroupList = phoneBook.getGroupListForContact(contact);
                    if (contactGroupList.size() > 0) {
                        for (String s : contactGroupList) {
                            System.out.print("<" + s + "> ");
                        }
                    }
                    System.out.print("\n");
                } else {
                    System.out.print(phone + "\n");
                }
            }
        } else {
            System.out.println("Список пропущенных вызовов пуст!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MissedCalls)) return false;
        MissedCalls that = (MissedCalls) o;
        return Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getMissedCallDate(), that.getMissedCallDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhone(), getMissedCallDate());
    }

    @Override
    public String toString() {
        return "MissedCalls { " +
                "phone -> '" + phone + '\'' +
                ", missedCallsDate -> " + missedCallDate +
                " } ";
    }
}
