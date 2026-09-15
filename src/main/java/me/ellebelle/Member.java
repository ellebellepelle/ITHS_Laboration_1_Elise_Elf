package me.ellebelle;

public class Member {
    private final int id;
    private String name;
    private int activeLones = 0;

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

    public int getActiveLones() {
        return activeLones;
    }

    public void setActiveLones(int activeLones) {
        this.activeLones = activeLones;
    }

    public boolean maxNumOfBorroedBooks() {
        return activeLones > 3;
    }
}
