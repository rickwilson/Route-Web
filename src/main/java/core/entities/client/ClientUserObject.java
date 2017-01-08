package core.entities.client;

import java.util.HashMap;

public class ClientUserObject {
    private long id;
    private String first;
    private String last;
    private String email;
    private String password;
    private String password2;
    private boolean self;
    private String role;
    private HashMap<Integer,String> rolesMap;

    public ClientUserObject() {
        rolesMap = new HashMap<>();
        rolesMap.put(0,"Account User");
        rolesMap.put(1,"Account Administrator");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public HashMap<Integer, String> getRolesMap() {
        return rolesMap;
    }

    public void setRolesMap(HashMap<Integer, String> rolesMap) {
        this.rolesMap = rolesMap;
    }
}
