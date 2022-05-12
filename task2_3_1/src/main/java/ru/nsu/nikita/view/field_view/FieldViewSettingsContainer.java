package ru.nsu.nikita.view.field_view;

import ru.nsu.nikita.backlogic.field.Field;

public class FieldViewSettingsContainer extends TileViewSettingsContainer {

    private Field field;

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
