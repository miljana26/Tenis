# Razvoj softvera za embeded operativne sisteme

## Prvi zadatak iz Jave: Tenis

### Opis zadatka

U ovom zadatku je potrebno implementirati simulaciju ATP Tour šampionata. Turniri koji mogu činiti šampionat, kao i teniseri koji mogu učestvovati na njima, su izlistani u odgovarajućim tekstualnim fajlovima. Prilikom pokretanja simulacije, ove liste se očitavaju, a zatim se, turnir po turnir, simuliraju svi mečevi - set po set, gem po gem, poen po poen, uz razne parametre koji mogu uticati na ishod svakog meča, ali i dalji tok šampionata. U toku celog šampionata se vodi evidencija o osvojenim ATP poenima i rankiranju tenisera, a na samom kraju šampionata će najboljih 8 tenisera odmeriti svoje snage na finalnom ATP Finals turniru.

### Beleške i nedostaci

- **Odradjen prvi zadatak iz Jave.**
- **Nedostaci kod projekta**:
  - **Player.java**: `probability -= rankDifference;` - stoji minus, ali treba plus
  - **SeasonTournament.java**:
    - Trenutno stoji:
      ```java
      winner.setAtpPoints(winner.getAtpPoints() + points);
      quarterFinalists.add(winner);
      ```
    - Treba da bude:
      ```java
      Player loser = (winner.equals(player1)) ? player2 : player1;
      loser.setAtpPoints(loser.getAtpPoints() + points);
      quarterFinalists.add(winner);
      ```
    - Umesto da se poeni dodaju samo pobednicima, treba da se dodaju poeni i gubitnicima.
  - **Verovatnoće**: Moguće je da vrednosti za verovatnoće nisu optimalne, ali to nije od suštinske važnosti.

### Dodatni materijali

- Dodat je PDF fajl u kojem je detaljno opisan zadatak.
- Dva propratna tekstualna fajla koja sadrže liste turnira i tenisera.

### Napomena

- Projekat je odrađen u NetBeans-u.