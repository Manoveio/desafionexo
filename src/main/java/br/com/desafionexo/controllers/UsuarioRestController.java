package br.com.desafionexo.controllers;


import br.com.desafionexo.model.AccountCredentialsVO;
import br.com.desafionexo.model.ResponseCredencialsVO;
import br.com.desafionexo.model.TokenVO;
import br.com.desafionexo.security.JWTTokenProvider;
import br.com.desafionexo.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioRestController {


    @Autowired
    private JWTTokenProvider jwtTokenProvider;


    @Autowired
    private UsuarioService userService;

    @GetMapping("/login")
    @ApiOperation(value = "${UsuarioRestController.login}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseCredencialsVO login(
                        @ApiParam("username") @RequestParam String username,
                        @ApiParam("password") @RequestParam String password) {

        String token = userService.signin(username, password);

        TokenVO tokenVO = new TokenVO(jwtTokenProvider.getType(token), token, jwtTokenProvider.getExpirationDate(token));
        AccountCredentialsVO accountCredentialsVO = new AccountCredentialsVO(username,  password);
        ResponseCredencialsVO responseCredencialsVO = new ResponseCredencialsVO(accountCredentialsVO, tokenVO);

        return responseCredencialsVO;
    }

}
