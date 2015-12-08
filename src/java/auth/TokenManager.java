/*
 * Тестовое задание для Вадима
 */
package auth;

import java.util.HashMap;
import petweb.PetsHelper;
import petweb.Users;


/**
 * Singleton менеджер токенов. Хранит в памяти все текущие сессии пользователей.
 * @author Илья Юхновский
 */
public class TokenManager {

    private static volatile TokenManager instance;
    private TokenManager(){}
    private HashMap<String,Token> map = new HashMap<>();
    private PetsHelper ph = new PetsHelper();
    
    public static TokenManager getInstance() {
        if (instance == null) {
            synchronized (TokenManager.class){
                if (instance == null) {
                    instance = new TokenManager();
                }
            }
        }
        return instance;
    }    
    
    public String registerUser(String login, String password){
        String token = "";
        Users user = ph.getUserByLogin(login);
        if(user!=null) {
            token = "ERROR: user already exists";
        } else{
            ph.addUser(login, password);
            Token tk = new Token(login);
            token = tk.setToken();
            map.put(login, tk);
        }
        return token;
    }
    
    public String loginUser(String login, String password){
        String token = "";
        Token tk;
        Users user = ph.getUserByLogin(login);
        if(user==null) {
            token = "ERROR: Wrong username";
        } else {
            tk = map.get(login);
            
            if (tk == null){
                tk = new Token(login);
                if (user.getPassword().equals(password)){
                    token = tk.setToken();
                    map.put(login, tk);
                } else {
                    token = "ERROR: Wrong password";
                    }
                
            } else{
                if (tk.isLocked()){
                    token = "ERROR: user has been locked";
                } else {
                    token = tk.getToken();
                }
            }
        }
        return token;
    }
    
    public boolean checkUserToken(String login, String token){
        Token tk = map.get(login);
        if (tk == null) {
            return false;
        } else {
            return tk.checkToken(token);
        }
    }

    public void unregisterUser(String login){
        map.remove(login);
    }

}
