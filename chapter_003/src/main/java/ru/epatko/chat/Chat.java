package ru.epatko.chat;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         24.12.16.
 */

import java.io.*;

/**
 * 5. Создать программу консольный чат. Пользователь вводит слово-фразу,
 * программа берет случайную фразу из текстового файла и выводит в ответ.
 * Программа замолкает если пользователь вводит слово «стоп» при этом он может
 * продолжать отправлять сообщения в чат. Если пользователь вводит слово «продолжить»,
 * программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу.
 * Запись диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 * Так делать не надо. while (true) { - консольный чат. должен явно выходить из цикла.
 * Не делайте вечный цикл.
 */

public class Chat {

    /**
     * Input method.
     */
    private Input input;
    /**
     * Chat logger.
     */
    private ChatLogger logger;
    /**
     * Chat-bot.
     */
    private Bot bot;
    /**
     * User's phrase.
     */
    private String userPhrase;
    /**
     * Bot's phrase.
     */
    private String botPhrase;
    /**
     * Disable/enable chat-bot.
     */
    private boolean botSpeak = true;

    /**
     *
     * @param inputMethod - input method.
     * @param chatBot - chat-bot.
     * @param logFile - log file.
     */
    public Chat(Input inputMethod, Bot chatBot, File logFile) {
        this.input = inputMethod;
        this.bot = chatBot;
        this.logger = new ChatLogger(logFile);
    }

    /**
     * Start chat.
     * @throws IOException - exception.
     */
    public void start() throws IOException {
        this.bot.fillAnswersArray();
        System.out.println("Well come to the chat with a chat-bot.");
        System.out.println("To exit chat enter \"finish\".");
        System.out.println("To disable the chat-bot enter \"stop\".");
        System.out.println("To enable the chat-bot enter \"start\".");

        do {
            this.userPhrase = this.input.say("User say: ");
            logger.log(String.format("User say: %s", this.userPhrase));
            if ((!"finish".equalsIgnoreCase(this.userPhrase))) {
                if (this.userPhrase.equalsIgnoreCase("stop")) {
                    botSpeak = false;
                } else if (this.userPhrase.equalsIgnoreCase("start")) {
                    botSpeak = true;
                }
                if (botSpeak) {
                    this.botPhrase = bot.say();
                    System.out.println(String.format("Bot say: %s", this.botPhrase));
                    logger.log(String.format("Bot say: %s", this.botPhrase));
                }
            }
        } while (!"finish".equalsIgnoreCase(this.userPhrase));
    }

    /**
     * Main method.
     * @param args - no arguments.
     * @throws IOException - exception.
     */
    public static void main(String[] args) throws IOException {
        File botAnswers = new File("chapter_003/src/main/java/ru/epatko/chat/botAnswers.txt");
        File logFile = new File("chapter_003/src/main/java/ru/epatko/chat/chat.log");
        logFile.delete();
        logFile.createNewFile();
        Input inputMethod = new ConsoleInput();

        if (!botAnswers.exists()) {
            System.out.println("File contains bot answers doesn't exist.");
        } else if (!botAnswers.canRead()) {
            System.out.println("Can't open to read file contains bot answers.");
        } else {
            Bot chatBot = new Bot(botAnswers);
            Chat chat = new Chat(inputMethod, chatBot, logFile);
            chat.start();
        }
    }
}
