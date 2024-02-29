    package com.rte.model.service;

    import com.rte.model.entity.User;

    import javax.servlet.http.HttpSession;
    import java.util.List;

    public interface UserService extends IGenericService<User, Integer> {
        boolean registerUser(User user);
        User findByEmail(String email);
        boolean loginUser(String email, String password);
        boolean emailExists(String email);

        void blockUser(Integer userId);

        void unblockUser(Integer userId);
        int countRegisteredAccounts();
        List<User> findAllPage(int page, int size);

        List<User> findByUserNameContaining(String userName);
    }
