package com.example.backendperform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // สำหรับบันทึก User ใหม่ตอนสมัครสมาชิก
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // สำหรับดึงข้อมูล Role ตอน Login
    @GetMapping("/uid/{uid}")
    public User getUserByUid(@PathVariable String uid) {
        return userRepository.findByFirebaseUid(uid);
    }
}