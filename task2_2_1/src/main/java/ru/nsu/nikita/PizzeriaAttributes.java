package ru.nsu.nikita;

import ru.nsu.nikita.employee.BakerAttributes;
import ru.nsu.nikita.employee.SupplierAttributes;

import java.util.List;

public class PizzeriaAttributes {

    private int bakersAmount;
    private int suppliersAmount;
    private int storageLimit;
    private int ordersAmount;

    private List<BakerAttributes> bakerAttributes;
    private List<SupplierAttributes> supplierAttributes;

    /**
     * Data structure for Pizzeria parameters.
     * @param bakersAmount amount of bakers
     * @param suppliersAmount amount of suppliers
     * @param storageLimit maximum orders storage can contain.
     * @param ordersAmount amount of orders in the queue. For automatic pre-generation.
     * @param bakerAttributes bakers parameters for each baker.
     * @param supplierAttributes suppliers parameters for each supplier.
     */

    public PizzeriaAttributes(int bakersAmount,
                              int suppliersAmount,
                              int storageLimit,
                              int ordersAmount,
                              List<BakerAttributes> bakerAttributes,
                              List<SupplierAttributes> supplierAttributes) {
        this.bakersAmount = bakersAmount;
        this.suppliersAmount = suppliersAmount;
        this.storageLimit = storageLimit;
        this.ordersAmount = ordersAmount;

        this.bakerAttributes = bakerAttributes;
        this.supplierAttributes = supplierAttributes;
    }

    public PizzeriaAttributes() {
        this.bakersAmount = 0;
        this.suppliersAmount = 0;
        this.storageLimit = 0;
        this.ordersAmount = 0;

        this.bakerAttributes = null;
        this.supplierAttributes = null;
    }

    public int getBakersAmount() {
        return bakersAmount;
    }

    public void setBakersAmount(int bakersAmount) {
        this.bakersAmount = bakersAmount;
    }

    public int getSuppliersAmount() {
        return suppliersAmount;
    }

    public void setSuppliersAmount(int suppliersAmount) {
        this.suppliersAmount = suppliersAmount;
    }

    public int getStorageLimit() {
        return storageLimit;
    }

    public void setStorageLimit(int storageLimit) {
        this.storageLimit = storageLimit;
    }

    public List<BakerAttributes> getBakerAttributes() {
        return bakerAttributes;
    }

    public void setBakerAttributes(List<BakerAttributes> bakerAttributes) {
        this.bakerAttributes = bakerAttributes;
    }

    public List<SupplierAttributes> getSupplierAttributes() {
        return supplierAttributes;
    }

    public void setSupplierAttributes(List<SupplierAttributes> supplierAttributes) {
        this.supplierAttributes = supplierAttributes;
    }

    public int getOrdersAmount() {
        return ordersAmount;
    }

    public void setOrdersAmount(int ordersAmount) {
        this.ordersAmount = ordersAmount;
    }

    @Override
    public String toString() {
        return "PizzeriaAttributes{\n" +
                " bakersAmount=" + bakersAmount + "" +
                ",\n suppliersAmount=" + suppliersAmount +
                ",\n storageLimit=" + storageLimit +
                ",\n ordersAmount=" + ordersAmount +
                ",\n bakerAttributes=" + bakerAttributes +
                ",\n supplierAttributes=" + supplierAttributes +
                '}';
    }
}
