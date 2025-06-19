package com.example.stocker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.stocker.domain.Shop;
import com.example.stocker.domain.User;
import com.example.stocker.form.ShopForm;
import com.example.stocker.repository.UserRepository;
import com.example.stocker.service.ShopService;


@Controller
@RequestMapping("/list")
public class ShopController {
    
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserRepository userRepository;

    /**
     * adminなら全件取得、userなら紐付いたshop一覧表示
     * @param model
     * @return
     */
    @GetMapping("/shops")
    public String shoplist (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mail = auth.getName();

        User loginUser = userRepository.findByMail(mail).orElseThrow();

        List<Shop> shoplist;
        if (loginUser.isAdminFlg()) {
            shoplist = shopService.shopList();
        } else {
            shoplist = shopService.shopListByUserId(loginUser.getId());
        }

        model.addAttribute("shoplist", shoplist);
        return "shop/shopList";
    }

    /**
     * 編集画面に遷移
     */
    @GetMapping("/shop/{id}/edit-form")
    public String showShopDetailEdit (@PathVariable("id") int id, Model model) {
        Shop shopDetailEdit = shopService.findById(id).orElseThrow();
        model.addAttribute("shopDetailEdit", shopDetailEdit);
        return "shop/shopDetailEdit";
    }

    /**
     * shopの編集
     * @param shop
     * @return
     */
    @PostMapping("/shop/{id}/update")
    public String shopUpdate (@PathVariable("id") int id, ShopForm form, RedirectAttributes redirectAttributes) {
        Shop shop = new Shop();
        shop.setId(form.getId());
        shop.setName(form.getName());
        shop.setImagePath(form.getImagePath());
        shopService.updateByShop(shop);

        redirectAttributes.addFlashAttribute("successMessage", "更新しました");
        return "redirect:/list/shop/" + id + "/edit-form";
    }

    /**
     * shopの削除
     * @param id
     * @return
     */
    @PostMapping("/shop/{id}/delete") 
    public String shopDelete (@PathVariable("id") int id) {
        shopService.deleteByShop(id);
        return "redirect:/shop/shopList";
    }
}
