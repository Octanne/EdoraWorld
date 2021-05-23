package eu.octanne.edora.packet;

public enum MenuType {
    PERSONAL_MENU("personalMenu",0), NATION_SELECTOR("nationSelector",1);

    private int index;
    private String name;

    MenuType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

	public int getIndex() {
		return index;
	}
    
}
