package dev.brunopablo.ecommerce.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.brunopablo.ecommerce.controller.dto.ApiResponse;
import dev.brunopablo.ecommerce.controller.dto.CreateUserRequest;
import dev.brunopablo.ecommerce.controller.dto.PaginationRequest;
import dev.brunopablo.ecommerce.entity.UserEntity;
import dev.brunopablo.ecommerce.service.UserService;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    
    private final UserService userService;
    
    public UsuarioController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest){
        
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
                new PaginationRequest(
                    pages.getNumber(),
                    pages.getSize(),
                    pages.getTotalElements(),
                    pages.getTotalPages()
                )
            )
        );
    }
}