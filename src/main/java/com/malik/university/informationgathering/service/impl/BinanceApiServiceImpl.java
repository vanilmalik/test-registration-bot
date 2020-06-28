package com.malik.university.informationgathering.service.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.exception.BinanceApiException;
import com.malik.university.informationgathering.service.BinanceApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.binance.api.client.domain.account.NewOrder.limitBuy;

@Service
public class BinanceApiServiceImpl implements BinanceApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinanceApiServiceImpl.class);

    @Override
    public Boolean checkApiKey(String publicKey, String privateKey) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(publicKey, privateKey);
        BinanceApiRestClient client = factory.newRestClient();

        try {
            client.newOrderTest(limitBuy("XLMETH", TimeInForce.GTC, "10", "0.00131337"));
        } catch (BinanceApiException e) {
            LOGGER.error("Error {} ", e.getMessage(), e);
            return false;
        }

        return true;
    }

    public Account getPermissions(String publicKey, String privateKey) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(publicKey, privateKey);
        BinanceApiRestClient client = factory.newRestClient();

        return client.getAccount();
    }
}
