package ru.kpfu.itis.game.server;

public abstract class AbstractServerEventListener implements ServerEventListener{

  protected boolean init;
  protected Server server;

  @Override
  public void init(Server server) {
    this.server = server;
    this.init = true;
  }}
