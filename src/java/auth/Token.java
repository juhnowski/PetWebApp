/*
 * Тестовое задание для Вадима
 */
package auth;
import java.util.UUID;

/**
 * Токен - уникальное значение, характеризающее авторизованную сессию
 * @author Илья Юхновский
 */
public class Token {
    private String token;
    private String login;
    private boolean locked;
    private int counter;
    private long unlockTime;
    
    public Token(String login){
        this.login = login;
        this.token = "";
        this.locked = false;
        
        this.counter = 0;
    }
    
    public String setToken(){
        this.token = UUID.randomUUID().toString();
        return this.token;
    }
    
    public String getToken(){
        return this.token;
    }
    
    public String getLogin(){
        return this.login;
    }
    
    private void lock(){
        this.locked = true;
        this.unlockTime = System.currentTimeMillis() + 1*60*60*1000;
    }
    
    private void unlock(){
        this.locked = false;
    }
    
    /**
     * Проверка токена. Токен может быть залочен, в соответствии с тестовым заданием
     * @param token
     * @return 
     */
    public boolean checkToken(String token){
        if (this.token.equals(token)){
            if (this.locked){
                if (System.currentTimeMillis() > unlockTime){
                    unlock();
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            if(++this.counter>10) {
                lock();
            }
            
            return false;
        }
    }
    
    public boolean isLocked(){
        return this.locked;
    }
    
}
