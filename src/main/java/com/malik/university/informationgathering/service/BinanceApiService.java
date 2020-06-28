package com.malik.university.informationgathering.service;

import com.binance.api.client.domain.account.Account;

public interface BinanceApiService {

    Boolean checkApiKey(String publicKey, String privateKey);

    Account getPermissions(String publicKey, String privateKey);

}
