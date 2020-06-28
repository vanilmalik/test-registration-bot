package com.malik.university.informationgathering.bot.command.impl;

import com.malik.university.informationgathering.entity.UserEntity;
import com.malik.university.informationgathering.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class StartCommand extends AbstractMurkupCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartCommand.class);

    @Autowired
    private UserService userService;

    @Override
    public void perform(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Choose an option");

        message.setReplyMarkup(buildReplyKeyboardMarkup());

        UserEntity userEntity = mapUserEntity(update);
        userService.doSave(userEntity);

        LOGGER.info("User with userName {} was successfully saved", userEntity.getTelegramUserName());

        try {
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Error during sending message", e);
        }
    }

    private UserEntity mapUserEntity(Update update) {
        User from = update.getMessage().getFrom();
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(from.getFirstName());
        userEntity.setLastName(from.getLastName());
        userEntity.setTelegramUserName(from.getUserName());
        userEntity.setUniqueTelegramUserId(from.getId());
        return userEntity;
    }

    @Override
    protected KeyboardRow addRow() {
        KeyboardRow row = new KeyboardRow();
        row.add("My account");
        row.add("Info");
        row.add("FAQ");

        return row;
    }
}
