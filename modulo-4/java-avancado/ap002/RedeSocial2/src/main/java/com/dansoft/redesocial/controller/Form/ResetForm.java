package com.dansoft.redesocial.controller.Form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ResetForm(
		@NotEmpty(message = "A senha não deve ser vazia") @NotNull(message = "A senha não deve ser nula") @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres") @Pattern(regexp = ".*[a-z].*", message = "A senha deve conter pelo menos uma letra minúscula") @Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos uma letra maiúscula") @Pattern(regexp = ".*\\d.*", message = "A senha deve conter pelo menos um dígito") @Pattern(regexp = ".*[@#$%^&+=.!].*", message = "A senha deve conter pelo menos um caractere especial") String senha) {

}
