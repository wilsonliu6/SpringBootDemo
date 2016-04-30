package com.mdt.open.domain.jpa;

import com.mdt.open.domain.api.Contract;
import com.mdt.open.domain.api.Record;
import com.mdt.open.domain.DeletedStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "record")
@NamedQuery(name = "RecordJpa.findRecordsByTheContractId", query = "select s from RecordJpa s where s.contract.contractId=?1")
public class RecordJpa implements Record {
    @Id
    @Column(name = "qrcode", columnDefinition = "VARCHAR", length = 128)
    @NotNull
    private String qrCode;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private ContractJpa contract;

    @Column(name = "url_qrcode", columnDefinition = "VARCHAR", length = 512)
    @NotNull
    private String urlQrCode;

    @Column(name = "promotion_point", columnDefinition = "INT")
    @NotNull
    private int promotionPoint;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date createdOn;

    @Column(name = "updated_on", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date updatedOn;

    @Column(columnDefinition = "INT")
    private DeletedStatus deleted;

    @PrePersist
    private void prePersist() {
        createdOn = updatedOn = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedOn = new Date();
    }

    protected RecordJpa(){}

    public RecordJpa(Builder builder){
        this.contract = builder.contract;
        this.qrCode = builder.qrCode;
        this.urlQrCode = builder.urlQrCode;
        this.promotionPoint = builder.promotionPoint;
        this.createdOn = builder.createdOn;
        this.updatedOn = builder.updatedOn;
        this.deleted = builder.deleted;
    }

    @Override
    public String getId(){
        return qrCode;
    }

    @Override public boolean isNew() {
        return qrCode == null;
    }

    @Override
    public String getQrCode(){
        return qrCode;
    }

    @Override
    public String getUrlQrCode(){
        return urlQrCode;
    }

    @Override
    public int getPromotionPoint(){return promotionPoint;}

    @Override
    public Contract getContract(){return contract;}

    @Override
    public String getContractId(){return contract.getContractId();}

    @Override
    public Date getCreatedOn(){
        return createdOn;
    }

    @Override
    public Date getUpdatedOn(){
        return updatedOn;
    }

    @Override
    public DeletedStatus getDeletedStatus(){return deleted;}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordJpa that = (RecordJpa) o;

        if(qrCode != null ? !qrCode.equals(that.qrCode) : that.qrCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return qrCode != null ? qrCode.hashCode() : 0;
    }

    public static class Builder{
        private ContractJpa contract;
        private String qrCode;
        private String urlQrCode;
        private int promotionPoint;
        private DeletedStatus deleted;
        private Date createdOn;
        private Date updatedOn;

        public Builder(ContractJpa contract){
            this.contract = contract;
        }

        public Builder qrCode(String qrCode){
            this.qrCode = qrCode;
            return this;
        }
        public Builder urlQrCode(String urlQrCode){
            this.urlQrCode = urlQrCode;
            return this;
        }
        public Builder promotionPoint(int promotionPoint){
            this.promotionPoint = promotionPoint;
            return this;
        }
        public Builder deleted(DeletedStatus deleted){
            this.deleted = deleted;
            return this;
        }

        public Builder createdOn(Date createdOn){
            this.createdOn = createdOn;
            return this;
        }

        public Builder updatedOn(Date updatedOn){
            this.updatedOn = updatedOn;
            return this;
        }

        public RecordJpa build(){
            return new RecordJpa(this);
        }
    }
}
