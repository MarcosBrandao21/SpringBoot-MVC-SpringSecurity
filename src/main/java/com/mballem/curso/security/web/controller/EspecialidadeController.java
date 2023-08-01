package com.mballem.curso.security.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballem.curso.security.domain.Especialidade;
import com.mballem.curso.security.enums.OperacaoEnum;
import com.mballem.curso.security.service.EspecialidadeService;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadeController {
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	private static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso!";
	
	private static final String RETURN = "especialidade/especialidade";
	
	private static final String REDIRECT = "redirect:/especialidades";
	
	@GetMapping({"","/"})
	public String especialidade(Especialidade especialidade) {
		return RETURN;
	}
	
	@PostMapping("/salvar")
	public String salvarEspecialidade(Especialidade especialidade, RedirectAttributes attributes) {
		especialidadeService.salvar(especialidade);
		attributes.addFlashAttribute(OperacaoEnum.SUCESSO.getValor(), MENSAGEM_SUCESSO);
		return REDIRECT;
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getEspecialidades (HttpServletRequest request) {
		return ResponseEntity.ok(especialidadeService.buscarEspecialidade(request));
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("especialidade", especialidadeService.obterEspecialidadePorId(id));
		return RETURN;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
		especialidadeService.removerEspecialidade(id);
		attributes.addFlashAttribute(OperacaoEnum.SUCESSO.getValor(), MENSAGEM_SUCESSO);
		return REDIRECT;
	}
}
