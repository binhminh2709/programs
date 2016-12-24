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


/**
 *
 * @param <T>
 */
public class PalindromeChecker<T> {

    /**
     * Reversed string.
     */
    private String tempA;
    /**
     * Source string.
     */
    private String tempB;

    /**
     * Jedi method.
     * @param word - word.
     * @return - boolean (the word is a palindrome and the word has 5 chars).
     */
    public boolean check(T word) {
        this.tempA = new StringBuilder(word.toString()).reverse().toString();
        this.tempB = word.toString();

        return (this.tempB.length() == 5) && this.tempB.equalsIgnoreCase(tempA);
    }
}
