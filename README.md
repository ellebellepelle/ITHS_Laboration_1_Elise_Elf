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
* Skapar registerMember() i Library-klassen och lägger i main menyn
========= Commit =========== 
======= Måndag 21/9 ========
* Gör en Array i Library-klassen som skall hålla Loan-objekt, med 10 plater för jag har bara 10 böcker.
  samt ett fält för att kunna räkna hur många platser som används.
* Gör en record Loans för att få en koppling mellan bok och låntagare. 
* Skapar borrowBook() i Library-klassen och lägger i main menyn
========= Commit ===========
* Skapar findBook() i Library-klassen för att kunna använda i både borrowBook() och i searchBook()
========= Commit ===========
* Ändrar findBook() så att jag får tillbaka Book[] med alla sökträffar.
* Korrigerar borrowBook() för att få tillbaka en array med alla sökresultat
========= Commit ===========
* borrowBook() är typ klar, behöver lägga till saker: inmatningar i addBook() och registerMember() 
  kan kracha om användaren skriver bokstäver där jag kör parseInt()
* bör testa om 0 träffar, 1 träff, flera träffar, felaktigt boknummer och att försöka
  låna samma bok två gånger... 
========= Commit ===========
======= Tisdag 22/9 ========
* 










FRÅN CHAT GPT, TA BORT SEN!!

Det viktigaste är att du inte börjar med att tänka ”vilka metoder ska jag skriva?”. 
Börja i stället med att läsa uppgiften som en beskrivning av ett verkligt litet bibliotek. 
Koden ska bara vara en modell av det biblioteket.

I ditt bibliotek finns framför allt tre saker som uppgiften själv pekar ut: böcker, 
medlemmar och biblioteket. Därifrån kommer Book, Member och Library. Du behöver alltså 
inte hitta på klasser från ingenstans. De kommer från sakerna som programmet behöver 
hålla reda på.

En Book representerar en enda bok. Om biblioteket har tio böcker har du tio Book-objekt. 
Varje sådant objekt innehåller informationen som beskriver just den boken, till exempel 
ISBN, titel och författare. Uppgiften föreslår att Book ska vara en record eftersom den 
informationen passar bra som information som hör ihop och inte behöver förändras. Boken 
"Harry Potter" fortsätter exempelvis att ha samma ISBN, titel och författare även när 
någon lånar den. Att boken är utlånad är alltså egentligen inte en egenskap hos själva 
bokinformationen. Det är något biblioteket behöver hålla reda på.

En Member representerar en enda medlem. Om Anna och Erik är registrerade finns det två 
Member-objekt. Ett Member-objekt behöver innehålla information om just den medlemmen, 
exempelvis id, namn och antal aktiva lån. Här används en vanlig klass eftersom medlemmen 
har information som faktiskt kan förändras. Namnet kanske kan ändras och framför allt kan 
antalet aktiva lån gå från exempelvis 0 till 1 och senare tillbaka till 0.

Sedan kommer Library. Den är viktig för att förstå hur allting ska kopplas ihop. 
Library representerar inte en bok eller en medlem. Den representerar själva 
bibliotekssystemet som känner till alla böcker och alla medlemmar. Därför säger 
uppgiften att Library ska hålla arrayer med böcker och medlemmar.

Tänk dig alltså att Library har ett bokregister och ett medlemsregister. 
Bokregistret är en array där platserna kan innehålla Book-objekt. 
Medlemsregistret är en annan array där platserna kan innehålla Member-objekt.

När du väl förstår detta blir nästa fråga mycket lättare: vem ska göra vad?

Om användaren väljer "Lägg till bok" är det egentligen biblioteket som ska registrera 
en bok. Därför är det logiskt att Library har funktionalitet för att lägga till en Book 
i sin bokarray. Samma tanke gäller "Registrera medlem". Library behöver kunna lägga in 
ett Member-objekt i medlemsarrayen.

Du kan använda exakt samma sätt att tänka när du läser resten av kraven. "Söka bok" 
betyder att Library behöver kunna leta bland sina böcker. "Visa alla böcker" betyder 
att Library behöver kunna gå igenom sina böcker. "Låna bok" betyder att Library behöver 
hantera kopplingen mellan en medlem och en bok. "Lämna tillbaka bok" betyder att Library 
behöver kunna ta bort den kopplingen igen.

Det är alltså inte nödvändigt att sitta och försöka uppfinna metoder. Du kan nästan 
översätta kraven till vad programmet måste kunna göra. Orden lägga till, registrera, 
låna, lämna tillbaka, söka och visa beskriver handlingar. Handlingar blir ofta metoder. 
Orden bok, medlem och bibliotek beskriver saker. Saker blir ofta klasser, records eller 
objekt.

Det som jag tror är den största knuten för dig är själva utlåningen: hur vet programmet 
att Anna har lånat en viss bok?

Där måste du skilja på objekten och relationen mellan objekten.

