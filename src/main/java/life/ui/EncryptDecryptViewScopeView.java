package life.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import openu.cryptograthy.CRYPTOSYSTEM_TYPE;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = EncryptDecryptViewScopeView.EN_DE_VIEW_NAME)
public class EncryptDecryptViewScopeView extends VerticalLayout implements View {
    public static final String EN_DE_VIEW_NAME = "EncryptDecryptView";
    private static final long serialVersionUID = 5784972560238064106L;

    String keyLength = Integer.toString(3);
//    String encrypted = CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem().encrypt(input,keyLength);
//    String decrypted = CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem().decrypt(encrypted,keyLength);

    // A new instance will be created for every view instance created
    @Autowired
    private DemoViewScopeBean viewBean;
    // The same instance will be used by all views of the UI
    @Autowired
    private DemoUIScopeBean uiBean;

    @PostConstruct
    void init() {
        TextArea planeText = new TextArea();
        Button encryptButton = new Button("Encrypt");
        TextArea encryptedText = new TextArea();
        Button decryptButton = new Button("Decrypt");
        addComponent(encryptButton);
        addComponent(planeText);
        addComponent(decryptButton);
        addComponent(encryptedText);
        encryptButton.addClickListener(clickEvent -> {
            Notification.show("Encrypting ...");
            encryptedText.setValue(CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem().encrypt(planeText.getValue(), keyLength));
        });
        decryptButton.addClickListener(clickEvent -> {
            Notification.show("Decrypting ...");
            planeText.setValue(CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem().decrypt(encryptedText.getValue(), keyLength));

        });
    }

    @Override
    public void enter(ViewChangeEvent event) {
        System.out.print("");
    }
}
