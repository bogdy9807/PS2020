# Proiect Service Auto Booking Online


Proiectul de fata reprezinta o platforma online ce contine o colectie de oferte despre service-uri auto pentru un grup de utilizatori care pot fi persoane fizice sau juridice.
Sistemul contine o baza de date in care se vor stoca date despre utilizatori, dar si despre service-urile care au ales aceasta sa-si prezinte ofertele pe aceasta platforma. De asemenea atat clientii cat si prestatorii vor beneficia de o interfata de comunicare accesibila si usor de folosit prin care acestia isi vor transmite mesaje.
Platforma reprezinta un serviciu de programare online la un service auto si functioneaza astfel: un client are posibilitatea de a vizualiza oferte legate de service-uri auto. Aceste oferte sunt create de prestatori si contin detalii despre costuri, diverse servicii si preturi, date de contact si date disponibile pentru programare la reparatie, dar mai pot contine si poze cu service-ul respectiv si detalii despre mecanicii pe care ii are angajati. Prestatorul va avea nevoie de acordul administratorului pentru a posta o oferta. Clientul poate alege o oferta si sa se programeze la un service ales, iar dupa ce realizeaza acest lucru, se deschide o conversatie intre el si prestatorul care a postat oferta de service pe care clientul a ales-o. In aceasta conversatie ei isi pot trimite mesaje. De exemplu: prestatorul ii transmite clientului o dauna care a descoperit-o in plus la masina lui, poate atasa si poze si poate sa-i ofere clientului reparatia acestei daune.

Practic prestatorii sunt patronii de service.


## Descrierea utilizatorilor

Actorii din aceasta platforma sunt:

1. clientii
1. prestatorii
1. vizitatorii
1. administratorul

### Administraror

Administratorul va fi cel care va avea grijă de întreţinerea sistemului, configurarea acestuia, într-un cuvânt – administrarea sistemului. Acesta va putea adăuga, elimina, modifica profiluri de utilizatori, deci va avea acces asupra datelor stocate in baza de date. Însă, pentru a avea acces la toate aceste facilităţi, administratorul va trebui sa se autentifice cu o parola. La sfârşitul sesiunii de lucru, acesta se va deautentifica de la sistem. Deasemenea  va administra cele doua sisteme: sistemul de newslettering, care trimite fiecarui utilizator (client) un mesaj cu care il va notifica cu produsele nou ararute si cea a ofertelor speciale si sistemul de notificari care trimite mailuri fiecarui utilizator (client si vanzator) in legatura cu mesaje noi primite. Tot el va administra si interfata de comunicare dintre vanzator si client si interfata de afisare a ofertelor pe care le va verifica inainte ca acestea sa fie postate pe pe site.

### Client

Clientul, adică utilizatorul obişnuit, va putea să se înregistreze, sa-si stearga contul, sa vizualizeze ofertele de pe platforma, sa trimita mesaje unui vanzator, sa aleaga o oferta si sa creeze o programare. De asemenea poate accesa un istoric al ofertelor (service-urilor) alese, dar si o interfata prin care va vedea programarile curente la service-urile alese.

### Prestator

Acest tip de user va putea sa se inregistreze cu calicatea de prestator, va putea sa isi stearga contul, va putea sa creeze si sa posteze (cu acordul administratorului) oferte pe platforma, sa acceseze un istoric al clientilor, sa isi editeze profilul creat, si sa trimita un mesaj clientului. De asemenea el poate sa vada clientii curenti care au ales oferta postata de ei.

### Vizitator

Vizitatorul, adica utilizator temporar, va putea doar vizita magazinul si sa vizualizeze ofertele, fara insa a putea alege o oferta sau a trimite un mesaj unui vanzator, va putea avea optiunea de intregistrare ca si client sau ca si vanzator.

## Unelte folosite

Baza de date a fost construita folosing MySQL, iar conectarea la Intellij a fost facuta prin documentul .jar din folderun BDConnection. Conectarea web este facuta prin springboot.

## Implementare

S-au construit clasele aferente utilizatorulor si inca o clasa de login care ne ajuta sa diferentiem utilizatorii. Accesul la informatiile din baza de date este facut prin metodele din clasele ce se afla in pachetul 'dao'.