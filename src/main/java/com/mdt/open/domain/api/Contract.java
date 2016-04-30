package com.mdt.open.domain.api;

import com.mdt.open.domain.ContractStatus;
import com.mdt.open.domain.DeletedStatus;
import org.springframework.data.domain.Persistable;

import java.util.Date;

public interface Contract extends Persistable<String>{
    String getContractId();
    ContractStatus getContractStatus();
    String getMerchantId();
    String getProductId();
    Long getQrCodeCount();
    String getDescription();
    String getPointPayload();
    Date getExpectInjectOn();
    Date getCreatedOn();
    Date getUpdatedOn();
    DeletedStatus getDeletedStatus();
}
