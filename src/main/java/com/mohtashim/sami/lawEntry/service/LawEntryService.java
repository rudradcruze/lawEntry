package com.mohtashim.sami.lawEntry.service;

import com.mohtashim.sami.lawEntry.model.LawEntry;
import com.mohtashim.sami.lawEntry.repository.LawEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawEntryService {

    @Autowired
    private LawEntryRepository lawEntryRepository;

    public List<LawEntry> getAll() {
        return (List<LawEntry>) lawEntryRepository.findAll();
    }

    public LawEntry get(long id) {
        return lawEntryRepository.getLawEntryById(id);
    }

    public void save(LawEntry lawEntry) {
        lawEntryRepository.save(lawEntry);
    }

    public void delete(long id) {
        lawEntryRepository.delete(get(id));
    }

}
