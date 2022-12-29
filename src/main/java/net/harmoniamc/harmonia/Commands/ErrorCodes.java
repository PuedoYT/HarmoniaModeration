package net.harmoniamc.harmonia.Commands;

public enum ErrorCodes {

    KICKED_BY_STAFF("KICKED_BY_STAFF", 1, "User was kicked by a staff member"),
    KICKED_BY_SYSTEM("KICKED_BY_SYSTEM", 2, "User was kicked by the console.");
    private String err_name;
    private final int err_code;
    private String err_description;

    ErrorCodes(String s, int i, String s1) {
        this.err_name = s;
        this.err_code = i;
        this.err_description = s1;
    }

    public int getErrorCode() {
        return err_code;
    }

}
