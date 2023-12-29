package com.example.lr14.controllers;

import org.springframework.ui.Model;
import com.example.lr14.entities.FlowerShop;
import com.example.lr14.services.FlowerShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/flowerShops")
public class FlowerShopController {

    private FlowerShopService flowerShopService;

    @Autowired
    public void setFlowerShopService(FlowerShopService flowerShopService) {
        this.flowerShopService = flowerShopService;
    }

    @GetMapping
    public String showFlowerShopsList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        model.addAttribute("currentUserName", currentUserName);

        FlowerShop flowerShop = new FlowerShop();
        model.addAttribute("flowerShops", flowerShopService.getAllFlowers());
        model.addAttribute("flowerShop", flowerShop);
        return "flowerShop";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        model.addAttribute("currentUserName", currentUserName);

        return "login";
    }

    @PostMapping("/add")
    public String addFlowerShop(@ModelAttribute(value = "flowerShop") FlowerShop flowerShop) {
        flowerShopService.add(flowerShop);
        return "redirect:/flowerShops";
    }

    @GetMapping("/show/{id}")
    public String showOneFlowerShop(Model model, @PathVariable(value = "id") Integer id) {
        FlowerShop flowerShop = flowerShopService.getById(id);
        model.addAttribute("flowerShop", flowerShop);
        return "flower-info";
    }

    @GetMapping("/delete/{id}")
    public String deleteFlowerShop(Model model, @PathVariable(value = "id") Integer id) {
        FlowerShop flowerShop = flowerShopService.getById(id);
        flowerShopService.delete(flowerShop);
        return "redirect:/flowerShops";
    }

    @GetMapping("/add")
    public String test(Model model) {
        FlowerShop flowerShop = new FlowerShop();
        model.addAttribute("flowerShops", flowerShopService.getAllFlowers());
        model.addAttribute("flowerShop", flowerShop);
        return "add-flowerShop";
    }

    @GetMapping("/filter")
    public String filterFlowerShops(Model model,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "address", required = false) String address,
                                    @RequestParam(value = "workingHours", required = false) String workingHours) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        model.addAttribute("currentUserName", currentUserName);

        FlowerShop flowerShop = new FlowerShop();
        model.addAttribute("flowerShop", flowerShop);

        model.addAttribute("flowerShops", flowerShopService.getAllFlowers(name, address, workingHours));

        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("workingHours", workingHours);

        return "flowerShop";
    }

    @GetMapping("/edit/{id}")
    public String editFlowerShop(Model model, @PathVariable(value = "id") Integer id) {
        FlowerShop flowerShop = flowerShopService.getById(id);
        model.addAttribute("flowerShop", flowerShop);
        return "edit-flowerShop";
    }

    @PostMapping("/edit/update")
    public String updateFlowerShop(@ModelAttribute(value = "flowerShop") FlowerShop updatedFlowerShop) {
        FlowerShop flowerShop = flowerShopService.getById(updatedFlowerShop.getId());
        flowerShopService.update(flowerShop, updatedFlowerShop);
        return "redirect:/flowerShops";
    }
}