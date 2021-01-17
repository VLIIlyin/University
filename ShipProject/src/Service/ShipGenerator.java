package Service;

import Model.Ship;
import ShipTypes.Size;
import ShipTypes.Type;

import java.util.Random;

public class ShipGenerator implements Runnable {
    private Tunnel tunnel;
    private int shipCount;

    public ShipGenerator(Tunnel tunnel, int shipCount) {
        this.tunnel = tunnel;
        this.shipCount = shipCount;
    }

    //Метод, который непосредственно будет исполняться потоком
    @Override
    public void run() {
        int count = 0;
        while (count < shipCount) {
            Thread.currentThread().setName(" Generator ship");
            count++;
            //Добавляем в туннель корабль случайного типа и размера
            tunnel.add(new Ship(getRandomSize(), getRandomType()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    //Генерируем рандомный тип корабля
    private Type getRandomType() {
        Random random = new Random();
        return Type.values()[random.nextInt(Type.values().length)];
    }

    //Генерируем рандомный размер корабля
    private Size getRandomSize() {
        Random random = new Random();
        return Size.values()[random.nextInt(Size.values().length)];
    }
}
