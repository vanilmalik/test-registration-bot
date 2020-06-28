package com.malik.university.informationgathering.bot;

import com.malik.university.informationgathering.bot.command.Command;
import com.malik.university.informationgathering.bot.command.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class RegistrationBot extends TelegramLongPollingBot {

    @Value("${tg.bot.name}")
    private String botUsername;

    @Value("${tg.bot.token}")
    private String botToken;

    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String commandText = retrieveCommand(update.getMessage().getText());
            Command command = commandExecutor.getCommand(commandText);
            command.perform(update, this);
        }
    }

    private String retrieveCommand(String text) {
        if (text.startsWith("/key")) {
            return "/key";
        }

        return text;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}
