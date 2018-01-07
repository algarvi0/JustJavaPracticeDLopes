/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
package com.davidlopes.justjavapracticedlopes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int pricePerCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayquantity(quantity);
        calculatePrice();
        //displayMessage(quantity*price);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        if(quantity < 0) {
            quantity = 0;
            Toast.makeText(getApplicationContext(),getString(R.string.less_then_zero), Toast.LENGTH_SHORT).show();
        }
        displayquantity(quantity);
        //displayMessage(quantity*price);
        calculatePrice();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //Calculates price and store in price variable
        int price = calculatePrice();
        //Log.i("MainActivity - ", String.valueOf(price));

        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();
        //Log.v("MainActivity - ", "Name ? - " + name);

        // Figure out if the user wants Whipped Cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
       //Log.v("MainActivity - ", "hasWhippedCream is checked ? - " + hasWhippedCream);


        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        //Log.v("MainActivity - ", "hasChocolate is checked ? - " + hasChocolate);
        String priceMessage = createOrderSumary(name, price, hasWhippedCream,hasChocolate);
        displayMessage(priceMessage);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);

    }
    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        return quantity * pricePerCup;
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
       // orderSummaryTextView.setBackgroundColor(Color.BLUE);

    }

    /* Creates order of the order
     *
     * @param name to show the user name
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */

    public String createOrderSumary(String name, int price, boolean addwhippedCream, boolean addChocolate) {
        String priceMessage = "Name: "+ name;
        priceMessage += "\nAdd Whipped Cream ? " + addwhippedCream;
        priceMessage += "\nAdd Chocolate ? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: € " + quantity * pricePerCup;
        priceMessage += "\nThank You !";
        displayMessage(priceMessage);
       // Toast.makeText(getApplicationContext(), priceMessage , Toast.LENGTH_SHORT).show();
        return priceMessage;
    }
}