package com.mdt.open.domain.jpa;

import com.mdt.open.domain.api.Contract;
import com.mdt.open.domain.ContractStatus;
import com.mdt.open.domain.DeletedStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contract")
@NamedQueries({
@NamedQuery(name = "ContractJpa.findByContractIdAndStatus", query = "select c from ContractJpa c where c.contractId=?1 and c.contractStatus=?2"),
@NamedQuery(name = "ContractJpa.findAllContracts", query = "select c from ContractJpa c")
})
public class ContractJpa implements Contract {
    @Id
    @Column(name = "contract_id", length = 36, columnDefinition = "VARCHAR")
    private String contractId;

    @Column(name = "contract_status", columnDefinition = "VARCHAR", length = 64)
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

//    @OneToMany(mappedBy = "contract",fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "contract",fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "contract",cascade = CascadeType.ALL)
    private List<RecordJpa> records = new ArrayList<>();

    @Column(name = "merchant_id", columnDefinition = "VARCHAR", length = 32)
    @NotNull
    private String merchantId;

    @Column(name = "product_id", columnDefinition = "VARCHAR", length = 32)
    @NotNull
    private String productId;

    @Column(name = "description", columnDefinition = "VARCHAR", length = 255)
    private String description;

    @Column(name="point_payload", columnDefinition = "TEXT")
    @NotNull
    private String pointPayload;

    @Column(name = "expect_inject_on", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date expectInjectOn;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date createdOn;

    @Column(name = "updated_on", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date updatedOn;

    @Column(name = "qrcode_count", nullable = false, columnDefinition="BIGINT")
    private Long qrCodeCount;

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

    protected ContractJpa(){}

    public ContractJpa(Builder builder){
        this.contractId = builder.contractId;
        this.contractStatus = builder.contractStatus;
        this.merchantId = builder.merchantId;
        this.productId = builder.productId;
        this.description = builder.description;
        this.pointPayload = builder.pointPayload;
        this.expectInjectOn = builder.expectInjectOn;
        this.createdOn = builder.createdOn;
        this.updatedOn = builder.updatedOn;
        this.qrCodeCount = builder.qrCodeCount;
        this.deleted = builder.deleted;
    }

    @Override
    public String getId(){
        return contractId;
    }

    @Override public boolean isNew() {
        return contractId == null;
    }

    @Override
    public String getContractId(){
        return contractId;
    }

    @Override
    public ContractStatus getContractStatus(){
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus){this.contractStatus = contractStatus;}

    @Override
    public String getMerchantId(){return merchantId;}

    @Override
    public String getProductId(){return productId;}

    @Override
    public Long getQrCodeCount(){return qrCodeCount;}

    @Override
    public String getDescription(){return description;}

    @Override
    public String getPointPayload(){return pointPayload;}

    @Override
    public Date getExpectInjectOn(){return expectInjectOn;}

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

    public void setExpectInjectOn(Date expectInjectOn){
        this.expectInjectOn = expectInjectOn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE).toString();
//        return contractId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractJpa that = (ContractJpa) o;

        if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return contractId != null ? contractId.hashCode() : 0;
    }

    public static class Builder{
        private String contractId;
        private ContractStatus contractStatus;
        private String merchantId;
        private String productId;
        private String description;
        private String pointPayload;
        private Long qrCodeCount;
        private Date expectInjectOn;
        private Date createdOn;
        private Date updatedOn;
        private DeletedStatus deleted;

        public Builder(String contractId){
            this.contractId = contractId;
        }

        public  Builder contractStatus(ContractStatus contractStatus){
            this.contractStatus = contractStatus;
            return this;
        }

        public Builder merchantId(String merchantId){
            this.merchantId = merchantId;
            return this;
        }

        public Builder productId(String productId){
            this.productId = productId;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder pointPayload(String pointPayload){
            this.pointPayload = pointPayload;
            return this;
        }
        public Builder qrCodeCount(Long qrCodeCount){
            this.qrCodeCount = qrCodeCount;
            return this;
        }
        public Builder expectInjectOn(Date expectInjectOn){
            this.expectInjectOn = expectInjectOn;
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
        public Builder deleted(DeletedStatus deleted){
            this.deleted = deleted;
            return this;
        }

        public ContractJpa build(){
            return new ContractJpa(this);
        }
    }
}
