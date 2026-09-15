* Väljer att göra en record för själva utlåningen ist för att ha ett enklare alternativ som 
  skulle varit att ha två arrayer (Book[] books; och Member[] borrowedBy;) som skulle hört ihop
  med varandra genom att de skulle haft samma index. dvs: public record Loan(Book book, Member member) {}
  På detta sätt blir en bok och medlemmen som lånat den ihopkopplade i ett enda objekt.
* Väljer att använda String till isbn eftersom isbn startar med 0 och då kan jag inte ha en int.
* Väljer att lägga till pages i Book-objekten för att alla andra parametrar är String och jag vill även ha med en int.
* 