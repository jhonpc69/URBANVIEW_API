package com.utp.piuratours.Dto.login;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginDto {

    private Boolean status;
    private String message;
}
