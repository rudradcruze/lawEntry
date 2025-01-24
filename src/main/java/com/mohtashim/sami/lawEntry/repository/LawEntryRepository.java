package com.mohtashim.sami.lawEntry.repository;

import com.mohtashim.sami.lawEntry.model.LawEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawEntryRepository extends CrudRepository<LawEntry, Long> {
    LawEntry getLawEntryById(long id);
}
