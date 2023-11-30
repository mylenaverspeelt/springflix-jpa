package com.projeto.springflixjpa.principal;

public enum GenerosEnum {
ACAO("Action"),
ROMANCE("Romance"),
COMEDIA("Comedy"),
DRAMA("Drama"),
CRIME("Crime");
	
	private String categoriaOmdb;
	
	GenerosEnum(String categoriaOmdb){
		this.categoriaOmdb = categoriaOmdb;
	}
	
	public static GenerosEnum fromString(String text) {
	    for (GenerosEnum categoria : GenerosEnum.values()) {
	        if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
	            return categoria;
	        }
	    }
	    throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
	}

}
