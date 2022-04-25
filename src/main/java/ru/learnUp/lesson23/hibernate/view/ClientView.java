package ru.learnUp.lesson23.hibernate.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.learnUp.lesson23.hibernate.dao.entity.Client;

import java.util.Date;

@Data
@Component
public class ClientView {

    private Long id;

    private String fullName;

    private Date birthDate;

    public ClientView mapToView(Client client) {
        ClientView view = new ClientView();
        view.setId(client.getId());
        view.setFullName(client.getFullName());
        view.setBirthDate(client.getBirthDate());
        return view;
    }

    public Client mapFromView(ClientView view) {
        Client client = new Client();
        client.setId(view.getId());
        client.setFullName(view.getFullName());
        client.setBirthDate(view.getBirthDate());
        return client;
    }
}
