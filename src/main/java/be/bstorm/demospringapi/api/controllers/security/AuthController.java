package be.bstorm.demospringapi.api.controllers.security;

import be.bstorm.demospringapi.api.models.security.dtos.UserDTO;
import be.bstorm.demospringapi.api.models.security.dtos.UserTokenDTO;
import be.bstorm.demospringapi.api.models.security.forms.LoginForm;
import be.bstorm.demospringapi.api.models.security.forms.RegisterForm;
import be.bstorm.demospringapi.bll.services.security.AuthService;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.il.utils.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterForm form
            ) {
        //Todo Gestion d'exception cleaner BindingResult
        authService.register(form.toUser());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(
            @Valid @RequestBody LoginForm form
    ) {
        //Todo Gestion d'exception cleaner BindingResult
        User user = authService.login(form.email(), form.password());
        UserDTO userDTO = UserDTO.fromUser(user);
        String token = jwtUtil.generateToken(user);
        UserTokenDTO userTokenDTO = new UserTokenDTO(userDTO, token);
        return ResponseEntity.ok(userTokenDTO);
    }
}
