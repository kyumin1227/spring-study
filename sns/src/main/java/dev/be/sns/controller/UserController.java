package dev.be.sns.controller;

import dev.be.sns.controller.request.UserJoinRequest;
import dev.be.sns.controller.request.UserLoginRequest;
import dev.be.sns.controller.response.Response;
import dev.be.sns.controller.response.UserJoinResponse;
import dev.be.sns.controller.response.UserLoginResponse;
import dev.be.sns.model.User;
import dev.be.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    TODO : implement
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        User join = userService.join(request.getUserName(), request.getPassword());
        return Response.success(UserJoinResponse.fromUser(join));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getUserName(), request.getPassword());
        return Response.success(new UserLoginResponse(token));
    }
}
