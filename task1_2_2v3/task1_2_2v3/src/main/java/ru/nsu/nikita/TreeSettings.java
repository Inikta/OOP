package ru.nsu.nikita;

public class TreeSettings {
    public enum traverseMode {bfs, dfs}
    public enum removeSetting {attachSubBranches, deleteSubBranches}
    public enum insertSetting {no, insertBeforeSingle, insertBeforeBunch}

    private traverseMode traverseParameter;
    private removeSetting removeParameter;
    public insertSetting insertParameter;

    public TreeSettings(traverseMode traverseParameter,
                        removeSetting removeParameter,
                        insertSetting insertParameter) {
        this.traverseParameter = traverseParameter;
        this.removeParameter = removeParameter;
        this.insertParameter = insertParameter;
    }

    public void setTraverseParameter(traverseMode traverseParameter) {
        this.traverseParameter = traverseParameter;
    }

    public traverseMode getTraverseParameter() {
        return traverseParameter;
    }

    public void setRemoveParameter(removeSetting removeParameter) {
        this.removeParameter = removeParameter;
    }

    public removeSetting getRemoveParameter() {
        return removeParameter;
    }

    public void setInsertParameter(insertSetting insertParameter) {
        this.insertParameter = insertParameter;
    }

    public insertSetting isInsertSetting() {
        return insertParameter;
    }
}
