package site.metacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.dto.ResponseDto;
import site.metacoding.white.dto.UserReqDto.JoinReqDto;
import site.metacoding.white.dto.UserReqDto.UserUpdateReqDto;
import site.metacoding.white.dto.UserRespDto.JoinRespDto;
import site.metacoding.white.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    // 회원정보 전체 보기
    @GetMapping("/user")
    public ResponseDto<?> findAll() {
        return new ResponseDto<>(1, "ok", userService.findAll());
    }

    // 회원정보 수정
    @PutMapping("/user/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody UserUpdateReqDto userUpdateReqDto) {
        userUpdateReqDto.setId(id);
        return new ResponseDto<>(1, " 성공", userService.update(userUpdateReqDto));
    }

    // 회원정보 보기
    @GetMapping("/user/{id}")
    public ResponseDto<?> findById(@PathVariable Long id) {
        return new ResponseDto<>(1, "ok", userService.findById(id));
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseDto<?> save(@RequestBody JoinReqDto joinReqDto) {
        JoinRespDto joinRespDto = userService.save(joinReqDto);
        return new ResponseDto<>(1, "ok", joinRespDto);
    }

    // @PostMapping("/login")
    // public ResponseDto<?> login(@RequestBody LoginReqDto loginReqDto) {
    // SessionUser sessionUser = userService.login(loginReqDto);
    // session.setAttribute("sessionUser", sessionUser);
    // return new ResponseDto<>(1, "ok", sessionUser);
    // }

}