package al.deandmorina.sportscenter.enums;

public enum HallType {
    FOOTBALL("FOOTBALL"),
    VOLLEYBALL("VOLLEYBALL"),
    TENNIS("TENNIS");

    private String name;

    private HallType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HallType getType(String name) {
        for (HallType hallType : HallType.values()) {
            if (hallType.getName().equalsIgnoreCase(name)) {
                return hallType;
            }
        }
        throw new IllegalArgumentException("No HallType with name " + name);
    }
}
