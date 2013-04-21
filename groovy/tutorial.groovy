/*
    Groovy als Skriptsprache - Grundelemente
    Zielgruppe: Auch für Java-Neulinge aber mit hoher Auffassungsgabe
*/

// *****************************************************************************
// Kommentare, Zeichenketten, Standardausgabe
/*
    Kommentar bis zum (nächsten) Kommentar-Ende - Schachteln geht nicht!!
*/
// Ausgabe über Standardausgabe und mit Standard-Zeichenkodierung
// print bzw. println mit Zeilenvorschub (ln = line)
println "Hallo" // Kommentar bis zum Ende der Zeile

// Ohne Zeilenvorschub
print "Grüezi"
// Zeilenvorschub als Sonderzeichen in der Zeichenkette
print " sagt der Schweizer\n"

// Anführungszeichen in der Zeichenkette selbst
println "Ich sag' \"Hallo\" (schon wieder)"
// Einfache Anführungszeichen gehen auch
println '"Hallo" sagt\'s sich mit Groovy'

// Zuweisung an einen neuen Bezeichner für eine Variable
// Gilt im gesamten Skript
wort = "Hallo"
println wort

// Zweisung an eine neue lokale Variable
// Gilt nur innerhalb des aktuellen Quelltext-Blocks
// Verbirgt alle äußeren Variablen
// Empfehlung: Lieber mit def deklarieren
def wort = "Grüezi"
println wort  // "Grüezi" statt "Hallo"


// *****************************************************************************
// Unterprogramme
println '\nUnterprogramme\n'

// Definition eines Unterprogramms
// () - leere List von Aufrufparametern
def begruessen() {
    println wort  // Hier sagt er wieder "Hallo"
    def wort = "Servus"
    println wort  // Und jetzt auf Österreichisch
}

// Gleicher Name möglich bei unterschiedlicher Anzahl von Parametern
// Die Anzahl der Parameter beim Aufruf stellt die Weichen

// Definition eines Unterprogramms mit Parametern
def begruessen(name, formel) {
    // GString - Groovy Zeichenkette als Schablone
    // Erfordert doppelte Anführungszeichen
    // $<variable> fügt Wert von <variable> ein
    // ${<ausdruck>} fügt das Ergebnis von <ausdruck> ein
    println "Ich sag mal '$formel', lieber ${name}."
}

// Keine Aufrufparameter zur Ausführung: Ein leeres Klammern-Paar
begruessen()
// Klammern sind beim Aufruf mit Parametern optional
begruessen "Willi", "Moin"

// Auch bei println() ohne Parameter sind die Klammern Pflicht (Leerzeile)
println()

// Gleicher Name möglich bei gleicher Anzahl von Parametern
// Die Art der Parameter beim Aufruf entscheidet
// Parameter benötigen spezifische Datentypen (int = Ganzzahl, 32-bit)

def begruessen(Integer anzahl, formel) {
    // Multiplikation Zeichenkette mal Ganzzahl
    println "Ein ${anzahl}-faches $formel: ${formel * anzahl}"

    println "Mit was für Typen haben wir es dann da zu tun?"
    // Die Burschen haben Klasse (Integer) und sind nicht primitiv (int)
    // Namen von Klassen fangen mit Großbuchstaben an
    println anzahl.class.name // java.lang.Integer
    println formel.class.name // java.lang.String    
}

// Entscheidung nach Aufrufparamtern bevorzugt den Spezialfall
// Diese Parameter passen auf (name, formel) und (int anzahl, formel)
// Letzteres ist aber spezifischer und wird genommen
begruessen(3, "Hip Hip Hurra! ")


// *****************************************************************************
// Arithmetik
println "\nArithmetik\n"

def x = 43
println "Der Rest von $x durch 5 ist ${ x % 5 }"

// Bei Division kommt auch mal etwas Krummes heraus
def y = x/5
println y
println "Ganzzahlig: ${y as Integer}"

// Arithmetik und Zweisung in einem Rutsch (wie bei Java)
// Mehrere Befehle getrennt durch Semikolon, optional auch am Zeilenende
x *= 2; x -= 6;

// Erhöhen und Verringern (x-- bzw. --x) mit altem bzw. neuem Wert
println "43 * 2 - 6 = ${x++}, doppelt erhöht sind es ${++x}"

// Dezimal-Arithmetik intern java.math.BigDecimal mit festem Komma
x = 1.40
println "$x + $y = ${x + y}"


