package Service;

import Model.Ship;
import ShipTypes.Type;

import java.util.ArrayList;
import java.util.List;

public class Tunnel {

    //List хранит в себе корабли, которые проходят через туннель
    private List<Ship> store;
    //Вместительность туннеля по условию 5
    private static final int maxShipsInTunnel = 5;
    private static final int minShipsInTunnel = 0;
    private int shipsCounter = 0;


    public Tunnel() {
        store = new ArrayList<>();
    }

    //Добавляет корабли в список
    public synchronized boolean add(Ship element) {

        try {
            //Можно добавить корабль только если в списке кол-во кораблей < 5
            if (shipsCounter < maxShipsInTunnel) {
                notifyAll();
                store.add(element);
                String info = String.format("%s + The ship arrived in the tunnel: %s %s %s", store.size(),
                        element.getType(), element.getSize(), Thread.currentThread().getName());
                System.out.println(info);
                shipsCounter++;

            } else {
                System.out.println(store.size() + "> There is no place for a ship in the tunnel: "
                        + Thread.currentThread().getName());
                //Когда ship > 5 поток должен остановить свою деятельность и подождать
                wait();
                return false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    //Достает и удаляет корабли из списка по необходимому типу
    public synchronized Ship get(Type shipType) {

        try {
            //Можно достать корабль только если в списке кол-во кораблей > 0
            if (shipsCounter > minShipsInTunnel) {
                //возобновляет работу всех потоков, у которых ранее был вызван метод wait()
                notifyAll();
                for (Ship ship : store) {
                    if (ship.getType() == shipType) {
                        shipsCounter--;
                        System.out.println(store.size() + "- The ship is taken from the tunnel: "
                                + Thread.currentThread().getName());
                        store.remove(ship);
                        return ship;
                    }
                }
            } else {
                System.out.println("0 < There are no ships in the tunnel");
                //Когда ship < 0 поток должен остановить свою деятельность и подождать
                wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}