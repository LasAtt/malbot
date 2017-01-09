package io.serd.malbot;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import io.serd.malbot.bot.MalBot;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.logging.BotsFileHandler;

public class Main {
    private static final String LOGTAG = "MAIN";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.getProperties().load(new FileReader(System.getProperty("properties.file")));

        BotLogger.setLevel(Level.ALL);
        BotLogger.registerLogger(new ConsoleHandler());
        try {
            BotLogger.registerLogger(new BotsFileHandler());
        } catch (IOException e) {
            BotLogger.severe(LOGTAG, e);
        }

        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new MalBot());
        } catch (TelegramApiRequestException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

}
