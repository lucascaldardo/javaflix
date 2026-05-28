package javaflix.exeption;

public class UsernameOrPasswordInvalidException extends RuntimeException{
    public UsernameOrPasswordInvalidException(String message){
        super(message);
    }
}
