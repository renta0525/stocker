package com.example.stocker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.stocker.domain.Order;
import com.example.stocker.domain.Stock;
import com.example.stocker.event.PurchaseEvent;
import com.example.stocker.kafka.PurchaseProducer;
import com.example.stocker.kafka.StockNotificationProducer;
import com.example.stocker.service.OrderService;
import com.example.stocker.service.StockService;

@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PurchaseProducer purchaseProducer;

    @Autowired
    private StockNotificationProducer stockNotificationProducer;

    @GetMapping("")
    public String index (Model model) {
        List<Stock> stockList = stockService.findAll();
        model.addAttribute("stockList", stockList);
        return "item/items";
    }

    @PostMapping("/purchase")
    public String purchase (@RequestParam("itemId") int itemId,
                            @RequestParam("quantity") int quantity,
                            @RequestParam("userName") String userName,
                            Model model) {

        Stock stock = stockService.findById(itemId).orElseThrow();

        if (stock.getQuantity() < quantity) {
            model.addAttribute("error", "在庫が不足しています");
            return "item/order-error";
        }

        if (stock.getQuantity() <= stock.getBoundaryValue()) {
            String lowStockMessage = String.format("⚠️ 在庫警告：%s の在庫が %d 個になりました（閾値: %d）",
            stock.getName(), stock.getQuantity(), stock.getBoundaryValue());
            stockNotificationProducer.sendLowStockNotification(stock.getId(), lowStockMessage);
        }

        stock.setQuantity(stock.getQuantity() - quantity);
        stockService.updateByStock(stock);

        Order order = new Order();
        order.setStockId(stock.getId());
        order.setQuantity(quantity);
        order.setUserName(userName);
        order.setTotalPrice(stock.getPrice() * quantity);
        orderService.insertOrder(order);

        PurchaseEvent event = new PurchaseEvent(stock.getId(), stock.getName(), userName, quantity);
        purchaseProducer.send(event);

        return "item/order-complete";
    }
}