Anta att biblioteket innehåller ett Book-objekt som representerar "Harry Potter". 
Det finns också ett Member-objekt som representerar Anna. De två objekten existerar 
helt oberoende av varandra. Bara för att båda finns i Library betyder det inte att Java 
automatiskt vet att Anna har lånat Harry Potter.

Programmet behöver därför lagra ytterligare information som säger ungefär:

Den här boken → är lånad av den här medlemmen.

Det är detta läraren menar med:

"samt en array/struktur som håller reda på vilka böcker som är utlånade och till vem."

Det är alltså den kopplingen som är själva lånet.

Föreställ dig bibliotekets information ungefär så här i huvudet:

Böcker: Harry Potter, Sagan om ringen, Dracula.

Medlemmar: Anna, Erik, Lisa.

Sedan behöver biblioteket dessutom kunna veta:

Harry Potter → Anna

Sagan om ringen → ingen

Dracula → Erik

Där har du egentligen hela utlåningssystemet. Ett lån betyder inte att Book-objektet 
och Member-objektet på något magiskt sätt sitter ihop. Du måste lagra information som 
kopplar ihop dem.

Det finns flera sätt att göra det på, och uppgiften lämnar medvetet lite frihet här. 
Du skulle exempelvis kunna ha information som för varje bok säger vilken medlem som 
har den. Du skulle också kunna skapa någon separat representation av ett lån. Men för 
G behöver du framför allt förstå principen: Library måste kunna svara på frågan "vem, 
om någon, har den här boken?"

När användaren väljer "Låna bok" är det därför inte första steget att ändra någonting. 
Först måste Library undersöka situationen.

Användaren talar om vilken bok som ska lånas och vilken medlem som lånar den. 
Library letar efter boken i sin bokarray. Om boken inte finns ska lånet inte genomföras. 
Library letar också efter medlemmen i medlemsarrayen. Om medlemmen inte finns ska lånet 
inte genomföras. Sedan kontrollerar Library informationen om utlåningar för att se om 
just den boken redan är kopplad till någon annan medlem. Om den redan är utlånad ska 
inget ändras.

Först när allt detta är godkänt skapar du själva kopplingen:

den hittade boken → den hittade medlemmen

Då är boken utlånad.

Om Anna innan lånet hade 0 aktiva lån behöver hennes antal aktiva lån samtidigt bli 1. 
Det är en förändring av Anna-objektets tillstånd. När hon lämnar tillbaka boken tas 
kopplingen mellan boken och Anna bort och hennes antal aktiva lån minskar igen.

Det betyder också att du ska vara försiktig med att blanda ihop två olika frågor:

"Är den här boken utlånad?"

och

"Hur många böcker har Anna lånat?"

Det första handlar om bibliotekets information om boken och utlåningen. 
Det andra handlar om medlemmen. De hör ihop, men de är inte samma information.

När du sedan ska visa alla böcker kan Library gå igenom bokarrayen en bok i taget. 
För varje bok behöver programmet fråga sin utlåningsinformation: "Finns det någon 
medlem kopplad till den här boken?" Om svaret är nej visas exempelvis "Tillgänglig". 
Om svaret är ja kan programmet visa "Utlånad till Anna".

Samma princip gäller återlämning fast baklänges. Library hittar boken, kontrollerar 
vem som har den, tar bort kopplingen mellan boken och medlemmen och minskar medlemmens 
antal aktiva lån.

Det här är egentligen kärnan i hela din uppgift:

Book vet vad en bok är. Member vet vad en medlem är och information om medlemmen. 
Library känner till alla Book- och Member-objekt och sköter relationen mellan dem. 
Menyn låter användaren tala om för Library vad som ska göras.

Din Main behöver därför inte vara själva biblioteket. Main kan du snarare tänka på 
som personen bakom disken som tar emot användarens önskemål. Användaren väljer "Låna bok". 
Main tar emot valet och informationen från användaren. Sedan får Library utföra själva 
biblioteksarbetet.

Det ger en ganska tydlig ansvarsfördelning i huvudet:

Main: Vad vill användaren göra?

Library: Går det att göra, och hur påverkar det bibliotekets data?

Book: Vilken bok är detta?

Member: Vilken medlem är detta och vilken information hör till medlemmen?

När du läser en kravspecifikation framöver kan du därför göra samma sak. Leta först 
efter sakerna programmet måste känna till. De leder ofta till klasser och objekt. 
Leta sedan efter informationen varje sak måste bära på. Det leder till fält. Leta 
därefter efter vad programmet måste kunna göra med sakerna. Det leder till metoder. 
Till sist letar du efter relationerna mellan sakerna. Där bestämmer du hur objekten 
ska kopplas ihop.

För just din G-uppgift kan du alltså läsa meningen "en medlem lånar en bok" som tre 
separata delar:

medlem → ett Member-objekt.

bok → ett Book-objekt.

lånar → en relation som Library måste skapa och hålla reda på.

När du har fått den tanken på plats blir resten av laborationen mycket mindre mystisk. 
Du bygger egentligen bara ett litet bibliotek i minnet och lär Java hur det ska komma 
ihåg vilka böcker som finns, vilka människor som finns och vem som har vilken bok.
