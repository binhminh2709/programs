package ru.epatko.testTask;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         23.02.17.
 */
public class Parser {



    private Map<String, Book> books = new HashMap<>();
    List<String> names = new ArrayList<>();

    public void start(String fileName) throws XMLStreamException, FileNotFoundException {

        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader (new FileInputStream(fileName));

            while (xmlr.hasNext()) {
                xmlr.next();

                if (xmlr.isStartElement()) {

                    if ("AddOrder".equals(xmlr.getLocalName())) {

                        String bookName = xmlr.getAttributeValue(0);
                        String type = "BUY".equals(xmlr.getAttributeValue(1)) ? "bid" : "ask";
                        float price = Float.parseFloat(xmlr.getAttributeValue(2));
                        int volume = Integer.parseInt(xmlr.getAttributeValue(3));
                        int id = Integer.parseInt(xmlr.getAttributeValue(4));
                        addOrder(bookName, type, price, volume, id);
                    }

                    if ("DeleteOrder".equals(xmlr.getLocalName())) {

                        String bookName = xmlr.getAttributeValue(0);
                        int id = Integer.parseInt(xmlr.getAttributeValue(1));
                        delOrder(bookName, id);
                    }
                }
            }
            xmlr.close();
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }

        sort();
        mergeDuplicates();
        adjust();
        books.forEach((key, book) -> System.out.println(book));
    }

    private void adjust() {

        for (String name : names) {
            Book book = books.get(name);

            int as = book.asks.size();
            int bs = book.bids.size();

            List<Ask> listA = new ArrayList<>();
            List<Bid> listB = new ArrayList<>();

            int asksIndex = 0;
            int bidsIndex = 0;

            while(asksIndex < as && bidsIndex < bs) {
                Ask ask = book.asks.get(asksIndex);
                Bid bid = book.bids.get(bidsIndex);

                if (bid.price >= ask.price) {
                    int vol = bid.volume - ask.volume;

                    if (vol < 0) {
                        ask.volume -= bid.volume;
                        bidsIndex++;
                    } else if (vol > 0) {
                        bid.volume -= ask.volume;
                        asksIndex++;
                    } else {
                        bidsIndex++;
                        asksIndex++;
                    }
                } else {
                    listB.add(bid);
                    listA.add(ask);
                    bidsIndex++;
                    asksIndex++;
                }
            }

            int dif = as - bs;
            if (dif < 0) {
                for (int i = as - 1; i < bs; i++) {
                    listB.add(book.bids.get(i));
                }
            } else if (dif > 0) {
                for (int i = bs - 1; i < as; i++) {
                    listA.add(book.asks.get(i));
                }
            }
            book.asks = listA;
            book.bids = listB;
        }
    }

    private void mergeDuplicates() {
        for (String name : names) {
            Book book = books.get(name);
            Ask temp1;
            List<Ask> listA = new ArrayList<>();
            List<Bid> listB = new ArrayList<>();
            int index = -1;
            Ask ask;
            Bid bid;

            for (int i = 0; i < book.asks.size(); i++) {
                ask = book.asks.get(i);
                if (index < 0) {
                    listA.add(ask);
                    index++;
                } else if (ask.price == listA.get(index).price) {
                    listA.get(index).volume += ask.volume;
                } else {
                    listA.add(ask);
                    index++;
                }
            }
            book.asks = listA;
            index = -1;

            for (int j = 0; j < book.bids.size(); j++) {
                bid = book.bids.get(j);
                if (index < 0) {
                    listB.add(bid);
                    index++;
                } else if (bid.price == listB.get(index).price) {
                    listB.get(index).volume += bid.volume;
                } else {
                    listB.add(bid);
                    index++;
                }
            }
            book.bids = listB;
        }
    }

    private void sort() {
        books.forEach((key, book) -> {
            Collections.sort(book.asks);
            Collections.sort(book.bids);
        }
        );
    }

    private void delOrder(String bookName, int id) {
        Book book = books.get(bookName);
        Bid bid = new Bid(0, 0, id);

        if (!book.bids.remove(bid)) {
            Ask ask = new Ask(0, 0, id);
            book.asks.remove(ask);
        }
    }

    private void addOrder(String bookName, String type, float price, int volume, int id) {

        if (!books.containsKey(bookName)) {
            names.add(bookName);
            Book book = new Book(bookName);
            books.put(bookName, book);
            if (type.equals("bid")) {
                book.bids.add(new Bid(price, volume, id));
            } else {
                book.asks.add(new Ask(price, volume, id));
            }
        } else {
            if (type.equals("bid")) {
                books.get(bookName).bids.add(new Bid(price, volume, id));
            } else {
                books.get(bookName).asks.add(new Ask(price, volume, id));
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        Parser parser = new Parser();

        final long startTime = System.currentTimeMillis();
        parser.start(args[0]);
        final long endTime = System.currentTimeMillis();
        System.out.println(String.format("Time: %,d ms", endTime - startTime));
    }
}
