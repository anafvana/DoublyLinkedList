package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        /*
        hode = new Node(null);
        hale = new Node(null);
       */
        hode = null;
        hale = null;
        antall = 0;
        endringer = 0;

    }

    public DobbeltLenketListe(T[] a) {
        int i = 0;
        for(; i < a.length && a[i] == null; i++);
        Node<T> p;

        if(i < a.length) {
            p = new Node<>(a[i], null, null);
            hode = new Node<>(null, null, p);
            antall = 1;

            for(i++; i < a.length; i++) {
                if(a[i] != null) {
                    p = p.neste = new Node<>(a[i], p, null);
                    antall++;
                }
            }
            hale = new Node<>(null, p, null);
        }
    }

    public Liste<T> subliste(int fra, int til){
        fratilKontroll(fra, til);
        DobbeltLenketListe<T> nyListe = new DobbeltLenketListe<>();
        if(fra == til){
            return nyListe;
        } else {
            Node<T> node = finnNode(fra);

            nyListe.leggInn(node.verdi);

            for (int i = fra + 1; i < til; i++) {
                node = finnNode(i);
                nyListe.leggInn(node.verdi);
            }
            return nyListe;
        }
    }

    public void fratilKontroll(int fra, int til){
        //eksemplet kommer fra pensum
        if (fra < 0){
            // fra er negativ
            throw new IndexOutOfBoundsException ("fra(" + fra + ") er negativ!");
        }
        if (til > antall) {
            // til er utenfor tabellen
            throw new IndexOutOfBoundsException ("til(" + til + ") > antall (" + antall + ")");
        }
        if (fra > til) {
            // fra er større enn til
            throw new IllegalArgumentException ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }
    }

    private Node<T> finnNode(int indeks) {
        int hjelpevariabel;
        Node<T> node;
        if(indeks < antall/2) { // fra hode
            hjelpevariabel = 0;
            node = hode.neste;
            while (indeks != hjelpevariabel){
                hjelpevariabel ++;
                node = node.neste;
            }
        } else { // fra hale
            hjelpevariabel = antall-1;
            node = hale.forrige;
            while (indeks != hjelpevariabel) {
                hjelpevariabel--;
                node = node.forrige;
            }
        }

        return node;
    }

    @Override
    public int antall() {
        return antall;
    }


    @Override
    public boolean tom() {
        //TODO Handle null liste?
        return antall() == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null verdier!");
        Node<T> p;

        if(antall == 0) {
            p = new Node<>(verdi, null, null);
            hode = new Node<>(null, null, p);
            hale = new Node<>(null, p, null);
        } else {
            Node<T> q = hale.forrige;
            p = new Node<>(verdi, q, null);
            q.neste = p;
            hale.forrige = p;
        }
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null verdier!");

        indeksKontroll(indeks, true);
        Node<T> p;

        if(indeks == 0) {
            if(antall == 0) {
                p = new Node<>(verdi, null, null);
                hode = new Node<>(null, null, p);
                hale = new Node<>(null, p, null);
            } else {
                Node<T> q = hode.neste;
                p = new Node<>(verdi, null, q);
                q.forrige = p;
                hode.neste = p;
            }
        } else if(indeks == antall) {
            Node<T> r = hale.forrige;
            p = new Node<>(verdi, r, null);
            r.neste = p;
            hale.forrige = p;
        } else {
            Node<T> q = hode.neste;
            for(int i = 1; i < indeks; i++) {
                q = q.neste;
            }
            Node<T> s = q.neste;
            p = new Node<>(verdi, q, s);
            s.forrige = p;
            q.neste = p;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        T verdi = null;
        try {
            verdi = finnNode(indeks).verdi;
            // kun feilmelding dersom det ikke funker pga indeksKontroll tar lang tid.
        } catch (Exception e){
            indeksKontroll(indeks, false);
        }
        return verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if(indeks >= antall || indeks < 0){
            throw new IndexOutOfBoundsException();
        }

        T oldVerdi = null;
        if(nyverdi == null) {
            throw new NullPointerException();
        } else {
            Node<T> node = finnNode(indeks);
            oldVerdi = node.verdi;
            node.verdi = nyverdi;
            endringer++;
        }
        return oldVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null){
            return false;
        }


        Node<T> node = hode.neste;
        while(!node.verdi.equals(verdi)){
            if(node.neste == null){
                return false;
            }
            node = node.neste;
        }

        if (node.equals(hode.neste) && node.equals(hale.forrige)) {
            hode.neste = null;
            hale.forrige = null;
        } else if(node.equals(hode.neste)){
            hode.neste.neste.forrige = null;
            hode.neste = hode.neste.neste;
            node.neste = null;
        } else if(node.equals(hale.forrige)){
            Node<T> nodeForrige = node.forrige;
            hale.forrige = nodeForrige;
            node.forrige.neste = null;
            node.forrige = null;
        } else {
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }

        antall--;
        endringer++;
        return true;
    }

    @Override
    public T fjern(int indeks) {
        if (indeks < 0 || (indeks > antall-1)) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = finnNode(indeks);
        try{
            if(indeks == 0){
                if(hode.neste.neste != null) {
                    hode.neste.neste.forrige = null;
                    hode.neste = hode.neste.neste;
                    node.neste = null;
                } else {
                    node.neste = null;
                    node.forrige = null;
                    hode.neste = null;
                    hale.forrige = null;
                }
            } else if(indeks == antall-1){
                hale.forrige = node.forrige;
                node.forrige.neste = null;
                node.forrige = null;
            } else {
                node.forrige.neste = node.neste;
                node.neste.forrige = node.forrige;
                node.forrige = null;
                node.neste = null;
            }
            antall--;
            endringer++;

        } catch (Exception e){
            indeksKontroll(indeks, false);
        }
        return node.verdi;
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(!tom()) {
            Node<T> p = hode.neste;
            sb.append(p.verdi);
            p = p.neste;

            while (p != null) {
                sb.append(",").append(" ").append(p.verdi);
                p = p.neste;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public String omvendtString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(!tom()) {
            Node<T> p = hale.forrige;
            sb.append(p.verdi);
            p = p.forrige;

            while (p != null) {
                sb.append(",").append(" ").append(p.verdi);
                p = p.forrige;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            indeksKontroll(indeks, false);
            denne = finnNode(indeks-1);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext(){
            return denne.neste != null;
            //return denne != null;
        }

        @Override
        public T next(){
            if(endringer != iteratorendringer) {
                throw new ConcurrentModificationException();
            }
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            denne = denne.neste;
            fjernOK = true;
            T denneVerdi = denne.verdi;;
            return denneVerdi;
        }

        @Override
        public void remove(){
            if(endringer != iteratorendringer){
                throw new ConcurrentModificationException();
            }
            if(!fjernOK) {
                throw new IllegalStateException("Ulovlig tilstand!");
            }

            fjernOK = false;

            if(antall == 1){
                hode.neste = null;
                hale.forrige = null;
            } else if(denne.equals(hale.forrige)){
                //siste elementet må fjernes
                Node<T> node = hale.forrige.forrige;
                hale.forrige.forrige = null;
                hale.forrige = node;
                node.neste = null;
                denne = hale;
            } else if (hode.neste == denne){
                //føste elementet fjernes
                Node<T> node = denne.neste;
                node.forrige = null;
                hode.neste.neste = null;
                hode.neste = node;
                denne = hode;
            } else if(denne.forrige == hode.neste) {
                Node<T> node = denne.neste;
                hode.neste.neste = node;
                node.forrige = hode.neste;
                denne.neste = null;
                denne.forrige = null;
                denne = node;
            } else {
                Node<T> node = denne.neste;
                Node<T> nodeForrige = denne.forrige;
                nodeForrige.neste = node;
                node.forrige = nodeForrige;
                denne.neste = null;
                denne.forrige = null;
                denne = node;
            }

            antall --;
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


