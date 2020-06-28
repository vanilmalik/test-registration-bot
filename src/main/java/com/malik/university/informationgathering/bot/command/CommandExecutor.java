package com.malik.university.informationgathering.bot.command;

import com.malik.university.informationgathering.bot.command.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

import static java.util.Objects.nonNull;

@Component
public class CommandExecutor {

    private HashMap<String, Command> commands = new HashMap<>();

    @Autowired
    private StartCommand startCommand;

    @Autowired
    private UnknownCommand unknownCommand;

    @Autowired
    private MyBinanceAPIKeysCommand myBinanceAPIKeysCommand;

    @Autowired
    private MyAccountCommand myAccountCommand;

    @Autowired
    private AddNewKeyCommand addNewKeyCommand;

    @Autowired
    private SaveKeyCommand saveKeyCommand;

    @PostConstruct
    private void init() {
        commands.put("/start", startCommand);
        commands.put("/key", saveKeyCommand);
        commands.put("My account", myAccountCommand);
        commands.put("My Binance API Keys", myBinanceAPIKeysCommand);
        commands.put("Add new Key", addNewKeyCommand);
    }

    public Command getCommand(String command) {
        if (nonNull(commands.get(command))) {
            return commands.get(command);
        } else {
            return unknownCommand;
        }
    }
}
