package com.mdt.open.service;

import com.mdt.open.domain.api.Contract;
import com.mdt.open.domain.jpa.ContractJpa;
import com.mdt.open.domain.ContractStatus;
import com.mdt.open.repository.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.transaction.Transactional;

@Service
public class ContractService {
    Logger logger = LoggerFactory.getLogger(ContractService.class);

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    RecordService recordService;

    @Transactional
    public Iterable<ContractJpa> getAllContracts(){
        return contractRepository.findAll();
    }

    @Transactional
    public Page<ContractJpa> getPageContracts(int page, int size, boolean isSort){
        Assert.isTrue(page>=0,"please make sure the 'page' param is bigger than zero!");
        Assert.isTrue(size>0, "Please make sure the 'size' param is bigger than zero!");

        Sort sort = isSort ? this.sortByCreatedOnDesc() : null;

        Pageable pageable = new PageRequest(page, size, sort);
        return contractRepository.findAll(pageable);
    }

    @Transactional
    public Contract findByContractId(String contractId){
        return contractRepository.findOne(contractId);
    }

    @Transactional
    public void updateStatusByContractId(ContractStatus status, String contractId){
        contractRepository.updateStatusByContractId(status, contractId);
    }

        //***************** Private Functions *******************
    private Sort sortByCreatedOnDesc(){
        return new Sort(Sort.Direction.DESC, "createdOn");
    }
}
