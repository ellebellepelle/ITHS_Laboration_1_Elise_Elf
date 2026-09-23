package me.ellebelle;

public class Member {
    private final int id;
    private String name;
    private int activeLoans = 0;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(int activeLoans) {
        this.activeLoans = activeLoans;
    }

    public boolean maxNumOfBorrowedBooks() {
        return activeLoans >= 3;
    }
}
