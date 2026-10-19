package com.utp.piuratours.Controller.prueba;

import com.utp.piuratours.Dto.Token.TokenResponse;
import com.utp.piuratours.Models.Usuarios;
import com.utp.piuratours.Models.VerificationCode;
import com.utp.piuratours.Repository.UsuariosRepository;
import com.utp.piuratours.Repository.VerificationCodeRepository;
import com.utp.piuratours.Services.AuthService;
import com.utp.piuratours.Services.emailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AuthController2Test {
    final AuthService auth = mock(AuthService.class);
    final AuthenticationManager manager = mock(AuthenticationManager.class);
    final UsuariosRepository users = mock(UsuariosRepository.class);
    final VerificationCodeRepository codes = mock(VerificationCodeRepository.class);
    final emailService mail = mock(emailService.class);
    final AuthController2 controller = new AuthController2(auth, manager, users, mail, codes);
    final Usuarios user = new Usuarios();
    final VerificationCode code = new VerificationCode();

    @BeforeEach void setup() {
        user.setCorreo("demo@urbanview.test");
        code.setUsuario(user);
        code.setCode("123456");
        code.setExpirationTime(LocalDateTime.now().plusMinutes(10));
        when(users.getUsuariosByCorreo(user.getCorreo())).thenReturn(Optional.of(user));
        when(codes.findByUsuario(user)).thenReturn(Optional.of(code));
    }

    @Test void reusesExpiredRowInsteadOfInsertingDuplicateUserCode() {
        code.setExpirationTime(LocalDateTime.now().minusMinutes(1));
        controller.authenticate2(user.getCorreo(), "password");
        verify(codes).save(same(code));
        verify(mail).sendVerificationCode(eq(user.getCorreo()), matches("[1-9][0-9]{5}"));
        assertTrue(code.getExpirationTime().isAfter(LocalDateTime.now()));
    }

    @Test void activeCodeRemainsUsableWhenLoginIsRetried() {
        assertTrue(controller.authenticate2(user.getCorreo(), "password").getBody().getStatus());
        verify(mail).sendVerificationCode(user.getCorreo(), "123456");
        assertEquals("123456", code.getCode());
    }

    @Test void rejectsExpiredWrongAndUsedCodes() {
        assertThrows(ResponseStatusException.class, () -> controller.verifyCode(user.getCorreo(), "999999", "password"));
        code.setExpirationTime(LocalDateTime.now().minusSeconds(1));
        assertThrows(ResponseStatusException.class, () -> controller.verifyCode(user.getCorreo(), "123456", "password"));
        code.setExpirationTime(LocalDateTime.now().plusMinutes(10));
        code.setCode("0");
        assertThrows(ResponseStatusException.class, () -> controller.verifyCode(user.getCorreo(), "0", "password"));
        verifyNoInteractions(auth);
    }

    @Test void consumesCodeOnlyAfterAuthenticationAndRejectsReplay() {
        TokenResponse tokens = new TokenResponse("access", "refresh", null, true);
        when(auth.authenticate(user.getCorreo(), "password")).thenReturn(tokens);
        assertSame(tokens, controller.verifyCode(user.getCorreo(), "123456", "password").getBody());
        assertEquals("0", code.getCode());
        assertThrows(ResponseStatusException.class, () -> controller.verifyCode(user.getCorreo(), "123456", "password"));
        verify(auth, times(1)).authenticate(user.getCorreo(), "password");
    }
}