// *****************************************************************************
// Bedingungen
println '\nBedingungen\n'

x = 82.0

// Bedingung für den folgenden Befehl
if (x > 80)
  println "x ist größer als 80"

// Entscheidung mit Alternativen
// Die Klammern gehören zur Syntax von if() - Achtung BASIC-Programmierer!
if (x >= 100) {
    println "Dreistellig (oder mehr)"
}
else if (x >= 10) // Einzelner Befehle geht auch ohne { } - nicht empfohlen
    println "Zweistellig"
else { // Mehrere Befehle müssen in einen { } Block
    println "Doch zu klein"
    println "Ätsch!"
}

// Gleichheit im Sinne von Groovy
if (x == 82.00) println "Basst scho..."
if (x != "82") println "Aber Zeichenketten sind etwas anderes"
if (x == "82" as Integer) println "...wenn man sie nicht umwandelt"

// *****************************************************************************
// Schleifen
println '\nSchleifen\n'

// Schleife mit Bedingung am Anfang
println "Countdown"
x = 82
while (x > 0) {
    // Formatierte Ausgabe - holt den Kernighan/Richie wieder raus!!
    printf('%4d\n',x)
    x -= 20
}
println()

// Schleife mit Initialisierung, Bedingung, Schritt
// Initialisierung einmalig ggf. inkl "def"
// Prüfung der Bedingung VOR jedem Durchlauf
// Ausführung des Schritts NACH jedem Durchlauf

for (def zeichen = 65; zeichen < 91; zeichen++) {
    print (zeichen as Character)
}
println " sagt die Sesamstraße"

// Schleife ohne Bedingung, ggf. Endlosschleife
for (x = 0; ; x += 2) {
    // Fortsetzen mit dem nächsten Durchlauf, vergiss den Rest des Blocks
    if (x < 10) continue
    // Abbrechen der ganzen Schleife, raus aus dem Block
    if (x > 20) break
    print x; print ';'
}
println()


// *****************************************************************************
println "\nListen\n"

// Listen sind 'eingebaute' Datenstrukturen in Groovy
// Aufzählung in eckigen Klammern getrennt durch Komma
def list = [ x, wort, y ]

// Index in Listen und Arrays 0-basiert und in eckigen Klammern
// String-Verkettung mit '+'
println "'wort' ist immer noch: " + list[1]

// Operator-Overloading - Ergebnis von Operationen durch Methoden definiert
// Methoden sind Funktionen, die auf ein Objekt angewendet werden
// Die Objektklassen (Class) bestimmen, welche Methoden es gibt
// Die Klasse 'List' hat eine .multiply() Methode (Liste n-fach wiederholen)
println "Doppelte Liste: ${list * 2}"

// Einfach etwas hinten an die Liste dranhängen
// List.shiftLeft() definiert den << Operator und entspricht List.add()
list << 'Wer A sagt' 
// oder so
list.add('muss nicht B sagen')
// oder so
list.push("und darf auch in C programmieren")
// oder so
list += "ohne Vitamin D"

println "\nStapel 'poppen' - gar nicht unständig:"
// Diese schöne Schleife habe ich bisher verheimlicht
4.times {
    // Von hinten wieder wegnehmen (Stack mit Push/Pop)
    // Viele Sprachelemente aus JavaScript in Groovy vorhanden!
    println list.pop()
}

def list1 = [ 3.0, 4 ]
def list2 = [ 3, 4.00 ]

// Zuweisung komplexer Objekte erfolgt 'by reference'
def list3 = list1

println()

if (list1 == list2) println "Gleich mit list2"
if (list1 != [ ]) println "Nicht leer"
if (list1.is(list2)) println "Identisch mit list2" // false
if (list1.is(list3)) println "Identisch mit list3" // true

println()

// Verkettung von Listen und formatierte Ausgabe
list += list1 + list2
// [22, Grüezi, 8.6, 3.0, 4, 3, 4.00]
println list

// Länge der Liste überschreiten
println list[9] // null
println "list hat jetzt ${list.size} Elemente"
list[9] = "Elem10"
// [22, Grüezi, 8.6, 3.0, 4, 3, 4.00, null, null, Elem10]
println list
println "list hat jetzt ${list.size} Elemente"

// Zugriff relativ zum Ende der Liste
println "list[-1] ist ${list[-1]}" // "Elem10"
println "list[-5] ist ${list[-5]}" // 3

