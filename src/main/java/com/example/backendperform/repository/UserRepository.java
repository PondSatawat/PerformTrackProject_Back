package com.example.backendperform.repository;

import com.example.backendperform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // คำสั่งสำหรับค้นหา User ด้วย UID
    User findByFirebaseUid(String firebaseUid);
}