/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
package com.davidlopes.justjavapracticedlopes;

import android.content.Intent;
import android.net.Uri;
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
    int quantity = 1;

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
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, getString(R.string.maximum_coffes), Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast


            Toast.makeText(this, getString(R.string.less_then_one), Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
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

        //Calculates price and store in price variable
        int price = calculatePrice(hasWhippedCream, hasChocolate);


        // This line of code prevents user from submitting an empty name field for the order.
        if (nameField.getText().toString().trim().length() < 2) {
            Toast.makeText(this, getString(R.string.name_error), Toast.LENGTH_SHORT).show();
            return;
        }

        String priceMessage = createOrderSumary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);

        // Use an intent to launch an email app.
        // Send the order summary in the email body.

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.order_summary_email_subject) + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);

    }

    /**
     * Calculates the price of the order.
     *
     * @param addChocolate    wants chocolate ?
     * @param addWhippedCream wnats WhippedCream ?
     * @return pricePerCup
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price for one Cup its 5
        int pricePerCup = 5;
        // Price for Whipped Cream Topping its 1
        int priceWhippedCreamTopping = 1;
        // Price for ChocolateTopping its 2
        int priceChocolateTopping = 2;

        // if user checks addWhippedCream add 1 to the pricePerCup
        if (addWhippedCream) {
            pricePerCup = pricePerCup + priceWhippedCreamTopping;
        }
        // if user checks ChocolateTopping add 2 to the pricePerCup
        if (addChocolate) {
            pricePerCup = pricePerCup + priceChocolateTopping;
        }
        //Returns pricePerCup plus orderd quantity
        return pricePerCup * quantity;
    }

    /**
     * This method displays the given price on the screen.
     *
     * @param message
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
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd Whipped Cream ? " + addwhippedCream;
        priceMessage += "\nAdd Chocolate ? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: € " + price;
        priceMessage += "\nThank You !";
        displayMessage(priceMessage);
        // Toast.makeText(getApplicationContext(), priceMessage , Toast.LENGTH_SHORT).show();
        return priceMessage;

    }


    // TO implement:
    // ResetOrder: zero all

}