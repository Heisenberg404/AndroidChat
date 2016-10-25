package com.example.andresmontoya.androidchat.lib;

/**
 el patrón Event Bus básicamente permite a objetos suscribirse a
 ciertos eventos del Bus, de modo que cuando un evento es publicado
 en el Bus se propaga a cualquier suscriptor interesado.
 */

public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
