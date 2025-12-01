## Super Duper Maven Projekt

## Anforderungen
* Java 17
* IntelliJ IDE
* MySQL 8.0 Command Line Client // nur erforderlich, wenn SQL-Daten getestet werden sollen

## ANLEITUNG ZUM AUSFÜHREN
Projekt in IntelliJ als Maven-Projekt öffnen.
Gehe zu `Main.java` und führe die Datei aus.

`Main.java` hat 3 Abschnitte, getrennt durch Kommentare:
- **Abschnitt 1:** Lade hartkodierte Daten, definiert in `/utils/SampleDataLoader.java` (Standard)
- **Abschnitt 2:** Lade Daten aus einer CSV-Datei (`/resources/products.csv`)
- **Abschnitt 3:** Lade Daten aus einer SQL-Datenbank

Der Simulator gibt die Anfangswerte der Produkte aus und zeigt für alle simulierten Tage die aktualisierten Werte: Name, Preis, Qualität und ob das Produkt entfernt werden muss.

Die Anzahl der zu simulierenden Tage kann in `Main.java` auf der letzten Zeile eingestellt werden:
      //  simulator.run(LocalDate.now(), NumberOfDays);   <-----

#____________________________________________
## NEUES PRODUKT HINZUFÜGEN

Neues Modell erstellen
Neuen ProductUpdater erstellen
Produkt im ProductService registrieren
(bereits erstellte Modelle: Honey und Joghurt)


#____________________________________________
## SQL – Einrichtung und Commands (Copy & Paste)
Benutzer mit Rechten erstellen
```
DROP USER IF EXISTS 'superuser'@'localhost';
CREATE USER 'superuser'@'localhost' IDENTIFIED BY 'SuperDuper123!';
GRANT ALL PRIVILEGES ON superduper.* TO 'superuser'@'localhost';
FLUSH PRIVILEGES;
```
## Datenbank erstellen
```
CREATE DATABASE superduper
```
## Datenbank auswählen
```
USE superduper;
```
##Tabelle ggf. löschen :
```
DROP TABLE IF EXISTS products;
```
##Tabelle erstellen
```
CREATE TABLE products (
      id INT AUTO_INCREMENT PRIMARY KEY,
      type VARCHAR(50) NOT NULL,       -- Cheese, Joghurt, Wine, Honey
      name VARCHAR(100) NOT NULL,
      quality INT NOT NULL,
      base_price DOUBLE NOT NULL,
      expiry_date DATE DEFAULT NULL,    -- only for Cheese and Joghurt
      stock_date DATE DEFAULT NULL      -- only for Wine
 );
```
##Daten einfügen
```
INSERT INTO products (type, name, quality, base_price, expiry_date, stock_date) VALUES
     ('Cheese', 'Gouda', 50, 10.0, '2025-12-28', NULL),
     ('Cheese', 'Brie', 40, 12.0, '2025-12-18', NULL),
     ('Joghurt', 'Joghurt', 7, 15.0, '2025-12-30', NULL),
     ('Wine', 'Merlot', 20, 30.0, NULL, '2025-01-20'),
     ('Wine', 'Chardonnay', 10, 25.0, NULL, '2025-01-15'),
     ('Honey', 'Acacia', 50, 20.0, NULL, NULL);
```
##Daten prüfen
```
SELECT * FROM products;
```