public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Invalid Password");
    }

    public InvalidPasswordException(String e) {
        super(e);
    }
}
