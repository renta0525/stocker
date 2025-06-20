package com.example.stocker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocker.domain.MessageHistory;
import com.example.stocker.form.HistoryForm;
import com.example.stocker.repository.MessageHistoryRepository;

@Service
public class MessageHistoryService {
    
    @Autowired
    private MessageHistoryRepository messageHistoryRepository;

    public List<MessageHistory> findAllMessageHistory() {
        return messageHistoryRepository.findAllMessageHistory();
    }

    public List<MessageHistory> search(HistoryForm form) {
    return messageHistoryRepository.searchHistory(form);
    }
}
