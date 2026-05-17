package com.javaquiz.service;

import com.javaquiz.dto.OpcaoDTO;
import com.javaquiz.dto.PerguntaDTO;
import com.javaquiz.dto.RespostaDTO;
import com.javaquiz.model.Opcao;
import com.javaquiz.model.Pergunta;
import com.javaquiz.model.enums.Nivel;
import com.javaquiz.model.enums.TipoPergunta;
import com.javaquiz.repository.PerguntaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;

    // Listar todas as perguntas (modo admin - mostra gabarito)
    public List<PerguntaDTO.Response> listarTodas() {
        return perguntaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Listar para quiz (esconde a resposta correta)
    public List<PerguntaDTO.QuizResponse> listarParaQuiz() {
        return perguntaRepository.findAllAleatorio()
                .stream()
                .map(this::toQuizResponse)
                .collect(Collectors.toList());
    }

    // Buscar por ID (modo quiz)
    public PerguntaDTO.QuizResponse buscarPorId(Long id) {
        Pergunta pergunta = buscarOuLancarErro(id);
        return toQuizResponse(pergunta);
    }

    // Buscar por nível
    public List<PerguntaDTO.QuizResponse> listarPorNivel(Nivel nivel) {
        return perguntaRepository.findByNivel(nivel)
                .stream()
                .map(this::toQuizResponse)
                .collect(Collectors.toList());
    }

    // Buscar por tipo
    public List<PerguntaDTO.QuizResponse> listarPorTipo(TipoPergunta tipo) {
        return perguntaRepository.findByTipo(tipo)
                .stream()
                .map(this::toQuizResponse)
                .collect(Collectors.toList());
    }

    // Buscar por categoria
    public List<PerguntaDTO.QuizResponse> listarPorCategoria(String categoria) {
        return perguntaRepository.findByCategoria(categoria)
                .stream()
                .map(this::toQuizResponse)
                .collect(Collectors.toList());
    }

    // Listar todas as categorias
    public List<String> listarCategorias() {
        return perguntaRepository.findAllCategorias();
    }

    // Criar nova pergunta
    public PerguntaDTO.Response criar(PerguntaDTO.Request dto) {
        Pergunta pergunta = new Pergunta();
        pergunta.setEnunciado(dto.getEnunciado());
        pergunta.setTipo(dto.getTipo());
        pergunta.setNivel(dto.getNivel());
        pergunta.setCategoria(dto.getCategoria().toUpperCase());

        List<Opcao> opcoes = dto.getOpcoes().stream().map(op -> {
            Opcao opcao = new Opcao();
            opcao.setTexto(op.getTexto());
            opcao.setCorreta(op.isCorreta());
            opcao.setPergunta(pergunta);
            return opcao;
        }).collect(Collectors.toList());

        pergunta.setOpcoes(opcoes);
        return toResponse(perguntaRepository.save(pergunta));
    }

    // Atualizar pergunta
    public PerguntaDTO.Response atualizar(Long id, PerguntaDTO.Request dto) {
        Pergunta pergunta = buscarOuLancarErro(id);
        pergunta.setEnunciado(dto.getEnunciado());
        pergunta.setTipo(dto.getTipo());
        pergunta.setNivel(dto.getNivel());
        pergunta.setCategoria(dto.getCategoria().toUpperCase());

        pergunta.getOpcoes().clear();
        List<Opcao> novasOpcoes = dto.getOpcoes().stream().map(op -> {
            Opcao opcao = new Opcao();
            opcao.setTexto(op.getTexto());
            opcao.setCorreta(op.isCorreta());
            opcao.setPergunta(pergunta);
            return opcao;
        }).collect(Collectors.toList());
        pergunta.getOpcoes().addAll(novasOpcoes);

        return toResponse(perguntaRepository.save(pergunta));
    }

    // Deletar pergunta
    public void deletar(Long id) {
        buscarOuLancarErro(id);
        perguntaRepository.deleteById(id);
    }

    // Verificar resposta
    public RespostaDTO.Response verificarResposta(Long perguntaId, Long opcaoId) {
        Pergunta pergunta = buscarOuLancarErro(perguntaId);

        Opcao opcaoEscolhida = pergunta.getOpcoes().stream()
                .filter(op -> op.getId().equals(opcaoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Opção não encontrada para esta pergunta"));

        Opcao opcaoCorreta = pergunta.getOpcoes().stream()
                .filter(Opcao::isCorreta)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma opção correta cadastrada"));

        RespostaDTO.Response resposta = new RespostaDTO.Response();
        resposta.setCorreta(opcaoEscolhida.isCorreta());
        resposta.setMensagem(opcaoEscolhida.isCorreta() ? "✅ Correto! Parabéns!" : "❌ Incorreto. Tente novamente!");
        resposta.setOpcaoCorretaId(opcaoCorreta.getId());
        resposta.setTextoOpcaoCorreta(opcaoCorreta.getTexto());

        return resposta;
    }

    // --- Métodos auxiliares ---

    private Pergunta buscarOuLancarErro(Long id) {
        return perguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada com ID: " + id));
    }

    private PerguntaDTO.Response toResponse(Pergunta p) {
        PerguntaDTO.Response dto = new PerguntaDTO.Response();
        dto.setId(p.getId());
        dto.setEnunciado(p.getEnunciado());
        dto.setTipo(p.getTipo());
        dto.setNivel(p.getNivel());
        dto.setCategoria(p.getCategoria());
        dto.setOpcoes(p.getOpcoes().stream().map(op -> {
            OpcaoDTO.Response opDto = new OpcaoDTO.Response();
            opDto.setId(op.getId());
            opDto.setTexto(op.getTexto());
            opDto.setCorreta(op.isCorreta());
            return opDto;
        }).collect(Collectors.toList()));
        return dto;
    }

    private PerguntaDTO.QuizResponse toQuizResponse(Pergunta p) {
        PerguntaDTO.QuizResponse dto = new PerguntaDTO.QuizResponse();
        dto.setId(p.getId());
        dto.setEnunciado(p.getEnunciado());
        dto.setTipo(p.getTipo());
        dto.setNivel(p.getNivel());
        dto.setCategoria(p.getCategoria());
        dto.setOpcoes(p.getOpcoes().stream().map(op -> {
            OpcaoDTO.QuizOpcao opDto = new OpcaoDTO.QuizOpcao();
            opDto.setId(op.getId());
            opDto.setTexto(op.getTexto());
            return opDto;
        }).collect(Collectors.toList()));
        return dto;
    }
}
