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
            System.out.println("Bibliotekets 10 platser i bokhyllan är fyllda." + "\n Det går ej lägga till fler böcker.");
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
            System.out.println("Vår medlemslista är full, välkommen åter när en av" + "\n våra 10 medlemmar har slutat.");
            return;
        }

        System.out.println("Ange medlemmens blivande ID-nr:");
        int id = Integer.parseInt(IO.readln());
        for (int i = 0; i < membersCounter; i++) {
            if (members[i].getId() == id) {
                System.out.println("Id´t du angav är upptaget, " + "vg. börja om för jag har inte gjort någon loop hr för att skriva in nytt id. 😜");
                return;
            }
        }
        System.out.println("Ange medlemmens namn:");
        String name = IO.readln();

        Member member = new Member(id, name);
        members[membersCounter] = member;
        membersCounter++;
    }


    public void borrowBook() {
        System.out.println("Vem vill låna en bok? ");
        String name = IO.readln();
        // kontrollera om medlem finnns
        Member member = null;
        for (int i = 0; i < membersCounter; i++) {
            // är members[i]:s namn samma som name som användaren skrev?
            // jag anropar metoden getName från member-objektet och jämför med inskrivet namn.
            if (members[i].getName().equalsIgnoreCase(name)) {
                member = members[i];
                break; // break -> medlemmen är hittad, avluta loopen och fortsätt resten av borrowBook()
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
        String search = IO.readln();
        // skicka söktexten till findBook(), ta emot alla sökträffar i matches
        Book[] matches = findBook(search);
        // borrowBook() räknar hur många träffar findBook() gav
        int matchesCounter = 0;
        for (int i = 0; i < matches.length; i++) {
            if (matches[i] != null) {
                matchesCounter++;
            }
        }
        // om ingen bok hittades avslutas borrowBook()
        if (matchesCounter == 0) {
            System.out.println("Ingen bok hittades.");
            return;
        }

        // variabel för den bok som användaren väljer att låna
        Book selectedBook;

        // om sökningen bara gav en träff behöver användaren inte välja,
        // den boken ligger då på första platsen i matches-arrayen
        if (matchesCounter == 1) {
            selectedBook = matches[0];
        } else {
            // går igenom alla träffar i matches och skriva ut dem
            System.out.println("Flera böcker hittades: ");
            for (int i = 0; i < matchesCounter; i++) {
                System.out.println((i + 1) + ". " + matches[i].title() + " - " + matches[i].author());
            }

            // användaren får välja en av böckerna som hittades
            // loopen fortsätter tills användaren skriver ett gilltigt nr
            while (true) {
                // användaren väljer vilken av träffarna hen vill låna
                System.out.println("Skriv nr på den boken du vill låna: ");
                try {
                    int choice = Integer.parseInt(IO.readln());
                    // valet måste vara minst 1 och högst antalet sökträffar
                    if (choice < 1 || choice > matchesCounter) {
                        System.out.println("Ogilltigt val. Välj mellan 1 och " + matchesCounter + ".");
                        continue;
                    }

                    // användarens val börjar på 1, men arrayens index börjar på 0
                    selectedBook = matches[choice - 1];
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Du måste ange ett nummer.");
                }
            }
        }

        // kontrollera om boken är tillgänglig
        for (int i = 0; i < loansCounter; i++) {
            if (loans[i].book().equals(selectedBook)) {
                System.out.println("Boken är redan utlånad.");
                return;
            }
        }

        // kolla så att inte lån-arrayen är full (bör inte vara för jag har bara 10 böcker
        if (loansCounter >= loans.length) {
            System.out.println("Det finn inte plat för fler aktiva lån i biblioteket kapacitet.");
            return;
        }
        // se till att bok och medlem blir ihopkopplade med ett Loan
        Loan loan = new Loan(member, selectedBook);
        // sparar lånet på nästa lediga plats i loans-arrayen
        loans[loansCounter] = loan;
        loansCounter++;

        // öka medlemmens antal aktiva lån med 1
        member.setActiveLoans(member.getActiveLoans() + 1);

        // bekräfta att utlåningen lyckades
        System.out.println(member.getName() + " har nu lånat " + selectedBook.title() + ".");
        System.out.println("Antal aktiva lån: " + member.getActiveLoans());
    }


    public Book[] findBook(String search) {
        Book[] matches = new Book[booksCounter];
        int matchesCounter = 0;

        for (int i = 0; i < booksCounter; i++) {
            if (books[i].title().toLowerCase().contains(search.toLowerCase()) || books[i].author().toLowerCase().contains(search.toLowerCase())) {

                matches[matchesCounter] = books[i];
                matchesCounter++;
            }
        }
        return matches;
    }
}



