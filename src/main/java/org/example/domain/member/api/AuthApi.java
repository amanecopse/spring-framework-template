package org.example.domain.member.api;

import lombok.RequiredArgsConstructor;
import org.example.domain.board.dto.SampleResponse;
import org.example.domain.member.application.AuthService;
import org.example.domain.member.dto.LoginRequest;
import org.example.domain.member.dto.SignupRequest;
import org.example.global.common.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@RequestBody @Validated SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<String>> authenticate(@RequestBody @Validated LoginRequest request) {
        String result = authService.authenticate(request);
        return ResponseEntity.ok().body(ApiResponse.success(result));
    }
}
