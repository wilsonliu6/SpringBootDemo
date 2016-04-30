package com.mdt.open.service;

import com.mdt.open.domain.api.Record;
import com.mdt.open.domain.jpa.RecordJpa;
import com.mdt.open.repository.RecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    Logger logger = LoggerFactory.getLogger(RecordService.class);

    @Autowired
    RecordRepository repository;

    public Iterable<RecordJpa> findAll(){
        return repository.findAll();
    }

    public Record findByRecordId(String recordId){
        return repository.findOne(recordId);
    }

    public Iterable<RecordJpa> findRecordsByTheContractId(String contractId){
        return repository.findRecordsByTheContractId(contractId);
    }
}
