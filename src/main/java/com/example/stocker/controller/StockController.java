package com.example.stocker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.stocker.domain.Stock;
import com.example.stocker.form.StockForm;
import com.example.stocker.service.StockService;

@Controller
@RequestMapping("stockList")
public class StockController {
    
    @Autowired
    private StockService stockService;

    /**
     * shopに紐付いたstock表示
     * @param shopId
     * @param model
     * @return
     */
    @GetMapping("/{shopId}")
    public String showStocks (@PathVariable("shopId") int shopId, Model model) {
        List<Stock> stockList = stockService.findByShopId(shopId);
        model.addAttribute("stockList", stockList);
        return "stock/stockList";
    }

    @GetMapping("/{id}/edit-form")
    public String showStockDetailEdit (@PathVariable("id") int id, Model model) {
        Stock stockDetailEdit = stockService.findById(id).orElseThrow();
        model.addAttribute("stockDetailEdit", stockDetailEdit);
        return "stock/stockDetailEdit";
    }

    @PostMapping("/{id}/update")
    public String stockUpdate (@PathVariable("id") int id, StockForm form, RedirectAttributes redirectAttributes) {
        Stock stock = new Stock();
        stock.setId(form.getId());
        stock.setName(form.getName());
        stock.setPrice(form.getPrice());
        stock.setQuantity(form.getQuantity());
        stock.setMemo(form.getMemo());
        stock.setBoundaryValue(form.getBoundaryValue());
        stockService.updateByStock(stock);

        redirectAttributes.addFlashAttribute("successMessage", "更新しました");
        return "redirect:/stockList/" + id + "/edit-form";
    }
}
