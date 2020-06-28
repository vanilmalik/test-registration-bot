package com.malik.university.informationgathering.bot.command.impl;

import com.malik.university.informationgathering.bot.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class UnknownCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnknownCommand.class);

    @Override
    public void perform(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        LOGGER.info("Unknown command was sent from user '{}' with text '{}'",
                update.getMessage().getFrom().getUserName(), update.getMessage().getText());

        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("This command is not implemented yet or does not exist");

        try {
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Error during sending message to chat {}", chatId);
        }
    }
}
