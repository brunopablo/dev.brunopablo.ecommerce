package dev.brunopablo.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.brunopablo.ecommerce.controller.dto.CreateUserRequest;
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
    
    public UserEntity createUser(CreateUserRequest createUserRequest){
        
        var billingAddresEntity = billingAddressRepository.save(getBillingAddresEntity(createUserRequest));
        
        return userRepository.save(getUserEntity(createUserRequest, billingAddresEntity));
        
    }

    private UserEntity getUserEntity(CreateUserRequest createUserRequest, BillingAddressEntity billingAddresEntity) {

        var user = new UserEntity();

        user.setName(createUserRequest.name());
        
        user.setBilling_address(billingAddresEntity);

        return user; 
    }
    
    private BillingAddressEntity getBillingAddresEntity(CreateUserRequest createUserRequest) {

        var billingAddres = new BillingAddressEntity();
        
        billingAddres.setAddress(createUserRequest.address());
        
        billingAddres.setNumber(createUserRequest.number());
        
        billingAddres.setComplement(createUserRequest.complement());

        return billingAddres;
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
}
