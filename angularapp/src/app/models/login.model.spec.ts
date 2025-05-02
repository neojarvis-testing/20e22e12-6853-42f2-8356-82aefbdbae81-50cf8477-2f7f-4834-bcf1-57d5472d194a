import { Login } from './login.model';

describe('LoginModel', () => {

  fit('frontend_Login_model_should_create_an_instance', () => {
    const login: Login = {
      username: 'abc',
      pwd: 'securepassword123'
    };
    expect(login).toBeTruthy();
    expect(login.username).toBeDefined();
    expect(login.pwd).toBeDefined();
  });

});
