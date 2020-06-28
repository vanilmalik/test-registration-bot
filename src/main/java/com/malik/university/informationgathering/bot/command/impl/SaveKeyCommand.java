package com.malik.university.informationgathering.bot.command.impl;

import com.binance.api.client.domain.account.Account;
import com.malik.university.informationgathering.bot.command.Command;
import com.malik.university.informationgathering.entity.ApiKeyEntity;
import com.malik.university.informationgathering.entity.UserEntity;
import com.malik.university.informationgathering.repository.UserEntityRepository;
import com.malik.university.informationgathering.service.ApiKeyService;
import com.malik.university.informationgathering.service.BinanceApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class SaveKeyCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveKeyCommand.class);

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private BinanceApiService binanceApiService;

    @Override
    public void perform(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        String responseMessage = "Key successfully saved";

        String incomingMessage = update.getMessage().getText();
        String[] parsedMessage = incomingMessage.split("\n");
        String secretKey = parsedMessage[1].split(":")[1];
        String publicKey = parsedMessage[2].split(":")[1];

        UserEntity userEntity = userEntityRepository.findByTelegramUserName(update.getMessage().getFrom().getUserName());
        ApiKeyEntity apiKeyEntity = new ApiKeyEntity();
        apiKeyEntity.setPublicKey(publicKey);
        apiKeyEntity.setSecretKey(secretKey);
        apiKeyEntity.setActive(true);
        apiKeyEntity.setUser(userEntity);

        if (!binanceApiService.checkApiKey(apiKeyEntity.getPublicKey(), apiKeyEntity.getSecretKey())) {
            responseMessage = "Key is invalid";
        } else {
            Account account = binanceApiService.getPermissions(apiKeyEntity.getPublicKey(), apiKeyEntity.getSecretKey());
            apiKeyEntity.setCanDeposit(account.isCanDeposit());
            apiKeyEntity.setCanTrade(account.isCanTrade());
            apiKeyEntity.setCanWithdraw(account.isCanWithdraw());

            apiKeyService.doSave(apiKeyEntity);
        }

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId())
                .setText(responseMessage);

        ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove();
        sendMessage.setReplyMarkup(keyboardRemove);
        try {
            telegramLongPollingBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            LOGGER.error("Error during sending message {}", e.getMessage(), e);
        }
    }
}
