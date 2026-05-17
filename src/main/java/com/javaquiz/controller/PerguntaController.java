package com.javaquiz.controller;

import com.javaquiz.dto.PerguntaDTO;
import com.javaquiz.dto.RespostaDTO;
import com.javaquiz.model.enums.Nivel;
import com.javaquiz.model.enums.TipoPergunta;
import com.javaquiz.service.PerguntaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perguntas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PerguntaController {

    private final PerguntaService perguntaService;

    // GET /api/perguntas → lista todas (modo quiz, sem gabarito)
    @GetMapping
    public ResponseEntity<List<PerguntaDTO.QuizResponse>> listarTodas() {
        return ResponseEntity.ok(perguntaService.listarParaQuiz());
    }

    // GET /api/perguntas/admin → lista todas com gabarito
    @GetMapping("/admin")
    public ResponseEntity<List<PerguntaDTO.Response>> listarAdmin() {
        return ResponseEntity.ok(perguntaService.listarTodas());
    }

    // GET /api/perguntas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PerguntaDTO.QuizResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(perguntaService.buscarPorId(id));
    }

    // GET /api/perguntas/nivel/{nivel} → ex: INICIANTE, INTERMEDIARIO, AVANCADO
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<PerguntaDTO.QuizResponse>> listarPorNivel(@PathVariable Nivel nivel) {
        return ResponseEntity.ok(perguntaService.listarPorNivel(nivel));
    }

    // GET /api/perguntas/tipo/{tipo} → ex: MULTIPLA_ESCOLHA, VERDADEIRO_FALSO, COMPLETAR_CODIGO
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PerguntaDTO.QuizResponse>> listarPorTipo(@PathVariable TipoPergunta tipo) {
        return ResponseEntity.ok(perguntaService.listarPorTipo(tipo));
    }

    // GET /api/perguntas/categoria/{categoria}
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<PerguntaDTO.QuizResponse>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(perguntaService.listarPorCategoria(categoria.toUpperCase()));
    }

    // GET /api/perguntas/categorias → lista todas as categorias disponíveis
    @GetMapping("/categorias")
    public ResponseEntity<List<String>> listarCategorias() {
        return ResponseEntity.ok(perguntaService.listarCategorias());
    }

    // POST /api/perguntas → criar nova pergunta
    @PostMapping
    public ResponseEntity<PerguntaDTO.Response> criar(@Valid @RequestBody PerguntaDTO.Request dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(perguntaService.criar(dto));
    }

    // PUT /api/perguntas/{id} → atualizar pergunta
    @PutMapping("/{id}")
    public ResponseEntity<PerguntaDTO.Response> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PerguntaDTO.Request dto) {
        return ResponseEntity.ok(perguntaService.atualizar(id, dto));
    }

    // DELETE /api/perguntas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        perguntaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/perguntas/{id}/responder → verificar resposta
    @PostMapping("/{id}/responder")
    public ResponseEntity<RespostaDTO.Response> verificarResposta(
            @PathVariable Long id,
            @Valid @RequestBody RespostaDTO.Request dto) {
        return ResponseEntity.ok(perguntaService.verificarResposta(id, dto.getOpcaoId()));
    }
}
