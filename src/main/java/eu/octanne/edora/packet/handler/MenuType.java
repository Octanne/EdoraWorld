package eu.octanne.edora.packet.handler;

public enum MenuType {
    PERSONAL_MENU("personalMenu"), NATION_SELECTOR("nationSelector");

    private String name;

    MenuType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
