package uz.company.librarymanagamentservice.users.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.company.librarymanagamentservice.book.service.AuthorService;
import uz.company.librarymanagamentservice.exceptionHandler.AppBadException;
import uz.company.librarymanagamentservice.users.dto.UserDTO;
import uz.company.librarymanagamentservice.users.entity.UserEntity;
import uz.company.librarymanagamentservice.users.repository.UserRepository;
import uz.company.librarymanagamentservice.util.SpringSecurityUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private UserRepository userRepository;

    public UserDTO addUser(UserDTO userDTO) {
        Integer currentUserId = SpringSecurityUtil.getCurrentUserId();
        Optional<UserEntity> byPhone = userRepository.findByPhoneAndVisibleTrue(userDTO.getPhone());
        Optional<UserEntity> byEmail = userRepository.findByEmailAndVisibleTrue(userDTO.getEmail());
        if (byEmail.isPresent()) {throw new AppBadException("Email already exists");
        }else if (byPhone.isPresent()) {throw new AppBadException("Phone already exists");
        }else {
            logger.info("Adding user: " + userDTO.getEmail());
            UserEntity userEntity = new UserEntity();
            userEntity.setPhone(userDTO.getPhone());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setName(userDTO.getName());
            userEntity.setCreatedById(currentUserId);
            userEntity.setMembershipDate(LocalDate.now());
            userEntity.setVisible(Boolean.TRUE);
            userRepository.save(userEntity);
            userDTO.setId(userEntity.getId());
            return userDTO;
        }
    }
    public List<UserDTO> getAllUsers() {
        logger.info("Get all users");
        List<UserEntity> users = userRepository.getAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : users) {
            userDTOs.add(toDTO(userEntity));
        }
        return userDTOs;
    }
    public UserDTO getUserById(Integer userId) {
        logger.info("Get user by id: {}", userId);
        Optional<UserEntity> byId = userRepository.findByIdAndVisibleTrue(userId);
        if (byId.isPresent()) {
            return toDTO(byId.get());
        }else logger.error("User not found{}", userId); throw new AppBadException("User not found");
    }

    public UserDTO update(Integer userId, UserDTO userDTO) {
        Optional<UserEntity> byId = userRepository.findByIdAndVisibleTrue(userId);
        if (byId.isPresent()) {
            if (byId.get().getCreatedById().equals(SpringSecurityUtil.getCurrentUserId())){
                byId.get().setName(userDTO.getName());
                byId.get().setPhone(userDTO.getPhone());
                byId.get().setEmail(userDTO.getEmail());
                byId.get().setCreatedById(SpringSecurityUtil.getCurrentUserId());
                byId.get().setMembershipDate(LocalDate.now());
                userRepository.save(byId.get());
                userDTO.setId(byId.get().getId());
                return userDTO;
            }else throw new AppBadException("The person who created the user can change it.");
        }else throw new AppBadException("User not found");
    }
    public Boolean delete(Integer userId) {
        Optional<UserEntity> byId = userRepository.findByIdAndVisibleTrue(userId);
        if (byId.isPresent()) {
            if (byId.get().getCreatedById().equals(SpringSecurityUtil.getCurrentUserId())) {
                byId.get().setVisible(Boolean.FALSE);
                userRepository.save(byId.get());
                return true;
            }else throw new AppBadException("The person who created the user can change it.");
        }else throw new AppBadException("User not found");
    }


    private UserDTO toDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setName(userEntity.getName());
        return userDTO;
    }
}
