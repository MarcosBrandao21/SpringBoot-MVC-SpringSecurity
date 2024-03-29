package com.mballem.curso.security.service;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.security.datatables.Datatables;
import com.mballem.curso.security.datatables.DatatablesColunas;
import com.mballem.curso.security.domain.Especialidade;
import com.mballem.curso.security.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository repository;
	
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(Especialidade especialidade) {
		repository.save(especialidade);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarEspecialidade(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.ESPECIALIDADES);
		Page<?> page = datatables.getSearch().isEmpty() 
				? repository.findAll(datatables.getPageable()) 
				: repository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Especialidade obterEspecialidadePorId(Long idEspecialidade) {
		Optional<Especialidade> especialidade = repository.findById(idEspecialidade);
		if(Boolean.FALSE.equals(especialidade.isPresent())) {
			throw new IllegalArgumentException("Especialidade com id: " + idEspecialidade + "não existe!");
		}
		return especialidade.get();
	}

	@Transactional(readOnly = false)
	public void removerEspecialidade(Long idEspecialidade) {
		repository.delete(obterEspecialidadePorId(idEspecialidade));
	}

}
