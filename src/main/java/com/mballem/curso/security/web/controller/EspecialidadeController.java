package com.mballem.curso.security.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping({"","/"})
	public String especialidade(Especialidade especialidade) {
		return "especialidade/especialidade";
	}
	
	@PostMapping("/salvar")
	public String salvarEspecialidade(Especialidade especialidade, RedirectAttributes attributes) {
		especialidadeService.salvar(especialidade);
		attributes.addFlashAttribute(OperacaoEnum.SUCESSO.getValor(),"Operação realizada com sucesso!");
		return "redirect:/especialidades";
	}

}
