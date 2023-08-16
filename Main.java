import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        List<ItemFabric> fabricList = new ArrayList<>();

        fabricList.add(new GoldGenerator());
        fabricList.add(new GemGenerator());

        for (int i = 0; i < 20; i++) {
            Random rnd = ThreadLocalRandom.current();
            int index = Math.abs(rnd.nextInt() % 2) == 0 ? 0 : 1;
            ItemFabric fabric = fabricList.get(index);
            IGameItem gameItem = fabric.createItem();
            gameItem.open();
        }
    }
}

abstract class ItemFabric {
    abstract IGameItem createItem();

    void openReward() {
        IGameItem gameItem = createItem();
        gameItem.open();
    }
}

interface IGameItem {
    void open();
}

class Gold implements IGameItem {
    @Override
    public void open() {
        System.out.println("You received gold!");
    }
}

class Gem implements IGameItem {
    @Override
    public void open() {
        System.out.println("You received a gem!");
    }
}

class GoldGenerator extends ItemFabric {
    @Override
    IGameItem createItem() {
        return new Gold();
    }
}

class GemGenerator extends ItemFabric {
    @Override
    IGameItem createItem() {
        return new Gem();
    }
}
