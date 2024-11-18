package br.com.chargetech.chargetechmvc.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("O formulário de cadastro de usuários deveria retornar o código http 200 (OK) ao receber uma requisição GET")
    void exibirCadastroDeUsuario() throws Exception {
        mockMvc.perform(get("/usuario/cadastrar"))
                .andExpect(status().isOk());
    }
}