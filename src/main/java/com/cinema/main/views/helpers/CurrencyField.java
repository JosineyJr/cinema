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
 * The CurrencyField class is a custom TextField that allows input and display of currency values.
 * It formats the input and display of currency values based on the specified locale.
 * The class extends the TextField class.
 */
public class CurrencyField extends TextField {

    private NumberFormat format;
    private SimpleDoubleProperty amount;

    /**
     * A custom field for handling currency values.
     */
    public CurrencyField() {
        this(Locale.getDefault());
    }

    /**
     * A custom field for handling currency values.
     * This field is used to input and display currency values in a specific locale.
     */
    public CurrencyField(Locale locale) {
        this(locale, 0.00);
    }

    /**
     * A custom JavaFX TextField that is used for displaying and editing currency values.
     * The CurrencyField class provides functionality to format the displayed value as a currency,
     * and allows the user to input and edit currency values.
     *
     * This class extends the JavaFX TextField class and provides additional features specific to currency values.
     * It automatically formats the displayed value according to the specified locale,
     * and allows the user to input and edit currency values in a user-friendly manner.
     *
     * The CurrencyField class also provides event listeners to handle changes in the text input,
     * and ensures that the displayed value is always formatted correctly.
     *
     * Usage:
     * CurrencyField currencyField = new CurrencyField(locale, initialAmount);
     *
     * @param locale The locale used to format the currency value.
     * @param initialAmount The initial currency amount to be displayed in the field.
     */
    public CurrencyField(Locale locale, Double initialAmount) {
        setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        amount = new SimpleDoubleProperty(this, "amount", initialAmount);
        format = NumberFormat.getCurrencyInstance(locale);
        setText(format.format(initialAmount));

        focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Platform.runLater(() -> {
                int length = getText().length();
                selectRange(length, length);
                positionCaret(length);
            });
        });

        textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                formatText(newValue);
            }
        });
    }

    /**
     * Returns the amount value of the CurrencyField.
     *
     * @return the amount value of the CurrencyField
     */
    public Double getAmount() {
        return amount.get();
    }

    /**
     * Returns the SimpleDoubleProperty representing the amount.
     *
     * @return the SimpleDoubleProperty representing the amount
     */
    public SimpleDoubleProperty amountProperty() {
        return this.amount;
    }

    /**
     * Sets the amount for the CurrencyField.
     * 
     * @param newAmount the new amount to be set
     */
    public void setAmount(Double newAmount) {
        if (newAmount >= 0.0) {
            amount.set(newAmount);
            formatText(format.format(newAmount));
        }
    }

    /**
     * Sets the currency format for the field.
     * 
     * @param locale the locale used to determine the currency format
     */
    public void setCurrencyFormat(Locale locale) {
        format = NumberFormat.getCurrencyInstance(locale);
        formatText(format.format(getAmount()));
    }

    /**
     * Formats the given text as a currency value and updates the amount and text accordingly.
     * 
     * @param text the text to be formatted
     */
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

    /**
     * Deletes the text within the specified range and updates the formatted text.
     *
     * @param start the starting index of the text to be deleted
     * @param end the ending index of the text to be deleted
     */
    @Override
    public void deleteText(int start, int end) {
        StringBuilder builder = new StringBuilder(getText());
        builder.delete(start, end);
        formatText(builder.toString());
        selectRange(start, start);
    }
}
