package com.example.library.dto.member;

import java.time.LocalDate;

/**
 * Outbound DTO representing a Member, including server-assigned fields
 * (`id`, `membershipDate`) that the request DTO deliberately omits.
 */
public class MemberResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate membershipDate;

    public MemberResponseDTO() {
    }

    public MemberResponseDTO(Long id, String name, String email, String phone, LocalDate membershipDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipDate = membershipDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }
}