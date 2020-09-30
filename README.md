# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
* Ana Flávia Vital - s344046@oslomet.no
* Mark van der Baan - s344105@oslomet.no
* Øyvind Ødegård Stenberg - s188886@oslomet.no

Vi har brukt git til å dokumentere arbeidet vårt. Vi har XXX commits totalt, og hver logg-melding beskriver det vi har gjort av endringer.

I oppgaven har vi hatt følgende arbeidsfordeling:
* Ana har hatt hovedansvar for oppgave 1, 4, og 7. 
* Øyvind har hatt hovedansvar for oppgave 2, 5, og 8. 
* Mark har hatt hovedansvar for oppgave 3, 6 og 9. 
* Vi har i fellesskap løst oppgave 10. 

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: Løste ved å implementere..

* Oppgave 2: Løste oppgave 2a ved å bruke en StringBuilder. Valgte å appende en "[" før if-setningen og en "]" etter if-setningen. 
             Inni if-setningen går man gjennom node for node med neste pekere og legger til verdiene med komma og mellomrom mellom dem. Akkurat samme metode for omvendtString() 
             bare at vi bruker hale som første verdi og forrige pekere. For 2b gjorde jeg klart for to muligheter, at verdien legges inn foran hvis antall er 0 eller bak hvis den 
             ikke er det. Hvis den legges inn foran er listen tom og det lages en ny node uten forrige og neste pekere. Hvis den legges inn bak vil den nye noden ha den tidligere 
             siste verdien som sin forrige verdi, og hale vil få en ny forrige verdi. Den tidligere forrige verdien må også få den nye verdien som sin neste verdi.
             
* Oppgave 3: 

* Oppgave 4: 

* Oppgave 5: Løste oppgaven ved å først tenke ut alle alternativer vi har, at den skal legges inn først når antall = 0 og først når antall er hva som helst annet - at den skal legges 
             inn til slutt eller mellom to noder. Så brukte jeg lang tid på å tenke ut akkurat hva som må gjøres for å sette riktige forrige og neste verdier i alle situasjonene. 
             Hjelpeverdier for de forskjellige nodene hjalp mye. Hadde skrevet denne koden tidligere der hode og hale hadde egne verdier (det samme gjorde jeg i de andre oppgavene) 
             så jeg måtte endre hele oppgaven for å fikse det, men det gikk til slutt. Brukte forrsten Objects.requireNonNull (som jeg lærte fra kompendiet) for å stoppe innlegging av null-verdier, 
             det samme gjorde jeg ovenfor.
             
* Oppgave 6:

* Oppgave 7:

* Oppgave 8a: Jeg gjorde akkurat som oppgaven sa, kastet en exception hvis iteratorendringer ikke er lik endringer og en annen exception hvis hasNext ikke er sann men det kastes også hvis denne er lik 
             både hode.nese og hale.forrige. Dette for at den skal kastes en exception når listen starter med bare en verdi og vi kaller next en gang (ingen exception) og så en gang til (exception). 
             Deretter omgjøres fjernOK til true, sånn at remove() metoden kan fjerne noe. Jeg lager en variabel som er lik hale.forrige for å kunne sjekke om variabelen denne er lik hale.forrige.forrige eller 
             hale.forrige. Hvis denne er lik hale.forrige.forrige så er den returnerte verdien lik denne.neste.verdi og denne = denne.neste.neste, hvis denne er lik hale.forrige og antall = 1 så returnerer 
             vi denne.verdi og denne = denne.neste. Ellers returnerer vi denne.neste.verdi og denne = denne.neste. Dette for at denne = hode skal fungere med vårt oppsett.

* Oppgave 8b,c,d: 8b er enkel, det er bare å instansiere en ny type av klassen DobbeltLenketListeIterator. 8c gjorde jeg ved å starte med en indeksKontroll, så måtte jeg skrive en if/else setning, og 
            hvis indeks == 0 skal denne = hode for at next() skal fungere riktig. Ellers så bruker jeg metoden finnNode med indeks-1 som parameter. Jeg bruker spesifikt indeks-1 også for at next() skal fungere riktig.
            Hvis ikke returneres feil verdi i next() metoden. Etter det så instansieres enkelt og greit fjernOK og iteratorendringer.
             
* Oppgave 9:

* Oppgave 10:

