package com.malik.university.informationgathering.bot.command;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void perform(Update update, TelegramLongPollingBot telegramLongPollingBot);
}
