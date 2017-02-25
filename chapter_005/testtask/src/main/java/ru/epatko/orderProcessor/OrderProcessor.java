package ru.epatko.orderProcessor;

import javax.xml.stream.*;
import java.io.*;
import java.util.*;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         25.02.17.
 */
public class OrderProcessor {

    private Map<String, Book> books = new HashMap<>();
    private List<String> bookNames = new ArrayList<>();
    private final String lineSeparator = System.getProperty("line.separator");


    public void process(String fileName) throws FileNotFoundException, XMLStreamException {

        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(fileName));

            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement()) {
                    if ("AddOrder".equals(xmlr.getLocalName())) {
                        addOrder(xmlr.getAttributeValue(0),
                                "BUY".equals(xmlr.getAttributeValue(1)) ? "bid" : "ask",
                                Float.parseFloat(xmlr.getAttributeValue(2)),
                                Integer.parseInt(xmlr.getAttributeValue(3)),
                                Integer.parseInt(xmlr.getAttributeValue(4)));
                    }
                    if ("DeleteOrder".equals(xmlr.getLocalName())) {
                        deleteOrder (xmlr.getAttributeValue(0), Integer.parseInt(xmlr.getAttributeValue(1)));
                    }
                }
            }
            xmlr.close();
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        calculateAndShowResult();
    }

    private void addOrder(String bookName, String type, float price, int volume, int id) {
        Order order = new Order(type, price, volume, id);
        if (!books.containsKey(bookName)) {
            bookNames.add(bookName);
            Book book = new Book(bookName);
            book.keyMap.put(id, order);

            if (type.equals("bid")) {
                book.bids.put(price, volume);
            } else {
                book.asks.put(price, volume);
            }
            books.put(bookName, book);
        } else {
            Book book = books.get(bookName);
            book.keyMap.put(id, order);

            if (type.equals("bid")) {
                if (book.bids.containsKey(price)) {
                    volume += book.bids.get(price);
                }
                book.bids.put(price, volume);
            } else {
                if (book.asks.containsKey(price)) {
                    volume += book.asks.get(price);
                }
                book.asks.put(price, volume);
            }
        }
    }

    private void deleteOrder(String bookName, int id) {

        float price = books.get(bookName).keyMap.get(id).price;
        int volume = books.get(bookName).keyMap.get(id).volume;
        if ("bid".equals(books.get(bookName).keyMap.get(id).type)) {
            books.get(bookName).bids.put(price, books.get(bookName).bids.get(price) - volume);
            if (books.get(bookName).bids.get(price) == 0) {
                books.get(bookName).bids.remove(price);
            }
        } else {
            books.get(bookName).asks.put(price, books.get(bookName).asks.get(price) - volume);
            if (books.get(bookName).asks.get(price) == 0) {
                books.get(bookName).asks.remove(price);
            }
        }
    }

    private void calculateAndShowResult() {

        for (int i = 0; i < bookNames.size(); i++) {
            Book book = books.get(bookNames.get(i));
            ArrayList<Float> keysA = new ArrayList<>();
            keysA.addAll(book.asks.keySet());
            ArrayList<Float> keysB = new ArrayList<>();
            keysB.addAll(book.bids.keySet());

            float keyA;
            float keyB;
            int dif, indA, indB;
            indA = indB = 0;

            while (indA < keysA.size() && indB < keysB.size()) {
                keyA = keysA.get(indA);
                keyB = keysB.get(indB);

                if (keyB >= keyA) {
                    dif = book.asks.get(keyA) - book.bids.get(keyB);
                    if (dif > 0) {
                        book.asks.put(keyA, dif);
                        book.bids.remove(keyB);
                        indB++;
                    } else if (dif < 0) {
                        book.bids.put(keyB, -dif);
                        book.asks.remove(keyA);
                        indA++;
                    } else {
                        book.bids.remove(keyB);
                        book.asks.remove(keyA);
                        indB++;
                        indA++;
                    }
                } else {
                    break;
                }
            }

            Iterator<Float> itA = book.asks.keySet().iterator();
            Iterator<Float> itB = book.bids.keySet().iterator();
            dif = book.asks.size() - book.bids.size();

            System.out.println();
            System.out.printf("%2$s%2$s       Order book: %s%s%2$s       BID              ASK%2$s%2$s", book.name, lineSeparator);
            ;
            System.out.println("  Volume@Price – Volume@Price");
            System.out.println();
            while (itA.hasNext() && itB.hasNext()) {
                keyA = itA.next();
                keyB = itB.next();
                System.out.printf("%7d@%6.2f - %7d@%.2f%s", book.bids.get(keyB), keyB, book.asks.get(keyA), keyA, lineSeparator);
            }
            if (dif > 0) {
                while (itA.hasNext()) {
                    keyA = itA.next();
                    System.out.printf("%14s - %7d@%.2f%s", "---------", book.asks.get(keyA), keyA, lineSeparator);
                }
            } else if (dif < 0) {
                 while (itB.hasNext()) {
                    keyB = itB.next();
                    System.out.printf("%7d@%6.2f - ---------%s", book.bids.get(keyB), keyB, lineSeparator);
                }
            }
        }
    }
}