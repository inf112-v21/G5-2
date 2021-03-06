[![Build Status](https://travis-ci.com/inf112-v21/5H.svg?branch=master)](https://travis-ci.org/github/inf112-v21/5H)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/59c74c9604594cb0a07585f2dd1d4f45)](https://www.codacy.com/gh/inf112-v21/5H/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v21/5H&amp;utm_campaign=Badge_Grade)

# Robo Rally

### INF112 5H

---


### Hva er RoboRally?
Roborally er et spill som involverer flere spillere, og lar dem kontrollere hver sin robot på et spillebrett. 
Roboten kontrolleres via programkort som hver spiller velger ut for sin robot, og robotene beveger seg så ut ifra de valgte kort-kommandoene. 
Man vinner ved å være den første til å besøke alle flaggene på brettet!

### Hvordan installere
Teknisk produktoppsett: 
- Software som trengs:
  - Last ned java versjon 13 eller nyere (https://www.oracle.com/java/technologies/javase-downloads.html)
  - En IDE, eksempelvis IntelliJ (https://www.jetbrains.com/idea/download/#section=windows)
- Setup:
    - Klon prosjektet fra github / last ned .zip med nyeste tagged commit
    - For å kjøre koden:
        1. Åpne prosjektet i IntelliJ (eller annen IDE), kjør Main.java.
        2. Velg "server", antall spillere du ønsker i spillet, og porter. (hvis du velger 1 spiller så avslutter spillet etter en runde, da du vinner)
        3. Du kan enten spille alene eller med andre via nettverk.
           - Dersom du ønsker å spille med andre må porten du velger være port-forwardet i ruter instillingene dine,
             da følger de andre samme steg som deg, men velger "client" i punkt 2. Du som server må finne ip-adressen din, eksempelvis på 
             https://whatismyipaddress.com/. Client må skrive inn denne ip adressen (IPv4), så portene du valgte i steg 2.
           - Dersom du ønsker å spille aleine (for testing), kjør Main2, velg "client", velg "localhost" som ip, og samme porter du valgte i steg 2. 
        4.  Når antall spillere som er med i spillet er lik antall spillere du valgte når du satt opp "server" starter spillet,
            og alle får tildelt en hånd. Denne blir for nå printet i konsollen. Du har bevegelse [prioritet]. Du velger disse ved å bruke 
            tastene 1-9. Når 5 kort er valgt blir disse sendt til serveren, og du avventer resten av spillerene. Når alle har sendt bevegelser
            skjer bevegelsene steg for steg for alle spillere. (KNOWN BUG: Desync, noen ganger skjer feile ting for Client, jobber med å fikse dette)

    - For å bygge koden:
      - Kjør mvn clean install. (Testet til å funke på alle platformer, skal få build success)

### Starte ett spill
1. Kjør spillet
2. Velg om du skal være server eller klient

*Server:*
1. Velg hvor mange spillere dere er (max 4)
2. Velg porter for å la de andre spillerne koble seg igjennom
3. Vent på at alle andre kobler seg til
4. Nå starter spillet og kjører av seg selv!

*Klient:*
1. Skriv inn IP-adressen til Hosten (dersom du kjører to instanser lokalt velger du "localhost" som ip)
2. Skriv inn portene hosten valgte
3. Sjekk at du får koblet til og vent på de andre spillerne
4. Når alle er tilkoblet starter spillet og du kan begynne å spille

### Spilleregler
For hver Omgang får hver spiller utdelt 9 kort. Spilleren må så velge seg ut
5 av disse kortene vha. nummertastene på tastaturet eller ved å klikke på dem. 
Når du har valgt 5 kort, trykk på "submit cards" knappen.
Hver omgang består av 5 runder. I hver runde beveger roboten din seg ut ifra programkortet 
som du valgte ut for den runden. 
Obs! På enden av hver runde Vil rullebånd og tannhjul kjøre og laserne fyres av! Husk at alle roboter har en innebygd laser.
Hvis du står på ett flagg på slutten av runden er du nærmere å vinne.
Vinn ved å være den første til å ha roboten sin innom alle tre flaggene vinner!



## Kjente Feil og mangler

- Vegger tar opp ett helt felt på brettet
- Mangler nye respawn points / skiftenøkkel
- Har bare et stort 12x12 brett, kan ikke ha to slike brett eller brettet med startpunkter til robotene slik instruksjoneen viser.
- Det er noen restriksjoner rundt rullebånd, to av dem kan ikke peke mot hverandre / på samme felt.
- Det er ingen timer som starter når en person er den eneste som ikke har submittet kort
- Når du respawner beholder du retningen du hadde før du døde. Denne burde bli satt tilbake til samme retning som du hadde i starten av spillet.
- Win screen kan se dårlig ut. Denne er beholdt med full viten, da vi finner sjarmerende og føler at spillets sjel er sterkt koblet sammen med win-screen.
- Hvis en spiller dør og en annen spiller står på respawn punktet, kan to spillere okkupere samme felt selv om dette ikke skal være lov. En av de vil da ikke se spriten sin.

## Bugs
- Det kan forekomme situasjoner hvor kortene ikke er klikkbare. I disse tilfeller kan du fortsatt velge kort med tastaturet.
- Spillet kan krasje i noen få tilfeller. Vi klarer ikke å reprodusere feilen og er dermed ikke helt sikker på hvorfor. 
- Lignende problem hvor spillet fryser istedenfor krasjer.

