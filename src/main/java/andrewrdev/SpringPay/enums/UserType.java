package andrewrdev.SpringPay.enums;

public enum UserType {
    COMMON("common"),
    RETAILER("retailer");

    private final String type;

    private UserType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}