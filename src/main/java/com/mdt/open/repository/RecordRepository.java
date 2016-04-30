package com.mdt.open.repository;

import com.mdt.open.domain.jpa.RecordJpa;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<RecordJpa, String> {
    Iterable<RecordJpa> findRecordsByTheContractId(String contract_id);
}
