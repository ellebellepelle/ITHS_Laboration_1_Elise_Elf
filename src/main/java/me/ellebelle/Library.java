package me.ellebelle;

public class Library {

    Book[] books = new Book[10];
    int booksCounter = 0;
    Member[] members = new Member[10];
    int membersCounter = 0;


    public void addBook() {
        // Kontroll (före användarinmatning) för att inte överskrida arrayens längd.
        if (booksCounter >= books.length) {
            System.out.println("Bibliotekets 10 platser i bokhyllan är fyllda." +
                    "\n Det går ej lägga till fler böcker.");
            return;
        }

        System.out.println("Ange bokens isbn: ");
        String isbn = IO.readln();
        System.out.println("Ange titel på boken; ");
        String title = IO.readln();
        System.out.println("Ange bokens författare: ");
        String author = IO.readln();
        System.out.println("Ange antal boksidor: ");
        int pages = Integer.parseInt(IO.readln());

        Book book = new Book(isbn, title, author, pages);

        books[booksCounter] = book;
        booksCounter++;
    }

    public void registerMember() {
        // Kontroll (före användarinmatning) för att inte överskrida arrayens längd.
        if (membersCounter >= members.length) {
            System.out.println("Vår medlemslista är full, välkommen åter när en av" +
                    "\n våra 10 medlemmar har slutat.");
            return;
        }

        System.out.println("Ange medlemmens blivande ID-nr:");
        int id = Integer.parseInt(IO.readln());
        for (int i = 0; i < membersCounter; i++) {
            if (members[i].getId() == id) {
                System.out.println("Id´t du angav är upptaget, " +
                         "vg. börja om för jag har inte gjort någon loop hr för att skriva in nytt id. 😜");
                return;
            }
        }
        System.out.println("Ange medlemmens namn:");
        String name = IO.readln();

        Member member = new Member(id, name);
        members[membersCounter] = member;
        membersCounter++;
    }
}



