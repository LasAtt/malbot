package io.serd.malbot.bot;

import io.serd.malbot.BuildVars;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

/**
 *
 * @author Atte Lassila
 */
public class MalBot extends TelegramWebhookBot {

    @Override
    public String getBotToken() {
        return BuildVars.token;
    }

    @Override
    public String getBotUsername() {
        return BuildVars.botName;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText("Well, all information looks like noise until you break the code.");
            return sendMessage;
        }
        return null;

    }

    @Override
    public String getBotPath() {
        return BuildVars.botName;
    }

}
