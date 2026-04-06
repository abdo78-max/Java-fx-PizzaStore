/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author Compu City
 */
public enum Sizes {
    SMALL("Small"), MEDIUM("Medium"), LARGE("Large");
    String size;

    Sizes(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


}
