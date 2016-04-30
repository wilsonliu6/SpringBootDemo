package com.mdt.open.domain.api;

import com.mdt.open.domain.DeletedStatus;
import org.springframework.data.domain.Persistable;

import java.util.Date;

public interface Record extends Persistable<String> {
    String getQrCode();
    String getContractId();
    Contract getContract();
    String getUrlQrCode();
    int getPromotionPoint();
    Date getCreatedOn();
    Date getUpdatedOn();
    DeletedStatus getDeletedStatus();
}
