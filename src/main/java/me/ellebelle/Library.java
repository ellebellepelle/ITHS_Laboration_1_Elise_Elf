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
        int pages;
        try {
            pages = Integer.parseInt(IO.readln());
        } catch (NumberFormatException e) {
            System.out.println("Antalet sidor måste anges med ett heltal.");
            return;
        }
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
        Member member = null;   // kontrollera om medlem finnns
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
        if (member.maxNumOfBorrowedBooks()) { // kontrollera om medlem har fler än 3 lån
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
        Book selectedBook;  // variabel för den bok som användaren väljer att låna
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
                System.out.println("Skriv nr på den boken du vill låna: ");
                try {
                    int choice = Integer.parseInt(IO.readln());
                    if (choice < 1 || choice > matchesCounter) {
                        System.out.println("Ogilltigt val. Välj mellan 1 och " + matchesCounter + ".");
                        continue; // börja om loopen
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
        // kolla så att inte lån-arrayen är full (bör inte vara för jag har bara 10 böcker)
        if (loansCounter >= loans.length) {
            System.out.println("Det finn inte plats för fler aktiva lån i biblioteket kapacitet.");
            return;
        }
        Loan loan = new Loan(member, selectedBook); // se till att bok och medlem blir ihopkopplade med ett Loan
        loans[loansCounter] = loan; // sparar lånet på nästa lediga plats i loans-arrayen
        loansCounter++;

        member.setActiveLoans(member.getActiveLoans() + 1); // öka medlemmens antal aktiva lån med 1
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


    public void returnBook() {
        System.out.println("Vilken bok vill du lämna tillbaka? Ange hela eller del av titel: ");
        String search = IO.readln();

        Loan[] matches = new Loan[loansCounter];
        int matchesCounter = 0;
        Loan selectedLoan = null;

        for (int i = 0; i < loansCounter; i++) {
            if (loans[i].book().title().toLowerCase().contains(search.toLowerCase())) { // ej equalIgnoreCae() här för
                // funkar ej med .contains()
                matches[matchesCounter] = loans[i];
                matchesCounter++;
            }
        }
        if (matchesCounter == 0) {
            System.out.println("Inga böcker med den titlen hittades som utlånad.");
            return;
        }
        if (matchesCounter == 1) {
            selectedLoan = matches[0];
        } else {
            System.out.println("Flera böcker matchade din sökning:");
            for (int i = 0; i < matchesCounter; i++) {
                System.out.println(
                        (i + 1) + ". "
                                + matches[i].book().title()
                                + " - lånad av "
                                + matches[i].member().getName()
                );
            }
            while (true) {
                System.out.println("Välj det nummer som du vill lämna tillbaka:");
                try {
                    int choice = Integer.parseInt(IO.readln());
                    if (choice < 1 || choice > matchesCounter) {
                        System.out.println("Ogilltigt val. Välj mellan 1 och " + matchesCounter + ".");
                        continue;
                    }
                    selectedLoan = matches[choice - 1];
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Du måste ange ett nummer.");
                }
            }
        }
        // anv.bekräftele att boken skall återlämnas
        System.out.println("""
                Du vill lämna tillbaka: \n
                Bok: """ + selectedLoan.book().title());
        System.out.println("Lånad av: " + selectedLoan.member().getName());
        while (true) {
            System.out.println("Vill du slutföra återlämningen? (ja/nej)");
            String answer = IO.readln();
            if (answer.equalsIgnoreCase("ja")) {
                break;
            }
            if (answer.equalsIgnoreCase("nej")) {
                System.out.println("Återlämningen avböts.");
                return;
            }
            System.out.println("Ogiltigt svar. Skriv ja eller nej.");
        }
        // ta bort selectedLoan ur loans[] och hanterar tomrummet som blir
        int loanIndex = -1;
        for (int i = 0; i < loansCounter; i++) {
            if (loans[i].equals(selectedLoan)) { // letar upp vilken plat i loans-arrayen återl.boken ligger på
                loanIndex = i;
                break;
            }
        }
        // extra felhantering
        if (loanIndex == -1) {
            System.out.println("Något gick fel. Lånet kunde inte hitta.");
            return;
        }
        for (int i = loanIndex; i < loansCounter - 1; i++) {
            loans[i] = loans[i + 1];
        }
        loans[loansCounter - 1] = null;
        loansCounter--;
        // minska activeLoans med 1, medlemmen finns redan i selectedLoan
        Member member = selectedLoan.member();
        member.setActiveLoans(member.getActiveLoans() - 1);

        System.out.println(
                selectedLoan.book().title()
                        + " är nu återlämnad."
        );
        System.out.println(
                member.getName()
                        + " har nu "
                        + member.getActiveLoans()
                        + " aktiva lån."
        );
        // visa vilka återstående aktiva lån member:n har
        if (member.getActiveLoans() > 0) {
            System.out.println("Böcker som fortfarande är lånade:");
            for (int i = 0; i < loansCounter; i++) {
                if (loans[i].member().equals(member)) {
                    System.out.println("- " + loans[i].book().title());
                }
            }
        }
    }


    public void searchBook() {
        System.out.println("Vilken bok vill du söka på? \n"
                + "Ange hela eller del av titel/författare:");
        String search = IO.readln();

        Book[] matches = findBook(search);

        boolean found = false;

        for (int i = 0; i < matches.length; i++) {
            if (matches[i] != null) {
                System.out.println(matches[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Det fanns inga sökningar som matchade.");
        }
    }


    public void showBook() {
        if (booksCounter == 0) {
            System.out.println("Det finns inga böcker i bibliotekets hyllor 😪");
            return;
        }
        for (int i = 0; i < booksCounter - 1; i++) {
            for (int j = 0; j < booksCounter - 1 - i; j++) {
                if (books[j].title().compareToIgnoreCase(books[j + 1].title()) > 0) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        System.out.println("Här är alla böcker som finns på biblioteket: ");
        for (int i = 0; i < booksCounter; i++) {
            Book book = books[i];
            boolean borrowed = false;

            for (int j = 0; j < loansCounter; j++) {
                if (loans[j].book().equals(book)) {
                    System.out.println(book + " - Utlånad till: " + loans[j].member().getName());
                    borrowed = true;
                    break;
                }
            }
            if (!borrowed) {
                System.out.println(book + " - Tillgänglig");
            }
        }
    }


    public void showStatistics() {
        if (membersCounter == 0) {
            while (true) {
                System.out.println("Det finns inga medlemmar i vårat bibliotek, vill du bli medlem? ja/nej");
                String answer = IO.readln();
                if (answer.equalsIgnoreCase("ja")) {
                    registerMember();
                    break;
                }
                else if (answer.equalsIgnoreCase("nej")) {
                    System.out.println("Okej tack då och adjö.");
                    return;
                } else {
                    System.out.println("Ogiltigt svar. Skriv ja eller nej.");
                }
            }
        }
        Member mostLoans = members[0]; // antar att melemmen på plats [0] har flest lån.
        for (int i = 1; i < membersCounter; i++) {
            if (members[i].getActiveLoans() > mostLoans.getActiveLoans()) {
                mostLoans = members[i];
            }
        }
        System.out.println("Medlem/medlemmar med flest aktiva lån:");
        for (int i = 0; i < membersCounter; i++) {
            if (members[i].getActiveLoans() == mostLoans.getActiveLoans()) {
                System.out.println(members[i].getName()
                + " - "
                + members[i].getActiveLoans()
                + " aktiva lån."
                );
            }
        }
    }
}

