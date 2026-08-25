package com.example.library.dto.member;

import com.example.library.model.Member;

import java.time.LocalDate;

public final class MemberMapper {

    private MemberMapper() {
    }

    /** Build a new (unsaved) domain model from client input; membershipDate defaults to today. */
    public static Member toNewModel(MemberRequestDTO dto) {
        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setMembershipDate(LocalDate.now());
        return member;
    }

    public static MemberResponseDTO toResponse(Member member) {
        return new MemberResponseDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getMembershipDate()
        );
    }
}