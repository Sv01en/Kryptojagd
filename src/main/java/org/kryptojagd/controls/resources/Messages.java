package org.kryptojagd.controls.resources;

/**
 * This class provides resources.
 *
 * @author Sonja Kuklok, Bartosz Treyde, Amelie Reichert
 */
public final class Messages {

    /**
     * The constant STANDARD_FEEDBACK_GOOD.
     */
    public static final String STANDARD_FEEDBACK_GOOD = "Richtig, weiter so!";

    /**
     * The constant STANDARD_FEEDBACK_BAD.
     */
    public static final String STANDARD_FEEDBACK_BAD = "Die Antwort war leider falsch! Eve ist der Floppy-Disk"
    + " einen Schritt näher gekommen. Beeile dich!";

    /**
     * The constant FINISHED_MULTIPLE_CHOICE.
     */
    public static final String FINISHED_MULTIPLE_CHOICE = "Glückwunsch, du hast alle Fragen richtig beantwortet und "
            + "die Floppy-Disk erhalten!\n Schicke dem NIV eine verschluesselte Bestaetigung, "
            + "dass ihr die Floppy-Disk erhalten habt.";

    /**
     * The constant LEVEL_FINISHED.
     */
    public static final String LEVEL_FINISHED = "Glückwunsch, du hast das Level erfolgreich beendet.";

    /**
     * The constant START_TEXT.
     */
    public static final String START_TEXT = "Du und deine Kollegen Bob und Alice wurdet von der Nationalen"
            + " Informatikervereinigung (NIV) beauftragt, die letzten fünf antiken Floppy-Disks zu finden "
            + "und sie sicher in das historische Computermuseum zu bringen."
            + "\nDie Herausforderung dabei ist, dass die Floppy-Disks auf der ganzen Welt verteilt sind."
            + "Aber ihr drei seid die besten Spürnasen weit und breit und scheut euch deshalb natürlich nicht davor,"
            + " diese Herausforderung anzunehmen.Die NIV hat im Darknet einen verschlüsselten Hinweis zu dem Fundort"
            + " der ersten Floppy-Disk entdeckt und euch diesen weitergeleitet...\n";

    /**
     * The constant DECRYPTION_QUESTION.
     */
    public static final String DECRYPTION_QUESTION = "Die Nachrichten beginnen mit 'Hallo'."
            + " Wähle aus, um welches Verschlüsselungsverfahren es sich handelt und sende dann"
            + " die letzten 3 Worte des Textes der NIV.";

    /**
     * The constant CITY_HELP_TEXT.
     */
    public static final String CITY_HELP_TEXT = "Wie soll ich hier vorgehen?\n"
            + "1. Überlege dir anhand des Infotextes und mit den 3 Vorschlägen A, B und C, "
            + "in welche Stadt du als nächstes musst.\n"
            + "2. Klicke dann auf den Knopf A, B oder C. Falls die Antwort falsch ist wiederhole Schritt 1 und 2.\n\n "
            + "Das Punktesystem:\n"
            + "Oben kannst du deinen aktuellen Punktestand einsehen, "
            + "für jede richtige Entschlüsselungsaufgabe bekommst du 50, "
            + "für jede richtige Multiple Choice Aufgabe 10 und für jede richtige Verschlüsselungsaufgabe 50 Punkte. "
            + "Nach Abschluss jedes Levels wird dir die Zeit auf deinen Punktestand dazu addiert, "
            + "wenn der Timer nicht bereits in diesem Level einmal abgelaufen ist.\n\n"
            + "Der Menü-Knopf:\n"
            + "Du kannst zurück zum Menü gehen, wenn du …\n"
            + "… in ein anderes Level (sofern es freigeschaltet ist) springen möchtest.\n"
            + "… in den Einstellungen den Dark Mode aktivieren/deaktivieren möchtest.\n"
            + "… das Kryptotool für die Caesar- oder Vigenère Verschlüsselung benutzen möchtest.\n";

