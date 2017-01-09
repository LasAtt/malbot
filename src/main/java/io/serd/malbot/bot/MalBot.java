package io.serd.malbot.bot;

import io.serd.malbot.BuildVars;
import io.serd.malbot.domain.AnimeSeries;
import io.serd.malbot.domain.AnimeEntry;
import io.serd.malbot.services.MyAnimeListService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 *
 * @author Atte Lassila
 */
public class MalBot extends TelegramLongPollingBot {

    private static final String LOGTAG = "MALBOT";

    private static final Integer CACHETIME = 86400;

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
        try {
            if (update.hasInlineQuery()) {
                handleIncomingInlineQuery(update.getInlineQuery());
            } else if (update.hasMessage() && update.getMessage().isUserMessage()) {
                try {
                    sendMessage(getHelpMessage(update.getMessage()));
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    private void handleIncomingInlineQuery(InlineQuery inlineQuery) {
        String query = inlineQuery.getQuery();

        BotLogger.debug(LOGTAG, "Searching: " + query);
        try {
            if (!query.isEmpty()) {
                AnimeSeries series = MyAnimeListService.queryAnime(query);
                answerInlineQuery(convertResultsToResponse(inlineQuery, series));
            } else {
                answerInlineQuery(convertResultsToResponse(inlineQuery, new AnimeSeries()));
            }
        } catch (TelegramApiException ex) {
            BotLogger.error(LOGTAG, ex);
        }
    }

    private static AnswerInlineQuery convertResultsToResponse(InlineQuery inlineQuery, AnimeSeries series) {
        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
        answerInlineQuery.setInlineQueryId(inlineQuery.getId());
        answerInlineQuery.setCacheTime(CACHETIME);
        answerInlineQuery.setResults(convertAnimeResults(series));
        return answerInlineQuery;
    }

    private static List<InlineQueryResult> convertAnimeResults(AnimeSeries series) {
        List<InlineQueryResult> results = new ArrayList<>();

        if (series.getEntries() == null) {
            return results;
        }

        int i = 0;
        for (AnimeEntry entry : series.getEntries()) {
            results.add(convertAnimeResult(entry, i, results));
            i++;
        }

        return results;
    }

    private static InlineQueryResult convertAnimeResult(AnimeEntry entry, int i, List<InlineQueryResult> results) {
        InputTextMessageContent messageContent = new InputTextMessageContent();
        messageContent.enableMarkdown(true);
        messageContent.setMessageText(makeMarkdownResult(entry));
        InlineQueryResultArticle anime = new InlineQueryResultArticle();
        anime.setInputMessageContent(messageContent);
        anime.setId(Integer.toString(i));
        anime.setTitle(entry.getTitle());
        anime.setDescription(entry.getSynopsis());
        anime.setThumbUrl(entry.getImage().toString());
        return anime;
    }

    private SendMessage getHelpMessage(Message message) {
        SendMessage send = new SendMessage();
        send.setChatId(message.getChatId());
        String helpMessage = "Hello! I'm an inline bot that you can use to query Anime series from MyAnimeList!\n"
            + "Try: @" + getBotUsername() + " yuyushiki";
        send.setText(helpMessage);
        return send;
    }

    private static String makeMarkdownResult(AnimeEntry entry) {
        StringBuilder markdown = new StringBuilder();
        markdown.append("*")
                .append(entry.getTitle())
                .append("*\n");
        markdown.append("[https://myanimelist.net/anime/")
                .append(entry.getId())
                .append("/](")
                .append(entry.getTitle())
                .append(")");
        
        return markdown.toString();
    }

}
