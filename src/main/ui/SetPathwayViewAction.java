package ui;

import java.awt.event.ActionEvent;

import javax.swing.*;

public class SetPathwayViewAction extends AbstractAction {
    private ProductsPane productsPane;

    public SetPathwayViewAction(String text, ProductsPane productsPane) {
        super(text);
        this.productsPane = productsPane;
    }

    public void actionPerformed(ActionEvent ae) {
        String name = ae.getActionCommand();
        //test
        System.out.println(name);

        productsPane.setCurrentGroup(name);
    }
}
