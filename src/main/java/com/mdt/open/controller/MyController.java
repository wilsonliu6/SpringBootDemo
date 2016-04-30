package com.mdt.open.controller;

import com.mdt.open.domain.api.Contract;
import com.mdt.open.domain.api.Record;
import com.mdt.open.domain.jpa.ContractJpa;
import com.mdt.open.domain.jpa.RecordJpa;
import com.mdt.open.service.ContractService;
import com.mdt.open.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class MyController {
    Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    ContractService contractService;

    @Autowired
    RecordService recordService;


    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public Page<ContractJpa> getPageContracts(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                             @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                             @RequestParam(value = "sort", required = false, defaultValue = "false") boolean sort) throws Exception{
        System.out.println(String.format("%s, %s, %s",page, size, sort));

        try{
            return contractService.getPageContracts(page, size, sort);
        }catch (Exception e){
            logger.error("Error of getAllPageContracts:"+e.getMessage());
            throw new Exception("Error:"+e.getMessage());
        }
    }

    @RequestMapping(value = "/contracts/{contractId}", method = RequestMethod.GET)
    public Contract getContractByContractId(@PathVariable("contractId") String contractId) throws Exception{
        try{
            return contractService.findByContractId(contractId);
        }catch (Exception e){
            logger.error("Error of getContractByContractId:"+e.getMessage());
            throw new Exception("Error:"+e.getMessage());
        }
    }

    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public Iterable<RecordJpa> findAll(){
        return recordService.findAll();
    }

    @RequestMapping(value = "/records/{recordId}", method = RequestMethod.GET)
    public Record findByRecordId(@PathVariable("recordId") String recordId){
        return recordService.findByRecordId(recordId);
    }
}
