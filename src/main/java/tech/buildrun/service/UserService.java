package tech.buildrun.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import tech.buildrun.Exception.UserNotFoundException;
import tech.entity.UserEntity;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public UserEntity createUser(UserEntity userEntity){
        UserEntity.persist(userEntity);
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize){
        return UserEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(UUID userId) {
        return (UserEntity) UserEntity.findByIdOptional(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
    }

    public UserEntity updateUser(UUID userId, UserEntity userEntity) {
        var user = findById(userId);
        user.username = userEntity.username;
        return user;
    }

    @Transactional
    public String deleteById(UUID userId) {
        var user = findById(userId);
        user.delete();
        return "Usuário " + userId + " deletado com sucesso";
    }

}
