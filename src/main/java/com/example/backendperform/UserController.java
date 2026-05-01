package com.example.backendperform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // เปลี่ยนจาก Create เป็น Create or Update (Upsert)
    @PostMapping
    public User createOrUpdateUser(@RequestBody User user) {
        // เช็คก่อนว่ามี UID นี้ในระบบหรือยัง
        User existingUser = userRepository.findByFirebaseUid(user.getFirebaseUid());
        
        if (existingUser != null) {
            // ถ้ามีแล้ว ให้อัปเดตข้อมูลแทนการสร้างใหม่
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setAcademicYear(user.getAcademicYear()); // เซฟชั้นปี
            
            if (user.getRole() != null) {
                existingUser.setRole(user.getRole());
            }
            return userRepository.save(existingUser);
        }
        
        return userRepository.save(user);
    }

    @GetMapping("/uid/{uid}")
    public User getUserByUid(@PathVariable String uid) {
        return userRepository.findByFirebaseUid(uid);
    }
}