// Der Collect-Operator *.
// Neue Liste mit den Ergebnissen einer Methode für alle Listen-Elemente
println "\nErgebnisse für list*.multiply(2) und list*.plus(10)"
println list*.multiply(2)
println list*.plus(10)

// *****************************************************************************
// Schleifen über Listen
println "\nSchleifen über Listen\n"

// Schleifen über Listen
// (oder beliebige Collections - Ansammlungen von Objekten/Elementen)
for (def elem: list) {
    print ":" + elem.toString()[0]
}
println()

// Schreibweise mit 'in' anstatt des Doppelpunkts von Java
for (def elem in list) {
    print "*" + elem.toString()[0]
}
println()

list.each { elem ->
    print "/" + elem.toString()[0]
}
println()

// Mit it ('es' oder Kurzform von 'item') als Standardparameter
list.each { print "+" + it.toString()[0] }
println()


// *****************************************************************************
// Maps
println "\nMaps (Zuordnungen)\n"

// Schlüssel/Wert (key/value) Paare mit Doppelpunkt getrennt
// Zahlen und Listen sind auch Objekte und als Schlüssel geeignet
// Beliebige Objekte (auch wieder Listen oder Maps) als Werte
def map = [ ort: "Hamburg", breite: 53.5, "laenge": 10.0, 7: [10, "cc"], [4,"c"]: "Lösung"]

// String als Key oder Bezeichner als Property sind äquivalent
println "${map.laenge} Grad West und ${map["breite"]} Grad Nord"

println map[7.0]        // Schlüssel nicht binär gleich (Hash) => null
println map[[4,"c"]]    // Hier passt es


// *****************************************************************************
// Klassen
println "\nKlassen - selbstdefinierte Datentypen\n"

class Location {
    def ort
    def breite
    def laenge
}

// Instanz ohne Initialisierung
def berlin = new Location()
// Eigenschaften/Methoden des gleichen Objektes nutzen mit 'with' (wie in VB)
berlin.with {
    ort = "Berlin-Mitte"
    breite = 52.3
    laenge = 13.2
}

// new mit Initialisierung
def london = new Location(ort: "Greenwich", breite: 51.3, laenge: 0.0)

// Umwandlung von Map in Groovy-Objekt
def madrid = [ ort: "Der Prado", breite: 40.2] as Location

// Maps funktionieren wie dynamische Objekte (analog zu JavaScript)
def prag = [ort: "Wenzelsplatz", laenge: 14.2]
def orte = [ berlin, london, madrid, prag ]

// Zugriff über Schlüssel-String oder Eigenschaftsbezeichner ist äquivalent
orte.each {
    println "${it["ort"]} liegt bei ${it.laenge} Grad Ost"
}

// Bei der Map sind weitere DYNAMISCHE Erweiterungen jederzeit möglich
prag.sprache = "Tschechisch"

// Bei den Objekten der Klasse Location geht das nicht
// Dieser Befehl gäbe einen Fehler bei der Ausführung:
// No such property: sprache for class: Location

/* madrid.sprache = "Spanisch" */


// *****************************************************************************
// Funktionen und Methoden
println '\nFunktionen und Methoden\n'

// Eine Funktion ist ein Unterprogramm, das ein Ergebnis liefert
// Auch Funktionen können 0 bis n Aufrufparameter haben
// Bei Groovy-Funktionen wird das Ergebnis der letzten Zeile geliefert
// Will man vorher ein Ergebnis zurückliefern, nutzt man 'return'

def koordinaten(location) {
    if (location.ort == 'Mond')
        return "Quatsch"
    // Rückgabe einer Liste mit zwei Einträgen
    [location.breite, location.laenge]
}

// In Groovy Skripten können Funktionen wie oben ohne Bezug zu einer Klasse
// definiert werden. Innerhalb einer Klassendefinition spricht man dann von
// Methoden

class LocationMitMethode extends Location {
    
    // ort, breite, laenge geerbt
    def sprache
    
    def koordinaten() {
        // Bedingter Befehl auf gleiche Zeile wie if()
        if (ort == 'Jupiter') return "Auch Quatsch"
        [breite, laenge]
    }
}

// Die Deklaration mit 'extends' bewirkt, dass die neue Klasse alle
// Methoden und Eigenschaften einer schon vorhandenen Klasse erbt.
// Die Eigenschaften 'ort', 'breite' und 'laenge' müssen nicht erneut definiert werden.
// Groovy hat dadurch, wie Java, eine Klassenhierarchie mit Eltern-Kind-Relationen.
// Urahn aller Klassen die Klasse 'Object'. Auch 'Integer' oder 'String' haben
// direkte oder indirekt von 'Object' geerbt.

