package ru.nsu.nikita.view.field_view;

import ru.nsu.nikita.backlogic.field.Field;

public class FieldViewSettingsContainer extends TileViewSettingsContainer {

    private Field field;

    /**
     * View settings for the field
     * @param field field itself
     * @param shiftX horizontal shift of the whole field
     * @param shiftY vertical shift of the whole field
     * @param tileWidth width of tiles
     * @param tileHeight height of tiles
     * @param padding distance between tiles
     */
    public FieldViewSettingsContainer(Field field, double shiftX, double shiftY, double tileWidth, double tileHeight, double padding) {
        super(shiftX, shiftY, tileWidth, tileHeight, padding);
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
