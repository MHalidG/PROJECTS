/*package libdirector.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import libdirector.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJWTController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<LResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        userService.register(registerRequest);

        LResponse response = new LResponse();
        response.setMessage(ResponseMessages.REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
*/