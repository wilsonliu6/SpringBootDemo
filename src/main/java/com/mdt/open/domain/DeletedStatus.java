package com.mdt.open.domain;

public enum DeletedStatus {
    NO(0),
    YES(1);

    private int deletedStatus;

    DeletedStatus(int deletedStatus){
        this.deletedStatus = deletedStatus;
    }

    public int getDeletedStatus(){return deletedStatus;}
}
