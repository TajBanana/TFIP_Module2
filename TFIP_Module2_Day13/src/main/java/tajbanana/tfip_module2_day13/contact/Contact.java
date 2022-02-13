package tajbanana.tfip_module2_day13.contact;

import java.util.Random;

public class Contact {
    private String ID;
    private String name;
    private String email;
    private String phoneNumber;

    public Contact() {
        this.ID = generateID();
    }

    public Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.ID = getID();
    }

    private synchronized String generateID() {
        Random random =  new Random();
        String stringID =  "";

        for (int i = 0; i < 8; i++) {
            String randHexDigit = Integer.toHexString(random.nextInt());
            stringID += randHexDigit;
        }
        stringID = stringID.substring(0,8);
        System.out.println(stringID);
        return stringID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
