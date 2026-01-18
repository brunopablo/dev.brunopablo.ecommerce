package dev.brunopablo.ecommerce.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.brunopablo.ecommerce.controller.dto.createOrUpdateUserRequest.CreateOrUpdateUserRequest;
import dev.brunopablo.ecommerce.entity.BillingAddressEntity;
import dev.brunopablo.ecommerce.entity.UserEntity;
import dev.brunopablo.ecommerce.repository.BillingAddressRepository;
import dev.brunopablo.ecommerce.repository.UserRepository;
import dev.brunopablo.ecommerce.util.Utils;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    private final BillingAddressRepository billingAddressRepository;

    private final Utils utils;
    
    public UserService(UserRepository userRepository, BillingAddressRepository billingAddressRepository, Utils utils) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
        this.utils = utils;
    }
    
    public UserEntity createUser(CreateOrUpdateUserRequest createUserRequest){
        
        var billingAddresEntity = billingAddressRepository.save(getBillingAddresEntity(createUserRequest));
        
        return userRepository.save(getUserEntity(createUserRequest, billingAddresEntity));
        
    }

    private UserEntity getUserEntity(CreateOrUpdateUserRequest createUserRequest, BillingAddressEntity billingAddresEntity) {

        var user = new UserEntity();

        user.setName(createUserRequest.name());
        
        user.setBilling_address(billingAddresEntity);

        return user; 
    }
    
    private BillingAddressEntity getBillingAddresEntity(CreateOrUpdateUserRequest createUserRequest) {

        var billingAddres = new BillingAddressEntity();
        
        billingAddres.setAddress(createUserRequest.address());
        
        billingAddres.setNumber(createUserRequest.number());
        
        billingAddres.setComplement(createUserRequest.complement());

        return billingAddres;
    }


    public Optional<UserEntity> updateUserById(Long userId, CreateOrUpdateUserRequest updateUserRequest){

        var userEntity = userRepository.findById(userId);

        if(userEntity.isPresent()){
            userEntity.get().getBilling_address().setNumber(updateUserRequest.number());
            userEntity.get().getBilling_address().setAddress(updateUserRequest.address());
            userEntity.get().getBilling_address().setComplement(updateUserRequest.complement());
            userEntity.get().setName(updateUserRequest.name());

            userRepository.save(userEntity.get());
        }

        return userEntity;
    }

    public Page<UserEntity> listUsers(Integer pageNumber, Integer pageSize, String orderBy) {
        
        var pageRequest = getPageRequest(pageNumber, pageSize, orderBy);

        var pages = userRepository.findAll(pageRequest);

        return pages;
    }

    private PageRequest getPageRequest(Integer pageNumber, Integer pageSize, String orderBy) {

        return utils.makePageRequest(pageNumber, pageSize, orderBy, "name");
    }

    public boolean  deleteUserById(Long userId) {
        
        var userExists = userRepository.existsById(userId);

        if(userExists){
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }
}
