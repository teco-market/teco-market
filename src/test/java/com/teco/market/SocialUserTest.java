package com.teco.market;

import org.junit.jupiter.api.Test;

public class SocialUserTest {
    @Test
    void name() {
        //when -> /oauth2/github
        //then -> 서버에서 토큰을 발급받고, redirect로 클라이언트에게 전달
        //given -> 클라이언트는 전달 받은 토큰을 서버에게 제공 (비동기)
        //when -> 서버는 이 토큰을 포장하여 디비에 저장하고
        //then -> 클라이언트에게 제공
    }
}