// berlin, london, madrid sind noch immer normale 'Location' Objekte 
// Weil prag eine Map ist, können wir daraus jetzt eine LocationMitMethode erzeugen
def pragMitMethode = new LocationMitMethode(prag)

println pragMitMethode.sprache
println pragMitMethode.koordinaten()

// Der Befehl 'assert' tut nichts, solange die Annahme, die dahinter in Form
// einer Bedingung formuliert ist, zutrifft.
// Sonst gibt es einen aussagekräften Laufzeitfehler.
// Dies ist der Einstieg in die Welt systematischer Software-Tests.
// Gerade nicht triviale Annahmen sollte man ruhig mal prüfen lassen.
// Motto: Don't ASSUME - or you'll make an ASS of U and ME
assert pragMitMethode.koordinaten() == koordinaten(prag)


// *****************************************************************************
// Groovy Truth

// Ein Ausdruck ist Äquivalent mit 'false' bei folgenden Ergebnissen
// - false
// - null
// - 0      (Ganzahl 0)
// - 0.00   (Fest-/Fließkommazahl 0)
// - ""     (Leerer String)
// - [ ]    (Leere Liste)
// - [:]    (Leere Map)
// - Java Collection mit size() == 0
// - Java Iterator mit hasNext() == false
//
// Alle anderen Objekte entsprechen 'true'

// Operator für Bool'sche Negation ist das !
// Und-Verknüpfung mit &&
// Oder-Verknüpfung mit ||

assert !(prag.breite)   // "breite" nicht als Key in der Map
assert !(madrid.laenge) // Nicht initialisiert (null)
assert !(london.laenge) // Wert vorhanden, aber 0.0

// Default-Wert mit dem "Elvis" ?: Operator
println "Madrid liegt auf ${ madrid.laenge ?: 'unbekannter'} Länge"

// Weiterer Ort ohne 'ort' Eigenschaft
orte += [ laenge: 8, breite: 61 ]

// Abgesicherter Zugriff auf Eigenschaften mit ?. Operator
// Property 'ort' aller gefundenen Elemente als neue Liste

println orte.findAll({ it.ort?.length() == 9 }).ort

// Der Einzeiler ersetzt folgende Zeilen Java-Code, die Groovy aber auch versteht

List result = new ArrayList();
for (Object it: orte) {
    String ort = null;
    if (it instanceof Map)
        ort = ((Map)it).get("ort");
    else if (it instanceof Location)
        ort = ((Location)it).ort;
    if (ort != null && ort.length() == 9) result.add(ort);
}
System.out.println(result);

// Das Prinzip, dass hier Maps mit "ort" und Locations über einen Kamm geschoren
// werden nennt sich in der dynamischen Programmierung auch 'Duck Typing'.
// Motto: If it walks like a duck, and it talks like a duck, it is a duck.
// Eine Map mit "ort" Eintrag passt sich in diesem Sinne in die Locations ein

// Falls es mit dem "ort" aber doch Probleme gibt, dann hilft der ?. Operator
// Sonst würde ein Zugriff mit "null" auf die length() Methode eines String knallen
// Java evaluiert das schon schöner als VB (AndAlso, OrElse)
// Aber nur Groovy ist wirklich "Groovy"

// *****************************************************************************
// Closures
println '\nClosures\n'

// Im Gegensatz zu if(), for() und while(), die echte Kontrollstrukturen im
// kompilierten Code erzeugen, sind each() und findAll() Methoden für iterierbare
// Objekte (also z.B. Listen), die als Parameter eine Closure erwarten.
// 
// Lassen wir den Begriff mal unübersetzt und definieren eine Closure als eine
// durch einen Code-Block implementierte namenslose Funktion, bei deren Ausführung
// auch Variablen verwendet werden können, die sich zum Zeitpunkt der Instanzierung
// der Closure im Kontext befinden.
//
// Über den Zeitpunkt der Instanzierung machen wir uns aber zunächst keine
// weiteren Gedanken, da die Closures die uns bisher begegnet sind, an Ort und
// Stelle deklariert und dann ausgeführt wurden.
//
// Für each() führt Groovy intern eine normale Schleife aus und ruft die Closure
// für jedes vom Iterator bereitgestellte Objekte auf, wobei das Objekt als
// Parameter übergeben wird. Closures die keine Parameter definieren (zu Beginn
// des Blocks vor -> ), stellen den Parameter it zur Verfügung.
//
// Wichtig ist noch, dass eine Closure durch 'return' vorzeitig verlassen
// werden kann. 'break' und 'continue' sind nicht anwendbar. Ein 'return'
// wirkt wie 'continue' in normalen Schleifen. Ein Verhalten mit 'break' hat
// die .find() Methode, die nach dem ersten Resultat mit Groovy-Truth die
// weitere Suche einstellt.

