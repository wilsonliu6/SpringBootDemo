package com.mdt.open.domain;

import org.springframework.util.Assert;

public enum ContractStatus {
    CONTRACT_CREATED("The qrcode generation contract is created"),
    START_INJECTION("Begin to inject into our platform"),
    REQUIRED_INTERVENTION("There are some errors while doing injection, and it needs manual intervention"),
    CONTRACT_FINISHED("The contract is finished");

    private String value;
    ContractStatus(String value){
        Assert.hasText(value);
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
