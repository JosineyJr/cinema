package com.cinema.main.views.helpers;
import java.text.NumberFormat;
import java.util.Locale;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextField;

/**
 * Simple Currency Field for JavaFX
 * @version 1.0
 */
public class CurrencyField extends TextField {

    private NumberFormat format;
    private SimpleDoubleProperty amount;

    // No-argument constructor for FXML
    public CurrencyField() {
        this(Locale.getDefault());
    }

    public CurrencyField(Locale locale) {
        this(locale, 0.00);
    }

    public CurrencyField(Locale locale, Double initialAmount) {
        setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        amount = new SimpleDoubleProperty(this, "amount", initialAmount);
        format = NumberFormat.getCurrencyInstance(locale);
        setText(format.format(initialAmount));

        // Remove selection when textfield gets focus
        focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Platform.runLater(() -> {
                int length = getText().length();
                selectRange(length, length);
                positionCaret(length);
            });
        });

        // Listen the text's changes
        textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                formatText(newValue);
            }
        });
    }

    public Double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return this.amount;
    }

    public void setAmount(Double newAmount) {
        if (newAmount >= 0.0) {
            amount.set(newAmount);
            formatText(format.format(newAmount));
        }
    }

    public void setCurrencyFormat(Locale locale) {
        format = NumberFormat.getCurrencyInstance(locale);
        formatText(format.format(getAmount()));
    }

    private void formatText(String text) {
        if (text != null && !text.isEmpty()) {
            String plainText = text.replaceAll("[^0-9]", "");

            while (plainText.length() < 3) {
                plainText = "0" + plainText;
            }

            StringBuilder builder = new StringBuilder(plainText);
            builder.insert(plainText.length() - 2, ".");

            Double newValue = Double.parseDouble(builder.toString());
            amount.set(newValue);
            setText(format.format(newValue));
        }
    }

    @Override
    public void deleteText(int start, int end) {
        StringBuilder builder = new StringBuilder(getText());
        builder.delete(start, end);
        formatText(builder.toString());
        selectRange(start, start);
    }
}
