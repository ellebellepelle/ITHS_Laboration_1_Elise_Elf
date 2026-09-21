package me.ellebelle;

public class Library {

    Book[] books = new Book[10];
    int booksCounter = 0;
    Member[] members = new Member[10];
    int membersCounter = 0;
    Loan[] loans = new Loan[10];
    int loansCounter = 0;


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
        if (pages <= 0) {
            System.out.println("Boken måste ha minst en sida, vad ska vi annas läsa?? 😂");
            return;
        }

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


    public void borrowBook(){
        System.out.println("Vem vill låna en bok? ");
        String name = IO.readln();
        // kontrollera om medlem finnns
        Member member = null;
        for (int i = 0; i < membersCounter; i++) {
            // är members[i]:s namn samma som name som användaren skrev?
            // jag anropar metoden getName från member-objektet och jämför med inskrivet namn.
            if (members[i].getName().equalsIgnoreCase(name)) {
                member = members[i];
                break; // break -> sluta leta och fortsätt till borrowBook()
            }
        }
        if (member == null) {
            System.out.println("Medlemmen finns inte, du skickas tillbaka till menyn.");
            return; // return -> avsluta hela metoden
        }
        // kontrollera om medlem har fler än 3 lån
        if (member.getActiveLoans() >= 3) {
            System.out.println("Medlemmen har redan tre lån, hen får inte låna fler böcker.");
            return;
        }
        System.out.println("Vilken bok vill du låna? ");
        String book =  IO.readln();
        // kontrollera om bok finns
        // jag ska ha en metod som söker efter titel eller författare
        // på del av eller hela namnet, kan jag använda den metoden här?
        // kontrollera om boken är tillgänglig
        // se till att bok och medlem på nått sätt blir ihopkopplade,, kanske en ny record?
    }
}



