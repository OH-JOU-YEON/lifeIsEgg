package com.lifeEgg.login.google;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleInfResponse {
    private String scope;
    private String email;
    private String email_verified;
}