import { User } from "src/app/models/user.model";


describe('UserModel', () => {
  fit('frontend_User_model_should_create_an_instance', () => {
    const email = 'testuser@example.com';
    const password = generateSecurePassword(); // Dynamically generate the password
    const username = 'testuser';
    const mobileNumber = '1234567890';
    const userRole = 'admin';

    const user: User = {
      email: email,
      password: password,
      username: username,
      mobileNumber: mobileNumber,
      userRole: userRole
    };

    expect(user).toBeTruthy();
    expect(user.email).toBeDefined();
    expect(user.password).toBeDefined();
    expect(user.username).toBeDefined();
    expect(user.mobileNumber).toBeDefined();
    expect(user.userRole).toBeDefined();
  });

  // Utility function to generate a secure password dynamically
  function generateSecurePassword(): string {
    return 'securePassword' + Math.random().toString(36).substring(2, 10); // Example dynamic password
  }
});