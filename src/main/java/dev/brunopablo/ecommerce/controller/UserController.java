package dev.brunopablo.ecommerce.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.brunopablo.ecommerce.controller.dto.apiResponse.ApiResponse;
import dev.brunopablo.ecommerce.controller.dto.createOrUpdateUserRequest.CreateOrUpdateUserRequest;
import dev.brunopablo.ecommerce.entity.UserEntity;
import dev.brunopablo.ecommerce.service.UserService;
import dev.brunopablo.ecommerce.util.Utils;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    private final Utils utils;
    
    public UserController(UserService userService, Utils utils) {
        this.userService = userService;
        this.utils = utils;
    }

    @PostMapping
    public ResponseEntity<String> createUser(
        @RequestBody CreateOrUpdateUserRequest createUserRequest
    ){        
        var userEntity = userService.createUser(createUserRequest);
        
        return ResponseEntity.created(URI.create("/users/" + userEntity.getId())).build();
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<UserEntity>> listUsers(
        @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
        @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
        @RequestParam(name="orderBy", defaultValue="desc") String orderBy
    ){
        var pages = userService.listUsers(pageNumber, pageSize, orderBy);
        
        return ResponseEntity.ok().body(
            new ApiResponse<>(
                pages.getContent(),
                utils.makePaginationInfoResponse(pages.getNumber(),
                                                 pages.getSize(),
                                                 pages.getTotalElements(),
                                                 pages.getTotalPages()
                )
            )
        );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(
        @PathVariable Long userId,
        CreateOrUpdateUserRequest updateUserRequest){

        var userExists = userService.updateUserById(userId, updateUserRequest);

        return userExists.isPresent() 
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId){

        return userService.deleteUserById(userId) 
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}