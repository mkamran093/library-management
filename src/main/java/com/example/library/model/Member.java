package com.example.library.model;

import java.time.LocalDate;

/**
 * Domain model mapped 1:1 to the `member` table.
 */
public class Member {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate membershipDate;

    public Member() {
    }

    public Member(Long id, String name, String email, String phone, LocalDate membershipDate) {
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