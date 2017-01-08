package io.serd.malbot;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import io.serd.malbot.bot.MalBot;
import io.serd.malbot.exception.NoSystemVarException;

public class Main {

    public static void main(String[] args) throws TelegramApiRequestException, NoSystemVarException {
        System.out.println(System.getenv("MAL_BOT_TOKEN"));

        System.out.println(new StringBuilder(BuildVars.botName)
                .append(", ")
                .append(BuildVars.certificateStorePassword)
                .append(", ")
                .append(BuildVars.externalUrl).toString());
        TelegramBotsApi telegramBotsApi
                = new TelegramBotsApi(
                        BuildVars.pathToCertificateStore,
                        BuildVars.certificateStorePassword,
                        BuildVars.externalUrl,
                        BuildVars.internalUrl);

        telegramBotsApi.registerBot(new MalBot());
    }

}
