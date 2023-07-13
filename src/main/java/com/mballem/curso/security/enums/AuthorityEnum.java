package com.mballem.curso.security.enums;

public enum AuthorityEnum {

	ADMIN("ADMIN"),
	USER("USER"),
	MEDICO("MEDICO");
	
	private String authority;
	
	AuthorityEnum(String authority) {
		this.authority = authority;
	}
	
	public String getAuthority() {
		return authority;
	}
}
