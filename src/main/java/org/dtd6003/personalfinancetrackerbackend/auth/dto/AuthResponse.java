package org.dtd6003.personalfinancetrackerbackend.auth.dto;

public class AuthResponse {
    private String token;
    private String email;
    private Long userId;

    // getters + setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
