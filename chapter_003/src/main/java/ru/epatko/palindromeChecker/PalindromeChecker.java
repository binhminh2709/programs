package ru.epatko.palindromeChecker;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         22.12.16.
 */

/**
 * 4. Создать программу, которая будет проверять, является ли слово из пяти букв, введённое пользователем,
 * палиндромом (примеры: «комок», «ротор»). Если введено слово не из 5 букв, то сообщать об ошибке.
 * Программа должна нормально обрабатывать слово, даже если в нём использованы символы разного регистра.
 * Например, слова «Комок» или «РОТОР» следует также считать палиндромами.
 */


public class PalindromeChecker {

    private String temp;
    private boolean result;

    /**
     *
     * @param word - word.
     * @return - boolean (is the word a palindrome).
     */
    public boolean check (String word) {
        this.temp = word;
        this.temp.toLowerCase();
        if (this.temp.length() == 5) {
            if (this.temp.charAt(0) == this.temp.charAt(4) & this.temp.charAt(1) == this.temp.charAt(3)) {
                this.result = true;
            } else {
                this.result = false;
            }
        } else {
            this.result = false;
        }
        return this.result;
    }
}
