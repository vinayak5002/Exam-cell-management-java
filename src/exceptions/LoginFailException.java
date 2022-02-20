package exceptions;

public class LoginFailException extends Exception{
    public LoginFailException(String msg){
        super(msg);
    }
}
