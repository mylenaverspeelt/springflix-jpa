package com.projeto.springflixjpa.principal;

public enum GenerosEnum {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime");

    private String categoriaPortugues;
    private String categoriaOmdb;

    GenerosEnum(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static GenerosEnum fromPortugues(String text) {
        for (GenerosEnum categoria : GenerosEnum.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static GenerosEnum fromString(String text) {
        for (GenerosEnum categoria : GenerosEnum.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text.trim())) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
