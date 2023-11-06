import java.util.Scanner;
import java.util.ArrayList;

public class CompagniaAerea {

    public static class Cliente {

        private String cognome;

        public Cliente(String nomeUnico) {
            cognome = nomeUnico;
        }

        public String getCognome() {
            return cognome;
        }
    }

    public static class Volo {

        private int n;

        // posti = array di dimensione pari al max numero di passeggeri --> tecnica
        // della sentinella (sentinella = variabile d'istanza)
        private Cliente[] posti;

        /*
         * Ricerca sequenziale, scandisce tutto l'array di interi per trovare la
         * "chiave"(key) cercata
         *
         * @param array l'array di elementi interi
         * 
         * @param key l'elemento intero da cercare
         * 
         * @return la posizione dell'elemento cercato
         */

        /*
         * public int ricercaSequenziale(int [] array , int key) {
         * for (int i=0; i<array.length; i++)
         * if (array[i]==key);
         * return i;
         * return -1;
         * //restituisce -1 se non trova l'elemento da cercare
         * }
         */

        // attesa = ArrayList senza limitazioni sulla dimensione.
        private ArrayList<Cliente> attesa;

        public Volo(int nPasseggieri) {
            n = 0;
            posti = new Cliente[nPasseggieri];
            attesa = new ArrayList<Cliente>();
        }

        // cliente prenota volo --> prende posto (se c'è disponibilità) --> altrimenti
        // in attesa
        public boolean prenota(String nomeUnico) {

            Cliente cognome = new Cliente(nomeUnico);

            if (n < posti.length) {
                posti[n] = cognome;
                n++;
                return true;
            } else {
                attesa.add(cognome);
                return false;
            }
        }

        // primo elemento di attesa spostato in posti
        public void disdici(String nomeUnico) {

            // 1 for: scandisce gli elementi dell’array Cliente[] posti, eliminando il
            // cliente cercato e spostandone uno dalla lista delle attese a quella delle
            // prenotazioni

            for (int i = 0; i < n; i++) {

                if (posti[i].getCognome().equalsIgnoreCase(nomeUnico)) {

                    if (attesa.size() >= 1) {
                        //
                        posti[n - 1] = attesa.get(0);
                        attesa.remove(0);
                    } else {
                        n--;
                        return;
                    }
                }

                // 2 for: verifica la presenza di un utente nell’arrayList () attesa,
                // rimuovendolo in caso di esito positivo.
                for (int k = 0; k < attesa.size(); k++) {

                    if (attesa.get(k).getCognome().equalsIgnoreCase(nomeUnico)) {
                        attesa.remove(k);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci numero massimo di posti: ");
        String nPosti = console.nextLine();

        int numP = Integer.parseInt(nPosti);

        Volo volo = new Volo(numP);

        boolean prenotato = false;

        while (!prenotato) {

            System.out.println("Digita 'p' per prenotare o 'd' per disdire il volo: ");
            String sceltaUtente = console.nextLine().toUpperCase();

            if (sceltaUtente.equals("P") || sceltaUtente.equals("D")) {

                switch (sceltaUtente) {

                    case "P": {

                        System.out.println("Inserisci cognome: ");
                        String nomeUnico1 = console.nextLine();

                        boolean prenotazione = volo.prenota(nomeUnico1);
                        if (prenotazione) {
                            System.out.println("Prenotazione avvenuta con successo!");
                            break;
                        } else {
                            System.out.println("Non c'è disponibilità, sei stato inserito nella lista di attesa");
                            break;
                        }

                    }

                    case "D": {

                        System.out.println("Inserisci cognome: ");
                        String nomeUnico2 = console.nextLine();

                        volo.disdici(nomeUnico2);
                        System.out.println("Il cliente " + nomeUnico2 + " ha disdetto la prenotazione");
                        break;
                    }
                }
            } else {
                System.out.println("Scelta non valida");
                break;
            }
        }
    }
}

