package com.malik.university.informationgathering.bot.command.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@Component
public class MyBinanceAPIKeysCommand extends AbstractMurkupCommand {

    @Override
    protected KeyboardRow addRow() {
        KeyboardRow row = new KeyboardRow();
        row.add("Add new Key");
        row.add("Keys List");

        return row;
    }
}
