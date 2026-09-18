package me.ellebelle;

public class Library {

    Member[] members = new Member[10];
    int membersCounter = 0;
    Book[] books = new Book[10];
    int booksCounter = 0;


    public void addBook() {
        // Kontroll (före användarinmatning) för att inte överskrida arrayens längd.
        if (booksCounter >= books.length) {
            System.out.println("Bibliotekets 10 platser i bokhyllan är fyllda." +
                    "Det går ej lägga till fler böcker.");
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
}



