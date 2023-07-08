package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

//        주의사항!!! 테스트 케이스의 경우 순서가 보장이 안됨. 따라서 findByName이 에러가 나는거임.
//        이를 해결하기 위해서 각 테스트 케이스 후 깔끔하게 삭제해줘야됨.
//    AfterEach는 각 메소드가 끝날 때마다 실행되는 친구임.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.printf("result = " + (result == member));
        // org.junit.jupiter 내의 Assertions
//        Assertions.assertEquals(member, result);
//        Assertions.assertEquals(member, null); //얘는 당연히 실패뜸.
        System.out.printf("org.junit.jupiter 내의 Assertions.assertEquals(비교군, 비교 대상) 하면 결과 출력은 없으나," +
                "\nrun 한 후 MemoryMemberRepository 밑에 save() 함수가 체크 표시가 뜸");
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //복붙 후 shift + f6 하면 rename 바로 가능함.
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);


    }
}
