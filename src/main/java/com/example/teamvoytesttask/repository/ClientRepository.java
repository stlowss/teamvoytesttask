package com.example.teamvoytesttask.repository;

import com.example.teamvoytesttask.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
