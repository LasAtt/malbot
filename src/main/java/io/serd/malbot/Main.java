package io.serd.malbot;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import io.serd.malbot.bot.MalBot;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    public static void main(String[] args) throws TelegramApiRequestException, FileNotFoundException, IOException {
        System.getProperties().load(new FileReader(System.getProperty("dev.properties")));
        
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        telegramBotsApi.registerBot(new MalBot());
    }

}
