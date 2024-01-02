package jpabook.jpabook.service;

import jpabook.jpabook.entity.Member;
import jpabook.jpabook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // join
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> memberList = memberRepository.findByName(member.getName());
        if (!memberList.isEmpty()) {
            throw new IllegalStateException("This member exists");
        }
    }

    // findAll
    public List<Member> findMembers (){
        return memberRepository.findAll();
    }


    // findById
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
