package com.example.stocker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.stocker.domain.MessageHistory;
import com.example.stocker.form.HistoryForm;
import com.example.stocker.service.MessageHistoryService;

@Controller
@RequestMapping("/messages")
public class MessageHistoryController {
    
    @Autowired
    private MessageHistoryService messageHistoryService;

    /**
     * 通知履歴を表示する
     * @param model
     * @return
     */
    @GetMapping("")
    public String index (Model model) {
        model.addAttribute("historyForm", new HistoryForm());
        List<MessageHistory> messageHistoryList = messageHistoryService.findAllMessageHistory();
        model.addAttribute("messageHistoryList", messageHistoryList);
        return "history/history-list";
    }

    @GetMapping("/search")
    public String searchHistory (@ModelAttribute HistoryForm form, Model model) {
        List<MessageHistory> messageHistoryList  = messageHistoryService.search(form);
        model.addAttribute("messageHistoryList", messageHistoryList);
        model.addAttribute("historyForm", form);
        return "history/history-list";
    }
}
