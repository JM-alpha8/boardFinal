package com.example.board2.web.view;

import com.example.board2.domain.User;
import com.example.board2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// 로그인 사용자 표시용 전역 모델
@ControllerAdvice
@RequiredArgsConstructor
public class CurrentUserAdvice {
    private final UserRepository userRepository;

    @ModelAttribute("displayName")
    public String displayName(Authentication auth) {
        if (auth == null) return null;
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .map(User::getName)
                .filter(n -> !n.isBlank())
                .orElse(email); //
    }
}

