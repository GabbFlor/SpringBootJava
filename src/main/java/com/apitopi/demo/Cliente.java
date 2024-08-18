package com.apitopi.demo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String nome;

    // Faz com que o Setter do lombok não funcione (criando ele manualmente)
    @Setter(AccessLevel.NONE)
    private String email;

    // verifica se tem o @, se não, fica null
    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        }
    }
}
