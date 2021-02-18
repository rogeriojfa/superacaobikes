package br.com.superacaobikes.admin.resources;


import br.com.superacaobikes.admin.dto.EmailDTO;
import br.com.superacaobikes.admin.security.JWTUtil;
import br.com.superacaobikes.admin.security.UserSS;
import br.com.superacaobikes.admin.services.AuthService;
import br.com.superacaobikes.admin.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    private final JWTUtil jwtUtil;
    private final AuthService authSrv;

    public AuthResource(JWTUtil jwtUtil, AuthService authSrv) {
        this.jwtUtil = jwtUtil;
        this.authSrv = authSrv;
    }

    @RequestMapping(value="/refresh_token", method= RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/forgot", method= RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
        authSrv.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
