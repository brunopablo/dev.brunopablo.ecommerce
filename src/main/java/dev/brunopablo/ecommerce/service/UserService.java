package dev.brunopablo.ecommerce.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.brunopablo.ecommerce.controller.dto.CreateOrUpdateUserRequest;
import dev.brunopablo.ecommerce.entity.BillingAddressEntity;
import dev.brunopablo.ecommerce.entity.UserEntity;
import dev.brunopablo.ecommerce.repository.BillingAddressRepository;
import dev.brunopablo.ecommerce.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    private final BillingAddressRepository billingAddressRepository;
    
    public UserService(UserRepository userRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
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

        Direction orderByDirection = Sort.Direction.DESC;

        if(orderBy.equalsIgnoreCase("asc"))
            orderByDirection = Sort.Direction.ASC;

        return PageRequest.of(pageNumber, pageSize, orderByDirection, "name");
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
