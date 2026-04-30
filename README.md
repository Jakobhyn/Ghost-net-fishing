# Ghost Net Fishing 🎣

Web-App zur Meldung und Bergung von Geisternetzen – IU Fallstudie **IPWA02-01**

## Beschreibung

Geisternetze sind herrenlose Fischernetze, die im Meer treiben und eine ernsthafte Bedrohung für die Meeresumwelt darstellen. Diese Web-App ermöglicht es:

- Geisternetze zu **melden** (auch anonym)
- Sich für die **Bergung** eines Netzes einzutragen
- Netze als **geborgen** zu markieren
- Eine **Übersicht** zu sehen, wer welche Netze bergt

## Technologie-Stack

| Technologie | Verwendung |
|---|---|
| Spring Boot 3.2 | Framework |
| Spring Data JPA | Datenbankzugriff |
| Hibernate | JPA-Provider |
| Thymeleaf | Template-Engine |
| H2 (Entwicklung) | In-Memory Datenbank |
| MySQL (Produktion) | Relationale Datenbank |

## Umgesetzte User Stories (Sprint 1)

| # | Priorität | User Story | Status |
|---|---|---|---|
| US1 | MUST | Geisternetze (anonym) erfassen | ✅ |
| US2 | MUST | Für Bergung eines Netzes eintragen | ✅ |
| US3 | MUST | Noch zu bergende Netze anzeigen | ✅ |
| US4 | MUST | Geisternetze als geborgen melden | ✅ |
| US6 | COULD | Übersicht wer welche Netze bergen möchte | ✅ |

## Seitennavigation

```
/                         → Redirect zu /geisternetze
/geisternetze             → Alle Netze (Tabelle)
/geisternetze/zu-bergen   → Nur gemeldete Netze (Cards)
/geisternetze/bergung/{id}→ Formular: Bergung eintragen
/geisternetze/geborgen/{id}→ POST: Als geborgen markieren
/geisternetze/uebersicht  → Wer bergt was?
/meldung/erfassen         → Formular: Netz melden
/h2-console               → Datenbank-Konsole (nur Dev)
```

## Installation & Start

### Voraussetzungen
- Java 17+
- Maven 3.8+

### Starten (H2 In-Memory, kein Setup nötig)

```bash
git clone https://github.com/Yooonnaa/ghost-net-fishing.git
cd ghost-net-fishing
mvn spring-boot:run
```

App läuft auf: **http://localhost:8080**

### Mit MySQL (Produktion)

1. MySQL-Datenbank erstellen:
```sql
CREATE DATABASE ghostnetdb CHARACTER SET utf8mb4;
```

2. In `application.properties` die H2-Konfiguration auskommentieren und MySQL aktivieren:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ghostnetdb
spring.datasource.username=root
spring.datasource.password=dein_passwort
```

## Datenbankstruktur

```
geisternetz          meldung              meldende_person
───────────          ───────              ───────────────
id (PK)              id (PK)              id (PK)
latitude             geisternetz_id (FK)  name
longitude            meldende_person_id   telefonnummer
groesse              gemeldet_am          anonym
status
bergende_person_id   bergende_person
(FK)                 ───────────────
                     id (PK)
                     name
                     telefonnummer
```

## Projektstruktur

```
src/main/java/com/ghostnet/
├── GhostNetFishingApplication.java
├── controller/
│   ├── HomeController.java
│   ├── GeisternetzController.java
│   └── MeldungController.java
├── entity/
│   ├── Geisternetz.java
│   ├── Meldung.java
│   ├── MeldendePerson.java
│   └── BergendePerson.java
├── enums/
│   └── GeisternetzStatus.java
├── repository/
│   ├── GeisternetzRepository.java
│   ├── MeldungRepository.java
│   ├── BergendePersonRepository.java
│   └── MeldendePersonRepository.java
└── service/
    ├── GeisternetzService.java
    └── MeldungService.java
```

---
IU Internationale Hochschule – Kurs IPWA02-01
