package io.serd.malbot.bot;

import io.serd.malbot.BuildVars;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

/**
 *
 * @author Atte Lassila
 */
public class MalBot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return BuildVars.token;
    }

    @Override
    public String getBotUsername() {
        return BuildVars.botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
