package service;

public interface UserService<User>
{
    public User createAccount(String name, String email);
}
