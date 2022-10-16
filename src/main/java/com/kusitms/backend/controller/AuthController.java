package com.kusitms.backend.controller;

import com.kusitms.backend.dto.AuthDto;
import com.kusitms.backend.dto.SignInRequest;
import com.kusitms.backend.response.BaseResponse;
import com.kusitms.backend.service.AuthService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/sign-in")
  public ResponseEntity<BaseResponse> signIn(@RequestBody @Validated SignInRequest request,
      HttpServletResponse response) {
    String accessToken = authService.signIn(request);
    response.addHeader("Authorization", "Bearer " + accessToken);
    return ResponseEntity.ok(BaseResponse.builder().message("로그인에 성공하셨습니다.").build());
  }

  //회원가입
  @PostMapping("/sign-up")
  public ResponseEntity<BaseResponse> signUp(@Valid @RequestBody AuthDto.Request request) {

    authService.signUp(request);

    return ResponseEntity.ok(BaseResponse.builder()
        .message("회원가입에 성공하셨습니다.").build());
  }

  //닉네임 중복 확인
  @GetMapping("/checkNickname/{nickname}")
  public ResponseEntity<BaseResponse> checkNicknameDuplicate(@PathVariable String nickname) {
    authService.checkNicknameDuplication(nickname);
    return ResponseEntity.ok(BaseResponse.builder().message("회원탈퇴에 성공하셨습니다.").build());
  }

  //회원 탈퇴
  @DeleteMapping("/withdrawal/{userId}")
  public ResponseEntity<BaseResponse> withdrawal(@PathVariable Long userId) {
    //현재 로그인한 회원의 userId 받아오도록 해야하는데 일단 매개변수로
    authService.withdrawal(userId);

    return ResponseEntity.ok(BaseResponse.builder()
        .message("회원 탈퇴에 성공하셨습니다.").build());
  }

}
