package com.example.client.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query(
        value = "SELECT * FROM chat WHERE user_id = ? order by chat_id desc", 
       nativeQuery = true)
       List<Chat> findAll2(String param);
}