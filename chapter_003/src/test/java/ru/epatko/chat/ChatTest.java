package ru.epatko.chat;

import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         25.12.16.
 */
public class ChatTest {
    @Test
    public void whenGiveBotAnswersFileThenGetBotAnswer() throws Exception {
        File botAnswers = new File("botAnswersTest.txt");
        Bot chatBot = new Bot(botAnswers);
        chatBot.fillAnswersArray();
        assertThat(chatBot.say(), is("Some text."));
    }
    @Test
    public void whenStartChatThenGetChatLog() throws Exception {
        File botAnswers = new File("botAnswersTest.txt");
        File logFile = new File("testChat.log");
        logFile.delete();
        logFile.createNewFile();
        Bot chatBot = new Bot(botAnswers);
        TestInput input = new TestInput();
        Chat chat = new Chat(input, chatBot, logFile);
        chat.start();

        try (RandomAccessFile raf = new RandomAccessFile(logFile, "rw")) {
        assertThat(raf.readLine(), is("User say: 1"));
        assertThat(raf.readLine(), is("Bot say: Some text."));
        assertThat(raf.readLine(), is("User say: finish"));
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }
}
class TestInput implements Input {
    int i = 0;
    public String say(String message) {
        String[] array = {"1", "finish"};
        return array [i++];
    }
}

