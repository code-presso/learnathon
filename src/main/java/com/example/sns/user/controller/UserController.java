package com.example.sns.user.controller;

import com.example.sns.user.dto.SignInDTO;
import com.example.sns.user.dto.SignUpDTO;
import com.example.sns.user.dto.UserDTO;
import com.example.sns.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> search(@RequestParam String name) {
        return ResponseEntity.ok(userService.search(name));
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        long userId = userService.signUp(signUpDTO);
        return ResponseEntity.created(URI.create("/users/" + userId + "/profile"))
                .header(SET_COOKIE, generateUidCookie(userId).toString())
                .build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserDTO> signIn(@Valid @RequestBody SignInDTO signInDTO) {
        long userId = userService.signIn(signInDTO);
        if (userId == 0) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok().header(SET_COOKIE,
                        generateUidCookie(userId).toString()).build();
    }

    private ResponseCookie generateUidCookie(long userId) {
        return ResponseCookie.from("uid",
                String.valueOf(userId)).build();
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserDTO> getProfile(@CookieValue("uid") long uid,
                                              @PathVariable Long userId) {

        if (uid != userId) {
            return ResponseEntity.status(403).build();
        }
        return userService.getProfile(userId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