// find() liefert den ersten Treffer, null wenn es keinen gibt
println orte.find({ it.laenge > 12 }).ort

// findAll() liefert alle Treffer in einer Liste, die leer ist, wenn es keine gibt
// collect() liefert eine Liste mit den Ergebnissen aus der Closure
println orte.findAll({ it.laenge > 12 }).collect({ it.ort })

// every() bildete ein Bool'sche UND-Verknüpfung über alle Ergebnisse
// Nur wenn immer Groovy-Truth geliefert wurde, ist das Ergebnis nicht false
madrid.laenge = -3.2
if (orte.every({ it.laenge < 25 })) println "Alles westlich des Ural"

// Bei Maps können Schlüssel und Wert in der Closure verwendet werden
println map
println map.findAll({ key, val -> key instanceof String})

// Bei each kann auch ein Index übergeben werden
orte.eachWithIndex { it, idx -> println "$idx: ${it.ort}" }

// *****************************************************************************
// Groovy JDK - File
println '\nGroovy JDK - File\n'

// Zu jeder neuen Sprache wird eigentlich auch immer eine Bibliothek mit den
// für die wichtigsten Lebenslagen notwendigen Funktion hinzuerfunden. Groovy
// erspart sich dies zu einem großen Teil und nimmt die Standardbibliothek
// aus dem Java Development Kit (JDK).

// Da Groovy aber die Möglichkeit hat, auch vorhandene, fertig kompilierte
// Java-Klassen noch dynamisch um weitere Methoden zu erweitern, wird dieser
// Ansatz kräftig genutzt. Das JDK kann also so wie gewohnt genutzt werden,
// ganz schnell aber auch auf eine Art, die echt "groovy" ist.

// Exemplarisch nehmen wir uns hier die Klasse java.io.File vor, bei der 
// die Erweiterungen durch das Groovy-JDK besonders zahlreich sind.

// Ein File Objekt ist zunächst nur ein Repräsentation für eine Datei im
// Filesystem. Sie muss noch nicht einmal existieren.

def datei = new File("testfile.txt")

// Besonders mächtig sind Methoden im Groovy-JDK, die wiederum Closures
// als Parameter haben. Die Methode .withWriter() öffnet die Datei im
// Schreibmodus und stellt über den Parameter der Closure einen Writer zur
// Verfügung. 

// Ein Writer sorgt für die Umwandlung von Zeichenketten in
// eine korrekt kodierte Abfolge von Bytes. Ohne explizite Angabe eines
// Zeichensatzes (z.B. UTF-8 oder ISO-8859-9) wird der Systemzeichnesatz
// verwendet. Während dies unter Linux meisten schon UTF-8 ist, muss man
// bei Windows noch von 8-bit ausgehen. Auch das systemspezifische Zeilen-
// ende (CR/LF) bzw. nur (LF) wird berücksichtigt.

datei.withWriter { out ->
    orte.each { out.println it.ort }
}

// Die Datei wird bei einem regulären Ende oder auch einer Exception in der
// Closure auf jeden Fall ordnungsgemäß geschlossen. Groovy hilft also auch
// beim sauberen Umgang mit Systemressourcen.

// Man kann auch einen mehrzeiligen String in einem Rutsch in die Datei schreiben
// und diese wieder schließen. Mehrzeilige Strings werden durch dreifache
// Anführungszeichen begrenzt. Ein \ am Zeilenende unterdrückt die Aufnahme des
// Zeilenvorschubs in den String. Auch mehrzeilige GStrings sind möglich.

datei.append """\
Boston
Wanne-\
Eickel
Honolulu
${map.ort}
"""

// Die geeigneten Methoden zum Einlesen der Datei sind .readLines()
// und .eachLine(). Die erstere liefert eine Map mit allen Zeilen, die andere
// stellt jeder Zeile sequentiell einer Closure zur Verfügung

