package util;

public class Main {
    public static void main(String[] args) {

        LuggageWarehouseManagementApp luggageWarehouseManagementApp = new LuggageWarehouseManagementApp();
        luggageWarehouseManagementApp.load();
        luggageWarehouseManagementApp.start();
    }
}

//    Creati o aplicatie Java care gestioneaza un depozit de bagaje intr-un aeroport. 1 bagaj ocupa 1 loc.
//        Costurile sunt 10 lei pentru prima ora, iar mai apoi cate 5 lei pe ora.
//        Rotunjirea se face in plus, astfel
//        50 de minute -> 1ora -> 10 lei
//        61 de minute -> 2 ore -> 10+5= 15 lei
//
//        Interactiunea cu aplicatia trebuie sa ofere urmatoarele informatii/functionalitati:
//        1. numarul de locuri disponibile
//        2. lasarea unui bagaj - clientul va primi un cod unic de identificare
//        3. ridicarea unui bagaj prin utilizarea codului de identificare - aici se va afisa un sumar bazat pe datele bagajului.
//
//        Aspecte tehnice:
//        0. Aplicatia va fi de tip Console Application generata cu Maven.
//        1. Nu este nevoie de stocare permanenta de date, totul putand fi stocat in memorie
//        2. Este importanta respectarea principiilor OOP
//        3. Cod clar, lizibil
//        4. Unit testing este un plus