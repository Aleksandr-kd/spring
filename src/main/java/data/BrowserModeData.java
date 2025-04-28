package data;

public enum BrowserModeData {
    KIOSK("kiosk"),
    FULLSCREEN("fullscreen"),
    HEADLESS("headless");

    private String name;

    BrowserModeData(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
