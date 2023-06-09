package main;

import java.lang.reflect.Member;

public class LoginService {
    private MemberDAO memberDao;

    public LoginService() {
        memberDao = new MemberDAO();
    }

    public void login(String id, String pw) {
        if (id == null || id.isEmpty() || pw == null || pw.isEmpty()) {
            return;
        }

        // 아이디는 2 ~ 20
        if (id.length() <= 2 || id.length() > 20)
            return;
        MemberDTO member = memberDao.login(id);
        if (member != null && member.getPw().equals(pw)) {
            // 로그인 성공
            Login.setId(member.getId());
            Login.setName(member.getName()); // 이름 설정
            Login.setEmail(member.getEmail()); // 이메일 설정
            Login.setGold(member.getGold());
            CommonService.msg("로그인 성공");
        } else {
            // 아이디 또는 비밀번호가 틀렸습니다.
            CommonService.msg("아이디 또는 비밀번호가 틀렸습니다.");
        }
    }
}