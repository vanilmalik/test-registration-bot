package com.malik.university.informationgathering.bot.command.impl;

import com.malik.university.informationgathering.bot.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class AddNewKeyCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewKeyCommand.class);

    @Override
    public void perform(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId()).setParseMode(ParseMode.MARKDOWN);

        String messageText = "To sent Binance key sent message in format: \n" +
                "/key" +  '\n' +
                "```secret:your_secret_key```" + '\n' +
                "```public:your_public_key```";

        message.setText(messageText);
        try {
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Error during sending message", e);
        }
    }
}
