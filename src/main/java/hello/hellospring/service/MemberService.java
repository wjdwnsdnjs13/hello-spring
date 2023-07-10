package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

//    회원 가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
        // optional로 하면 이쁘지 않아서 밑의 코드로 주로 쓴다고 함.
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
//        어차피 findByName 결과는 optional 멤버니까 .ifPresent 가능함.
//        또한 이런 경우 메소드로 따로 뽑는 게 낫다고 하므로
//        Crtl + Alt + Shift + T 눌러서 refactor 메뉴에서 Extract Method 해서 메소드로 빼줌.
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
