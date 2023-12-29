package com.example.lr14.repositories;

import com.example.lr14.entities.FlowerShop;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface FlowerShopRepository extends JpaRepository<FlowerShop, Integer> {

}