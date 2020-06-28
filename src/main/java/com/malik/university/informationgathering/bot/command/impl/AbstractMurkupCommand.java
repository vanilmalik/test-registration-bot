package com.malik.university.informationgathering.bot.command.impl;

import com.malik.university.informationgathering.bot.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMurkupCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMurkupCommand.class);

    @Override
    public void perform(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Choose an option");

        message.setReplyMarkup(buildReplyKeyboardMarkup());
        try {
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Error during sending message");
        }
    }

    protected ReplyKeyboard buildReplyKeyboardMarkup() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        keyboard.add(addRow());

        keyboardMarkup.setOneTimeKeyboard(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    protected abstract KeyboardRow addRow();
}
