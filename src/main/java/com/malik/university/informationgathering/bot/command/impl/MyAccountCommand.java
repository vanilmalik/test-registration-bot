package com.malik.university.informationgathering.bot.command.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@Component
public class MyAccountCommand extends AbstractMurkupCommand {

    @Override
    protected KeyboardRow addRow() {
        KeyboardRow row = new KeyboardRow();
        row.add("My Binance API Keys");
        row.add("Configuration");

        return row;
    }
}
