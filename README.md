* Väljer att göra en record för själva utlåningen ist för att ha ett enklare alternativ som 
  skulle varit att ha två arrayer (Book[] books; och Member[] borrowedBy;) som skulle hört ihop
  med varandra genom att de skulle haft samma index. dvs: public record Loan(Book book, Member member) {}
  På detta sätt blir en bok och medlemmen som lånat den ihopkopplade i ett enda objekt.
* Väljer att använda String till isbn eftersom isbn startar med 0 och då kan jag inte ha en int.
* Väljer att lägga till pages i Book-objekten för att alla andra parametrar är String och jag vill även ha med en int.
* Book-objekten är record för att det inte skall kunna gå att ändra dess data.
* Member-objekten är en klass som jag har gjort för att datan skall kunna gå att ändras.
  Den innehåller bla både getters och setters för att jag skall kunna både hämta värden och sätta nya värden
  om någon ex byter namn eller lånar fler böcker. 
  Id är dock final för att det inte skall kunna gå att ändra. Därmed behöver jag inte setId.
  Jag sätter activeLoans till 0 och har inte med den i konstruktorn eftersom att när en medlem skapas har den inga lån.

======= Fredag 18/9 ========
* Library-klassen får två Arrays med 10 platser var för att kunna lagra medlemmar och böcker.
  samt fält för att räkna dess platser. 
* Lägger till metoden addBook() som anropas på mitt malmoLibrary-objekt i main-menyn och i Library-klassen.
* Skapar ett malmoLibrary-objekt i Main-klassen som blir det jag arbetar med i projektet.
========= Commit ===========
* 