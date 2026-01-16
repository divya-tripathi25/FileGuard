package model;

public class Data {
    private int id;
    private String fileName;
    private String path;
    private String email;

    public Data( String path, String fileName, int id) {
        this.path = path;
        this.fileName = fileName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Data(String path, int id, String fileName, String email) {
        this.path = path;
        this.id = id;
        this.fileName = fileName;
        this.email = email;
    }
}
