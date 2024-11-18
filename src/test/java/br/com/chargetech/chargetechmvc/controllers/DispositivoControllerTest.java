package br.com.chargetech.chargetechmvc.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DispositivoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("O formulário de cadastro de dispositivos deveria retornar o código http 400 (Bad Request) ao receber uma requisição POST com o 'nome' vazio")
    void cadastrarDispositivo() throws Exception {
        mockMvc.perform(post("/dispositivo/cadastrar")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .param("nome", "")
                        .param("consumoMedio", "10")
                        .param("ambiente", "Sala")
                        .with(user("usuario-test@email.com").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("dispositivo", "nome"));
    }

    @Test
    @DisplayName("A lista de dispositivos deveria retornar o código http 200 (OK) ao receber uma requisição GET")
    void exibirDispositivos() throws Exception {
        mockMvc.perform(get("/dispositivo"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("O formulário de cadastro de dispositivos deveria retornar o código http 200 (OK) ao receber uma requisição GET")
    void exibirFormularioCadastrar() throws Exception {
        mockMvc.perform(get("/dispositivo/cadastrar"))
                .andExpect(status().isOk());
    }
}