package ru.kpfu.itis.game.listener;

import javafx.util.Pair;
import ru.kpfu.itis.game.client.GameClient;
import ru.kpfu.itis.game.exception.ClientEventListenerException;
import ru.kpfu.itis.protocol.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractClientEventListener implements ClientEventListener{

    protected Queue<Pair<Message, GameClient>> queue;
    protected List<Integer> types;
    protected boolean init;

    @Override
    public void init() {
        this.init = true;
        this.queue = new LinkedList<>();
        this.types = new ArrayList<>();
        //add types
    }

    @Override
    public List<Integer> getTypes() {
        return types;
    }

    @Override
    public void run() {
        while (true){
            Thread.yield();
            if (!queue.isEmpty()){
                try{
                    //Этот метод извлекает значение первого элемента очереди, удаляя его из очереди.
                    // При каждом вызове он удаляет первый элемент списка, и если список уже пуст, он возвращает null,
                    // но не создает никаких исключений .
                    Pair<Message, GameClient> pair = queue.poll();
                    Message message = pair.getKey();
                    handle(message, GameClient.getID());
                } catch (ClientEventListenerException e){
                    throw new IllegalStateException(e);
                }
            }
        }
    }
}
