package ru.netology;

import java.time.LocalDateTime;
import java.util.*;

public class PhoneBook {

    private String phoneBookName;

    private HashMap<String, List<Contact>> groupList;
    private Map<LocalDateTime, String> missedCallsList;

    public PhoneBook(String phoneBookName) {
        this.phoneBookName = phoneBookName;
        this.groupList = new HashMap<>();
        this.missedCallsList = new TreeMap<>();
    }

    public boolean createGroup(String groupName) {
        if (groupName == null || groupName.equals("") || groupName.length() == 0) {
            return false;
        } else {
            List<Contact> contactList = new ArrayList<>();
            this.groupList.put(groupName, contactList);
            return true;
        }
    }

    public int getNumberOfGroups() {
        return this.groupList.size();
    }

    public boolean isGroupInPhoneBook(String groupName) {
        if (this.groupList.containsKey(groupName)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createContactInGroup(String name, String phone, String groupName) {
        if (this.groupList.containsKey(groupName)) {
            List<Contact> contactList = this.groupList.get(groupName);
            contactList.add(new Contact(name, phone));
            return true;
        } else {
            return false;
        }
    }

    public boolean addContactsInGroup(Contact[] contacts, String groupName) {
        if (this.groupList.containsKey(groupName)) {
            List<Contact> contactList = this.groupList.get(groupName);
            contactList.addAll(List.of(contacts));
            return true;
        } else {
            return false;
        }
    }

    public void showGroupsNamesInLine() {
        if (this.groupList.isEmpty()) {
            System.out.println("Нет Групп контактов в Телефонной Книге !");
        } else {
            System.out.print("Доступные группы: ");
            int count = 0;
            for (String s : groupList.keySet()) {
                System.out.print("<" + s + ">");
                count++;
                if (count != groupList.keySet().size()) {
                    System.out.print(", ");
                } else {
                    System.out.print("\n");
                }
            }
        }
    }

    public void showContactsInGroup(String groupName) {
        if (this.groupList.containsKey(groupName)) {
            List<Contact> contactList = this.groupList.get(groupName);
            if (contactList.size() > 0) {
                System.out.println("Контакты в группе <" + groupName + ">");
                for (Contact contact : contactList) {
                    System.out.println(contact.getName() + " : " + contact.getPhone());
                }
            } else {
                System.out.println("В группе <" + groupName + "> нет контактов.");
            }
        } else {
            System.out.println("Нет группы контактов <" + groupName + "> в Телефонной Книге !");
        }
    }

    public void showAllContactsInAllGroups() {
        if (this.groupList.isEmpty()) {
            System.out.println("Нет Групп контактов в Телефонной Книге !");
        } else {
            for (String s : groupList.keySet()) {
                this.showContactsInGroup(s);
                System.out.println();
            }
        }
    }

    public Contact searchContactByPhone(String phone) {
        for (String s : groupList.keySet()) {
            List<Contact> contactList = this.groupList.get(s);
            for (Contact contact : contactList) {
                if (contact.getPhone().equals(phone)) {
                    return contact;
                }
            }
        }
        return null;
    }

    public List<String> getGroupListForContact(Contact contact) {
        List<String> contactGroupList = new ArrayList<>();
        String phone = contact.getPhone();
        for (String s : groupList.keySet()) {
            List<Contact> contactList = this.groupList.get(s);
            for (Contact c : contactList) {
                if (c.getPhone().equals(phone)) {
                    contactGroupList.add(s);
                }
            }
        }
        if (contactGroupList.size() > 0) {
            return contactGroupList;
        } else {
            return null;
        }
    }

    public Map<LocalDateTime, String> getMissedCallsList() {
        return this.missedCallsList;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBookName = '" + phoneBookName + '\'' +
                ",\n groupList = " + groupList +
                ",\n missedCallsList = " + missedCallsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneBook)) return false;
        PhoneBook phoneBook = (PhoneBook) o;
        return phoneBookName.equals(phoneBook.phoneBookName)
                && groupList.equals(phoneBook.groupList)
                && getMissedCallsList().equals(phoneBook.getMissedCallsList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneBookName, groupList, getMissedCallsList());
    }
}
