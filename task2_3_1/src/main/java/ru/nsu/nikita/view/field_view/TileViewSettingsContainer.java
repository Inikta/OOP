package ru.nsu.nikita.view.field_view;

public class TileViewSettingsContainer {
    private double shiftX;
    private double shiftY;
    private double tileWidth;
    private double tileHeight;
    private double padding;

    /**
     * View settings for tile
     * @param shiftX horizontal shift
     * @param shiftY vertical shift
     * @param tileWidth width
     * @param tileHeight height
     * @param padding distance between tiles
     */
    public TileViewSettingsContainer(double shiftX, double shiftY, double tileWidth, double tileHeight, double padding) {
        this.shiftX = shiftX;
        this.shiftY = shiftY;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.padding = padding;
    }

    public double getShiftX() {
        return shiftX;
    }

    public void setShiftX(double shiftX) {
        this.shiftX = shiftX;
    }

    public double getShiftY() {
        return shiftY;
    }

    public void setShiftY(double shiftY) {
        this.shiftY = shiftY;
    }

    public double getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(double tileWidth) {
        this.tileWidth = tileWidth;
    }

    public double getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(double tileHeight) {
        this.tileHeight = tileHeight;
    }

    public double getPadding() {
        return padding;
    }

    public void setPadding(double padding) {
        this.padding = padding;
    }
}
