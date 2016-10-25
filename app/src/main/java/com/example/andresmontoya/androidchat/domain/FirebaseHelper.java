package com.example.andresmontoya.androidchat.domain;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andres.montoya on 24/10/2016.
 */

public class FirebaseHelper {
    private Firebase dataReference;
    private final static String SEPARATOR = "_____";
    private final static String CHATS_PATH = "chats";
    private final static String USERS_PATH = "users";
    private final static String CONTACT_PATH = "contacts";
    private final static String FIRABASE_URL = "https://wonderapp.firebaseio.com";

    private static class SingletonHolder{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper(){
        this.dataReference = new Firebase(FIRABASE_URL);
    }

    public Firebase getDataReference(){
        return dataReference;
    }

    //Obtener el correo del usuario autenticado en el momento.
    public String getAuthUserEmail(){
        AuthData authData = dataReference.getAuth();
        String email = null;
        if (authData != null){
            Map<String, Object> providerData = authData.getProviderData();
            email = providerData.get("email").toString();
        }
        return email;
    }
    //obtener la referencia con el email
    public Firebase getUserReference(String email){
        Firebase userReference = null;
        if(email != null){
            String emailKey = email.replace(".","_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);

        }
        return  userReference;
    }
    //obtener la referencia de mi usuario.
    public Firebase getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    //obtener referencia de contactos
    public Firebase getContactsReference(String email){
        return getUserReference(email).child(CONTACT_PATH);
    }
    //OBTENER MI REFERENCIA DE CONTACTOS
    public Firebase getMyContactsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    //obtener un contacto de mi lista
    public Firebase getOneContactReference(String mainEmail, String childEmail){
        String childKey = childEmail.replace(".","_");
        return getUserReference(mainEmail).child(CONTACT_PATH).child(childKey);
    }

    //obtener referencia de un chat atravez de enviar sender y receiver
    public Firebase getChatsReference(String receiver){
        String keySender = getAuthUserEmail().replace(".","_");
        String keyReceiver = receiver.replace(".","_");
        String keyChat = keySender + SEPARATOR + keyReceiver;
        if (keySender.compareTo(keyReceiver) > 0){
            keyChat = keyReceiver + SEPARATOR + keySender;
        }
        return  dataReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    //CAMBIAR ESTADO A USUARIOS
    public  void changeUserConnectionStatus(boolean online){
        if (getMyUserReference() != null){
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);
            notifyContactsOfConnectionChange(online);
        }
    }

    public void notifyContactsOfConnectionChange(boolean online) {
        notifyContactsOfConnectionChange(online, false);
    }


    public void notifyContactsOfConnectionChange(final boolean online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {

            //notificar a cada uno de mis contactos mi cambio de estado
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    String email = child.getKey();
                    Firebase reference = getOneContactReference(email, myEmail);
                    reference.setValue(online);
                }
                if (signoff){
                    dataReference.unauth();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void signOff(){
        notifyContactsOfConnectionChange(false, true);
    }

}