    /**
     * The constant VIGENERE_EXPLANATION.
     */
    public static final String VIGENERE_EXPLANATION = "Funktionsweise des Vigenère-Tools:\n\n\n"
        + "1. Falls du das Tool für die Kryptojagd verwendest, wähle einen Schlüssel der Länge 4-6 aus, \n"
        + "ansonsten wähle eine beliebige Schlüssellänge aus.\n\n"
        + "2. Nun kannst du zwischen so vielen Diagrammen wechseln, wie die Länge deines Schlüssels groß ist. "
        + "Hierbei kannst du das erste Diagramm nutzen,\n um den ersten Buchstaben des Schlüsselwortes zu knacken,"
        + "das zweite Diagramm für den zweiten Buchstaben usw.\n\n"
        + "3. Um bspw. den ersten Buchstaben zu knacken, kannst du versuchen das erste Diagramm so lange nach links und"
        + " rechts zu schieben, bis sich die\nbeiden Balkendiagramme der Buchstabenhäufigkeiten möglichst ähnlich sind."
        + "\nDer rote Buchstabe der nun über dem schwarzen A steht, ist nun höchstwahrscheinlich der erste "
        + "Buchstabe des Schlüsselwortes,\n falls die richtige Länge gewählt wurde.\n\n"
        + "4. Diesen solltest du dann noch beim Tool an erster Stelle des Schlüsselwortes eintragen, indem du den "
        + "bisherigen Buchstaben markierst und \nden neuen einfügst, um dir den Text Schritt für Schritt unten im Tool "
        + "entschlüsseln zu lassen. \nFalls du das Tool für die Kryptojagd verwendest kannst du darauf achten, "
        + "dass unten im Text an erster Stelle ein Hallo sichtbar werden sollte.\nWenn dies nicht der Fall ist "
        + "überprüfe die Verschiebung der Diagramme oder ändere die Schlüssellänge.\n\n"
        + "5. Nachdem du einen Schlüsselbuchstaben herausgefunden hast, wiederhole die Schritte 3 und 4 so oft, "
        + "bis du das ganze Schlüsselwort hast.";

    /**
     * The constant BEAUFORT_EXPLANATION.
     */
    public static final String BEAUFORT_EXPLANATION = "Wenn du bis hierhin gekommen bist, hast du das Vigenère Level "
            + "bereits gelöst, Glückwunsch erstmal!\n\n\n"
            + "Die Beaufort-Verschlüsselung funktioniert recht ähnlich wie die Vigenère-Verschlüsselung, sie hat nur "
            + "einen entscheidenden Kniff.\n"
            + " Anstatt des normalen Alphabets wird das umgekehrte Alphabet benutzt, wobei "
            + "das „A“ jedoch an erster Stelle bleibt.\n"
            + " Also sieht das Alphabet wie folgt aus:\n\n"
            + "AZYXWVUTSRQPONMLKJIHGFEDCB\n\n"
            + "1. Tipp: Die Häufigkeitsanalyse ist immer noch der Weg zum Ziel. Probiere es erst einmal mit diesem "
            + "etwas wagen Hinweis, falls du damit noch nicht zur Lösung kommst, werden dir in den \n"
            + "nächsten Punkten noch weitere Hilfestellungen gegeben.\n\n\n"
            + "2. Tipp: Am besten schreibst du dir das Beaufort-Alphabet und das normale Alphabet auf ein Blatt Papier "
            + "so übereinander, dass du sehen kannst \n"
            + "welcher Buchstabe im normalen Alphabet welchem Buchstaben im Beaufort-Alphabet entspricht.\n\n\n"
            + "3.Tipp: Versuche Gemeinsamkeiten in den beiden Alphabeten zu finden (Steht ein Buchstabe in "
            + "beiden Alphabeten an der selben Stelle?) Wie könnte dir das bei der Häufigkeitsanalyse helfen?\n\n\n"
            + "4.Tipp: Durch die Verdrehung des Alphabets musst du nur die Wahrscheinlichkeitsverteilung an dem "
            + "Buchstaben „N“ spiegeln. \n\n\n"
            + "Der letzte Tipp führt dich hoffentlich zum Ziel: Verschiebe die Balken so, dass sie an der "
            + "entsprechenden Stelle im Beaufort Alphabet liegen. Verwende hierbei deine Notizen aus Tipp 2.\n"
            + "(also A --> A, B --> Z, …) \n";



    /* private utility-class constructor */
    private Messages() { }
}
