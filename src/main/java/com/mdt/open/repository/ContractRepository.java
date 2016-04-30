package com.mdt.open.repository;

import com.mdt.open.domain.ContractStatus;
import com.mdt.open.domain.jpa.ContractJpa;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface ContractRepository extends PagingAndSortingRepository<ContractJpa,String> {
    ContractJpa findByContractIdAndStatus(String contractId, ContractStatus contractStatus);
    List<ContractJpa> findAllContracts();

    @Modifying
    @Query("update ContractJpa c set c.contractStatus=?1 where c.contractId=?2")
    void updateStatusByContractId(ContractStatus contractStatus, String contractId);
}
