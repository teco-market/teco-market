import React from 'react';
import { loginByGoogle } from '../api/auth';

const Auth = ({ match, location, history }) => {
  const queryParams = new URLSearchParams(location.search);
  const code = queryParams.get('code')

  switch (match.params.platformType) {
    case "google":
      loginByGoogle(code).then(response => {
        localStorage.setItem('access_token', response.data.token)
        //서버요청
        // 빈페이지
        // ->
        history.replace('/')
      }).catch(() => {
        history.replace('/login')
      })
      break;
  }

  return (
    <div>인증 시도중...</div>
  );
}

export default Auth;