println datei.readLines().grep(~/.+o.+o.+/)

// Gegen Ende des Tutorials sind jetzt noch ein paar elementare Häppchen
// Grooviness übrig. Dazu gehört auch der Support von Groovy für Regular
// Expressions. Zunächst gibt es für diese noch eine dritte Form von Strings,
// die durch Schrägstriche / / begrenzt sind. Innerhalb solcher Strings ist der
// Backslash \ kein Escape-Zeichen. Elementare Platzhalter wie \d oder \w
// müssen deshalb nicht als \\d oder \\w geschrieben werden.

// Mit dem Präfix ~ wird entsteht dann ein java.util.regex.Pattern
// Die .grep() Methode filtert auch einer Collection die Einträge heraus,
// die dem Pattern entsprechen, hier also zwei mal 'o' enthalten.

// Für CSV Dateien und ähnliche Falle kann jede Zeile nach dem Einlesen auch
// gleich gesplittet werden. Hier dient jeder kleine Vokal als Trenner.
// Zwecks Ausgabe werden die Konsonanten dann mit * wieder zusammengefügt.

datei.splitEachLine(~/[aeiou]/) {
    println it.join('*')
}

// *****************************************************************************
// Ranges
println '\nRanges\n'

// Während Listen und Maps in Groovy sich auf existierende Implmentierungen im
// Java SDK (ArrayList und LinkedHashMap um genau zu sein) abstützen, gibt es
// eine bedeutende komplexe Datenstruktur bei der Groovy eine eigene Umsetzung
// benötigt. Dies sind Ranges, fortlaufende Folgen ganzzahliger Werte mit
// gegebenen Start- und Endwerten. Ranges können überall dort verwendet werden
// wo auch Listen zum Einsatz kommen. Sie stellen alle enthaltenen Werte in
// definierter Reihenfolge (auf- oder absteigend) bereit. Ranges sind besonders
// nützlich um aus Strings oder anderen Listen Teilfolgen herauszuschneiden.

// Range-Konstante 0..<8, wobei < den Nicht-Einschluss des Endwerts festlegt
(0..<8).each {print it + ' '}; println();
// Range-Konstante zur Iteration von 0 bis hinunter zu -8 (einschließlich)
(0..-8).each {print it + ' '}; println();

// Ranges sind nicht nur etwas für 'Squares'
println ( (1..10).collect {it*it} )

// Ranges als Indexbereiche für Strings
// Erste 8 Zeichen
println "tutorial.groovy"[0..<8]
// Letzte 7 Zeichen
println "tutorial.groovy"[-7..-1]
// Alle Zeichen, bis auf die letzen 7
println "tutorial.groovy"[0..-8]

// *****************************************************************************
// switch/case (Mehrfachentscheidung)
println '\nswitch/case\n'

// Groovy übernimmt für die Mehrfachentscheidung die nicht gerade glückliche
// Vorlage der Ahnen von C bis Java. Das heißt, wenn der Code des nächsten
// Falls nicht auch durchlaufen werden soll, muss ein explizites 'break' her.
// Groovy macht aber viel wieder wett, indem für die Vergleichswerte nicht nur
// Konstanten einfacher Datentypen (vor Java 7 noch nicht einmal String)
// genutzt werden können, sondern Konstanten und Variablen verschiedenster
// Klassen. Die Implementierung einer isCase() Methode an der Klasse, auch durch
// die Erweiterungen des Groovy JDK, bestimmt, ob eine Übereinstimmung vorliegt

def pattern = ~/\d{4}/
def einzelwert = 'C++'

["4711", "91", 92, "Ruby", "C++", "egal", 7.7].each {

    print it + ' -> '
    switch(it) {
        case pattern:
            println '4 Ziffern'
            break
        case 0..99:
            println 'uHu'
            break
        case ['Perl', 'Python', 'Ruby', 'Groovy']:
            println 'Nette Skriptsprache'
            break
        case einzelwert:
            println 'Grusel'
            break
        case String:
            println 'Zeichenkette'
            break
        default:
            println 'Etwas ganz anderes'
    }

}

// Die Ergebnisse bezüglich der Range zeigen, dass aber nicht jeder mögliche
// Umwandlung vorher berücksichtigt werden. Weder "91" noch 7.7 fallen unter
// die Werte 0..99. Die Fallunterscheidung anhang von Klassen ist besonders
// hilfreich, um if/else/instanceof zu vermeiden.
