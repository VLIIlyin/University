package Model;

import ShipTypes.Size;
import ShipTypes.Type;

//Корабль
public class Ship {
    //счетчик загруженных товаров
    private int count;
    private Size size;
    private Type type;

    public Ship(Size size, Type type) {
        this.size = size;
        this.type = type;
    }

    public void add(int count) {
        this.count += count;
    }

    //на корабль нельзя погрузить больше, чем обозначено в свойстве Size
    public boolean countCheck() {
        if (count >= size.getValue()) {
            return false;
        }
        return true;
    }

    public int getCount() {
        return count;
    }

    public Type getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }
}