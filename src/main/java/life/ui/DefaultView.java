package life.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final String VIEW_NAME = ""; //default
    private static final long serialVersionUID = -3903205444585313680L;

    @PostConstruct
    void init() {
        addComponent(new Label("Welcome !!! "));
        addComponent(new Label("JDBC_DATABASE_URL : " +System.getenv("JDBC_DATABASE_URL")));
        addComponent(new Label("DATABASE_URL : " +System.getenv("DATABASE_URL")));
        addComponent(new Label("SPRING_DATABASE_URL : " +System.getenv("SPRING_DATABASE_URL")));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
