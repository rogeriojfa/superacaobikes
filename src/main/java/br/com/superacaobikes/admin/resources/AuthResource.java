package br.com.superacaobikes.admin.resources;


import br.com.superacaobikes.admin.security.JWTUtil;
import br.com.superacaobikes.admin.security.UserSS;
import br.com.superacaobikes.admin.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    private final JWTUtil jwtUtil;

    public AuthResource(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value="/refresh_token", method= RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
