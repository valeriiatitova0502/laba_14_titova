package com.example.lr14.services;

import com.example.lr14.entities.FlowerShop;
import com.example.lr14.repositories.FlowerShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerShopService {

    private final FlowerShopRepository repository;

    @Autowired
    public FlowerShopService(FlowerShopRepository repository) {
        this.repository = repository;
    }

    public FlowerShop getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<FlowerShop> getAllFlowers() {
        return repository.findAll();
    }

    public List<FlowerShop> getAllFlowers(String name, String address, String workingHours) { // Изменено название метода и параметра
        return repository.findAll().stream()
                .filter(o -> name.isBlank() || o.getName().contains(name))
                .filter(o -> address.isBlank() || o.getAddress().contains(address))
                .filter(o -> workingHours.isBlank() || o.getWorkingHours().contains(workingHours)) // Изменено на getWorkingHours
                .collect(Collectors.toList());
    }

    private boolean isTimeInRange(String workingHours, String time) {
    if (time == null || time.isBlank()) {
            return false;
        }
        String[] hours = workingHours.split("-");
        if (hours.length != 2) {
            return false;
        }
        try {
            int startTime = Integer.parseInt(hours[0].trim());
            int endTime = Integer.parseInt(hours[1].trim());
            int workingTime = Integer.parseInt(time.trim());
            return startTime <= workingTime && endTime >= workingTime;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void add(FlowerShop flowerShop) {
        repository.save(flowerShop);
    }

    public void delete(FlowerShop flowerShop) {
        repository.delete(flowerShop);
    }

    public void update(FlowerShop exist, FlowerShop updated) {
        if (!updated.getName().isBlank()) exist.setName(updated.getName());
        if (!updated.getAddress().isBlank()) exist.setAddress(updated.getAddress());
        if (!updated.getPhone().isBlank()) exist.setPhone(updated.getPhone());
        if (!updated.getWorkingHours().isBlank()) exist.setWorkingHours(updated.getWorkingHours());
        repository.save(exist);
    }
}
