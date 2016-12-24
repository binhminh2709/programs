package ru.epatko.fileSorter;

import java.io.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         15.12.16.
 *
 *  Задача:
 *  1. Есть файл размер более 3G.
 *  2. Файл текстовый. В каждой строке записана строка произвольной длины.
 *  3. Нужно реализовать интерфейс sort(File source, File dist);
 *  File source - это txt файл со строками. File dist - несуществующий файл, его надо создать и записать
 *  туда результат сортировки.
 *  4. Необходимо отсортировать файл по возрастанию длин строк,
 *  для чего использовать внешнюю сортировку и RandomAccessFile.
 *
 */
public class FileSorter {

    /**
     * Temp file A.
     */
    private File tempA;
    /**
     * Temp file B.
     */
    private File tempB;
    /**
     * temp result file.
     */
    private File result;
    /**
     * Line separator.
     */
    private String lineSeparator;


    /**
     * Class.
     */
    public FileSorter() {
        this.tempA = new File("tempA");
        this.tempB = new File("tempB");
        this.result = new File("result");
        this.lineSeparator = System.getProperty("line.separator");
    }

    /**
     *
     * @param source - source file.
     * @param dist - destination file.
     * @throws IOException - exception.
     */
    public void sort(File source, File dist) throws IOException {

        try (RandomAccessFile sourceRaf = new RandomAccessFile(source, "r");
             RandomAccessFile resultRaf = new RandomAccessFile(this.result, "rw")) {
            while (sourceRaf.getFilePointer() != sourceRaf.length()) {
                resultRaf.writeByte(sourceRaf.read());
            }
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }

//*************************************************************************************

        /**
         * main cycle.
         */
        while (true) {
            splitResultFile();
            try (RandomAccessFile tempRafA = new RandomAccessFile(this.tempA, "r");
                 RandomAccessFile tempRafB = new RandomAccessFile(this.tempB, "r");
                 RandomAccessFile distRaf = new RandomAccessFile(dist, "rw")) {

                if ((this.tempA.length() == source.length())) {
                    while (tempRafA.getFilePointer() != tempRafA.length()) {
                        distRaf.write(tempRafA.read());
                    }
                    break;
                }
                if ((this.tempB.length() == source.length())) {
                    while (tempRafB.getFilePointer() != tempRafB.length()) {
                        distRaf.write(tempRafB.read());
                    }
                    break;
                }
            } catch (IOException ioe) {
                ioe.getStackTrace();
            }
            mergeTempFiles();
        }
        result.delete();
        tempA.delete();
        tempB.delete();
    }

//*************************************************************************************

    /**
     * Split temp result file to two temp files.
     * @throws IOException  - exception.
     */
    public void splitResultFile() throws IOException {

        try (RandomAccessFile tempRafA = new RandomAccessFile(this.tempA, "rw");
             RandomAccessFile tempRafB = new RandomAccessFile(this.tempB, "rw");
             RandomAccessFile resultRaf = new RandomAccessFile(this.result, "r")) {

            resultRaf.seek(0);
            tempRafA.seek(0);
            tempRafB.seek(0);
            String firstString;
            String secondString;
            boolean writeToTempA = true;

            if ((firstString = resultRaf.readLine()) != null) {
                tempRafA.writeBytes(String.format("%s%s", firstString, lineSeparator));
            }
            while (resultRaf.getFilePointer() != resultRaf.length()) {
                secondString = resultRaf.readLine();

                if (firstString.length() <= secondString.length() && writeToTempA) {
                        tempRafA.writeBytes(String.format("%s%s", secondString, lineSeparator));
                        firstString = secondString;

                } else if (writeToTempA) {
                    tempRafB.writeBytes(String.format("%s%s", secondString, lineSeparator));
                    firstString = secondString;
                    writeToTempA = false;

                } else if (firstString.length() <= secondString.length() && !writeToTempA) {
                    tempRafB.writeBytes(String.format("%s%s", secondString, lineSeparator));
                    firstString = secondString;

                } else if (!writeToTempA) {
                    tempRafA.writeBytes(String.format("%s%s", secondString, lineSeparator));
                    firstString = secondString;
                    writeToTempA = true;
                }
            }
        } catch (IOException ioe) {
           ioe.getStackTrace();
        }
        this.result.delete();
        this.result.createNewFile();
    }

//*************************************************************************************


    /**
     * Merge two temp files to one temp result file.
     * @throws IOException - exception.
     */
    public void mergeTempFiles() throws IOException {

        try (RandomAccessFile resultRaf = new RandomAccessFile(this.result, "rw");
             RandomAccessFile tempRafA = new RandomAccessFile(this.tempA, "r");
             RandomAccessFile tempRafB = new RandomAccessFile(this.tempB, "r")) {

            boolean skipReadFromTempA = false;
            boolean skipReadFromTempB = false;
            resultRaf.seek(0);
            tempRafA.seek(0);
            tempRafB.seek(0);
            String firstString = null;
            String secondString = null;
            if (tempRafA.getFilePointer() != tempRafA.length()) {
                firstString = tempRafA.readLine();
            } else {
                skipReadFromTempA = true;
            }
            if (tempRafB.getFilePointer() != tempRafB.length()) {
                secondString = tempRafB.readLine();
            } else {
                skipReadFromTempB = true;
            }
            do {
                if ((firstString.length() <= secondString.length() || skipReadFromTempB) & !skipReadFromTempA) {
                    resultRaf.writeBytes(String.format("%s%s", firstString, lineSeparator));
                    if (tempRafA.getFilePointer() != tempRafA.length()) {
                        firstString = tempRafA.readLine();
                    } else {
                        skipReadFromTempA = true;
                    }
                } else if ((firstString.length() > secondString.length() || skipReadFromTempA) & !skipReadFromTempB) {
                    resultRaf.writeBytes(String.format("%s%s", secondString, lineSeparator));
                    if (tempRafB.getFilePointer() != tempRafB.length()) {
                        secondString = tempRafB.readLine();
                    } else {
                        skipReadFromTempB = true;
                    }
                }
            } while (!skipReadFromTempA || !skipReadFromTempB);
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
        this.tempA.delete();
        this.tempB.delete();
        this.tempA.createNewFile();
        this.tempB.createNewFile();
    }
}