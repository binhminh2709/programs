package ru.epatko.chat;

import java.io.*;
import java.util.ArrayList;

/**
 * Chat-bot.
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.12.16.
 */
public class Bot {
    /**
     * Bot's answer.
     */
    private String answer;
    /**
     * Array of the answer's position in the bot answers file.
     */
    private ArrayList<Long> answersPosition = new ArrayList<>();
    /**
     * File contains the bot answers.
     */
    private File file;

    /**
     * Bot class constructor.
     * @param botAnswers - file contains bot answers.
     */
    public Bot(File botAnswers) {
        this.file = botAnswers;
    }

    /**
     * Fill answers array.
     * @throws IOException - exception.
     */
    public void fillAnswersArray() throws IOException {
        try (RandomAccessFile raFile = new RandomAccessFile(this.file, "r")) {
            raFile.seek(0);
            while (raFile.getFilePointer() != raFile.length()) {
                answersPosition.add(raFile.getFilePointer());
                raFile.readLine();
            }
        } catch (IOException ioe) {
            ioe.getStackTrace();
            return;
        }
    }
    /**
     * Bot random answer method.
     * @return - bot answer.
     * @throws IOException - exception.
     */
    public String say() throws IOException {
        try (RandomAccessFile raFile = new RandomAccessFile(this.file, "r")) {
            int answerNumber = (int) (Math.random() * this.answersPosition.size());
            raFile.seek(this.answersPosition.get(answerNumber));
            this.answer =  raFile.readLine();
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
        return this.answer;
    }
}
