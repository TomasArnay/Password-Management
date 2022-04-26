package clases;

public class PasswordGenerator {
    public String generate(int length){
        String chars = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGH" +
                "IJKLMNOPQRSTUVWXYZ!@#$%^&*()-_=+][}{,./?><`~";

        for (int i = 0; i < length; i++) {
            try{
                password += (chars.charAt((int)(Math.random() * chars.length()+1)));
            }catch (StringIndexOutOfBoundsException s){
                System.out.println("Se excedio");
            }
        }

        return password;
    }

    String password = "";
}
