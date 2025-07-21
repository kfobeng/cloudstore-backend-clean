package com.cloudstore.service;

import com.cloudstore.model.User;
import com.cloudstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StorageUpgradeService {

    private final UserRepository userRepository;

    public StorageUpgradeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean upgradeStorage(String email, Long bonusBytes) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Long currentLimit = user.getStorageLimit();
            user.setStorageLimit(currentLimit + bonusBytes);
            userRepository.save(user);
            return true;
        }

        return false;
    }
}
