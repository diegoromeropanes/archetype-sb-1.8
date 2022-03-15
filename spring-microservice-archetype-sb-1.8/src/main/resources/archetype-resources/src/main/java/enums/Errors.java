package ${package}.enums;

public enum Errors {

    DEFAULT("99", "Error Genérico");
	
    private final String code;
    private final String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }
}