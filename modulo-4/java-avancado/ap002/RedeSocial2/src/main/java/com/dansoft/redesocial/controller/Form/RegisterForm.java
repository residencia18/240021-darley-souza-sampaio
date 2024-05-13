package com.dansoft.redesocial.controller.Form;

import com.dansoft.redesocial.model.UsuarioLoginRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterForm(
        @NotEmpty(message = "O login não deve ser vazio")
        @NotNull(message = "O login não deve ser nulo")
        @Size(min = 5, message = "O login deve ter no mínimo 5 caracteres")
        String login,

        @NotEmpty(message = "O email não deve ser vazio")
        @NotNull(message = "O email não deve ser nulo")
        @Email(regexp = ".+[@].+[\\.].+", message = "Email inválido")
        String email,

        @NotEmpty(message = "A senha não deve ser vazia")
        @NotNull(message = "A senha não deve ser nula")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @Pattern.List({
            @Pattern(regexp = ".*[a-z].*", message = "A senha deve conter pelo menos uma letra minúscula"),
            @Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos uma letra maiúscula"),
            @Pattern(regexp = ".*\\d.*", message = "A senha deve conter pelo menos um dígito"),
            @Pattern(regexp = ".*[@#$%^&+=.!].*", message = "A senha deve conter pelo menos um caractere especial")
        })
        String senha,

        @NotNull(message = "A role não deve ser nula")
        UsuarioLoginRole role
) {
}
