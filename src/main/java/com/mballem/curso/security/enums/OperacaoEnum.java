package com.mballem.curso.security.enums;

public enum OperacaoEnum {

	SUCESSO("sucesso"),
	FALHA("falha");
	
	private String operacao;
	
	OperacaoEnum(String operacao) {
		this.operacao = operacao;
	}
	
	public String getValor() {
		return operacao;
	}
}
