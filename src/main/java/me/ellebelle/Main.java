package me.ellebelle;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Library malmoLibrary = new Library();

        boolean condition = true;
        do {
            System.out.println("""
                    ======================================================
                    Välkommen till Bibliotekshanteraren!
                    Var god gör ditt menyval:
                    ======================================================
                    1. Lägg till bok.
                    2. Registrera medlem.
                    3. Låna bok.
                    4. Lämna tillbaka bok.
                    5. Sök bok (titel eller författare, del av eller hela ordet.).
                    6. Visa alla böcker.
                    7. Avsluta.
                    ======================================================
                    """);

            try {

                int userInput = Integer.parseInt(IO.readln());

                switch (userInput) {
                    case 1:
                        malmoLibrary.addBook();
                        break;
                    case 2:
                        malmoLibrary.registerMember();
                        break;
                    case 3:
                        malmoLibrary.borrowBook();
                        break;
                    case 4:
                        malmoLibrary.returnBook();
                        break;
                    case 5:
                        malmoLibrary.searchBook();
                        break;
                    case 6:
                        malmoLibrary.showBook();
                        break;
                    case 7:
                        condition = false;
                        break;
                    default:
                        System.out.println("Ogilltigt val, vg. välj 1-7");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ogilltigt val, du måste skriva ett heltal mellan 1-7.");
            }
        } while (condition);
    }
}
