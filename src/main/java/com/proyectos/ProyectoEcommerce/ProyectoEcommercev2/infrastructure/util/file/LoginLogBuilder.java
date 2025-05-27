package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.file;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.util.user.ClientIPFinder;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class LoginLogBuilder implements Serializable {

    private static LoginLogBuilder instance = null;

    private LoginLogBuilder() {
    }

    public static LoginLogBuilder getInstance(){
        if(instance == null) {
            synchronized (LoginLogBuilder.class){
                if(instance == null) {
                    instance = new LoginLogBuilder();
                }
            }
        }
        return instance;
    }

    public void imprimirLog(User user) throws IOException {

        StringBuilder sb = new StringBuilder();

        String mensajeUser = sb.append("[Firstname: ")
                .append(user.getFirstname())
                .append(", Lastname: ")
                .append(user.getLastname())
                .append(", Email: ")
                .append(user.getEmail())
                .append(", Cellphone: ")
                .append(user.getCellphone())
                .append("]")
                .toString();

        String log = "LOGIN - User: " + mensajeUser + ", IP: " + ClientIPFinder.getInstance().getClientIp() + ", Timestamp: " + LocalDateTime.now();
        FileUtil.getInstance().write(log, "src/main/resources/logsUsuarios.txt");
    }

    protected Object readResolver(){
        return instance;
    }
}
